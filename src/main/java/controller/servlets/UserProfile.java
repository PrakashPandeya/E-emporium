package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.UserModel;
import util.PasswordEncryptionWithAes;
import util.StringUtils;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_USER_PROFILE})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		UserModel user = dbc.getUserInfo((int) session.getAttribute("userID"));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/pages/user/user_profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("submit").equals("Save changes")) {
			String userName = request.getParameter(StringUtils.USERNAME);
			String olduser=request.getParameter("olduser");
			String dobString = request.getParameter(StringUtils.BIRTHDAY);
			LocalDate dob = LocalDate.parse(dobString);
			String gender = request.getParameter(StringUtils.GENDER);
			String email = request.getParameter(StringUtils.EMAIL);
			String phoneNumber = request.getParameter(StringUtils.PHONE);
			String address = request.getParameter(StringUtils.ADDRESS);
			String password = PasswordEncryptionWithAes.decrypt(request.getParameter("passhash"), olduser);
			Part imagePart = request.getPart("imagePart");
			
			UserModel user;
			
			if (imagePart != null && imagePart.getSize() > 0) {
				user = new UserModel(dob, gender, email, phoneNumber, address, userName, password, imagePart);
			} else {
				user = new UserModel(dob, gender, email, phoneNumber, address, userName, password, imagePart);
				user.setImagePath(request.getParameter("current-image"));
			}
			
			user.setRole(request.getParameter(StringUtils.ROLE));
			user.setUserID(Integer.parseInt(request.getParameter("userID")));
			
			
			int result = dbc.UpdateUser(user);
			
			if (result==1) {
				
				imagePart.write(StringUtils.IMAGE_USER_PATH+user.getImagePath());
				
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "User Successfully Updated!");
				doGet(request,response);
			} else if (result==0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "There was a mistake in the input, please fix it.");
				doGet(request,response);
				
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured.");
				doGet(request,response);
			}
		} else if (request.getParameter("submit").equals("pass")) {
			String olduser=request.getParameter("olduser");
			String current=request.getParameter("current-pass");
			String password=request.getParameter("password");
			String repassword=request.getParameter("re-password");
			int uid=Integer.parseInt(request.getParameter("userID"));
			
			if (!(current.equals(PasswordEncryptionWithAes.decrypt(request.getParameter("passhash"), olduser)))) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "Current Passwords do not match!");
				doGet(request,response);
			} else if (password==null || password.equals("") || !(password.equals(repassword))) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "New Passwords do not match!");
				doGet(request,response);
			}
			
			UserModel user = new UserModel();
			user.setUserID(uid);
			user.setUsername(olduser);
			user.setPassword(password);
			
			int result = dbc.UpdatePass(user);
			if (result==1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Password Successfully Updated!");
				doGet(request,response);
			} else if (result==0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "There was a mistake in the input, please fix it.");
				doGet(request,response);
				
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured.");
				doGet(request,response);
			}
		}
	}

}
