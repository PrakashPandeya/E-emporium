package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADMIN_PRODUCT_UPDATE })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class AdminUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DatabaseController dbc = new DatabaseController();

	public AdminUpdateProduct() {
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
		ProductModel product = dbc.getProductInfo(Integer.parseInt(request.getParameter("productID")));
		request.setAttribute("product", product);
		request.getRequestDispatcher("/pages/admin/dashboard_product_update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("submit").equals("update")) {
			int productID = Integer.parseInt(request.getParameter("productID"));
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			String description = request.getParameter("description");
			String category = request.getParameter("category");
			int stock = Integer.parseInt(request.getParameter("stock"));
			Part imagePart = request.getPart("imagePart");
			
			ProductModel product;
			
			if (imagePart != null && imagePart.getSize() > 0) {
				product = new ProductModel(productID, name, price, description, category, stock, imagePart);
			} else {
				product = new ProductModel(productID, name, price, description, category, stock, imagePart);
				product.setImagePath(request.getParameter("current-image"));
			}
			
			int result = dbc.UpdateProduct(product);
			
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
			int productID = Integer.parseInt(request.getParameter("productID"));
			int result = dbc.DeleteProduct(productID);
			if (result==1) {
				request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
				request.setAttribute(StringUtils.MESSAGE, "Product Successfully Deleted!");
				request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_PRODUCT).forward(request, response);
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
