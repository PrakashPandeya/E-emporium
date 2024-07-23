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
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/cart.css">
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

	<div class="bg-filter">
		<div class="sidebar" id="sidebar">
			<div class="sidebar-top">
				<p>My Cart</p>

			</div>
			<div class="sidebar-content">
				<c:forEach var="item" items="${cart.item }">
					<form class="sidebar-card" action="<%=contextPath+StringUtils.SERVLET_URL_USER_CART%>" method="post">
					<input name="itemID" value="${item.itemID }" style="display:none;">
						<img src="<%=contextPath%>/resource/products/${item.imagePath}">
						<div class="card-text">
							<h2>${item.name }</h2>
							<p>Rs. ${item.price }</p>
							<div class="quantity-slide">
								<button type="button" onclick="changequant(this)">-</button>
								<input name="quantity" type="number" value="${item.quantity }" min="1" max="${item.stock }">
								<button type="button" onclick="changequant(this)">+</button>
							</div>
						</div>
						<button type="submit" name="submit" value="removeFromCart" class="remove-card">X</button>
					</form>
				</c:forEach>
			</div>
			<form class="sidebar-bottom" action="<%=contextPath+StringUtils.SERVLET_URL_USER_CART%>" method="post">
				<p>Total:</p>
				<input type="hidden" name="total">
				<button type="submit" name="submit" value="purchase">Purchase</button>
			</form>
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
			calculateTotal()
		}
		function calculateTotal() {
		    let total = 0;
		    const sidebarCards = document.querySelectorAll('.sidebar-card');

		    sidebarCards.forEach(card => {
		        const priceText = card.querySelector('.card-text p').textContent;
		        const price = parseInt(priceText.replace('Rs. ', ''));

		        const quantityInput = card.querySelector('input[name="quantity"]');
		        const quantity = parseInt(quantityInput.value);

		        total += (price * quantity);
		    });

		    const totalDisplay = document.querySelector('.sidebar-bottom p');
		    totalDisplay.textContent = 'Total: Rs. ' + total;
		    const totalInput = document.querySelector('input[name="total"]');
		    totalInput.value = total; 
		}

		calculateTotal();
	</script>

	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>