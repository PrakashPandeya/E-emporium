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
 * Servlet implementation class AddProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_ADMIN_PRODUCT_ADD})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AdminAddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController dbc = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductModel product = (ProductModel) request.getAttribute("product");
		request.setAttribute("product", product);
		request.getRequestDispatcher("/pages/admin/dashboard_product_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productID = 0;
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		int stock = Integer.parseInt(request.getParameter("stock"));
		Part imagePart = request.getPart("imagePart");
		
		ProductModel product = new ProductModel(productID, name, price, description, category, stock, imagePart);
		int result = dbc.AddProduct(product);
		if (result==1) {
			
			imagePart.write(StringUtils.IMAGE_PRODUCT_PATH+product.getImagePath());
			
			request.setAttribute(StringUtils.MESSAGE_TITLE, "Notice!");
			request.setAttribute(StringUtils.MESSAGE, "Product Successfully Added!");
			request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN_PRODUCT).forward(request, response);
		} else if (result==0) {
			request.setAttribute(StringUtils.MESSAGE_TITLE, "Warning!");
			request.setAttribute(StringUtils.MESSAGE, "There was a mistake in the input, please fix it.");
			request.setAttribute("product", product);
			doGet(request,response);
		} else {
			request.setAttribute(StringUtils.MESSAGE_TITLE, "Error!");
			request.setAttribute(StringUtils.MESSAGE, "An unexpected error occured.");
			request.setAttribute("product", product);
			doGet(request,response);
		}
	}

}
