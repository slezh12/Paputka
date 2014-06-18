<%@page import="JavaPackage.ParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="JavaPackage.User"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Paputka</title>
		<link rel="shortcut icon" type="image/x-icon" href="style/images/favicon.png" />
		<link rel="stylesheet" type="text/css" href="style.css" />
		
		<script type="text/javascript" src="style/js/jquery-1.6.4.min.js"></script>
		<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
		<script type="text/javascript" src="style/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="style/js/jquery.leanModal.min.js"></script>
	</head>
	<body>
		<!-- Begin Wrapper -->
		<div id="wrapper">
			<!-- Begin Sidebar -->
			<div id="sidebar">
				<div id="logo"><img src="style/images/logo.png" alt="Paputka" /></div>
				<!-- Begin Menu -->
				<% 
				
				int userID = Integer.parseInt((String)request.getAttribute("id"));
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				User current = (User) session.getAttribute("user");
				int userIDGuest = current.getID();
				UserParseInfo userParse = new UserParseInfo(source);
				User user = userParse.getUserByID(userID);
				String gender = "";
				if(user.getGender()){
					
				}
				
				
				%>
				
				  <ul>
		<!-- 		  	<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li> -->
				  	<li style="color: #fff;">
						რეიტინგი 
					</li>
					<li style="color: #fff;">
						 სტატუსი  
					</li>
					<li style="color: #fff;">
						დაბადების თარიღი  <%= user.getBirthdate() %>
					</li>
					
				  </ul>
				</div>
				<!-- End Menu -->
			</div>
			<!-- End Sidebar -->
			<div class="line"></div>
		<!-- Begin Content -->
			<div id="content">
				<h2> <%= user.getFirstName() + " " + user.getLastName() %></h2>
				<div class="line"></div>
		<!-- End Content -->
			</div>
		</div>
<script type="text/javascript">
$(function(){
  $('#registerform').submit(function(e){
    return false;
  });
  $('#modaltrigger').leanModal({ top:5, overlay: 0.45, closeButton: ".hidemodal" });
});
</script>

</body>
</html>
