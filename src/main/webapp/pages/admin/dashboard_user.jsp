<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/admin_nav.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/admin_footer.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/dashboard_prods.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/dashboard_sidebar.css">
	<link rel="stylesheet" href="<%=contextPath %>/stylesheets/notice.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
</head>

<body>
	<jsp:include page="dashboard_header.jsp"></jsp:include>


	<main class="dashboard-container">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<script>
            document.getElementById("side-user").classList.toggle("selected");
            </script>
		<div class="main-content">
			<div class="content-header">

				<a class="add-product-btn" href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER_ADD%>">
					<span class="add-product-text">Add User</span> <img
						src="<%=contextPath%>/resource/icons/add_product_icon.svg" alt=""
						class="add-product-icon" />
				</a>
				<form class="search-bar" action="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER%>">
					<img src="<%=contextPath %>/resource/icons/211818_search_icon 1.svg" alt="Search bar">
					<input type="text" name="search" placeholder="Search...">
					<input type="submit" style="display:none;">
				</form>
			</div>
			<div class="product-list">
				<h2 class="product-list-title">List of Users</h2>
				<div class="actual-list">
					<c:forEach var="user" items="${users}">
						<div class="product-item">
							<div class="product-number"></div>
							<a class="product-name"
								href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER_UPDATE%>?userID=${user.userID}">${user.username}</a>
						</div>
					</c:forEach>
				</div>
				<script>
						let start = 1;
						const elements = document.getElementsByClassName("product-number");
						const list = document.getElementById("actual-list");

						Array.from(elements).forEach(function(element) {
							element.innerHTML = start;
							start +=1;
						});
					</script>
			</div>
		</div>
	</main>

	<jsp:include page="dashboard_footer.jsp"></jsp:include>
</body>

</html>