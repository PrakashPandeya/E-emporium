<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String contextPath = request.getContextPath();
String currentURL = request.getRequestURL().toString();
%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/nav.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/footer.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/user_home.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<script src="<%=contextPath%>/script/script.js"></script>
</head>

<body>
	<jsp:include page="user_nav.jsp"/>

	<section class="hero-section">
    <div class="hero-content">
      <div class="hero-text-container">
        <h1 class="hero-title">
          ${hero.name }
        </h1>
        <p class="hero-subtitle">Now Available On Emporium</p>
        <a href="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCT_DESC %>?productID=${hero.productID}" class="hero-cta">Shop Now</a>
      </div>
      <div class="hero-image-container">
        <img src="<%=contextPath%>/resource/products/${hero.imagePath}" class="hero-image" />
      </div>
    </div>
  </section>

  <section class="fps">
    <div class="featured-products-section">
      <h2 class="section-title">Our Products</h2>
      <div class="featured-products-icons">
        <button onclick="scroll_left()" class="featured-product-icon"><</button>
            <button onclick="scroll_right()" class="featured-product-icon">></button>
      </div>
      <script>
        function scroll_left() {
          document.getElementById('product-grid').scrollBy({left:-200, behavior:"smooth"});
        }
        function scroll_right() {
          document.getElementById('product-grid').scrollBy({left:200, behavior:"smooth"});
        }
      </script>
    </div>

    <div id="product-grid" class="product-grid">
    <c:forEach var="product" items="${products }">
      <a href="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCT_DESC %>?productID=${product.productID}" class="product-card">
        <form class="product-image-container" action="<%=contextPath+StringUtils.SERVLET_URL_USER_CART%>" method="post">
          <img
            src="<%=contextPath%>/resource/products/${product.imagePath}"
            alt="${product.name }" class="product-image" />
            <input name="productID" value="${product.productID }" style="display:none;">
            <input name="sourcePage" value="<%=StringUtils.SERVLET_URL_USER_HOME%>" style="display:none;">
          <button type="submit" name="submit" value="addToCart" class="add-to-cart-button">Add To Cart</button>
        </form>
        <h3 class="product-name">${product.name }</h3>
        <p class="product-price">Rs. ${product.price }</p>
      </a>
      </c:forEach>
    </div>
  </section>

  <section class="features-section">
      <div class="feature-card">
        <img
          src="<%=contextPath%>/resource/icons/delivery icon.svg"
          alt="" class="feature-icon" />
        <h3 class="feature-title">FREE AND FAST DELIVERY</h3>
        <p class="feature-description">Free delivery for all orders over
          $140</p>
      </div>

      <div class="feature-card">
        <img
          src="<%=contextPath%>/resource/icons/support icon.svg"
          alt="" class="feature-icon" />
        <h3 class="feature-title">24/7 CUSTOMER SERVICE</h3>
        <p class="feature-description">Friendly 24/7 customer support</p>
      </div>

      <div class="feature-card">
        <img
          src="<%=contextPath%>/resource/icons/security icon.svg"
          alt="" class="feature-icon" />
        <h3 class="feature-title">MONEY BACK GUARANTEE</h3>
        <p class="feature-description">We return money within 30 days</p>
      </div>
  </section>

  <section class="product-showcase">
        <div class="product-info">
            <p class="product-label">Today's Product</p>
            <h2 class="product-title">${prod.name}</h2>
            <h4>${prod.description }</h4>
            
            <a href="<%=contextPath+StringUtils.SERVLET_URL_USER_PRODUCT_DESC %>?productID=${prod.productID}" style="color:black;text-decoration:none;" class="buy-button">Buy Now!</a>
        </div>
        <div class="product-media">
            <img
              src="<%=contextPath%>/resource/products/${prod.imagePath}"
              alt="Product Image" class="product-image-tp" />
        </div>
  </section>

	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>