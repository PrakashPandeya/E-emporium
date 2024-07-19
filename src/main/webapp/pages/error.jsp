<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Euphorium</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/error.css">
</head>
<body>
    <div class="error-container">
        <h1>Error 405</h1>
        <p>Page Not Allowed</p>
        <a href="<%=request.getContextPath()+StringUtils.SERVLET_URL_LOGIN%>" class="back-link">Back</a>
    </div>

</body>
</html>