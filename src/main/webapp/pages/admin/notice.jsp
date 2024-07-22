<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String contextPath = request.getContextPath();
%>


<div class="notice" id="notice">
        <div class="message-box">
            <h2>${message_title}</h2>
            <p id="notice-message">${message}</p>
            <button onclick="removemsg()">OK</button>
        </div>
    </div>
    <script>
        const message=document.getElementById("notice-message");
        const notice=document.getElementById("notice");
        if (message.innerHTML!="") {
            notice.style.display="flex";
        }
        function removemsg() {
            notice.style.display="none";
        }
    </script>