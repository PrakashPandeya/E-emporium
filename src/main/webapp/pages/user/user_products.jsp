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
	href="<%=contextPath%>/stylesheets/user_products.css">
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
	<script>
		document.getElementById("nav-search").style.display = "none";
	</script>
	<main>
		<div class="filterop">
			<form class="filterform"
				action="<%=contextPath + StringUtils.SERVLET_URL_USER_PRODUCTS%>">
				<div class="filter-box">
					<h2>Product Filter</h2>
					<select name="category">
						<option value="" selected>Select Category</option>
						<option value="smartphones"
							${category == "smartphones" ? 'selected': ''}>Smartphones</option>
						<option value="laptops"
							${category == "laptops" ? 'selected': ''}>Laptops</option>
						<option value="speakers"
							${category == "speakers" ? 'selected': ''}>Speakers</option>
						<option value="earphones"
							${category == "earphones" ? 'selected': ''}>Earphones
							and Earbuds</option>
					</select> <input type="number" id="minp" name="min_price" oninput="upd()"
						value="${min_price }" placeholder="Min Price" min="0"> <input
						type="number" id="maxp" name="max_price" oninput="upd()"
						value="${max_price }" placeholder="Max Price" min="0">
					<script>
						function upd() {
							const minp = document.getElementById('minp');
							const maxp = document.getElementById('maxp');

							minp.setAttribute('max', maxp.value);
							maxp.setAttribute('min', minp.value);
						}
					</script>
					<button>Apply</button>
				</div>
				<input type="hidden" name="search" value="" />
			</form>
			<div class="main-list">
				<div class="search">
					<img src="<%=contextPath%>/resource/icons/211818_search_icon 1.svg"
						alt="Search bar"> <input type="text" id="mainSearchInput"
						placeholder="Search..." oninput="updateHiddenSearch()">
				</div>
				<div class="product-grid">
					<c:forEach var="product" items="${products }">
						<a href="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCT_DESC%>?productID=${product.productID}"
						class="product-card">
							<form class="product-image-container"
								action="<%=contextPath + StringUtils.SERVLET_URL_USER_CART%>"
								method="post">
								<img
									src="<%=contextPath%>/resource/products/${product.imagePath}"
									alt="${product.name }" class="product-image" /> <input
									name="productID" value="${product.productID }"
									style="display: none;"> <input name="sourcePage"
									value="<%=StringUtils.SERVLET_URL_USER_PRODUCTS%>"
									style="display: none;">
								<button type="submit" name="submit" value="addToCart"
									class="add-to-cart-button">Add To Cart</button>
							</form>
							<h3 class="product-name">${product.name }</h3>
							<p class="product-price">Rs. ${product.price }</p>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>
	<script>
		function updateHiddenSearch() {
			const mainSearchInput = document.getElementById('mainSearchInput');
			const hiddenSearchInput = document
					.querySelector('.filterform input[name="search"]');
			hiddenSearchInput.value = mainSearchInput.value;
		}
	</script>
	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>