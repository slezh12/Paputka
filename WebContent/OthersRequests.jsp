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
<link rel="stylesheet" type="text/css"
	href="style/js/DateTimePicker.css" />
<script type="text/javascript" src="style/js/DateTimePicker.js"></script>

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
			<h2 style="color: #8693EE">
				<strong>Notifications</strong>
			</h2>
			<div class="line"></div>
			<%
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				User current = (User) session.getAttribute("user");
				int userID = current.getID();
				UserParseInfo userParse = new UserParseInfo(source);
				ArrayList<Request> list = userParse.getOthrRequests(userID);
				for (int i = 0; i < list.size(); i++) {
					Request temp = list.get(i);
					int requestID = temp.getID();
					int eventID = temp.getEventID();
					EventParseInfo eventParse = new EventParseInfo(source);
					Event tempEvent = eventParse.getEventByID(eventID);
					boolean validation = tempEvent.getValidation();
					if (validation) {
						Route route = tempEvent.getRoute();
						String from = route.getFromPlace();
						String to = route.getToPlace();
						int accept = temp.getAcception();
						ParseInfo parse = new ParseInfo(source);
						int fromUserID = temp.getFromUserID();
						String tel = parse.getPrivateInfo(temp.getFromUserID(),
								"PhoneNumber", "Tel");
						User eventOwner = userParse.getUserByID(temp
								.getFromUserID());
						String mail = eventOwner.getEmail();
						String name = eventOwner.getFirstName();
						String lastName = eventOwner.getLastName();
			%>
			<div id="column">
				<ul id="latestnews">
					<p>
						<strong><a style="font-size: 17px;"
							href="Event.jsp?id=<%=eventID%>"><%=from%> <i
								class="fa fa-arrow-right fa-spin"></i><%="  " + to%></a></strong>
					</p>
					<p>
						<strong><a href="Profile.jsp?id=<%=fromUserID%>"><%=name + " "%><%=lastName%></a></strong>
					</p>
					<h4>
						<%=temp.getText()%>
					</h4>
					<%
						if (accept == 0) {
					%>

					<form action="OtherRequestsServlet" method="post">
						<input type="radio" class="radio1" name="acc" value="yes"
							tabindex="5"></input><img style="opacity: 1; float: left;"
							src="style/images/onebit_34.png"> <input type="radio"
							class="radio2" name="acc" value="no" tabindex="6"><img
							style="opacity: 1;" src="style/images/onebit_35.png"></input> <input
							type="hidden" name="request" value=<%=requestID%>> <input
							type="hidden" name="event" value=<%=eventID%>> <input
							type="hidden" name="fromUserID" value=<%=fromUserID%>> <input
							type="submit" style="width: 150px;" class="mailnumber"
							value="Execute"></input>
					</form>
					<%
						} else if (accept == 1) {
					%>
					<img style="opacity: 1; float: left;"
						src="style/images/onebit_34.png">
					<img style="opacity: 0.4;" src="style/images/onebit_35.png">
					<%
						} else if (accept == 2) {
					%>
					<img style="opacity: 0.4; float: left;"
						src="style/images/onebit_34.png">
					<img style="opacity: 1;" src="style/images/onebit_35.png">
					<%
						}
					%>
					<p>
					<div id="number<%=i%>"
						style="float: left; font-size: 15px; color: #79bbff;">
						<strong>Tel:</strong>
						<%=" " + tel%></div>
					<div id="mail<%=i%>"
						style="float: right; font-size: 15px; color: #79bbff;">
						<strong>E-Mail:</strong>
						<%=" " + mail%></div>
					</p>
				</ul>
			</div>
			<div class="line" style="margin-top: 50px"></div>
			<%
				}
				}
			%>
			<!-- End Content -->
		</div>
	</div>

</body>
</html>