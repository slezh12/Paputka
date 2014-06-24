<!DOCTYPE html>
<%@page import="JavaPackage.WantToGo"%>
<%@page import="JavaPackage.WantToGoParseInfo"%>
<%@page import="JavaPackage.ParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.User"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Paputka</title>
<link rel="shortcut icon" type="image/x-icon"
	href="style/images/favicon.png" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet" />
<script type="text/javascript" src="style/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="style/js/jquery.leanModal.min.js"></script>
</head>
<body>
	<!-- Begin Wrapper -->
	<div id="wrapper">
		<!-- Begin Sidebar -->
		<div id="sidebar">
			<div id="logo">
				<img src="style/images/logo.png" alt="Paputka" />
			</div>
			<!-- Begin Menu -->
			<div id="menu" class="menu-v">
				<ul>
					<li><a href="UserPage.jsp" class="active">Main Page</a></li>
				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
			<h2 style="color:#8693EE"><strong>My Filtres</strong></h2>
			<div class="line"></div>
			<%
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				User current = (User) session.getAttribute("user");
				int userID = current.getID();
				WantToGoParseInfo wantToGo = new WantToGoParseInfo(source);
				ArrayList<WantToGo> list = wantToGo.getWantToGos(userID);
				for (int i = 0; i < list.size(); i++) {
					WantToGo temp = list.get(i);
					int wantToGoID = temp.getID();
					String title = temp.getTitle();
					boolean type = temp.getType();
			%>
			<div id="column">
				<ul id="latestnews">
					<a href="WantToGoPage.jsp?id=<%=wantToGoID%>"><h3 "><strong><%=title%></strong></h3></a>
					<p>
						<%
							if (type) {
						%>
					
					<h4>One Way</h4>
					<%
						} else {
					%>
					<h4>Daily</h4>
					<%
						}
					%>
					</p>
				</ul>
			</div>
			<div class="line"></div>
			<%
				}
			%>
			<!-- End Content -->
		</div>
	</div>
</body>
</html>