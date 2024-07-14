package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.OrderModel;
import util.StringUtils;

/**
 * Servlet implementation class AdminOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADMIN_ORDER })
public class AdminOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<OrderModel> orders = dbc.getOrdersInfo(request.getParameter(getServletName()));
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/pages/admin/dashboard_order.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
