<!DOCTYPE html>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.Time"%>
<%@page import="JavaPackage.WantToGoForEveryDay"%>
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
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
			<h2>ჩემი მინდა წასვლა</h2>
			<div class="line"></div>
			<%
				String id = request.getParameter("id");
				User current = (User) session.getAttribute("user");
				int userID = current.getID();
				int wantToGoID = Integer.parseInt(id);
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				WantToGoParseInfo parse = new WantToGoParseInfo(source);
				WantToGo want = parse.getWantToGo(wantToGoID, userID);
				String title = want.getTitle();
				boolean type = want.getType();
			%>
			<h2>
				<%=title%></h2>

			<%
				if (type) {
			%>
			<h2>ერთჯერადი</h2>
			<%
				ArrayList<Timestamp> list = parse.getOnce(wantToGoID);
			%>
			<h2>
				დასაწყისი:<%=list.get(0)%>
				დასასრული:<%=list.get(1)%></h2>
			<%
				} else {
					ArrayList<WantToGoForEveryDay> list = parse
							.getEveryDay(wantToGoID);
			%>
			<h2>განმეორებადი</h2>
			<%
				for (int i = 0; i < list.size(); i++) {
						WantToGoForEveryDay temp = list.get(i);
						int day = temp.getDay();
						Time start = temp.getStart();
						Time finish = temp.getFinish();
						if (day == 0) {
			%>
			<h4>
				ორშაბათი:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				} else if (day == 1) {
			%>
			<h4>
				სამშაბათი:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				} else if (day == 2) {
			%>
			<h4>
				ოთხშაბათი:
				<%=start%>
				<%=finish%>
			</h4>

			<%
				} else if (day == 3) {
			%>
			<h4>
				ხუთშაბათი:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				} else if (day == 4) {
			%>
			<h4>
				პარასკევი:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				} else if (day == 5) {
			%>
			<h4>
				შაბათი:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				} else if (day == 6) {
			%>
			<h4>
				კვირა:
				<%=start%>
				<%=finish%>
			</h4>
			<%
				}
					}
				}
			%>

			<div class="line"></div>

			<!-- End Content -->
		</div>
	</div>
</body>
</html>