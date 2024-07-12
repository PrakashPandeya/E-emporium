package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.CartItem;
import model.CartModel;
import model.LoginModel;
import model.OrderDetail;
import model.OrderModel;
import model.ProductModel;
import model.UserModel;
import util.PasswordEncryptionWithAes;
import util.StringUtils;

public class DatabaseController {
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
	}

	public int registeruser(UserModel user) {
	    try {
	        // Get a prepared statement for the user registration query
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

	        // Set values in the query based on the provided UserModel 
	        stmt.setString(1, user.getEmail());
	        stmt.setString(2, user.getGender());
	        stmt.setString(3, user.getAddress());
	        stmt.setString(4, user.getphone());
	        stmt.setDate(5, Date.valueOf(user.getDob())); 
	        stmt.setString(6, user.getUsername());

	        // Encrypt the password before storing
	        stmt.setString(7, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword()));

	        stmt.setString(8, user.getRole());
	        stmt.setString(9, user.getImagePath());

	        // Execute the insert query and get the result status
	        int result = stmt.executeUpdate(); 

	        // Indicate success or failure based on the result 
	        if (result > 0) {
	            return 1; // Registration successful
	        } else {
	            return 0; // Registration failed
	        }

	    } catch (ClassNotFoundException | SQLException ex) {
	        ex.printStackTrace(); // Log the error
	        return -1; // Indicate registration error
	    }
	}


	public LoginModel getuserLoginInfo(LoginModel loginModel) {
	    try {
	        // Prepare a SQL statement to check user credentials
	        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_CHECK_USER);
	        st.setString(1, loginModel.getUsername());  // Set username for the query

	        // Execute the query and get results
	        ResultSet result = st.executeQuery();

	        // If a matching user is found:
	        if (result.next()) {
	            // Retrieve user details from the database 
	            int uid = result.getInt("userID");
	            String userDb = result.getString(StringUtils.USERNAME);
	            String encryptedPwd = result.getString(StringUtils.PASSHASH);

	            // Decrypt the password
	            String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);  

	            String role = result.getString(StringUtils.ROLE);

	            // Create a LoginModel object with the retrieved and decrypted data
	            LoginModel loginResult = new LoginModel(userDb, decryptedPwd, role);
	            loginResult.setUid(uid); 

	            return loginResult; // Return the LoginModel with authentication details

	        } else {
	            // If the user is not found:
	            loginModel.setRole("NF");  // Indicate 'Not Found' role
	            return loginModel; 
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        loginModel.setRole(""); // Set an empty role on error (for security)
	        return loginModel;
	    }
	}


	public ArrayList<UserModel> getUsersInfo(String name) {
	    ArrayList<UserModel> users = new ArrayList<UserModel>(); // Create a list to hold user data

	    // Modify 'name' for pattern matching (using wildcards for a 'LIKE' query)
	    if (name == null || name.equals("")) { 
	        name = "%%";  // Match all if the name is empty
	    } else {
	        name = "%" + name + "%"; // Add wildcards for partial name matching 
	    }

	    try {
	        // Get a prepared statement using the pre-defined query
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USERS);
	        stmt.setString(1, name); // Set the name parameter (protects against SQL injection)

	        // Execute the query and get the results
	        ResultSet result = stmt.executeQuery();

	        // Process each user record in the result set
	        while (result.next()) {
	            UserModel user = new UserModel(); // Create a new user object for each record

	            // Extract user data from the current result row
	            user.setUserID(result.getInt("userID"));
	            user.setUsername(result.getString("username"));
	            user.setEmail(result.getString("email"));
	            user.setAddress(result.getString("address"));
	            user.setDob(result.getDate("dob").toLocalDate());
	            user.setGender(result.getString("gender"));
	            user.setphone(result.getString("phone"));
	            user.setPassword(result.getString("passhash")); 
	            user.setRole(result.getString("role"));

	            users.add(user); // Add the populated 'user' object to the list
	        }

	        return users; // Return the complete list of users

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error for troubleshooting
	        return users; // Return an empty list on database errors 
	    }
	}


	public UserModel getUserInfo(int uid) {
	    UserModel user = new UserModel(); // Create an empty UserModel (in case of errors)

	    try {
	        // Prepare a SQL query to fetch user details by user ID
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USER);
	        stmt.setInt(1, uid); // Set the user ID parameter in the query

	        // Execute the query and get the results
	        ResultSet result = stmt.executeQuery();

	        // If a matching user is found:
	        if (result.next()) {
	            // Populate the 'user' object with data from the database
	            user.setUserID(uid); // Set the userID (may be redundant if already passed in)
	            user.setUsername(result.getString("username"));
	            user.setEmail(result.getString("email"));
	            user.setphone(result.getString("phone"));
	            user.setDob(result.getDate("dob").toLocalDate());
	            user.setAddress(result.getString("address"));
	            user.setGender(result.getString("gender"));
	            user.setImagePath(result.getString("imagePath"));
	            user.setRole(result.getString("role"));
	            user.setPassword(result.getString("passhash"));

	            return user; // Return the populated UserModel object
	        } else {
	            // If no user is found, return the empty 'user' object 
	            return user; 
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return user; // Return the empty 'user' object on database errors
	    }
	}


	public int UpdateUser(UserModel user) {
	    try {
	        // Prepare a SQL query to update user details 
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_USER);

	        // Set values in the update query based on the provided UserModel 
	        stmt.setString(1, user.getEmail());
	        stmt.setString(2, user.getGender());
	        stmt.setString(3, user.getAddress());
	        stmt.setString(4, user.getphone());
	        stmt.setDate(5, Date.valueOf(user.getDob())); 
	        stmt.setString(6, user.getUsername());
	        
	        // Encrypt the password (if it has changed)
	        stmt.setString(7, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword())); 

	        stmt.setString(8, user.getRole());
	        stmt.setString(9, user.getImagePath());

	        // Specify the userID to target the correct user for the update
	        stmt.setInt(10, user.getUserID()); 

	        // Execute the update query and get the result status
	        int result = stmt.executeUpdate(); 

	        // Indicate success or failure based on the result 
	        if (result > 0) {
	            return 1; // Update successful
	        } else {
	            return 0; // Update failed (possibly no updates were made)
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return -1; // Indicate update error
	    }
	}


	public int UpdatePass(UserModel user) {
	    try {
	        // Prepare a SQL statement to update the user's password
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PASS);

	        // Encrypt the new password
	        String encryptedPassword = PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword());
	        stmt.setString(1, encryptedPassword);

	        // Set the userID to target the correct user for the update
	        stmt.setInt(2, user.getUserID());

	        // Execute the update query and get the result status
	        int result = stmt.executeUpdate();

	        // Indicate success or failure based on the result 
	        if (result > 0) {
	            return 1; // Password update successful
	        } else {
	            return 0; // Password update failed (possibly no changes were made)
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return -1; // Indicate password update error
	    }
	}


	public int DeleteUser(int uid) {
	    try {
	        // Prepare a SQL statement to delete a user based on their ID
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_USER);
	        stmt.setInt(1, uid); // Set the user ID parameter in the query

	        // Execute the delete query and get the result status 
	        int result = stmt.executeUpdate();

	        // Indicate success or failure based on the result
	        if (result > 0) {
	            return 1; // User deletion successful
	        } else {
	            return 0; // User deletion failed (possibly the user didn't exist)
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return -1; // Indicate deletion error
	    }
	}


	public Boolean checkEmailIfExists(String email) {
	    try {
	        // Prepare a SQL query to check if a user with the given email exists
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_CHECK_EMAIL);
	        stmt.setString(1, email); 

	        // Execute the query and get the results
	        ResultSet result = stmt.executeQuery();

	        // Check if a record exists with the given email
	        if (result.next()) { 
	            return true;  // Email exists
	        } else {
	            return false; // Email doesn't exist
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); 
	        return true; // Assume email exists in case of database errors (may not be ideal)
	    }
	}


	public Boolean checkNumberIfExists(String number) {
	    try {
	        // Prepare a SQL query to check if a user with the given phone number exists
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_CHECK_PHONE);
	        stmt.setString(1, number); // Set the phone number in the query

	        // Execute the query and get the results
	        ResultSet result = stmt.executeQuery();

	        // Check if a record exists with the given phone number
	        if (result.next()) {
	            return true;  // Phone number exists 
	        } else {
	            return false; // Phone number doesn't exist
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return true; // Assume the phone number exists in case of errors (may not be ideal)
	    }
	}


	public Boolean checkUsernameIfExists(String username) {
	    try {
	        // Prepare a SQL query to check if a user with the given username exists
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_CHECK_USER);
	        stmt.setString(1, username); // Set the username in the query

	        // Execute the query and get the results
	        ResultSet result = stmt.executeQuery();

	        // Check if a record exists with the given username
	        if (result.next()) { 
	            return true;  // Username exists
	        } else {
	            return false; // Username doesn't exist
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return true; // Assume username exists in case of database errors (may not be ideal)
	    }
	}


	public int AddProduct(ProductModel product) {
	    try {
	        // Prepare a SQL query to insert a new product
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_PRODUCT);

	        // Set values in the query based on the provided ProductModel
	        stmt.setString(1, product.getName());
	        stmt.setInt(2, product.getPrice());
	        stmt.setString(3, product.getDescription());
	        stmt.setString(4, product.getCategory());
	        stmt.setInt(5, product.getStock());
	        stmt.setString(6, product.getImagePath());

	        // Execute the insert query and get the status
	        int result = stmt.executeUpdate();

	        // Indicate success or failure based on the result
	        if (result > 0) {
	            return 1; // Product added successfully
	        } else {
	            return 0; // Product addition failed
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the error
	        return -1; // Indicate product addition error
	    }
	}


	// Method to retrieve product information based on search parameters
	public ArrayList<ProductModel> getProductsInfo(String name, String min_price, String max_price, String category) {
	    // List to store fetched product data
	    ArrayList<ProductModel> products = new ArrayList<ProductModel>();

	    // Ensure search parameters are suitable for querying
	    if (name == null || name.equals("")) {
	        name = "%%"; // Match any name if empty
	    } else {
	        name = "%" + name + "%"; // Allow partial name matches
	    }
	    if (min_price == null || min_price.equals("")) {
	        min_price = "0"; // Set default minimum price
	    }
	    if (max_price == null || max_price.equals("")) {
	        max_price = "99999999999"; // Set default maximum price
	    }
	    if (category == null || category.equals("")) {
	        category = "%%"; // Match any category if empty
	    }

	    try {
	        // Prepare SQL statement to retrieve products
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_PRODUCTS);
	        stmt.setString(1, name);
	        stmt.setString(2, min_price);
	        stmt.setString(3, max_price);
	        stmt.setString(4, category);
	        ResultSet result = stmt.executeQuery();
	        
	        // Iterate through the results and populate ProductModel objects
	        while (result.next()) {
	            ProductModel product = new ProductModel();
	            product.setProductID(result.getInt("productID"));
	            product.setName(result.getString("name"));
	            product.setPrice(result.getInt("price"));
	            product.setDescription(result.getString("description"));
	            product.setCategory(result.getString("category"));
	            product.setStock(result.getInt("stock"));
	            product.setImagePath(result.getString("imagePath"));
	            products.add(product); // Add product to the list
	        }
	        return products; // Return the list of products
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return products; // Return empty list in case of exception
	    }
	}
	
	public ProductModel getDummyProduct() {
		ProductModel product = new ProductModel();
        product.setProductID(0);
        product.setName("No Product Found");
        product.setPrice(0);
        product.setDescription("No Product Found");
        product.setCategory("No Product Found");
        product.setStock(0);
        product.setImagePath("No Product Found");
        return product;
	}


	// Method to retrieve information of a specific product by its ID
	public ProductModel getProductInfo(int pid) {
	    try {
	        // Prepare SQL statement to retrieve product information
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_PRODUCT);
	        stmt.setInt(1, pid);
	        ResultSet result = stmt.executeQuery();

	        // If product information is found
	        if (result.next()) {
	            // Create and populate ProductModel object with retrieved data
	            ProductModel product = new ProductModel();
	            product.setProductID(result.getInt("productID"));
	            product.setName(result.getString("name"));
	            product.setPrice(result.getInt("price"));
	            product.setDescription(result.getString("description"));
	            product.setCategory(result.getString("category"));
	            product.setStock(result.getInt("stock"));
	            product.setImagePath(result.getString("imagePath"));
	            return product; // Return the product
	        } else { // If no product information is found
	            // Create a ProductModel object with the given ID and return
	            ProductModel product = new ProductModel();
	            product.setProductID(pid);
	            return product;
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        // Return an empty ProductModel object in case of exception
	        ProductModel product = new ProductModel();
	        return product;
	    }
	}


	// Update product information in the database
	public int UpdateProduct(ProductModel product) {
	    try {
	        // Prepare SQL statement for updating product
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_PRODUCT);
	        stmt.setString(1, product.getName());
	        stmt.setInt(2, product.getPrice());
	        stmt.setString(3, product.getDescription());
	        stmt.setString(4, product.getCategory());
	        stmt.setInt(5, product.getStock());
	        stmt.setString(6, product.getImagePath());
	        stmt.setInt(7, product.getProductID());
	        int Result = stmt.executeUpdate(); // Execute update

	        // Check if update was successful
	        if (Result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Delete product from the database
	public int DeleteProduct(int pid) {
	    try {
	        // Prepare SQL statement for deleting product
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_PRODUCT);
	        stmt.setInt(1, pid);
	        int result = stmt.executeUpdate(); // Execute delete

	        // Check if delete was successful
	        if (result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Retrieve orders information based on search criteria
	public ArrayList<OrderModel> getOrdersInfo(String search) {
	    ArrayList<OrderModel> orders = new ArrayList<OrderModel>(); // List to store fetched order data
	    
	    if (search == null || search.equals("")) {
	        search = "%%"; // Match any search if empty
	    } else {
	        search = "%" + search + "%"; // Allow partial search matches
	    }
	    
	    try {
	        // Prepare SQL statement to retrieve orders
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ORDERS);
	        stmt.setString(1, search);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        while (result.next()) { // Iterate through results
	            OrderModel order = new OrderModel(); // Create OrderModel object
	            order.setOrderID(result.getInt("orderID"));
	            order.setOrderDate(result.getDate("orderDate").toLocalDate());
	            order.setTotal(result.getInt("total"));
	            order.setStatus(result.getString("status"));
	            order.setUserID(result.getInt("userID"));
	            order.setUsername(result.getString("username"));
	            orders.add(order); // Add order to list
	        }
	        return orders; // Return list of orders
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return orders; // Return empty list in case of exception
	    }
	}

	// Retrieve orders information for a specific user
	public ArrayList<OrderModel> getOrdersInfo(int uid) {
	    ArrayList<OrderModel> orders = new ArrayList<OrderModel>(); // List to store fetched order data
	    
	    try {
	        // Prepare SQL statement to retrieve orders for a specific user
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ORDERS_2);
	        stmt.setInt(1, uid);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        while (result.next()) { // Iterate through results
	            OrderModel order = new OrderModel(); // Create OrderModel object
	            order.setOrderID(result.getInt("orderID"));
	            order.setOrderDate(result.getDate("orderDate").toLocalDate());
	            order.setTotal(result.getInt("total"));
	            order.setStatus(result.getString("status"));
	            order.setUserID(result.getInt("userID"));
	            order.setUsername(result.getString("username"));
	            orders.add(order); // Add order to list
	        }
	        return orders; // Return list of orders
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return orders; // Return empty list in case of exception
	    }
	}

	// Retrieve information for a specific order
	public OrderModel getOrderInfo(int oid) {
	    OrderModel order = new OrderModel(); // Create OrderModel object
	    
	    try {
	        // Prepare SQL statement to retrieve order information
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ORDER);
	        stmt.setInt(1, oid);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        if (result.next()) { // If order information is found
	            order.setOrderID(result.getInt("orderID"));
	            order.setOrderDate(result.getDate("orderDate").toLocalDate());
	            order.setTotal(result.getInt("total"));
	            order.setStatus(result.getString("status"));

	            // Retrieve user information for the order
	            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_GET_USER);
	            st.setInt(1, result.getInt("userID"));
	            ResultSet res = st.executeQuery();
	            if (res.next()) {
	                order.setUserID(res.getInt("userID"));
	                order.setUsername(res.getString("username"));
	            }

	            // Retrieve order details
	            PreparedStatement st2 = getConnection().prepareStatement(StringUtils.QUERY_GET_ORDER_DETAILS);
	            st2.setInt(1, result.getInt("orderID"));
	            ResultSet res2 = st2.executeQuery();
	            ArrayList<OrderDetail> orderdetails = new ArrayList<OrderDetail>();
	            while (res2.next()) { // Iterate through order details
	                OrderDetail orderdetail = new OrderDetail(); // Create OrderDetail object
	                orderdetail.setDetailID(res2.getInt("detailID"));
	                orderdetail.setQuantity(res2.getInt("quantity"));

	                // Retrieve product information for the order detail
	                PreparedStatement st3 = getConnection().prepareStatement(StringUtils.QUERY_GET_PRODUCT);
	                st3.setInt(1, res2.getInt("productID"));
	                ResultSet res3 = st3.executeQuery();
	                if (res3.next()) {
	                    orderdetail.setProductID(res3.getInt("productID"));
	                    orderdetail.setName(res3.getString("name"));
	                    orderdetail.setPrice(res3.getInt("price"));
	                }
	                orderdetails.add(orderdetail); // Add order detail to list
	            }

	            order.setDetails(orderdetails); // Set order details for the order
	        }
	        return order; // Return order
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return order; // Return empty order in case of exception
	    }
	}

	// Add an order to the database
	public int AddOrder(OrderModel order) {
	    try {
	        // Prepare SQL statement to add an order
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_ORDER);
	        stmt.setInt(1, order.getUserID());
	        stmt.setDate(2, Date.valueOf(order.getOrderDate()));
	        stmt.setInt(3, order.getTotal());
	        stmt.setString(4, order.getStatus());
	        int result = stmt.executeUpdate(); // Execute update
	        
	        if (result > 0) { // If order addition was successful
	            // Retrieve the latest order ID
	            PreparedStatement stm = getConnection().prepareStatement(StringUtils.QUERY_GET_LATEST_ORDER);
	            ResultSet resul = stm.executeQuery();
	            
	            if (resul.next()) { // If a latest order ID is found
	                int oid = resul.getInt("latest"); // Retrieve the latest order ID
	                int ret = 0;
	                for (OrderDetail detail : order.getDetails()) { // Iterate through order details
	                    // Prepare SQL statement to add order details
	                    PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_ADD_ORDER_DETAILS);
	                    st.setInt(1, oid);
	                    st.setInt(2, detail.getProductID());
	                    st.setInt(3, detail.getQuantity());
	                    int res = st.executeUpdate(); // Execute update
	                    if (res > 0) {
	                        ret = 1;
	                    } else {
	                        ret = 0;
	                    }
	                }
	                return ret; // Return result of adding order details
	            } else {
	                return 0; // Return failure code if no latest order ID is found
	            }
	        } else {
	            return 0; // Return failure code if order addition failed
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Update order status in the database
	public int UpdateOrder(OrderModel order) {
	    try {
	        // Prepare SQL statement to update order status
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_ORDER);
	        stmt.setString(1, order.getStatus());
	        stmt.setInt(2, order.getOrderID());
	        int Result = stmt.executeUpdate(); // Execute update

	        // Check if update was successful
	        if (Result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }

	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Delete an order from the database
	public int DeleteOrder(int oid) {
	    try {
	        // Prepare SQL statement to delete order details
	        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_DELETE_ORDER_DETAILS);
	        st.setInt(1, oid);
	        int res = st.executeUpdate(); // Execute delete
	        
	        if (res >= 0) { // If order details deletion was successful
	            // Prepare SQL statement to delete the order
	            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_ORDER);
	            stmt.setInt(1, oid);
	            int result = stmt.executeUpdate(); // Execute delete
	            
	            // Check if delete was successful
	            if (result > 0) {
	                return 1; // Return success code
	            } else {
	                return 0; // Return failure code
	            }
	        } else {
	            return 0; // Return failure code if order details deletion failed
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Check the count of a specified database table
	public int checkCount(String db) {
	    try {
	        // Prepare SQL statement to get count of specified database table
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_COUNT + db);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        if (result.next()) { // If there's a result
	            return result.getInt("count"); // Return the count
	        } else {
	            return 0; // Handle the case where no count is found
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return 0; // Return 0 in case of exception
	    }
	}

	// Check if a user has items in their cart
	public boolean checkCart(int uid) {
	    try {
	        // Prepare SQL statement to check if a user has items in their cart
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CART);
	        stmt.setInt(1, uid);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        if (result.next()) { // If there are results
	            return true; // Return true if user has items in their cart
	        } else {
	            return false; // Return false if user has no items in their cart
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return false; // Return false in case of exception
	    }
	}


	// Add a new cart for a user
	public int AddCart(int uid) {
	    try {
	        // Prepare SQL statement to add a cart
	        PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_ADD_CART);
	        st.setInt(1, uid);
	        int result = st.executeUpdate(); // Execute update
	        
	        // Check if cart addition was successful
	        if (result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Add an item to the user's cart
	public int AddToCart(int uid, CartItem item) {
	    try {
	        // If user has no cart, create one
	        if (!checkCart(uid)) {
	            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_ADD_CART);
	            st.setInt(1, uid);
	            st.executeUpdate();
	        }
	        
	        // Prepare SQL statement to add item to cart
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_TO_CART);
	        stmt.setInt(1, getCartInfo(uid).getCartID());
	        stmt.setInt(2, item.getProductID());
	        stmt.setInt(3, item.getQuantity());
	        int result = stmt.executeUpdate(); // Execute update
	        
	        // Check if item addition was successful
	        if (result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Remove an item from the user's cart
	public int removeFromCart(int itemID) {
	    try {
	        // Prepare SQL statement to delete item from cart
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_CART_ITEM);
	        stmt.setInt(1, itemID);
	        int result = stmt.executeUpdate(); // Execute delete
	        
	        // Check if item removal was successful
	        if (result > 0) {
	            return 1; // Return success code
	        } else {
	            return 0; // Return failure code
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

	// Retrieve information about the user's cart
	public CartModel getCartInfo(int uid) {
	    CartModel cart;
	    try {
	        // Prepare SQL statement to retrieve cart information
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CART);
	        stmt.setInt(1, uid);
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        if (result.next()) { // If cart information is found
	            cart = new CartModel(result.getInt("cartID")); // Create CartModel object
	            cart.setUserID(result.getInt("userID"));
	            ArrayList<CartItem> items = new ArrayList<CartItem>();
	            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_GET_CART_ITEMS);
	            st.setInt(1, result.getInt("cartID"));
	            ResultSet res = st.executeQuery();
	            while (res.next()) { // Iterate through cart items
	                CartItem item = new CartItem(res.getInt("itemID"), res.getInt("quantity")); // Create CartItem object
	                item.setProductID(res.getInt("productID"));
	                PreparedStatement st2 = getConnection().prepareStatement(StringUtils.QUERY_GET_PRODUCT);
	                st2.setInt(1, item.getProductID());
	                ResultSet res2 = st2.executeQuery();
	                if (res2.next()) {
	                    item.setName(res2.getString("name"));
	                    item.setPrice(res2.getInt("price"));
	                    item.setDescription(res2.getString("description"));
	                    item.setCategory(res2.getString("category"));
	                    item.setStock(res2.getInt("stock"));
	                    item.setImagePath(res2.getString("imagePath"));
	                }
	                items.add(item); // Add cart item to list
	            }
	            cart.setItem(items); // Set cart items for the cart
	            return cart; // Return cart
	        } else { // If no cart information is found
	            AddCart(uid); // Create a new cart for the user
	            return getCartInfo(uid); // Retry fetching cart information
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        cart = new CartModel(); // Return an empty CartModel object in case of exception
	        return cart;
	    }
	}

	// Check if a product is in the user's cart
	public boolean checkInCart(int uid, int pid) {
	    try {
	        // Prepare SQL statement to check if a product is in the user's cart
	        PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_CART_PRODS);
	        stmt.setInt(1, pid);
	        stmt.setInt(2, getCartInfo(uid).getCartID());
	        ResultSet result = stmt.executeQuery(); // Execute query
	        
	        if (result.next()) { // If there are results
	            return true; // Return true if product is in cart
	        } else {
	            return false; // Return false if product is not in cart
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return true; // Return true in case of exception
	    }
	}

	// Convert items in the user's cart to an order
	public int cartToOrder(int uid, int total) {
	    try {
	        CartModel cart = getCartInfo(uid); // Get user's cart information
	        OrderModel order = new OrderModel(); // Create new order
	        order.setUserID(cart.getUserID());
	        order.setOrderDate(LocalDate.now());
	        order.setTotal(total);
	        order.setStatus("pending");
	        ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
	        for (CartItem item : cart.getItem()) { // Iterate through cart items
	            OrderDetail detail = new OrderDetail(); // Create OrderDetail object
	            detail.setQuantity(item.getQuantity());
	            detail.setProductID(item.getProductID());
	            detail.setPrice(item.getPrice());
	            detail.setCategory(item.getCategory());
	            detail.setDescription(item.getDescription());
	            detail.setStock(item.getStock());
	            detail.setImagePath(item.getImagePath());
	            detail.setName(item.getName());
	            details.add(detail); // Add OrderDetail to list
	        }
	        order.setDetails(details); // Set order details
	        int result = AddOrder(order); // Add order to database
	        
	        if (result > 0) { // If order addition was successful
	            for (CartItem item : cart.getItem()) { // Iterate through cart items
	                removeFromCart(item.getItemID()); // Remove item from cart
	            }
	            PreparedStatement stmt=getConnection().prepareStatement(StringUtils.QUERY_DELETE_CART);
	            stmt.setInt(1, uid); // Delete user's cart
	            if (stmt.executeUpdate()>0) { // If cart deletion was successful
	                return 1; // Return success code
	            } else {
	                return 0; // Return failure code
	            }
	        } else {
	            return 0; // Return failure code if order addition failed
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print any SQL or class loading exceptions
	        return -1; // Return error code
	    }
	}

}