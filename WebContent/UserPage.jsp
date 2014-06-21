<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.BaseConnection"%>
<%@page import="JavaPackage.WantToGo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="JavaPackage.WantToGoParseInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="JavaPackage.User"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Paputka</title>
<link rel="shortcut icon" type="image/x-icon"
	href="style/images/favicon.png" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link
    href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
    rel="stylesheet" />

<script type="text/javascript" src="style/js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="style/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="style/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="style/js/jquery.leanModal.min.js"></script>
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
			<%
				User user = (User) session.getAttribute("user");
									String name = user.getFirstName();
									String lastname = user.getLastName();
									int userID = user.getID();
									BasicDataSource source = (BasicDataSource) application
											.getAttribute("connectionPool");
									WantToGoParseInfo parse = new WantToGoParseInfo(source);
									ArrayList<WantToGo> list = parse.getWantToGos(userID);
			%>
			<h3 id="welcomeUser">
				Welcome,
				<%=name+" "+lastname%>
			</h3>
			<div id="Usermenu" class="menu-v" style="marign-top: 20px;">
				<ul>
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
					<li><a href="Profile.jsp?id=<%=userID%>">პროფილი</a></li>
					<li><a href="ChangePrivateInfo.jsp">პროფილის შეცვლა</a></li>
					<li><a href="CreateEvent.jsp">წავიყვან</a></li>
					<li><a href="CreateWantToGo.jsp">წასვლა მინდა</a></li>
					<li><a href="MyRequests.jsp">ჩემი მოთხოვნები</a></li>
					<li><a href="OthersRequests.jsp">სხვისი მოთხოვნები</a></li>
					<li><a href="MyWantToGos.jsp">ჩემივონთთუგოუ</a></li>
					<li><a href="index.jsp">გამოსვლა </a></li>

				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
				<h2 style="color:#8693EE"><strong>რელევანტური  პოსტები</strong></h2>	
			<div class="line"></div>
			<%
				if(list.size() == 0){
									EventParseInfo parseEvent = new EventParseInfo(source);
									ArrayList<Event> listOfEvents = parseEvent.getLastEvents();
									for(int i = 0; i < listOfEvents.size(); i++){
										Event temp = listOfEvents.get(i);
										Route rout = temp.getRoute();
										int eventID = temp.getID();
										String from = rout.getFromPlace();
										String to = rout.getToPlace();
										int eventOwnerID = temp.getUserID();
										UserParseInfo userParse = new UserParseInfo(source);
										User postOwner = userParse.getUserByID(eventOwnerID);
			%>
			<div id="column">
				<ul id="latestnews">
					<strong><a style="font-size:17px; "href="Event.jsp?id=<%=eventID%>"><%= from%> <i class="fa fa-arrow-right fa-spin"></i><%="  " +  to%></a></strong>
					<strong><a href="Profile.jsp?id=<%=eventOwnerID%>"><h4><%=postOwner.getFirstName()+" "%><%=postOwner.getLastName()%></h4></a></strong>
				</ul>
			</div>
			<div class="line"></div>

			<%
				}
								}
			%>
			<!-- End Content -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#registerform').submit(function(e) {
				return false;
			});
			$('#modaltrigger').leanModal({
				top : 5,
				overlay : 0.45,
				closeButton : ".hidemodal"
			});
		});
	</script>

</body>
</html>
