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
import util.StringUtils;

/**
 * Servlet implementation class UserProducts
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_USER_PRODUCTS})
public class UserProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc=new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductModel> products=dbc.getProductsInfo(request.getParameter("search"), request.getParameter("min_price"), request.getParameter("max_price"), request.getParameter("category"));
		request.setAttribute("search", request.getParameter("search"));
		request.setAttribute("min_price", request.getParameter("min_price"));
		request.setAttribute("max_price", request.getParameter("max_price"));
		request.setAttribute("category", request.getParameter("category"));
		request.setAttribute("products", products);
		request.getRequestDispatcher("/pages/user/user_products.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
