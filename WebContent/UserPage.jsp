<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Paputka</title>
		<link rel="shortcut icon" type="image/x-icon" href="style/images/favicon.png" />
		<link rel="stylesheet" type="text/css" href="style.css" />
		
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
				<div id="menu" class="menu-v">
				  <ul>
					<li>
						<a href="UserPage.jsp" class="active">მთავარი გვერდი</a>
					</li>
				  </ul>
				</div>
				<!-- End Menu -->
				<!--Begin Login & Registration -->
				
				<h3 id="welcome"> Welcome, <%= request.getParameter("name") %> </h3>
				<div id="user">
					<a href="#"><b>მიმართულების დამატება</b></a>
					<br>
					<a href="#">პროფილი</a>
					<br>
					<a href="index.html" >გამოსვლა </a>
				</div>
				<div class="sidebox">
					<ul class="share">
						<li><a id="facebook" href="#"><img src="style/images/fb.png" alt="Facebook" /></a></li>
						<li><a id="google" href="#"><img src="style/images/g+.png"  alt="G+" /></a></li>
					</ul>
				</div>
			</div>
			<!-- End Sidebar -->
			
			<!-- Begin Content -->
			<div id="content">
				<div class="line"></div>
				<html>
					<head>
						<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
						<script>
						function initialize()
						{
						var mapProp = {
						  center:new google.maps.LatLng(42.347485,43.7),
						  zoom:7,
						  mapTypeId:google.maps.MapTypeId.ROADMAP
						  };
						var map=new google.maps.Map(document.getElementById("googleMap")
						  ,mapProp);
						}

						google.maps.event.addDomListener(window, 'load', initialize);
						</script>
					</head>
					<body>
						<div id="googleMap" style="width:800px;height:400px;"></div>
					</body>
				</html>
				<div class="line"></div>
				<div id="footer" >
					<h3>ჩვენ შესახებ</h3>
					<p>პაპუტკა.ჯი საიტი აკეთებს რაღაცეებს და შექმნილია იმისთვის რომ ბლა .</p> 
					<p>
						  <span class="lite1">ტელ:</span> +555 636 646 62
						  <span class="lite1">მეილი:</span> name@domain.com
					</p>
				</div>
				<!-- End Content -->
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