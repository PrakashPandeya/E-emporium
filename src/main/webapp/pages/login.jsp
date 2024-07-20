<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String contextPath = request.getContextPath();
%>


<!DOCTYPE html>
<html lang="en">

<head>
<!-- The head section contains the meta information and the title of the page -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/login.css">
<link rel="stylesheet" href="<%=contextPath %>/stylesheets/notice.css">
<title>Login</title>
</head>

<body>
<jsp:include page="notice.jsp"></jsp:include>
	<div class="div">
		<div class="column">
					<div class="div-4">
						<div class="form-container">
                            <div class="form-title">Login</div>
                            <form action="<%=contextPath + StringUtils.SERVLET_URL_LOGIN%>"
                                method="post" class="user-details">
                                <div class="input-box">
                                    <span class="details">Username</span> <input type="text"
                                        name="username" value="${username}">
                                </div>
                                <div class="input-box">
                                    <span class="details">Password</span> <input type="password"
                                        name="password">
                                </div>
                                <div class="form-buttons">
                                    <button type="submit" class="form-button">Login</button>
                                    <a href="<%=contextPath+StringUtils.SERVLET_URL_REGISTER %>" class="form-button" style="text-decoration: none;">Register</a>
                                </div>
                            </form>
                        </div>
					</div>
				</div>
					<img src="<%=contextPath %>/resource/icons/login.jpg" class="column" />
			</div>
		</div>
	</div>
</body>

</html>
