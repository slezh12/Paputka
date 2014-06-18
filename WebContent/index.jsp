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
<%@page import="JavaPackage.Comment" %>
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
 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<script type="text/javascript" src="style/js/jquery.leanModal.min.js"></script>

 <link rel="stylesheet" type="text/css" href="style/js/DateTimePicker.css" />
<script type="text/javascript" src="style/js/DateTimePicker.js"></script>


<!-- This script is for Google + Login -->

<script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
<script type="text/javascript">
  function loginFinishedCallback(authResult) {
    if (authResult) {
      if (authResult['error'] == undefined){
	     gapi.client.load('plus', 'v1', function() {
            var request = gapi.client.plus.people.get({
                'userId': 'me'
            });
            request.execute(function(response) {
            	document.getElementById('firstname').value = response['name'].familyName;
            	document.getElementById('lastname').value = response['name'].givenName;
            	if(response['gender']=='male'){
            	   document.getElementById('Male').checked = true;
            	}
            	if(response['gender']=='female'){
            		document.getElementById('Female').checked = true;
            	}
            	document.getElementById('mail').value = response['emails'][0].value;
            	document.getElementById('datetime').value = response['birthday'];
            	document.getElementById('password').value = 'Google';
            	document.getElementById('registerform').action = "GoogleServlet"; 
            	document.forms["registerform"].submit();
            });
        });
      } else {
      	window.location.href = 'InvalidLogin.jsp';
        console.log('An error occurred');
      }
    } else {
      window.location.href = 'InvalidLogin.jsp';
      console.log('Empty authResult');  // Something went wrong
    }
  }
  </script>

<!-- This script is for Facebook Login -->
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
     	ParseInfoGoToServlet();
    } else if (response.status === 'not_authorized') {
    } else {
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
  FB.init({
    appId      : '454173334719658',
    cookie     : true,  // enable cookies to allow the server to access 
                        // the session
    xfbml      : true,  // parse social plugins on this page
    version    : 'v2.0' // use version 2.0
  });
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
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

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function ParseInfoGoToServlet() {
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
   // 	document.forms["registerform"].submit();
    });
  }
</script>
<!-- For picking date from Calendar -->
<script>
		 $(document).ready(function() {
				$("#datepicker").DateTimePicker();
		 });
</script>
    
<script type="text/javascript"> 
	function CheckAlerts(){
		if((registerform.firstname.value).length == 0 || (registerform.firstname.value).length >20){
			alert("სახელი უნდა იყოს 1-დან 20 სიმბოლომდე ზომის");
			return false;
		}
		if((registerform.lastname.value).length==0 || (registerform.lastname.value).length >20){
			alert("გვარი უნდა იყოს 1-დან 20 სიმბოლომდე ზომის");
			return false;
		}
		if((registerform.mail.value).length==0 || (registerform.mail.value).length >40 ){
			alert("ელ-ფოსტა უნდა იყოს 1-დან 40 სიმბოლომდე ზომის");
			return false;
		}
		if((registerform.datetime.value).length==0){
			alert("შეიყვანეთ დაბადების დღის თარიღი");
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
			alert("აირჩიეთ სქესი");
			return false;
		}
		if((registerform.password.value).length < 6 || (registerform.password.value).length >20){
			alert("პაროლი უნდა იყოს 6 სიმბოლომდე ზომის");
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
					<li><a href="index.jsp" class="active">მთავარი გვერდი</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->

			<h3 id="welcome">Welcome Guest</h3>
			<form action="LoginServlet" method="post">
				<input type="text" name="name" placeholder="ელ-ფოსტა"></input> <br />
				<input type="password" name="pass" placeholder="პაროლი"></input> <br />
				<input type="submit" class="login" value="Login"> </input> <a
					class="register" href="#registermodal" id="modaltrigger">Register</a>
			</form>
			<div id="registermodal" style="display: none;">
				<h1 class="txt">ახალი მომხმარებლის რეგისტრაცია</h1>
				<h4>
					<b>გთხოვთ შეავსოთ ყველა ველი</b>
				</h4>
				<form action="RegisterServlet" id="registerform" name="registerform"
					method="post">
					<div class="txtfield">
						<span id="icons" class="fa fa-user"></span> <input type="text" id="firstname"
							name="firstname" placeholder="სახელი" class="textfield"
							tabindex="1"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-users"></span> <input type="text" id="lastname"
							name="lastname" placeholder="გვარი" class="textfield"
							tabindex="2"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-envelope-o"></span> <input id="mail"
							type="text" name="mail" placeholder="ელ-ფოსტა" class="textfield"
							tabindex="3"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-calendar"></span> <input id="datetime"
							type="text" data-field='date' name="datetime" placeholder="დაბადების თარიღი" class="textfield"
							tabindex="4"></input>
					</div>	
					<div id="datepicker"></div>	
					<div class="txtfield">
						
						<img style="float:left; margin-left:330px;" src="style/images/male.png" alt="Male" />
							<input type="radio" class="radio1" name="gender" id="Male" value="Male" tabindex="5""></input>
						<img style="float:left; " src="style/images/female.png"/>
							<input type="radio" class="radio2" name="gender" id="Female" value="Female" tabindex="6"></input>
					</div>
					<div class="txtfield">
						<span id="icons" class="fa fa-lock"></span> <input type="password" id="password"
							name="password" placeholder="პაროლი" class="textfield"
							tabindex="7"></input>
					</div>
					<input type="submit" name="registerbtn" id="registerbtn"
						class="flatbtn-blu hidemodal" value="რეგისტრაცია" tabindex="8" onClick="return CheckAlerts();"></input>
				</form>
			</div>
			<div class="sidebox">
				<ul class="share">
						<div style="float:left;">
							<fb:login-button scope="public_profile,email,user_birthday" onlogin="checkLoginState();" size="large">
							</fb:login-button>
						</div>
						<br><br>
					 <div style="float:left; margin-left:5px;"id="signin-button" class="show">
					     <div class="g-signin"
					      data-callback="loginFinishedCallback"
					      data-approvalprompt="force"
					      data-clientid="1057790320383-t8pqial3gdd7l6arvojtq0qdufm62adt.apps.googleusercontent.com"
					      data-scope="https://www.googleapis.com/auth/plus.login"
					      data-height="standard"
					      data-cookiepolicy="single_host_origin"
					      >
					    </div>
					    <!-- In most cases, you don't want to use approvalprompt=force. Specified
					    here to facilitate the demo.-->
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
		<%
	    	BasicDataSource source = (BasicDataSource) application.getAttribute("connectionPool");
			EventParseInfo parse = new EventParseInfo(source);
			ArrayList<Event> arr = parse.getEvents();
			for(int i = 0; i<arr.size(); i++){
				Route r = arr.get(i).getRoute();
		%>
		 var myLatlng = new google.maps.LatLng(<%=r.getFromLatitude() %>,<%= r.getFromLongitude()%>);
		    var myLatlng2 = new google.maps.LatLng(<%=r.getToLatitude()%>,<%=r.getToLongitude() %>);
		    var marker = new google.maps.Marker({
		      position : myLatlng,
		      map : map,
		    });
		    var marker2 = new google.maps.Marker({
		      position : myLatlng2,
		      map : map,
		    });
		<%} %>
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
				<h3>ჩვენ შესახებ</h3>
				<p>პაპუტკა.ჯი საიტი აკეთებს რაღაცეებს და შექმნილია იმისთვის რომ
					ბლა .</p>
				<p>
					<span class="lite1">ტელ:</span> +555 636 646 62 <span class="lite1">მეილი:</span>
					name@domain.com
				</p>
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
