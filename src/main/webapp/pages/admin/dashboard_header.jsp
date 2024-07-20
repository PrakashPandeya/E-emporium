<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String contextPath = request.getContextPath();
%>

<jsp:include page="notice.jsp"></jsp:include>

<header class="header">
        <div class="logo-container">
            <img src="<%=contextPath %>/resource/icons/Logo Emporium.png"
                alt="Euphorium logo" class="logo-img" />
            <span class="logo-text">euphorium</span>
        </div>
        <button class="logout-btn" onclick="window.location.href='<%=contextPath+StringUtils.SERVLET_URL_LOGOUT%>'">Logout</button>
</header>