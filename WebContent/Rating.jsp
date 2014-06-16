<!DOCTYPE html>
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
			<form id="ratingsForm">
				<div class="stars">
					<input type="radio" name="star" class="star-1" id="star-1" />
					<label class="star-1" for="star-1"></label>
					<input type="radio" name="star" class="star-2" id="star-2" />
					<label class="star-2" for="star-2"></label>
					<input type="radio" name="star" class="star-3" id="star-3" />
					<label class="star-3" for="star-3"></label>
					<input type="radio" name="star" class="star-4" id="star-4" />
					<label class="star-4" for="star-4"></label>
					<input type="radio" name="star" class="star-5" id="star-5" />
					<label class="star-5" for="star-5"></label>
					<span></span>
				</div>
			</form>
		</div>

</body>
</html>