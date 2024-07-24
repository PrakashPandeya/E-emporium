<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/user_profile.css">
<link rel="stylesheet" href="<%=contextPath %>/stylesheets/notice.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<script src="<%=contextPath%>/script/script.js"></script>
</head>

<body>
	<jsp:include page="user_nav.jsp"/>
	
	
	<main>
        <div class="container">
            <div class="title">Profile Description</div>
            <div class="images">
                <img src="<%=contextPath %>/resource/user/${user.imagePath}" alt="profile-picture">
            </div>
            <form action="<%=contextPath+StringUtils.SERVLET_URL_USER_PROFILE%>" method="post" enctype="multipart/form-data">
                <div class="user-details">
                <input type="text" name="userID" value="${user.userID }" style="display:none;">
                <input type="text" name="role" value="${user.role }" style="display:none;">
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="username" value="${user.username }" required>
                        <input type="text" name="olduser" value="${user.username }" style="display:none;">
                    </div>
                    <div class="input-box">
                        <span class="details">Email</span>
                        <input type="email" name="email" value="${user.email }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Phone Number</span>
                        <input type="number" name="phone" value="${user.phone }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Address</span>
                        <input type="text" name="address" value="${user.address }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Date of Birth</span>
                        <input type="date" name="dob" value="${user.dob }" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Gender</span>
                        <select type="text" name="gender" required>
                            <option value="" selected>Select an Option</option>
                            <option value="male" ${user.gender=="male" ? 'selected' : '' }>Male</option>
                            <option value="female" ${user.gender=="female" ? 'selected' : '' }>Female</option>
                            <option value="other" ${user.gender=="other" ? 'selected' :'' }>Other</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Password</span>
                        <button type="button" onclick="this.parentElement.children[2].style.display='flex';">Change Password</button>
                        <div class="notice">
								<div class="message-box">
								<input type="password" name="passhash" value="${user.password}" style="display:none;">
									<h2>Change Password</h2>
									<div>
										<span>Current Password</span>
										<input type="password" name="current-pass">
									</div>
									<div>
										<span>New Password</span>
										<input type="password" name="password">
									</div>
									<div>
										<span>Re-Password</span>
										<input type="password" name="re-password">
									</div>
									<div class="notice-buttons">
										<button type="submit" name="submit" value="pass">Change</button>
										<button type="button" style="background-color: #FF030A;"
											onclick="this.parentElement.parentElement.parentElement.style.display='none';">Back</button>
									</div>
								</div>
							</div>
                    </div>
                    
                    <div class="input-box">
                        <span class="details">Upload Profile Picture</span>
                        <input class="upload-your-image" name="imagePart" type="file" accept="Image/*">
                        <input type="text" name="current-image" value="${user.imagePath}" style="display:none;">
                    </div>
                    <div class="form-button">
                        <input type="submit" name="submit" value="Save changes">
                    </div>

                </div>
            </form>
        </div>
    </main>
	
	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>