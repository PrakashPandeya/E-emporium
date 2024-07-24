<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String contextPath = request.getContextPath();
%>
<jsp:include page="notice.jsp"></jsp:include>
<header class="nav">
	<a href="<%=contextPath + StringUtils.SERVLET_URL_USER_HOME%>"
		class="logo"> <img
		src="<%=contextPath%>/resource/icons/Logo Emporium.png" alt="Logo">
		<span class="logotxt">Emporium</span>
	</a>

	<div class="navigation">
		<a href="<%=contextPath + StringUtils.SERVLET_URL_USER_HOME%>">Home</a>
		<a href="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCTS%>">Shop</a>
		<a href="<%=contextPath+StringUtils.SERVLET_URL_USER_ABOUT%>">About</a>
	</div>

	<div class="rhs">
		<form class="search" id="nav-search" action="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCTS%>">
			<img src="<%=contextPath%>/resource/icons/211818_search_icon 1.svg"
				alt="Search bar"> <input type="text" name="search"
				placeholder="Search...">
				<input type="submit" style="display:none;">
		</form>

		<div class="icons">
			<button id="dropdown-btn" onclick="ddc()">
				<img
					src="<%=contextPath%>/resource/icons/8324223_ui_essential_app_avatar_profile_icon.svg"
					alt="User Profile" width="25px">
				<div class="dropdown-content" id="myDropdown">
					<a href="<%=contextPath + StringUtils.SERVLET_URL_USER_PROFILE%>">User Profile</a>
					<a href="<%=contextPath + StringUtils.SERVLET_URL_USER_ORDERS%>">Order History</a>
					<a href="<%=contextPath + StringUtils.SERVLET_URL_LOGOUT%>" method="post">Logout</a>
			</button>

			<a href="<%=contextPath+StringUtils.SERVLET_URL_USER_CART%>">
				<img
					src="<%=contextPath%>/resource/icons/9025885_shopping_cart_icon.svg"
					width="25px">
			</a>

		</div>
	</div>
	<script>
		function ddc() {
			const dropdownContent = document.getElementById('myDropdown');
			dropdownContent.classList.toggle("ddc-shown");
		}
	</script>
</header>