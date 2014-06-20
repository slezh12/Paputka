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
			<li style="color: #fff;">
				სათაური: <br><%=title%></li>

			<%
				if (type) {
			%>
			<li style="color: #fff;">ერთჯერადი</li>
			<%
				ArrayList<Timestamp> list = parse.getOnce(wantToGoID);
			%>
			<li style="color: #fff;">
				დასაწყისი:<%=list.get(0)%><br>
				დასასრული:<%=list.get(1)%></li>
			<%
				} else {
					ArrayList<WantToGoForEveryDay> list = parse
							.getEveryDay(wantToGoID);
			%>
			<li style="color: #fff;">განმეორებადი</li>
			<%
				for (int i = 0; i < list.size(); i++) {
						WantToGoForEveryDay temp = list.get(i);
						int day = temp.getDay();
						Time start = temp.getStart();
						Time finish = temp.getFinish();
						if (day == 0) {
			%>
			<li style="color: #fff;">
				ორშაბათი:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				} else if (day == 1) {
			%>
			<li style="color: #fff;">
				სამშაბათი:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				} else if (day == 2) {
			%>
			<li style="color: #fff;">
				ოთხშაბათი:<br>
				<%=start%> -
				<%=finish%>
			</li>

			<%
				} else if (day == 3) {
			%>
			<li style="color: #fff;">
				ხუთშაბათი:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				} else if (day == 4) {
			%>
			<li style="color: #fff;">
				პარასკევი:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				} else if (day == 5) {
			%>
			<li style="color: #fff;">
				შაბათი:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				} else if (day == 6) {
			%>
			<li style="color: #fff;">
				კვირა:<br>
				<%=start%> -
				<%=finish%>
			</li>
			<%
				}
					}
				}
			%>
				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
			<h2>ძებნის შედეგები</h2>
			<script
    src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
			<script>
			var directionsDisplay;
			var directionsService = new google.maps.DirectionsService();
			var dD;
    		var map;    		
            var waypts = [];
            var optimal;
            var current;
            var a = new google.maps.LatLng(<%=want.getFromLatitude() %>,<%= want.getFromLongitude()%>);
            var b = new google.maps.LatLng(<%=want.getToLatitude()%>,<%=want.getToLongitude() %>);
    		function initialize() {
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
        		calcRoute();
        	}
    		
            <%
            EventParseInfo ep = new EventParseInfo(source);
            ArrayList<Event> arr = ep.getEvents();
            ArrayList<Event> toShow = new ArrayList<Event>();
            //for(int i = 0; i<arr.size(); i++) {
                Route r = arr.get(5).getRoute();    
            %>
            var start = new google.maps.LatLng(<%=r.getFromLatitude() %>,<%= r.getFromLongitude()%>);
            var end = new google.maps.LatLng(<%=r.getToLatitude()%>,<%=r.getToLongitude() %>);
            function calcRoute() {
                var request = {
                	origin: start,
              	    destination: end,
              	  	waypoints: waypts,
              	    travelMode: google.maps.TravelMode.DRIVING      
                };
                directionsService.route(request, function(response, status) {
          	    	if (status == google.maps.DirectionsStatus.OK) {
          	      		directionsDisplay.setDirections(response);
          	      		var route = response.routes[0];
          	      		var distance = route.distance.text;
          	      		document.getElementById("demo").innerHTML = "Paragraph changed.";
          	    } 
      	  	});   
                request = {
                    	origin: start,
                  	    destination: end,
                  	    travelMode: google.maps.TravelMode.DRIVING      
                    };
                directionsService.route(request, function(response, status) {
          	    	if (status == google.maps.DirectionsStatus.OK) {
          	      		dD.setDirections(response);
          	      		var route = response.routes[0];
          	      		var distance = route.distance;
          	    } 
      	  	});   
          	  
          	} 
                
            <%//} %>
        
            google.maps.event.addDomListener(window, 'load', initialize);
</script>
			<div class="line"></div>
			<p id="demo">My First Paragraph</p>
			<div id="googleMap" style="width: 800px; height: 400px;"></div>
			<div class="line"></div>

			<!-- End Content -->
		</div>
	</div>
</body>
</html>