<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@page import="JavaPackage.Route"%>
<%@page import="JavaPackage.Event"%>
<%@page import="JavaPackage.EventParseInfo"%>
<%@page import="JavaPackage.Request"%>
<%@page import="JavaPackage.ParseInfo"%>
<%@page import="JavaPackage.Route"%>
<%@page import="java.util.ArrayList"%>
<%@page import="JavaPackage.Comment"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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


<script type="text/javascript" src="style/js/jquery.leanModal.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="style/js/DateTimePicker.css" />
<script type="text/javascript" src="style/js/DateTimePicker.js"></script>


<!-- This script is for Google + Login -->

<script src="https://apis.google.com/js/client:plusone.js"
	type="text/javascript"></script>
<script type="text/javascript">
function render(){
    gapi.signin.render("myButton", {
          'callback': loginFinishedCallback,
          'clientid': '1057790320383-t8pqial3gdd7l6arvojtq0qdufm62adt.apps.googleusercontent.com',
          'cookiepolicy': 'single_host_origin',
          'requestvisibleactions': 'http://schemas.google.com/AddActivity',
          'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email'
        });
}
function loginFinishedCallback(authResult) {
    if (authResult) {
      if (authResult['error'] == undefined){
         gapi.client.load('oauth2', 'v2', function() {
          gapi.client.oauth2.userinfo.get().execute(function(response) {
              document.getElementById('mail').value = response.email;
          })
       	});
         gapi.client.load('plus', 'v1', function() {
            var request = gapi.client.plus.people.get({
                'userId': 'me'
            });
            request.execute(function(response) {
                document.getElementById('firstname').value = response['name'].givenName;
                document.getElementById('lastname').value = response['name'].familyName;
                if(response['gender']=='male'){
                   document.getElementById('Male').checked = true;
                }
                if(response['gender']=='female'){
                    document.getElementById('Female').checked = true;
                }
                document.getElementById('datetime').value = response['birthday'];
                document.getElementById('password').value = 'Google';
                if(response['birthday']==null){
                	document.getElementById('datetime').value = "";
                }
                document.getElementById('registerform').action = "GoogleServlet";
				if(response['birthday']==null || response['gender']=='other'){
	                document.getElementById('password').readOnly  = true;
	                document.getElementById('firstname').readOnly  = true;
	                document.getElementById('lastname').readOnly  = true;
	                document.getElementById('mail').readOnly  = true;
					if(response['birthday']!=null){
		                document.getElementById('datetime').readOnly = true;
					}
	                document.getElementById('modaltrigger').click();
				}
				if(response['birthday']!=null && response['gender']!='other'){
	                document.forms["registerform"].submit();         
				}   
            });
        });
      } else {
          console.log(authResult);
        console.log('An error occurred');
      }
    } else {
      console.log('Empty authResult');  // Something went wrong
    }
  }
  </script>

<!-- This script is for Facebook Login -->
<script>
  // This is called with the results from from FB.getLoginStatus().
  function fb_login(){
    FB.login(function(response) {
        if (response.authResponse) {
            FB.api('/me', function(response) {
                 document.getElementById('firstname').value = response.first_name;
                document.getElementById('lastname').value = response.last_name;
                if(response.gender =='male'){
                   document.getElementById('Male').checked = true;
                }
                if(response.gender =='female'){
                    document.getElementById('Female').checked = true;
                }
                document.getElementById('mail').value = response.email;
                document.getElementById('datetime').value = response.birthday;
                document.getElementById('password').value = 'Facebook';
                document.getElementById('registerform').action = "FacebookServlet";
                if(response.birthday==null){
                    document.getElementById('datetime').value = "";
                }
				if(response.birthday==null || response.gender ==null){
	                document.getElementById('password').readOnly  = true;
	                document.getElementById('firstname').readOnly  = true;
	                document.getElementById('lastname').readOnly  = true;
	                document.getElementById('mail').readOnly  = true;
					if(response.birthday!=null){
		                document.getElementById('datetime').readOnly = true;
					}
	                document.getElementById('modaltrigger').click();
				}
				if(response.birthday!=null && response.gender!=null){
	                document.forms["registerform"].submit();         
				}   
            });
        } else {
                console.log('User cancelled login or did not fully authorize.');
        }
    }, {
        scope: 'public_profile,email,user_birthday '
    });
}

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '454173334719658',
    cookie     : true,  // enable cookies to allow the server to access
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.0' // use version 2.0
  });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
</script>
<!-- For picking date from Calendar -->
<script>
         $(document).ready(function() {
                $("#datepicker").DateTimePicker();
         });
</script>

