package controller.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.UserModel;
import util.PasswordEncryptionWithAes;
import util.StringUtils;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADMIN_USER_UPDATE })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class AdminUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserModel user =(UserModel) dbc.getUserInfo(Integer.parseInt(request.getParameter("userID")));
		request.setAttribute("user", user);
		request.getRequestDispatcher("/pages/admin/dashboard_user_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("submit").equals("update")) {
			String userName = request.getParameter(StringUtils.USERNAME);
			String olduser=request.getParameter("olduser");
			String dobString = request.getParameter(StringUtils.BIRTHDAY);
			LocalDate dob = LocalDate.parse(dobString);
			String gender = request.getParameter(StringUtils.GENDER);
			String email = request.getParameter(StringUtils.EMAIL);
			String phoneNumber = request.getParameter(StringUtils.PHONE);
			String address = request.getParameter(StringUtils.ADDRESS);
			String password = request.getParameter(StringUtils.PASSWORD);
			Part imagePart = request.getPart("imagePart");
			
			if (password==null || password.equals("")) {
				password=PasswordEncryptionWithAes.decrypt(request.getParameter("passhash"), olduser);
			}
			System.out.println(password);
			
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
		} else if(request.getParameter("submit").equals("delete")){
			int userID = Integer.parseInt(request.getParameter("userID"));
			int result = dbc.DeleteUser(userID);
			if (result==1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "User Successfully Deleted!");
				request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_USER).forward(request, response);
			} else if (result==0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "A server error occured.");
				doGet(request,response);
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured.");
				doGet(request,response);
			}
		}
	}

}
