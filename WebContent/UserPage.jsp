<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="JavaPackage.User"%>
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
				<% 
				User user = (User) session.getAttribute("user");
				String name = user.getFirstName();
				String lastname = user.getLastName();
				%>
				<h3 id="welcomeUser">Welcome, <%= name+" "+lastname %> </h3> 
				<div id="Usermenu" class="menu-v" style="marign-top: 20px;">
				  <ul>
				  	<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
				  	<li>
						<a href="#">პროფილი</a>
					</li>
					<li>
						<a href="ChangePrivateInfo.jsp">პროფილის შეცვლა</a>
					</li>
					<li>
						<a href="CreateEvent.jsp">წავიყვან</a>
					</li>
					<li>
						<a href="CreateWantToGo.jsp">წასვლა მინდა</a>
					</li>
					<li>
						<a href="MyRequests.jsp">ჩემი მოთხოვნები</a>
					</li>
					<li>
						<a href="OthersRequests.jsp">სხვისი მოთხოვნები</a>
					</li>
					<li>
						<a href="#">ჩემივონთთუგოუ</a>
					</li>
					<li>
						<a href="index.html" >გამოსვლა </a>
					</li>

				  </ul>
				</div>
				<!-- End Menu -->
			</div>
			<!-- End Sidebar -->
			<div id="column">
				<ul id="latestnews">
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">didi posti kokos pageze</a></strong>
							kokoooo
						</p></li>
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">POSTIIIIIIIIIIIIIIIII</a></strong>
							didi posti
						</p></li>
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">posti</a></strong>
							ok
						</p></li>
					<li><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">bikentia</a></strong>
							chaartkit dzmakacobis xelebi
						</p></li>
					<li class="last"><img src="images/demo/80x80.gif" alt="" />
						<p>
							<strong><a href="#">gamcie dzma</a></strong>
							ok
						</p></li>
				</ul>
			</div>
			<!-- Begin Content -->
			<div id="content">
				<div class="line"></div>
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
