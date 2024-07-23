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
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/product_details.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/cart.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<script src="<%=contextPath%>/script/script.js"></script>
</head>
<body>
	<jsp:include page="user_nav.jsp" />
	<section class="prodetails">
		<div class="single-pro-image">
			<img src="<%=contextPath %>/resource/products/${product.imagePath}">
		</div>
		<div class="single-pro-details">
			<h6>Category / ${product.category }</h6>
			<div>
				<h1>${product.name }</h1>
				<h4>Rs. ${product.price }</h4>
				<h2>${product.stock } in stock!</h2>
			</div>
			<form class="add-cart-button"
				action="<%=contextPath + StringUtils.SERVLET_URL_USER_CART%>"
				method="post">
				<div class="quantity-slide">
					<button type="button" onclick="changequant(this)">-</button>
					<input type="number" name="quantity" value="1" min="0"
						max="${product.stock }">
					<button type="button" onclick="changequant(this)">+</button>
				</div>
				<input name="productID" value="${product.productID }"
					style="display: none;"> <input name="sourcePage"
					value="<%=StringUtils.SERVLET_URL_USER_PRODUCT_DESC%>"
					style="display: none;">
				<button type="submit" name="submit" value="addToCartDesc"
					class="add-btn">Add to Cart</button>
			</form>
			<div>
				<h3>Product Details</h3>
				<span>${product.description }</span>
			</div>
		</div>
		<script>
			function changequant(element) {
				var text = element.parentElement.children[1];
				if (element.innerHTML == "+") {
					text.value = String(Number(text.value) + 1);
				} else {
					text.value = String(Number(text.value) - 1);
				}
				if (Number(text.value) < 1) {
					text.value = "1";
				}
			}
		</script>
	</section>
	<jsp:include page="user_footer.jsp"></jsp:include>
</body>
</html>