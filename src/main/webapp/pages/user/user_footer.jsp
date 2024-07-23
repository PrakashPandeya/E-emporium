<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String contextPath = request.getContextPath();
%>


<footer class="footer">
	<div class="footer-column">
		<div class="footer-logo">
			<img src="<%=contextPath%>/resource/icons/Logo Emporium.png"
				alt="Euphorium logo" class="footer-logo-img" />
			<div class="footer-logo-text">euphorium</div>
		</div>
		<div class="footer-social-icons">
			<a href="#"><img src="<%=contextPath%>/resource/icons/Insta icon.svg"
				alt="Instagram icon" class="footer-social-icon" /></a> <a href="#"><img
				src="<%=contextPath%>/resource/icons/FB Icon.svg" alt="Facebook icon"
				class="footer-social-icon" /></a> <a href="#"><img
				src="<%=contextPath%>/resource/icons/X icon.svg" alt="Twitter icon"
				class="footer-social-icon" /></a>
		</div>
	</div>
	<div class="footer-column">
		<div class="footer-contact">
			<div class="footer-contact-title">CONTACT US</div>
			<div class="footer-contact-email">emporium@gmail.com</div>
			<div class="footer-contact-address">Kathmandu, Nepal</div>
			<div class="footer-contact-phone">+01 5236123</div>
		</div>
	</div>
	<div class="footer-column">
		<div class="footer-newsletter">
			<div class="footer-newsletter-title">Latest E-emporium</div>
			<div class="footer-newsletter-description">Be the first to know
				about exciting new accessories, special discounts, store openings
				and much more.</div>
			<form class="footer-newsletter-form">
				<input type="email" name="newsletter_email" placeholder="Email"
					class="footer-newsletter-input" required />
				<button type="submit" class="footer-newsletter-submit">
					<img src="<%=contextPath%>/resource/icons/email icon.svg" alt="Submit" />
				</button>
			</form>
		</div>
	</div>
</footer>