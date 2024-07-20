<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/register.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
<title>Register User</title>
</head>

<body>
	<jsp:include page="notice.jsp"></jsp:include>
	<div class="div">
		<img src="<%=contextPath%>/resource/icons/register.jpg"
			class="column" />
		<div class="column">
			<div class="form-container">
				<div class="form-title">Registration</div>
				<form action="<%=contextPath + StringUtils.SERVLET_URL_REGISTER%>"
					method="post" class="user-details" enctype="multipart/form-data">
					<input type="text" name="userID" value="${user.userID }"
						style="display: none;">
					<div class="input-box">
						<span class="details">Username</span> <input type="text"
							name="username" value="${user.username }" required>
					</div>
					<div class="input-box">
						<span class="details">E-mail</span> <input type="email"
							name="email" value="${user.email }" required>
					</div>
					<div class="input-box">
						<span class="details">Gender</span> <select name="gender">
							<option value="" selected>Select an Option</option>
							<option value="male" ${user.gender == "male" ? 'selected': ''}>Male</option>
							<option value="female" ${user.gender == "female" ? 'selected': ''}>Female</option>
							<option value="other" ${user.gender == "other" ? 'selected': ''}>Other</option>
						</select>
					</div>
					<div class="input-box">
						<span class="details">Address</span> <input type="text"
							name="address" value=${user.address } required>
					</div>
					<div class="input-box">
						<span class="details">Phone</span> <input type="number"
							name="phone" value=${user.phone } required>
					</div>
					<div class="input-box">
						<span class="details">Date Of Birth</span> <input type="date"
							name="dob" value="${user.dob }" required>
					</div>
					<div class="input-box">
						<span class="details">Password</span> <input type="password"
							name="password" required>
					</div>
					<div class="input-box">
						<span class="details">Re-Password</span> <input type="password"
							name="re-password" required>
					</div>

					<div class="input-box">
						<span class="details">Image</span> <input
							class="upload-your-image" type="file" name="imagePart"
							accept="Image/*" style="border: none;">
					</div>
					<div class="form-buttons">
						<button type="submit" class="form-button">Register</button>
						<a href="<%=contextPath+StringUtils.SERVLET_URL_LOGIN %>" class="form-button" style="text-decoration: none;">Login</a>

					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>