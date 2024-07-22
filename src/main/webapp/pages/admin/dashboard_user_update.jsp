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
	href="<%=contextPath%>/stylesheets/dashboard_user_update.css">
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
                <div class="form-title">Update User</div>
                <form action="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER_UPDATE %>" method="post"
                    class="user-details" enctype="multipart/form-data">
                    <input type="text" name="userID" value="${user.userID}" style="display: none;">
                    <div class="input-box">
                        <span class="details">Current Profile Picture</span>
                        <img src="<%=contextPath%>/resource/user/${user.imagePath}" alt="profile">
                    </div>
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="username" value="${user.username}" required>
                        <input type="text" name="olduser" value="${user.username }" style="display:none;">
                    </div>
                    <div class="input-box">
                        <span class="details">E-mail</span>
                        <input type="email" name="email" value="${user.email}" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Gender</span>
                        <select name="gender">
                            <option value="" selected>Select an Option</option>
                            <option value="male" ${user.gender=="male" ? 'selected' : '' }>Male</option>
                            <option value="female" ${user.gender=="female" ? 'selected' : '' }>Female</option>
                            <option value="other" ${user.gender=="other" ? 'selected' :'' }>Other</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Address</span>
                        <input type="text" name="address" value="${user.address }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Phone</span>
                        <input type="text" name="phone" value=${user.phone } required>
                    </div>
                    
                    <div class="input-box">
                        <span class="details">Password</span>
                        <input type="password" name="password">
                        <input type="text" name="passhash" value="${user.password }" style="display:none;">
                    </div>
                    <div class="input-box">
                        <span class="details">Date Of Birth</span>
                        <input type="date" name="dob" value="${user.dob }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Role</span>
                        <select name="role">
                            <option value="" selected>Select an Option</option>
                            <option value="user" ${user.role=="user" ? 'selected' : '' }>User</option>
                            <option value="admin" ${user.role=="admin" ? 'selected' : '' }>Admin</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Image</span>
                        <input class="upload-your-image" type="file" name="imagePart" accept="Image/*">
                        <input type="text" name="current-image" value="${user.imagePath}" style="display:none;">
                    </div>
                    <div class="form-buttons">
                        <button type="submit" name="submit" value="update">Update</button>
                        <button type="button" onclick="this.parentElement.children[2].style.display='flex';"
                            style="background-color: #FF030A;">Delete</button>
                        <div class="notice">
                            <div class="message-box">
                                <h2>Confirm Delete?</h2>
                                <p>Do you confirm the deletion of ${user.username}</p>
                                <div class="notice-buttons">
                                    <button type="submit" name="submit" value="delete">Yes</button>
                                    <button type="button" style="background-color: #FF030A;"
                                        onclick="this.parentElement.parentElement.parentElement.style.display='none';">No</button>
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