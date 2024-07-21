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
    <link rel="stylesheet" href="<%=contextPath %>/stylesheets/admin_nav.css">
    <link rel="stylesheet" href="<%=contextPath %>/stylesheets/admin_footer.css">
    <link rel="stylesheet" href="<%=contextPath %>/stylesheets/dashboard_home_1.css">
    <link rel="stylesheet" href="<%=contextPath %>/stylesheets/dashboard_sidebar.css">
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
            document.getElementById("side-home").classList.toggle("selected");
            </script>
            <div class="main-content">
                <p class="admin-welcome-title">Welcome Admin!</p>

                <section class="stats-container">
                    <div class="stats-row">
                            <div class="stats-card">
                                <p class="stats-card-title">Total Products</p>
                                <p class="stats-card-value">${productCount }</p>
                            </div>
                            <div class="stats-card">
                                <p class="stats-card-title">Total Users</p>
                                <p class="stats-card-value">${userCount }</p>
                            </div>

                            <div class="stats-card">
                                <p class="stats-card-title">Total Orders</p>
                                <p class="stats-card-value">${orderCount }</p>
                            </div>

                    </div>
                </section>
            </div>
</main>

    <jsp:include page="dashboard_footer.jsp"></jsp:include>
</body>

</html>