package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtils;

@WebFilter("/*")
public class Authentication implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    // Cast request and response objects to HttpServletRequest and HttpServletResponse for type safety
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;

	    // Get the requested URI
	    String uri = req.getRequestURI();
	    System.out.println("filtering uri = "+uri);

	    // Allow access to static resources (CSS) and the index page without checking login
	    if (uri.endsWith(".css") || uri.contains("/resource/") || uri.contains("/script/") || uri.contains("error.jsp") || uri.endsWith("/login")) {
	        chain.doFilter(request, response);
	        return;
	    }

	 // Check if the current request is for login
	    boolean isLogin = uri.endsWith(StringUtils.PAGE_URL_LOGIN);
	    boolean isLoginServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGIN);

	    // Check if the current request is for registration
	    boolean isRegisterPage = uri.endsWith(StringUtils.PAGE_URL_REGISTER);
	    boolean isRegisterServlet = uri.endsWith(StringUtils.SERVLET_URL_REGISTER);

	    // Check if the current request is for logout
	    boolean isLogoutServlet = uri.endsWith(StringUtils.SERVLET_URL_LOGOUT);

	    // Retrieve the session
	    HttpSession session = req.getSession(false);
	    boolean isLoggedIn = session != null && session.getAttribute(StringUtils.USERNAME) != null; // Check if the user is logged in

	    System.out.println("logged in? =" + isLoggedIn); // Output whether the user is logged in

	    // Handle different scenarios based on user's login status and requested URLs
	    // False && !(FALSE||
	    if (!isLoggedIn && !(isLogin || isLoginServlet || isRegisterPage || isRegisterServlet)) { // If user is not logged in and not accessing login/register pages or servlets
	        res.sendRedirect(req.getContextPath() + StringUtils.SERVLET_URL_LOGIN); // Redirect to login page
	    } else if (isLoggedIn && !(isLogoutServlet)) { // If user is logged in and not accessing logout servlet
	        String role = session.getAttribute(StringUtils.ROLE).toString(); // Get user's role
	        
	        System.out.println("role=" + role); // Output user's role
	        
	        if (role.equals("admin") && (uri.contains("pages/admin/") || (uri.contains("/Admin")))) { // If user is admin and accessing admin pages
	            chain.doFilter(req, res); // Allow access
	        } else if (role.equals("user") && (uri.contains("pages/user/") || (uri.contains("/User")))) { // If user is regular user and accessing user pages
	            chain.doFilter(request, response); // Allow access
	        } else { // If user is trying to access unauthorized pages
	            res.sendRedirect(req.getContextPath() + "/pages/error.jsp"); // Redirect to error page
	        }
	    } else {
	        chain.doFilter(request, response); // Allow access for all other cases
	    }
	}


	@Override
	public void destroy() {
	}
}