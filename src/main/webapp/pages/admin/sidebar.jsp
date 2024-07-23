<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String contextPath = request.getContextPath();
%>

<nav class="sidebar-menu">
			<a href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_HOME%>" id="side-home" class="sidebar-link">Dashboard</a> <a
				href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_PRODUCT%>" id="side-prod" class="sidebar-link">Manage
				Products</a> <a href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_ORDER %>"  id="side-order" class="sidebar-link">Manage
				Orders</a> <a href="<%=contextPath+StringUtils.SERVLET_URL_ADMIN_USER%>" id="side-user" class="sidebar-link">Manage
				Users</a>
</nav>