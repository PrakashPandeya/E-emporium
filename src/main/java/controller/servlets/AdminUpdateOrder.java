package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DatabaseController;
import model.OrderModel;
import util.StringUtils;

/**
 * Servlet implementation class AdminUpdateOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADMIN_ORDER_UPDATE })
public class AdminUpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderModel order = dbc.getOrderInfo(Integer.parseInt(request.getParameter("orderID")));
		request.setAttribute("order", order);
		request.getRequestDispatcher("/pages/admin/dashboard_order_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("submit").equals("update")) {
			int orderID = Integer.parseInt(request.getParameter("orderID"));
			String status = request.getParameter("status");
			
			OrderModel order = new OrderModel();
			order.setOrderID(orderID);
			order.setStatus(status);
			
			int result = dbc.UpdateOrder(order);
			
			if (result==1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Product Successfully Updated!");
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
			int orderID = Integer.parseInt(request.getParameter("orderID"));
			int result = dbc.DeleteOrder(orderID);
			if (result==1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Order Successfully Deleted!");
				request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_ORDER).forward(request, response);
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
