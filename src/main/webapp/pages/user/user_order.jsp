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
	href="<%=contextPath%>/stylesheets/user_order.css">
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
	<div class="main-content">
		<a href="<%=contextPath + StringUtils.SERVLET_URL_USER_ORDERS%>"
			class="go-back">Back</a>
		<div class="form-container">
			<div class="form-title">Update Order</div>
			<form action="<%=contextPath + StringUtils.SERVLET_URL_USER_ORDER%>"
				method="post" class="user-details">
				<div class="input-box">
					<span class="details">Order ID</span> <input type="text"
						value="${order.orderID}" disabled> <input type="text"
						name="orderID" value="${order.orderID}" style="display: none;">
				</div>
				<div class="input-box">
					<span class="details">Ordered By</span> <input type="text"
						name="username" value="${order.username}" disabled>
				</div>
				<div class="input-box">
					<span class="details">Order Date</span> <input type="date"
						name="orderDate" value="${order.orderDate}" disabled>
				</div>
				<div class="input-box">
					<span class="details">Order Total Value</span> <input type="text"
						name="total" value="Rs. ${order.total}" disabled>
				</div>
				<div class="input-box">
					<span class="details">Status</span> <select name="status" disabled>
						<option value="" selected>Select an Option</option>
						<option value="pending"
							${order.status=="pending" ? 'selected' : '' }>Pending</option>
						<option value="delivered"
							${order.status=="delivered" ? 'selected' : '' }>Delivered</option>
						<option value="cancelled"
							${order.status=="cancelled" ? 'selected' : '' }>Cancelled</option>
					</select>
				</div>
				<div class="input-box">
					<span>Action</span>
					<div class="form-buttons">
						<button type="button"
							onclick="this.parentElement.children[1].style.display='flex';"
							style="background-color: #FF030A;" name="cancel">Cancel</button>
						<div class="notice">
							<div class="message-box">
								<h2>Confirm Cancellation?</h2>
								<p>Do you confirm the cancellation of order ID
									${order.orderID}?</p>
								<div class="notice-buttons">
									<button type="submit" name="submit" value="delete">Yes</button>
									<button type="button" style="background-color: #FF030A;"
										onclick="this.parentElement.parentElement.parentElement.style.display='none';">No</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<script>
					const statusSelect = document
							.querySelector('select[name="status"]');
					const cancelButton = document
							.querySelector('.form-buttons button[name="cancel"]');

					statusSelect.addEventListener('change', function() {
						cancelButton.disabled = this.value === 'cancelled';
					});
					statusSelect.dispatchEvent(new Event('change'));
				</script>
				<table class="ProductList">
					<tr>
						<th width="15%">PID</th>
						<th width="45%">Name</th>
						<th width="20%">Quantity</th>
						<th width="20%">Price</th>
					</tr>
					<c:forEach var="orderdetail" items="${order.details}">
						<tr>
							<td>${orderdetail.productID}</td>
							<td>${orderdetail.name }</td>
							<td>${orderdetail.quantity }</td>
							<td>${orderdetail.price }</td>
						</tr>
					</c:forEach>
				</table>

			</form>
		</div>
	</div>

	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>