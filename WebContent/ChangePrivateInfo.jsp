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


<script>function CheckAlerts() {
		if ((registerform.about.value).length > 200) {
			alert("About Me should not be more than 200 symbols");
			return false;
		}
		if ((registerform.tel.value).length > 25) {
			alert("Tel should not be more than 25 symbols");
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
					<li><a href="UserPage.jsp" class="active">Main Page</a></li>
				</ul>
			</div>
			<!-- End Menu -->
		</div>
		<!-- End Sidebar -->
		<!-- Begin Content -->
		<div id="content">
				<h2 style="color:#8693EE"><strong>Change Profile Settings</strong></h2>				
				<div class="line"></div>
					<form action="ChangeProfileServlet" id="registerform" name="registerform"
						method="post">
						<textarea style="resize:none;" class="textfield" rows="3" placeholder="About Me" name="about" tabindex="1"></textarea>
						<input type="text" name="tel" placeholder="Tel" class="textfield" tabindex="2"></input>
						<p>
						<input type="submit" name="create" id="registerbtn"
							class="flatbtn-blu hidemodal" value="SUBMIT" tabindex="3"
							onClick="return CheckAlerts();"></input>
						</p>
					</form>
					<form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
						<b>Select Avatar to Upload:</b><input type="file" name="fileName">
					<input type="submit" class="flatbtn-blu hidemodal" value="Upload">
					</form>
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