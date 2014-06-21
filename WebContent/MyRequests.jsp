<!DOCTYPE html>
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
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
						<h2 style="color:#8693EE"><strong>ჩემი მოთხოვნები</strong></h2>	
			<div class="line"></div>
			<%
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				User current = (User) session.getAttribute("user");
				int userID = current.getID();
				UserParseInfo userParse = new UserParseInfo(source);
				ArrayList<Request> list = userParse.getMyRequests(userID);
				for (int i = 0; i < list.size(); i++) {
					Request temp = list.get(i);
					int eventID = temp.getEventID();
					EventParseInfo eventParse = new EventParseInfo(source);
					Event tempEvent = eventParse.getEventByID(eventID);
					Route route = tempEvent.getRoute();
					String from = route.getFromPlace();
					String to = route.getToPlace();
					int accept = temp.getAcception();
					ParseInfo parse = new ParseInfo(source);
					String tel = parse.getPrivateInfo(tempEvent.getUserID(),
							"PhoneNumber", "Tel");
					UserParseInfo use = new UserParseInfo(source);
					User eventOwner = use.getUserByID(tempEvent.getUserID());
					String mail = eventOwner.getEmail();
			%> 
			<div id="column">
				<ul id="latestnews">
							<strong><a style="font-size:17px; "href="Event.jsp?id=<%=temp.getID()%>"><%= from%> <i class="fa fa-arrow-right fa-spin"></i><%="  " +  to%></a></strong>
					<h4>
						<%=temp.getText()%>
					</h4>
					<p>
						<%
							if (accept == 0) {
						%>
						<img style="opacity: 0.4; float: left;"
							src="style/images/onebit_34.png"> <img
							style="float: left; opacity: 0.4;"
							src="style/images/onebit_35.png"> <img style="opacity: 1;"
							src="style/images/onebit_36.png">
						<%
							} else if (accept == 1) {
						%>
						<img style="opacity: 1; float: left;"
							src="style/images/onebit_34.png"> <img
							style="float: left; opacity: 0.4;"
							src="style/images/onebit_35.png"> <img
							style="opacity: 0.4;" src="style/images/onebit_36.png">
							<div id="number<%=i%>" style="float:left; font-size:15px; color:#79bbff;"><strong>ტელეფონი:</strong> <%=" "+ tel %></div>
							<div id="mail<%=i%>" style="float:right;font-size:15px; color:#79bbff;"><strong>ელ-ფოსტა:</strong> <%=" " + mail %></div>
						<%
							} else if (accept == 2) {
						%>
						<img style="opacity: 0.4; float: left;"
							src="style/images/onebit_34.png">
						<img style="float: left; opacity: 1;"
							src="style/images/onebit_35.png">
						<img style="opacity: 0.4;" src="style/images/onebit_36.png">
					<%
						}
					%>
					</p>
				</ul>
			</div>
			<div style="margin-top:30px"class="line"></div>
			<%
				}
			%>
			<!-- End Content -->
		</div>
	</div>
</body>
</html>