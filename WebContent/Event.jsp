<!DOCTYPE html>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="JavaPackage.EventConnection"%>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%><%@page
	import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="JavaPackage.User"%>
<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.EventDate "%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaPackage.Comment"%>
<%@page import="java.sql.Date;"%>

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

<script>
	function CheckAlerts() {
		if ((registerform.comment.value).length == 0) {
			alert("Please enter text");
			return false;
		}

		if ((registerform.comment.value).length > 400) {
			alert("Comment must be between 1-400 symbols");
			return false;
		}

		return true;
	}
</script>

</head>
<body>
	<%
		String id = request.getParameter("id");
			    int EventID = Integer.parseInt(id);
			    BasicDataSource source = (BasicDataSource) application.getAttribute("connectionPool");
			    User Currentuser = (User) session.getAttribute("user");
			    EventParseInfo eventParse = new EventParseInfo(source);
			    UserParseInfo userParse = new UserParseInfo(source);
			    EventConnection ev = new EventConnection(source);
			    Event e = eventParse.getEventByID(EventID);
			    User u = userParse.getUserByID(e.getUserID());
			    Route r = e.getRoute();
			    ArrayList<Comment> arr = eventParse.getComments(EventID);
			    Timestamp dt = eventParse.EventDate(EventID);
			    if(e.getType() && e.getValidation()){
			    	java.util.Date today = new java.util.Date();
			        Timestamp now = new java.sql.Timestamp(today.getTime());
			    	if(dt.getYear() < now.getYear()){
			    		e.setValidation(false);
			    		ev.updateEvent(e.getID(), false);
			    	} else if(dt.getYear() == now.getYear()){
			    		if(dt.getMonth() < now.getMonth()){
			    			e.setValidation(false);
			    			ev.updateEvent(e.getID(), false);
			    		} else if(dt.getMonth() == now.getMonth()){
			    			if(dt.getDay() < now.getDay()){
			    				e.setValidation(false);
			    				ev.updateEvent(e.getID(), false);
			    			} else if(dt.getDay() == now.getDay()){
			    				if(dt.getTime() < now.getTime()){
			    					e.setValidation(false);
			        				ev.updateEvent(e.getID(), false);
			    				}
			    			}
			    		} 
			    	}
			    }
	%>
	<!-- Begin Wrapper -->
	<div id="wrapper">
		<!-- Begin Sidebar -->
		<div id="sidebar">
			<div id="logo">
				<img src="style/images/logo.png" alt="Paputka" />
			</div>
			<!-- Begin Menu -->
			<div id="menu" class="menu-v" style="marign-top: 20px;">
				<ul>
					<li><a href="UserPage.jsp" class="active">Main Page</a></li>
					<%
						if(e.getValidation() && !eventParse.HasRequest(e.getID(), Currentuser.getID()) && (Currentuser.getID()!=e.getUserID())){
					%>
					<li><a href="#registermodal" id="modaltrigger">Send
							 Request</a></li>
					<%
						}
					%>

				</ul>
			</div>
			<%
				if((Currentuser.getID()==e.getUserID()) && e.getValidation()){
			%>
			<form id="validation" action="ValidationServlet" method="post">
				<input type="submit" name="btn"
					style="margin-top: 70px; margin-right: 30px; width: 80px; height: 40px;"
					class="mailnumber" value="Cancel"></input> <input type="hidden"
					name="eventID" value="<%=EventID%>"></input>
			</form>
			<%
				}
			%>
			<!-- End Menu -->
			<!--Begin Login & Registration -->
			<div id="registermodal" style="display: none;">
				<a href="#"><img class="modal_close" src="style/images/close.png"></a>
				<h2 class="txt">Send Request</h2>
				<form action="RequestServlet" id="sendrequest" name="sendrequest"
					method="post">
					<textarea style="resize: none;" class="textfield" rows="3"
						placeholder="Please enter text" name="requesttext"
						tabindex="1"></textarea>
					<br>
					<p>
						<input type="submit" name="sendrequest" id="sendrequest"
							class="flatbtn-blu hidemodal" value="Send Request"
							tabindex="3"></input>
					</p>
					<input type="hidden" name="EventID" value="<%=EventID%>"></input>
				</form>
			</div>
		</div>
		<!-- End Sidebar -->

		<!-- Begin Content -->
		<div id="content">
			<div class="line"></div>
			<html>
