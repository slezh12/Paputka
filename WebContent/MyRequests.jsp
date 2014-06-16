<!DOCTYPE html>
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


<script>
	function CheckAlerts() {
		if ((registerform.about.value).length > 200) {
			alert("ჩემს შესახებ ველი  არ უნდა აღემატებოდეს 200 სიმბოლოს");
			return false;
		}
		if ((registerform.tel.value).length > 25) {
			alert("ტელეფონის ველი  არ უნდა აღემატებოდეს 25 სიმბოლოს");
			return false;
		}
		return true;
	}
</script>
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
			<h2>ჩემი მოთხოვნები</h2>
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
			%>
			<div id="column">
				<ul id="latestnews">
					<strong><a href="#"><h2><%=from%>-----><%=to%></h2></a></strong>
					<h4>
						<%=temp.getText()%>
					</h4>
					<p>
					<img src="style/images/onebit_34.png">
					<img src="style/images/onebit_35.png">
					<img src="style/images/onebit_36.png">
					<%
					if(accept == 0){
						
					} else if(accept == 1){
						
					} else if(accept == 2){
						
					}
					%>
					</p>
				</ul>
			</div>
			<%
				}
			%>
			<div class="line"></div>
			<!-- End Content -->
		</div>
	</div>
</body>
</html>