<!-- Check Alerts -->
<script type="text/javascript">
    function CheckAlerts(){
        if((registerform.firstname.value).length == 0 || (registerform.firstname.value).length >20){
            alert("Firstname must be between 1-20 symbols");
            return false;
        }
        if((registerform.lastname.value).length==0 || (registerform.lastname.value).length >20){
            alert("Lastname must be between 1-20 symbols");
            return false;
        }
        if((registerform.mail.value).length==0 || (registerform.mail.value).length >40 ){
            alert("E-mail must be between 1-40 symbols");
            return false;
        }
        if((registerform.datetime.value).length==0){
            alert("Enter date of birth");
            return false;
        }
        var radios = document.getElementsByName('gender');
        var Chcount = 0;
        for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                Chcount = 1;
                break;
            }
        }
        if(Chcount==0){
            alert("Choose gender");
            return false;
        }
        if((registerform.password.value).length < 6 || (registerform.password.value).length >20){
            alert("Password must be more than 6 symbols");
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
					<li><a href="index.jsp" class="active">Main Page</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->

			<h3 id="welcome">Welcome Guest</h3>
			<form action="LoginServlet" method="post">
				<input type="text" name="name" placeholder="E-Mail"></input> <br />
				<input type="password" name="pass" placeholder="Password"></input> <br />
				<input type="submit" class="login" value="Login"> </input> <a
					class="register" href="#registermodal" id="modaltrigger">Register</a>
			</form>
			<div id="registermodal" style="display: none;">
			<a href="#"><img class="modal_close" src="style/images/close.png"></a>
				<h1 class="txt">Sign Up</h1>
				<h4>
					<b>Please fill every field</b>
				</h4>
				<form action="RegisterServlet" id="registerform" name="registerform"
					method="post">
					<div class="txtfield">
						<span id="icons" class="fa fa-user"></span> <input type="text"
							id="firstname" name="firstname" placeholder="Firstname"
							class="textfield" tabindex="1"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-users"></span> <input type="text"
							id="lastname" name="lastname" placeholder="Lastname"
							class="textfield" tabindex="2"></input>
					</div>
					
					<div class="txtfield">
						<span id="icons" class="fa fa-envelope-o"></span> <input id="mail"
							type="text" name="mail" placeholder="E-mail" class="textfield"
							tabindex="3"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-calendar"></span> <input
							id="datetime" type="text" data-field='date' name="datetime"
							placeholder="Date of birth" class="textfield" tabindex="4"></input>
					</div>
					<div id="datepicker"></div>
					<div class="txtfield">

						<img style="float: left; margin-left: 330px;"
							src="style/images/male.png" alt="Male" /> <input type="radio"
							class="radio1" name="gender" id="Male" value="Male" tabindex="5""></input>
						<img style="float: left;" src="style/images/female.png" /> <input
							type="radio" class="radio2" name="gender" id="Female"
							value="Female" tabindex="6"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-lock"></span> <input type="password"
							id="password" name="password" placeholder="Password"
							class="textfield" tabindex="7"></input>
					</div>
					<input type="submit" name="registerbtn" id="registerbtn"
						class="flatbtn-blu hidemodal" value="Register" tabindex="8"
						onClick="return CheckAlerts();"></input>
				</form>
			</div>
			<div class="sidebox">
				<ul class="share">
					<div style="float: left;">
						<a href="#" onclick="fb_login();"><img
							src="style/images/fb.png" border="0" alt=""></a>
					</div>
					<br>
					<br>
					<div style="float: left; cursor: pointer; margin-top: 10px;"
						id="gSignInWrapper">
						<div id="myButton" onclick="render();" class="classesToStyleWith">
							<img src="style/images/g+.png">
						</div>
					</div>
				</ul>
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
<script>
    function initialize() {
        var mapProp = {
            center : new google.maps.LatLng(42.347485, 43.7),
            zoom : 7,
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("googleMap"),
                mapProp);
        <%BasicDataSource source = (BasicDataSource) application.getAttribute("connectionPool");
            EventParseInfo parse = new EventParseInfo(source);
            ArrayList<Event> arr = parse.getEvents();
            for(int i = 0; i<arr.size(); i++){
            	if(arr.get(i).getValidation()){
                Route r = arr.get(i).getRoute();%>
         var myLatlng = new google.maps.LatLng(<%=r.getFromLatitude()%>,<%=r.getFromLongitude()%>);
            var myLatlng2 = new google.maps.LatLng(<%=r.getToLatitude()%>,<%=r.getToLongitude()%>);
            var marker = new google.maps.Marker({
              position : myLatlng,
              icon :'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
              title : '<%=r.getFromPlace()%>',
              map : map
            });
            var marker2 = new google.maps.Marker({
              position : myLatlng2,
              icon :'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
              title : '<%=r.getToPlace()%>',
			map : map
		});
<%} 
        }%>
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div id="googleMap" style="width: 800px; height: 400px;"></div>
</body>
			</html>
			<div class="line"></div>
			<div id="footer">
				<h2 style="font-size:30px;color:#8693EE">About Us</h2>
				<p><strong>Paputka.ge is a web-site which focuses on making journeys
					through out the world, making journeys more comfortable and fun.
					It is a great way to meet new people and make new friends.</strong></p>
				<h2>Made By <strong style="color:#BF0E45">DZMEBI</strong> Inc.</h2>
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
	</script>

</body>
</html>