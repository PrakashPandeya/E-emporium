package controller.servlets;

import java.io.IOException;
import controller.DatabaseController;
import model.LoginModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_LOGIN })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("role")!=null) {

			if (session.getAttribute("role").toString().equals("admin")) {
				request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_HOME).forward(request, response);
			} else if (session.getAttribute("role").toString().equals("user")) {
				request.getRequestDispatcher(StringUtils.SERVLET_URL_USER_HOME).forward(request, response);
			}
		} else {
			request.setAttribute("username", request.getAttribute("username"));
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		// Create a LoginModel object to hold user credentials
		LoginModel loginModel = new LoginModel(userName, password);

		// Call DBController to validate login credentials
		LoginModel loginResult = dbController.getuserLoginInfo(loginModel);

		// Check if the username and password match the credentials from the database
		if (loginResult.getUsername().equals(loginModel.getUsername())
				&& loginResult.getPassword().equals(loginModel.getPassword())
				&& (loginResult.getRole().equals("user") || loginResult.getRole().equals("admin"))) {
			// Login successful, return 1
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userID", loginResult.getUid());
			userSession.setAttribute(StringUtils.USERNAME, userName);
			userSession.setAttribute(StringUtils.ROLE, loginResult.getRole());
			userSession.setMaxInactiveInterval(30 * 60);

			Cookie userCookie = new Cookie("user", userName);
			userCookie.setMaxAge(30 * 60);
			response.addCookie(userCookie);

			if (loginResult.getRole().equals("admin")) {
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADMIN_HOME);
			} else if (loginResult.getRole().equals("user")) {
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_USER_HOME);
			}
		} else if (loginResult.getRole().equals("NF")) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.setAttribute("username", userName);
			doGet(request, response);
		} else if (!(loginResult.getUsername().equals(loginModel.getUsername()))
				|| !(loginResult.getPassword().equals(loginModel.getPassword()))) {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_LOGIN);
			request.setAttribute("username", userName);
			doGet(request, response);
		} else {
			request.setAttribute(StringUtils.MESSAGE, StringUtils.MESSAGE_ERROR_SERVER);
			request.setAttribute("username", userName);
			doGet(request, response);
		}
	}

}
