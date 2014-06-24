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
					<li><a href="UserPage.jsp" class="active">Main Page</a></li>
				</ul>
			</div>
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
			<div id="wanttogoinfo" >
			<strong style="color: #6AA86B; ">
				Title:</strong> <%=title%>
				<br>
			<%
				if (type) {
			%>
			<strong style="color: #6AA86B; ">Type: </strong>One Way
			<br>
			<%
				ArrayList<Timestamp> list = parse.getOnce(wantToGoID);
			%>
			<strong style="color: #6AA86B; ">
				Start Time:</strong><%=list.get(0)%><br>
				<strong style="color: #6AA86B; ">End Time:</strong><%=list.get(1)%>
			<%
				} else {
					ArrayList<WantToGoForEveryDay> list = parse
							.getEveryDay(wantToGoID);
			%>
			<strong style="color: #6AA86B; ">Type: </strong>Daily
							<%
				for (int i = 0; i < list.size(); i++) {
						WantToGoForEveryDay temp = list.get(i);
						int day = temp.getDay();
						Time start = temp.getStart();
						Time finish = temp.getFinish();
				if (day == 0) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Monday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				} else if (day == 1) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Tuesday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				} else if (day == 2) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Wednesday:</strong><br>
				<%=start%> -
				<%=finish%>

			<%
				} else if (day == 3) {
			%>
			<br>			
			<strong style="color: #6AA86B; ">
				Thursday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				} else if (day == 4) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Friday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				} else if (day == 5) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Saturday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				} else if (day == 6) {
			%>
			<br>
			<strong style="color: #6AA86B; ">
				Sunday:</strong><br>
				<%=start%> -
				<%=finish%>
			<%
				}
					}
				}
			%>
			</div>
			<form id="delete" action="DeleteWantToGoServlet" method="post">
				<input type="submit" name="btn"
					style="margin-top: 70px; margin-right: 30px; width: 80px; height: 40px;"
					class="mailnumber" value="Delete"></input> <input type="hidden"
					name="wantToGoID" value="<%=want.getID()%>"></input>
					<input type="hidden"
					name="type" value="<%=want.getType()%>"></input>
			</form>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
			<h2 style="color:#8693EE"><strong>Search Results</strong></h2>
			<div class="line"></div>
			<%if (want.getValidation()) { %>
			<script
    src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
			<script>
			var directionsDisplay;
			var directionsService = new google.maps.DirectionsService();
			var dD;
    		var map;    		
            var waypts = [];
            var arr = [];
            var summaryPanel;
    		function initialize() {
    			var a = new google.maps.LatLng(<%=want.getFromLatitude() %>,<%= want.getFromLongitude()%>);
                var b = new google.maps.LatLng(<%=want.getToLatitude()%>,<%=want.getToLongitude() %>);
                waypts.push({
                    location:a,
                    stopover:true
                });
                waypts.push({
                    location:b,
                    stopover:true
                });
    			directionsDisplay = new google.maps.DirectionsRenderer();
    			dD = new google.maps.DirectionsRenderer();
        		var mapProp = {
        	            center : new google.maps.LatLng(42.347485, 43.7),
        	            zoom : 7,
        	            mapTypeId : google.maps.MapTypeId.ROADMAP
        	    };
        		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
        		directionsDisplay.setMap(map);
        		dD.setMap(map);
        	}
    		
            <%
            EventParseInfo ep = new EventParseInfo(source);
            ArrayList<Event> arr = ep.getEventsForSearch(want);
            for(int i = 0; i<arr.size(); i++) {
            	Event e = arr.get(i);
            	if (e.getValidation()) {
                	Route r = e.getRoute();  
                	String p1 = r.getFromPlace();
                	String p2 = r.getToPlace();
            %>           
            function calcRoute<%=i %>(){
            	var optimal = 0;
                var current = 0;
                var start = new google.maps.LatLng(<%=r.getFromLatitude() %>,<%= r.getFromLongitude()%>);
                var end = new google.maps.LatLng(<%=r.getToLatitude()%>,<%=r.getToLongitude() %>);
                var request = {
                	origin: start,
              	    destination: end,
              	  	waypoints: waypts,
              	    travelMode: google.maps.TravelMode.DRIVING ,   
              	  	unitSystem: google.maps.UnitSystem.METRIC
                };                
                directionsService.route(request, function(response, status) {
          	    	if (status == google.maps.DirectionsStatus.OK) {
          	      		directionsDisplay.setDirections(response);
          	      		var route = response.routes[0];          	      		
          	      		for (var i = 0; i < route.legs.length; i++) {
                        	current+=route.legs[i].distance.value;                         	
                   		}          	      	
          	    	} 
      	  		});               
                request = {
                    	origin: start,
                  	    destination: end,
                  	    travelMode: google.maps.TravelMode.DRIVING,  
                  	  	unitSystem: google.maps.UnitSystem.METRIC
                };
                directionsService.route(request, function(response, status) {
          	    	if (status == google.maps.DirectionsStatus.OK) {
          	      		dD.setDirections(response);
          	      		var route = response.routes[0];
          	      		for (var i = 0; i < route.legs.length; i++) {
                        	optimal+=route.legs[i].distance.value;                         	
                   		}
          	      		optimal = optimal*1.15;           	      	
                    	if (current < optimal) {                    		
                       		summaryPanel = document.getElementById("div"+<%=i %>);
                    summaryPanel.innerHTML= '<strong> <a id="link<%=i %>" href="Event.jsp?id=<%=e.getID()%>"><%=p1%> <i class="fa fa-arrow-right fa-spin"></i> <%=p2%></a></strong><div class="line"></div></div>';
                    	} else {
                    		summaryPanel = document.getElementById("div"+<%=i %>);
                    		summaryPanel.innerHTML='';
                    	}
                    	
          	    	} 
      	  		});
                
          	}
            </script>
            <div id="div<%=i %>">
            </div>
            <script>
            calcRoute<%=i %>();
                
            <%
            			} 
            	}
            %>
            
            google.maps.event.addDomListener(window, 'load', initialize);
			</script>
<%				
			}
%>
			<div id="googleMap" style="width: 800px; height: 400px; visibility:hidden;"></div>

			<!-- End Content -->
		</div>
	</div>
</body>
</html>