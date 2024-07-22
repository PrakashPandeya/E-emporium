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
	href="<%=contextPath%>/stylesheets/dashboard_prods_add1.css">
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
            <a href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER %>" class="go-back">Back</a>
            <div class="form-container">
                <div class="form-title">Add User</div>
                <form action="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER_ADD %>" method="post" class="user-details" enctype="multipart/form-data">
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="username" value="${user.username}" required>
                    </div>
                    <div class="input-box">
                        <span class="details">E-mail</span>
                        <input type="email" name="email" value="${user.email }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Gender</span>
                        <select name="gender">
                            <option value="" selected>Select an Option</option>
                            <option value="male" ${user.gender == "male" ? 'selected': ''}>Male</option>
                            <option value="female" ${user.gender == "female" ? 'selected': ''}>Female</option>
                            <option value="other" ${user.gender=="other" ? 'selected':'' }>Other</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Address</span>
                        <input type="text" name="address" value="${user.address}" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Phone</span>
                        <input type="number" name="phone" value=${user.phone } required>
                    </div>
                    <div class="input-box">
                        <span class="details">Date Of Birth</span>
                        <input type="date" name="dob" value=${user.dob } required>
                    </div>
                    <div class="input-box">
                        <span class="details">Password</span>
                        <input type="password" name="password" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Re-Password</span>
                        <input type="password" name="re-password" required>
                    </div>
                    
                    <div class="input-box">
                        <span class="details">Role</span>
                        <select name="role">
                            <option value="" selected>Select an Option</option>
                            <option value="user" ${user.role == "user" ? 'selected': ''}>User</option>
                            <option value="admin" ${user.role == "admin" ? 'selected': ''}>Admin</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Image</span>
                        <input class="upload-your-image" type="file" name="imagePart" accept="Image/*">
                    </div>
                    <button type="submit" class="form-button">Add</button>
                    </form>
            </div>
        </div>
	</main>

	<jsp:include page="dashboard_footer.jsp"></jsp:include>
</body>

</html>