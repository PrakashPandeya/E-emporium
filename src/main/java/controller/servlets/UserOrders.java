package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.OrderModel;
import util.StringUtils;

/**
 * Servlet implementation class UserOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_USER_ORDERS})
public class UserOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc=new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArrayList<OrderModel> orders=dbc.getOrdersInfo((Integer)session.getAttribute("userID"));
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/pages/user/user_orders.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
