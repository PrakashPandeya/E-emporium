package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.ProductModel;
import util.HelpUtil;
import util.StringUtils;

/**
 * Servlet implementation class UserHome
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_USER_HOME })
public class UserHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserHome() {
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
		ArrayList<ProductModel> products = dbc.getProductsInfo("", "", "", "");
		request.setAttribute("products", products);
		if (products.isEmpty()) {
			request.setAttribute("prod", dbc.getDummyProduct());
			request.setAttribute("hero", dbc.getDummyProduct());
		} else {
			request.setAttribute("prod", products.get(HelpUtil.randInt(0, products.size())));
			request.setAttribute("hero", products.get(HelpUtil.randInt(0, products.size())));
		}
		request.getRequestDispatcher("/pages/user/user_home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
