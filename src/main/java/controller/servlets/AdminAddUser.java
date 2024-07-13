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
import util.StringUtils;
import util.ValidationUtil;

/**
 * Servlet implementation class AddUser
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_ADMIN_USER_ADD})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AdminAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc=new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserModel user = (UserModel) request.getAttribute("user");
		request.setAttribute("user", user);
		request.getRequestDispatcher("/pages/admin/dashboard_user_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter(StringUtils.USERNAME);
		String dobString = request.getParameter(StringUtils.BIRTHDAY);
		LocalDate dob = LocalDate.parse(dobString);
		String gender = request.getParameter(StringUtils.GENDER);
		String email = request.getParameter(StringUtils.EMAIL);
		String phoneNumber = request.getParameter(StringUtils.PHONE);
		String address = request.getParameter(StringUtils.ADDRESS);
		String password = request.getParameter(StringUtils.PASSWORD);
		String repassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
		Part imagePart = request.getPart("imagePart");
		UserModel user = new UserModel(dob, gender, email, phoneNumber, address, userName, password, imagePart);
		user.setRole(request.getParameter(StringUtils.ROLE));
		
		if (!ValidationUtil.isAlphanumeric(userName) || !ValidationUtil.isEmail(email)
				|| !ValidationUtil.isNumbersOnly(phoneNumber) || !ValidationUtil.isGenderMatches(gender)) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
			request.setAttribute("user", user);
			doGet(request, response);
		} else if (!(password.equals(repassword))) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_PASSWORD_UNMATCHED);
			request.setAttribute("user", user);
			doGet(request, response);
		} else if (dbc.checkEmailIfExists(email)) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_EMAIL);
			request.setAttribute("user", user);
			doGet(request, response);
		} else if (dbc.checkNumberIfExists(phoneNumber)) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_PHONE);
			request.setAttribute("user", user);
			doGet(request, response);
		} else if (dbc.checkUsernameIfExists(userName)) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_USERNAME);
			request.setAttribute("user", user);
			doGet(request, response);
		} else {
			int result = dbc.registeruser(user);
			if (result==1) {
				
				imagePart.write(StringUtils.IMAGE_USER_PATH+user.getImagePath());
				
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "User Successfully Added!");
				request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_USER).forward(request, response);
			} else if (result==0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "There was a mistake in the input, please fix it.");
				request.setAttribute("user", user);
				doGet(request,response);
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured.");
				request.setAttribute("user", user);
				doGet(request,response);
			}
		}
	}

}
