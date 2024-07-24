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
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/nav.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/footer.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/user_orders.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<script src="<%=contextPath%>/script/script.js"></script>
</head>

<body>
	<jsp:include page="user_nav.jsp" />

	<main>
		<div class="header">
			<h2>Order History</h2>
		</div>

		<div class="order-list">
		<c:forEach var="order" items="${orders }">
			<a class="order-item" href="<%=contextPath+StringUtils.SERVLET_URL_USER_ORDER%>?orderID=${order.orderID}"> <span class="product-number"></span>Order ID ${order.orderID } [Status: ${order.status }]</a> 
			</c:forEach>
		</div>
		<script>
			let start = 1;
			const elements = document.getElementsByClassName("product-number");

			Array.from(elements).forEach(function(element) {
				element.innerHTML = start;
				start += 1;
			});
		</script>
	</main>

	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>