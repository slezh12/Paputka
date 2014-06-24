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
		<link
    href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
    rel="stylesheet" />
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
				
				int userID = Integer.parseInt((String)request.getParameter("id"));
				
				BasicDataSource source = (BasicDataSource) application
						.getAttribute("connectionPool");
				User current = (User) session.getAttribute("user");
				ParseInfo parseInfo = new ParseInfo(source);
				
				
				int userIDGuest = current.getID();
				
				UserParseInfo userParse = new UserParseInfo(source);
				User user = userParse.getUserByID(userID);
				String statuses = parseInfo.getInfoAboutStatuses(userID);
				
				EventParseInfo events = new EventParseInfo(source);
				ArrayList<Event> list = events.getUsersEvents(userID);

				
				%>
				<h3 id="welcomeUser"><%= user.getFirstName()+" "+user.getLastName() %> </h3> 
				<img src="style/null/<%=userParse.selectFromAvatars(user.getID())%>">				
			
				<div id="menu" class="menu-v">
					<ul>
						<li><a href="UserPage.jsp" class="active">Main Page</a></li>
					</ul>
				</div>
				<div id="UserProfile">
						<strong style="color:#6AA86B; float:left;">Date of birth:</strong>  <%= user.getBirthdate() %>  
					<br><br>
						<div style="float:left" > <strong style="color:#6AA86B"> Gender:</strong> 
						</div>		
						<% if (user.getGender()){%>
							<img  style="margin-left:50px; " src="style/images/male.png"/>
						<% }else{ %>
							<img  style="margin-left: 50px; " src="style/images/female.png"/>
						<%}%>
						<div style="float:left;" ><strong style="float:left; color:#6AA86B;"> Status:</strong>  <%= statuses  %></div>
					
					<div style="float:left; margin-top:20px; margin-bottom:30px;" >
					<% 
					Integer rating = userParse.getRating(userID);
					if (rating != null) {
					%>
					<strong style=" color:#6AA86B; margin-right:40px;">	User Rating</strong>				
							<form id="ratingsForm1">
								<div class="stars" style="margin-right:35px;">
									<%
										for (int i = 1; i <= userParse.getRating(userID); i++) {
									%>
									<input type="radio" name="star" class="star-<%=i %>" id="sta<%=i %>" checked/>
									<label class="star-<%=i %>" for="star-<%=i %>"></label>  
										<%} %>
									<span></span>
								</div>
							</form>
						<%} else { %>
						<div style="margin-left:70px;">
						<strong style=" color:#6AA86B">Not Yet Rated</strong>
						</div>
						<%}%>
					</div>
					<br>
					<% if (userParse.canRate(userIDGuest, userID)){%>
						<strong style="color:#6AA86B">Rate User</strong>
							<form id="ratingsForm" action="RatingServlet" method="post">
								<div style="margin-left:50px;"class="stars">
									<input type="radio" name="star" class="star-1" id="star-1" value="1" /> 
									<label class="star-1" for="star-1"></label>
					 				<input type="radio" name="star" class="star-2" id="star-2" value="2" />
									<label class="star-2" for="star-2"></label>
									<input type="radio" name="star" class="star-3" id="star-3" value="3" />
									<label class="star-3" for="star-3"></label>
									<input type="radio" name="star" class="star-4" id="star-4" value="4" />
									<label class="star-4" for="star-4"></label>
									<input type="radio" name="star" class="star-5" id="star-5" value="5" />
							 		<label class="star-5" for="star-5"></label>   
									<span></span>
								</div> 
								<input type="submit" name="btn" class="mailnumber" value="SEND" ></input>
								<input type="hidden" name="userID" value="<%=userID%>" >
							</form>
					<%}%>
				</div>	
				<!-- End Menu -->
			</div>
			<!-- End Sidebar -->
		<!-- Begin Content -->
			<div id="content">
				<h2 style="color:#8693EE"><strong><%=current.getFirstName()%>'s posts</strong></h2>	
				<div class="line"></div>
		<!-- End Content -->
		<%for (int i = 0; i < list.size(); i++) {
			Event temp = list.get(i);
			
		%>	
			<strong><a style="font-size:17px;"href="Event.jsp?id=<%=temp.getID()%>"><%= temp.getRoute().getFromPlace()%> <i class="fa fa-arrow-right fa-spin"></i><%="  " +  temp.getRoute().getToPlace()%></a></strong>
			
		<div class="line"></div>	
		<% } %>
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
