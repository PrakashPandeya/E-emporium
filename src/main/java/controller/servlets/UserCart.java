package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.CartItem;
import model.CartModel;
import util.StringUtils;

/**
 * Servlet implementation class UserCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_USER_CART })
public class UserCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserCart() {
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
		CartModel cart = dbc.getCartInfo((int) session.getAttribute("userID"));
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("/pages/user/user_cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (request.getParameter("submit").equals("addToCart")) {
			int pid = Integer.parseInt(request.getParameter("productID"));
			int uid = (int) session.getAttribute("userID");
			if (dbc.checkInCart(uid, pid)) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Product Already in Cart!");
				request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				
			} else {
				CartItem item = new CartItem();
				item.setProductID(pid);
				item.setQuantity(1);
				int result = dbc.AddToCart(uid, item);
				if (result == 1) {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
					request.setAttribute(StringUtils.MESSAGE, "Product Successfully Added to Cart!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				} else if (result == 0) {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
					request.setAttribute(StringUtils.MESSAGE, "The product cannot be added to cart!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				} else {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
					request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				}
			}
		} else if (request.getParameter("submit").equals("addToCartDesc")) {
			int pid = Integer.parseInt(request.getParameter("productID"));
			int uid = (int) session.getAttribute("userID");
			int quant = Integer.parseInt(request.getParameter("quantity"));
			if (dbc.checkInCart(uid, pid)) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Product Already in Cart!");
				request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				
			} else {
				CartItem item = new CartItem();
				item.setProductID(pid);
				item.setQuantity(quant);
				int result = dbc.AddToCart(uid, item);
				if (result == 1) {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
					request.setAttribute(StringUtils.MESSAGE, "Product Successfully Added to Cart!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				} else if (result == 0) {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
					request.setAttribute(StringUtils.MESSAGE, "The product cannot be added to cart!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				} else {
					request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
					request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured!");
					request.getRequestDispatcher(request.getParameter("sourcePage")).forward(request, response);
				}
			}
		} else if (request.getParameter("submit").equals("removeFromCart")) {
			int itemID = Integer.parseInt(request.getParameter("itemID"));
			int result = dbc.removeFromCart(itemID);
			if (result == 1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Product Successfully removed from Cart!");
				doGet(request,response);
			} else if (result == 0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "The product cannot be removed or already removed from cart!");
				doGet(request,response);
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured!");
				doGet(request,response);
			}
		} else if (request.getParameter("submit").equals("purchase")) {
			int userID=(int) session.getAttribute("userID");
			int total=Integer.parseInt(request.getParameter("total"));
			int result = dbc.cartToOrder(userID, total);
			if (result == 1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Order Successfully Created!");
				request.getRequestDispatcher(StringUtils.SERVLET_URL_USER_HOME).forward(request, response);
			} else if (result == 0) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
				request.setAttribute(StringUtils.MESSAGE, "The order cannot be created!");
				doGet(request,response);
			} else {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
				request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured!");
				doGet(request,response);
			}
		}
	}

}