<head>
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
<body>

	<script>
		function initialize() {
			var mapProp = {
				center : new google.maps.LatLng(42.347485, 43.7),
				zoom : 7,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);
			var myLatlng = new google.maps.LatLng(
	<%=r.getFromLatitude()%>
		,
	<%=r.getFromLongitude()%>
		);
			var myLatlng2 = new google.maps.LatLng(
	<%=r.getToLatitude()%>
		,
	<%=r.getToLongitude()%>
		);
			var marker = new google.maps.Marker(
					{
						position : myLatlng,
						icon : 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
						map : map,
					});
			var infowindow = new google.maps.InfoWindow({
				content : 'Departure Point'
			});
			infowindow.open(map, marker);

			var marker2 = new google.maps.Marker({
				position : myLatlng2,
				icon : 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
				map : map,
			});

			var infowindow = new google.maps.InfoWindow({
				content : 'Destination'
			});
			infowindow.open(map, marker2);
		}

		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	<div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
			</html>
			<div class="line"></div>
			<%
				if (e.getValidation()) {
			%>
			<h2 style="color: #4D6DC1">
				Active Trip</2>
				<%
				}else{
			%>
				<h2 style="color: #4D6DC1">Cancelled Trip</h2>
				<%
					}
				%>
				<h3>
					Fee(GEL) :
					<%=e.getPrice()%></h3>
				<h3>
					Number Of Places :
					<%=e.getPlaces()%></h3>
				<h3>
					Number Of Free Places :
					<%=e.getPlaces()-ev.getParticipantsByEventID(EventID)%></h3>
				<%
					ev.CloseConnection();
				%>
				<h3>
					Owner : <a href="Profile.jsp?id=<%=u.getID()%>"><%=u.getFirstName()+ " " + u.getLastName()%></a>
				</h3>

				<h3><%=r.getFromPlace() + " "%><i
						class="fa fa-arrow-right fa-spin"></i><%=" " + r.getToPlace()%></h3>
				<%
					if(e.getType()){
				%>
				<h3>
					Date
					<%=dt%></h3>
				<%
					} else {
												    	 ArrayList<EventDate> ar = eventParse.EveryDayDates(EventID);
												    	 for(int i = 0; i<ar.size(); i++){
												    		 EventDate d = ar.get(i);
												    		 switch(d.getDay()){
												    		 case 0:
				%>
				<h3>
					Monday -
					<%=d.getDate()%></h3>
				<%
					break; 
												    		 case 1:
				%>
				<h3>
					Tuesday -
					<%=d.getDate()%></h3>
				<%
					break; 
												    		 case 2:
				%>
				<h3>
					Wednesday -
					<%=d.getDate()%></h3>
				<%
					break; 
												    		 case 3:
				%>
				<h3>
					Thursday -
					<%=d.getDate()%></h3>
				<%
					break;
												    		 case 4:
				%>
				<h3>
					Friday -
					<%=d.getDate()%></h3>
				<%
					break; 
												    		 case 5:
				%>
				<h3>
					Saturday -
					<%=d.getDate()%></h3>
				<%
					break;
												    		 case 6:
				%>
				<h3>
					Sunday -
					<%=d.getDate()%></h3>
				<%
					break; 
												    			
												    		 }
				%>
				<%
					}
												    }
				%>
				<div class="line"></div>
				<h2>Comments</h2>

				<div id="column">
					<ul id="latestnews">
						<%
							for(int i =0; i<arr.size(); i++){ 
																						          User temp = userParse.getUserByID(arr.get(i).getUserID());
						%>
						<li>
							<%if(userParse.selectFromAvatars(temp.getID()).equals("")){ %>
							<center><img style="width:75px; height:75px;"src="style/images/images.jpg">	</center>				
						<%}else{ %>
						<center><img style="width:75px; height:75px;"src="UploadDownloadFileServlet?fileName=<%=userParse.selectFromAvatars(temp.getID())%>">	</center>				
						<%} %>
							<p>
								<strong><a href="Profile.jsp?id=<%=temp.getID()%>"><%=temp.getFirstName() + " " + temp.getLastName()%></a></strong>
								<%=arr.get(i).getText()%>
							</p> <br>
							<p>
								Date:
								<%=arr.get(i).getDate()%></p></li>

						<%
							}
						%>
						<li>
							<H2>Write a Comment</H2>
							<form action="CommentServlet" method="post" id="registerform"
								name="registerform">
								<h4>Comment must be between 1-400 symbols</h4>
								<textarea style="resize: none;" class="textfield" rows="3"
									placeholder="Please enter text" name="comment"
									tabindex="1"></textarea>
								<br>
								<p>
									<input type="submit" name="create" id="registerbtn"
										class="flatbtn-blu hidemodal" value="Add Comment"
										tabindex="3" onClick="return CheckAlerts();"></input>
								</p>
								<input type="hidden" name="eventID" value="<%=EventID%>"></input>
							</form>
						</li>
					</ul>
				</div>
				<!-- End Content -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {

			$('#modaltrigger').leanModal({
				top : 5,
				overlay : 0.45,
				closeButton : ".modal_close"
			});
		});
		$(document).ready(function() {
			$('.timepicker').timepicker();
		});
	</script>

</body>
</html>
