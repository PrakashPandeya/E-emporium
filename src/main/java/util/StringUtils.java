package util;

public class StringUtils {

	// DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/emp";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";

	// Queries
	public static final String QUERY_GET_COUNT="SELECT COUNT(*) AS count FROM ";
	
	public static final String QUERY_REGISTER_USER = "INSERT INTO `user` (`email`, `gender`, `address`, `phone`, `dob`, `username`, `passhash`, `role`, `imagePath`) VALUES (?,?,?,?,?,?,?,?,?);";
	public static final String QUERY_CHECK_EMAIL = "SELECT * FROM `user` WHERE email=?;";
	public static final String QUERY_CHECK_PHONE = "SELECT * FROM `user` WHERE phone=?;";
	public static final String QUERY_CHECK_USER = "SELECT * FROM `user` WHERE username=?;";
	public static final String QUERY_GET_USERS = "SELECT * FROM user WHERE username LIKE ?";
	public static final String QUERY_GET_USER = "SELECT * FROM user WHERE userID = ?";
	public static final String QUERY_UPDATE_USER = "UPDATE `user` SET `email`=?,`gender`=?,`address`=?,`phone`=?,`dob`=?,`username`=?,`passhash`=?,`role`=?, `imagePath`=? WHERE `userID`=?";
	public static final String QUERY_UPDATE_PASS = "UPDATE user SET passhash=? WHERE userID=?";
	public static final String QUERY_DELETE_USER = "DELETE FROM user WHERE userID=?";

	public static final String QUERY_GET_PRODUCT = "SELECT * FROM product WHERE productID=?";
	public static final String QUERY_GET_PRODUCTS = "SELECT * FROM `product` WHERE name LIKE ? AND price >= ? AND price <= ? AND category LIKE ?";
	public static final String QUERY_UPDATE_PRODUCT = "UPDATE `product` SET `name`=?,`price`=?,`description`=?,`category`=?,`stock`=?,`imagePath`=? WHERE `productID`=?";
	public static final String QUERY_ADD_PRODUCT = "INSERT INTO `product` (`name`, `price`, `description`, `category`, `stock`, `imagePath`) VALUES (?,?,?,?,?,?)";
	public static final String QUERY_DELETE_PRODUCT = "DELETE FROM product WHERE productID=?";

	public static final String QUERY_GET_ORDERS = "SELECT * FROM `orders` JOIN user ON orders.userID=user.userID WHERE user.username LIKE ?";
	public static final String QUERY_GET_ORDERS_2 = "SELECT * FROM `orders` JOIN user ON orders.userID=user.userID WHERE orders.userID=?";
	public static final String QUERY_GET_ORDER = "SELECT * FROM orders WHERE orderID=?";
	public static final String QUERY_GET_ORDER_DETAILS="SELECT * FROM orderdetails WHERE orderID=?";
	public static final String QUERY_ADD_ORDER="INSERT INTO `orders`(`userID`, `orderDate`, `total`, `status`) VALUES (?,?,?,?)";
	public static final String QUERY_GET_LATEST_ORDER="SELECT MAX(orderID) as latest FROM orders";
	public static final String QUERY_ADD_ORDER_DETAILS="INSERT INTO `orderdetails`(`orderID`, `productID`, `quantity`) VALUES (?,?,?)";
	public static final String QUERY_UPDATE_ORDER = "UPDATE orders SET status=? WHERE orderID=?";
	public static final String QUERY_DELETE_ORDER = "DELETE FROM orders WHERE orderID=?";
	public static final String QUERY_DELETE_ORDER_DETAILS = "DELETE FROM orderdetails WHERE orderID=?";
	
	public static final String QUERY_ADD_CART="INSERT INTO cart (userID) VALUES (?)";
	public static final String QUERY_ADD_TO_CART="INSERT INTO `cart_items`(`cartID`, `productID`, `quantity`) VALUES (?,?,?)";
	public static final String QUERY_GET_CART="SELECT * FROM cart WHERE userID=?";
	public static final String QUERY_GET_CART_ITEMS="SELECT * FROM cart_items WHERE cartID=?";
	public static final String QUERY_GET_CART_PRODS="SELECT * FROM cart_items WHERE productID=? AND cartID=?";
	public static final String QUERY_DELETE_CART = "DELETE FROM cart WHERE userID=?";
	public static final String QUERY_DELETE_CART_ITEM="DELETE FROM cart_items WHERE itemID=?";
	// IMAGE URL
	public static final String IMAGE_ROOT = "D:\\eclipse\\preference\\emporium repo\\E Emporium\\src\\main\\webapp\\resource\\";
	public static final String IMAGE_PRODUCT_PATH = IMAGE_ROOT + "products\\";
	public static final String IMAGE_USER_PATH = IMAGE_ROOT + "user\\";

	// Parameter names
	public static final String USERNAME = "username";
	public static final String BIRTHDAY = "dob";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String ADDRESS = "address";
	public static final String PASSWORD = "password";
	public static final String PASSHASH = "passhash";
	public static final String ROLE = "role";
	public static final String RETYPE_PASSWORD = "re-password";

	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please re-check the form data.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

	public static final String MESSAGE = "message";
	public static final String MESSAGE_TITLE = "message_title";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// Messages

	// JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_DASHBOARD_HOME = "/pages/admin/dashboard_home.jsp";
	public static final String PAGE_URL_DASHBOARD_PRODUCT = "/pages/admin/dashboard_product.jsp";
	public static final String PAGE_URL_HOME = "/pages/user/user_home.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String URL_DASHBOARD_HOME = "/admin/dashboard_home.jsp";
	public static final String URL_DASHBOARD_PRODUCT = "/admin/dashboard_product.jsp";
	public static final String URL_HOME = "/user/user_home.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String URL_REGISTER = "/register.jsp";

	// Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	
	public static final String SERVLET_URL_ADMIN_HOME="/AdminHome";

	public static final String SERVLET_URL_ADMIN_PRODUCT = "/AdminProduct";
	public static final String SERVLET_URL_ADMIN_PRODUCT_UPDATE = "/AdminUpdateProduct";
	public static final String SERVLET_URL_ADMIN_PRODUCT_ADD = "/AdminAddProduct";
	

	public static final String SERVLET_URL_ADMIN_USER = "/AdminUser";
	public static final String SERVLET_URL_ADMIN_USER_ADD = "/AdminAddUser";
	public static final String SERVLET_URL_ADMIN_USER_UPDATE = "/AdminUpdateUser";

	public static final String SERVLET_URL_ADMIN_ORDER = "/AdminOrder";
	public static final String SERVLET_URL_ADMIN_ORDER_UPDATE = "/AdminUpdateOrder";
	
	public static final String SERVLET_URL_USER_HOME = "/UserHome";
	public static final String SERVLET_URL_USER_PROFILE = "/UserProfile";
	public static final String SERVLET_URL_USER_CART = "/UserCart";
	public static final String SERVLET_URL_USER_ORDERS="/UserOrders";
	public static final String SERVLET_URL_USER_ORDER="/UserOrder";
	public static final String SERVLET_URL_USER_PRODUCTS="/UserProducts";
	public static final String SERVLET_URL_USER_PRODUCT_DESC="/UserProdDesc";
	public static final String SERVLET_URL_USER_ABOUT="/UserAbout";

	// Normal Text
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
}
