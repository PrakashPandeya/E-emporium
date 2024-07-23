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
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/user_about.css">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/notice.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<script src="<%=contextPath%>/script/script.js"></script>
</head>

<body>
	<jsp:include page="user_nav.jsp"/>
	
	<section id="team">
        <div id="hero" style="background-image:url('<%=contextPath%>/resource/icons/hero.jpg')">
                <h2>About us</h2>
                <br>
                <p>"Code with Clarity, Innovate with Purpose."</p>
        </div>

        <div id="ceo">
            <div id="txt">
                <h2>WE ARE EUPHORIUM</h2>
                <p>E-emporium is an online gadgets and accessories website.</p>
                <br>
                <p>The categories range from mobile phones to gaming and what not.</p>
                <br>
                <p>"Empowering You, Securing Trust."</p>
                <br>
                <p>We would ensure you the best of the best quality products leaving you no space to compromise</p>
                <br>
                <span id="nigs">CEO Rubesh Shrestha</span>
            </div>
            <div id="ceophoto">
                <img src="<%=contextPath %>/resource/icons/Rubesh ko photo.png" alt="Rubesh">
            </div>
        </div>

        <div id="meet">
            <h2>Meet The Team</h2>
            <br>
            <p>With a passion for excellence, we strive to exceed expectations and foster enduring partnerships built on trust and integrity.
            </p>
        </div>
            
        <div id="box5">
            <div class="personas">
                <img src="<%=contextPath %>/resource/icons/Awashek.jpg" alt="">
                <br>
                <h3>Awashek Acharya</h3>
                <p>9810281791</p>
                <p>awashek@gmail.com</p>
                <span id="roles">Frontend Developer</span>
            </div>
            <div class="personas">
                <img src="<%=contextPath %>/resource/icons/Shubham.jpg" alt="">
                <br>
                <h3>Shubham Phuyal</h3> 
                <p>9821231792</p>
                <p>shubham@gmail.com</p>
                <span id="roles">Senior Product Designer</span>
            </div>
            <div class="personas">
                <img src="<%=contextPath %>/resource/icons/Nidiv.jpg" alt="">
                <br>
                <h3>Nidiv Kayastha</h3>
                <p>9861119872</p>
                <p>nidiv@gmail.com</p> 
                <span id="roles">UI Designer</span>
            </div>
            <div class="personas">
                <img src="<%=contextPath %>/resource/icons/Prakash.jpg" alt="">
                <br>
                <h3>Prakash Pandeya</h3> 
                <p>9861256187</p>
                <p>prakash@gmail.com</p>
                <span id="roles">Backend Developer</span>
            </div>

        </div>
            


    </section>
	

	<jsp:include page="user_footer.jsp"></jsp:include>
</body>

</html>