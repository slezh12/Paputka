<!DOCTYPE html>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%><%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="JavaPackage.User"%>
<%@page import="JavaPackage.UserParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaPackage.Comment" %>
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
          <li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
          
        </ul>
      </div>
      <!-- End Menu -->
      <!--Begin Login & Registration -->
      
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
  <%
    String id = request.getParameter("id");
    int EventID = Integer.parseInt(id);
    BasicDataSource source = (BasicDataSource) application.getAttribute("connectionPool");
    EventParseInfo eventParse = new EventParseInfo(source);
    UserParseInfo userParse = new UserParseInfo(source);
    Event e = eventParse.getEventByID(EventID);
    User u = userParse.getUserByID(e.getUserID());
    Route r = e.getRoute();
    ArrayList<Comment> arr = eventParse.getComments(EventID);
    
  %>
  <script>
  function initialize() {
    var mapProp = {
      center : new google.maps.LatLng(42.347485, 43.7),
      zoom : 7,
      mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("googleMap"),
        mapProp);
    var myLatlng = new google.maps.LatLng(<%=r.getFromLongitude() %>,<%=r.getFromLatitude() %>);
    var myLatlng2 = new google.maps.LatLng(<%=r.getToLongitude() %>,<%=r.getToLatitude() %>);
    var marker = new google.maps.Marker({
      position : myLatlng,
      map : map,
    });
    var marker2 = new google.maps.Marker({
      position : myLatlng2,
      map : map,
    });

  }


  google.maps.event.addDomListener(window, 'load', initialize);
  </script>
  <div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
      </html>
      <div class="line"></div>
      <h3>გადასახადი : <%= e.getPrice() %></h3>
      <h3>მძღოლი : <%= u.getFirstName()+ " " + u.getLastName() %></h3>
      <h3><%= r.getFromPlace() + " - " + r.getToPlace() %></h3>
      <div class="line"></div>
      <h2>komentarebi</h2>
      
      <div id="column">
        <ul id="latestnews">
        <% for(int i =0; i<arr.size(); i++){ 
          User temp = userParse.getUserByID(arr.get(i).getUserID());%>
          <li><img src="images/demo/80x80.gif" alt="" />
            <p>
              <strong><a href="#"><%= temp.getFirstName() + " " + temp.getLastName() %></a></strong>
              <%=  arr.get(i).getText() + "  :" + arr.get(i).getDate()%>
            </p></li>
          
        <%} %>
        </ul>
      </div>
      <!-- End Content -->
    </div>
  </div>


</body>
</html>
