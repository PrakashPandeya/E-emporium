<%@page import="util.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/admin_nav.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/admin_footer.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/dashboard_prods_update.css">
<link rel="stylesheet"
	href="<%=contextPath%>/stylesheets/dashboard_sidebar.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
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
            document.getElementById("side-prod").classList.toggle("selected");
            </script>
		<div class="main-content">
			<a href="<%=contextPath + StringUtils.SERVLET_URL_ADMIN_PRODUCT%>"
				class="go-back">Back</a>
			<div class="form-container">
				<div class="form-title">Update Product</div>
				<form
					action="<%=contextPath + StringUtils.SERVLET_URL_ADMIN_PRODUCT_UPDATE%>"
					class="user-details" method="post" enctype="multipart/form-data">
					<input type="text" name="productID" value="${product.productID }"
						style="display: none;">
					<div class="current-image">
						<span class="details">Current Image</span> <img
							src="<%=contextPath %>/resource/products/${product.imagePath}"
							alt="product">
					</div>
					<div class="input-box">
						<span class="details">Name</span> <input type="text" name="name"
							value="${product.name }" required>
					</div>
					<div class="input-box">
						<span class="details">Price</span> <input type="number"
							name="price" value="${product.price }" required>
					</div>
					<div class="input-box">
						<span class="details">Description</span>
						<textarea type="text" name="description" required>${product.description }</textarea>
					</div>
					<div class="input-box">
						<span class="details">Category</span> <select name="category"
							required>
							<option value="" disabled selected>Select an Option</option>
							<option value="smartphones"
								${product.category == "smartphones" ? 'selected': ''}>Smartphones</option>
							<option value="laptops"
								${product.category == "laptops" ? 'selected': ''}>Laptops</option>
							<option value="speakers"
								${product.category == "speakers" ? 'selected': ''}>Speakers</option>
							<option value="earphones"
								${product.category == "earphones" ? 'selected': ''}>Earphones
								and Earbuds</option>
						</select>
					</div>
					<div class="input-box">
						<span class="details">Stock</span> <input type="number"
							name="stock" value="${product.stock }" required>
					</div>
					<div class="input-box">
						<span class="details">Image</span> <input
							class="upload-your-image" type="file" name="imagePart"
							accept="Image/*">
							<input type="text" name="current-image" value="${product.imagePath}" style="display:none;">
					</div>
					<div class="form-action">
						<span>Action</span>
						<div class="form-buttons">
							<button type="submit" name="submit" value="update">Update</button>
							<button type="button" onclick="this.parentElement.children[2].style.display='flex';"
								style="background-color: #FF030A;">Delete</button>
							<div class="notice">
								<div class="message-box">
									<h2>Confirm Delete?</h2>
									<p>Do you confirm the deletion of ${product.name }</p>
									<div class="notice-buttons">
										<button type="submit" name="submit" value="delete">Yes</button>
										<button type="button" style="background-color: #FF030A;"
											onclick="this.parentElement.parentElement.parentElement.style.display='none';">No</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>

	<jsp:include page="dashboard_footer.jsp"></jsp:include>
</body>

</html>