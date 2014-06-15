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
<link rel="stylesheet" type="text/css"
	href="style/js/DateTimePicker.css" />
<script type="text/javascript" src="style/js/DateTimePicker.js"></script>

<script>
	$(document).ready(function() {
		$("#datepicker").DateTimePicker();
	});
</script>

<script type="text/javascript">
	function CheckAlerts() {
		if (check != 2) {
			alert("მონიშნეთ ორი წერტილი რუკაზე");
			return false;
		}
		if ((registerform.from.value).length == 0
				|| (registerform.from.value).length > 30) {
			alert("გასვლის ადგილი უნდა იყოს 1-დან 30 სიმბოლომდე ზომის");
			return false;
		}
		if ((registerform.to.value).length == 0
				|| (registerform.to.value).length > 30) {
			alert("დანიშნულების ადგილი უნდა იყოს 1-დან 30 სიმბოლომდე ზომის");
			return false;
		}
		if((registerform.fee.value).length==0){
			alert("შეიყვანეთ გადასახადი");
			return false;
		}
		if((registerform.places.value).length==0){
			alert("შეიყვანეთ ადგილების რაოდენობა");
			return false;
		}
		var radios = document.getElementsByName('type');
		var Chcount = 0;
		var once = false;
		var everyDay = false;
		for (var i = 0, length = radios.length; i < length; i++) {
			if (radios[i].checked) {
				if (i == 0) {
					everyDay = true;
				} else if (i == 1) {
					once = true;
				}
				Chcount = 1;
				break;
			}
		}
		if (Chcount == 0) {
			alert("აირჩიეთ ყოველდღიური ან ერთჯერადი");
			return false;
		}
		if (once) {
			if (((registerform.date.value).length == 0)
					|| ((registerform.time.value).length == 0)) {
				alert("ერთი ველი მაინც ერთჯერადისთვის ცარიელია");
				return false;
			}
		}
		var array = [];
		var index = 0;
		if (everyDay) {
			var inputElements = document.getElementsByTagName('input');
			for (var i = 0; inputElements[i]; i++) {
				if ((inputElements[i].name == "0" && inputElements[i].checked)
						|| (inputElements[i].name == "1" && inputElements[i].checked)
						|| (inputElements[i].name == "2" && inputElements[i].checked)
						|| (inputElements[i].name == "3" && inputElements[i].checked)
						|| (inputElements[i].name == "4" && inputElements[i].checked)
						|| (inputElements[i].name == "5" && inputElements[i].checked)
						|| (inputElements[i].name == "6" && inputElements[i].checked)) {

					array[index] = inputElements[i].name;
					index++;
				}
			}
			if (index == 0) {
				alert("არც ერთი დღე არ არის მონიშნული");
				return false;
			}
			for (var i = 0; i < index; i++) {
				if (array[i] == "0") {
					if ((registerform.time0.value).length == 0) {
						alert("ორშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "1") {
					if ((registerform.time1.value).length == 0) {
						alert("სამშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "2") {
					if ((registerform.time2.value).length == 0) {
						alert("ოთხშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "3") {
					if ((registerform.time3.value).length == 0) {
						alert("ხუთშაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "4") {
					if ((registerform.time4.value).length == 0) {
						alert("პარასკევი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "5") {
					if ((registerform.time5.value).length == 0) {
						alert("შაბათი დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				} else if (array[i] == "6") {
					if ((registerform.time6.value).length == 0) {
						alert("კვირა დღე მონიშნულია,მაგრამ საწყისი დრო არ არის მითითებული");
						return false;
					}
				}
			}
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
					<li><a href="UserPage.jsp" class="active">მთავარი გვერდი</a></li>
					<li><a href="#registermodal" id="modaltrigger">შეავსეთ
							დეტალური ინფორმაცია</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->
			<div id="registermodal" style="display: none;">
				<h2 class="txt">დეტალური ინფორმაცია მგზავრობაზე</h2>
				<h5>
					<b>გთხოვთ შეავსოთ ყველა ველი</b>
				</h5>
				<form action="EventServlet" id="registerform" name="registerform"
					method="post">
					<div class="txtfield">
						<input type="text" name="from" placeholder="გასვლის ადგილი"
							class="textfield" tabindex="1"></input>
					</div>
					<div class="txtfield">
						<input type="text" name="to" placeholder="დანიშნულების ადგილი"
							class="textfield" tabindex="2"></input>
					</div>
					<div class="txtfield">
						<input type="number" min="0" step="0.01" name="fee"
							placeholder="გადასახადი (ლარებში)" class="textfield" tabindex="3"></input>
					</div>
					<div class="txtfield">
						<input type="number" min="1" name="places"
							placeholder="ადგილების რაოდენობა" class="textfield" tabindex="3"></input>
					</div>
					<p>
						ყოველდღიური <input type="radio" class="radio1" name="type"
							value="everyday" tabindex="5"></input>
					</p>
					<p>
						ორშაბათი: <input type="checkbox" name="0" tabindex="5"></input> <input
							type="text" data-field='time' name="time0" placeholder="დრო"
							tabindex="4"></input> სამშაბათი: <input type="checkbox" name="1"
							tabindex="5"></input> <input type="text" data-field='time'
							name="time1" placeholder="დრო" tabindex="4"></input> ოთხშაბათი: <input
							type="checkbox" name="2" tabindex="5"></input> <input type="text"
							data-field='time' name="time2" placeholder="დრო" tabindex="4"></input>
					</p>
					<p>
						ხუთშაბათი: <input type="checkbox" name="3" tabindex="5"></input> <input
							type="text" data-field='time' name="time3" placeholder="დრო"
							tabindex="4"></input> პარასკევი: <input type="checkbox" name="4"
							tabindex="5"></input> <input type="text" data-field='time'
							name="time4" placeholder="დრო" tabindex="4"></input> შაბათი: <input
							type="checkbox" name="5" tabindex="5"></input> <input type="text"
							data-field='time' name="time5" placeholder="დრო" tabindex="4"></input>
					</p>
					<p>
						კვირა: <input type="checkbox" name="6" tabindex="5"></input> <input
							type="text" data-field='time' name="time6" placeholder="დრო"
							tabindex="4"></input>
					</p>
					<p>
						ერთჯერადი <input type="radio" class="radio2" name="type"
							value="oneway" tabindex="6"></input>
					</p>
					<p>
						<input type="text" data-field='date' name="date"
							placeholder="თარიღი" tabindex="4"></input> <input type="text"
							data-field='time' name="time" placeholder="დრო" tabindex="4"></input>
					</p>
					<div id="datepicker"></div>
					<div id="timepicker"></div>
					<input type="submit" name="registerbtn" id="registerbtn"
						class="flatbtn-blu hidemodal" value="შექმნა" tabindex="8"
						onClick="return CheckAlerts();"></input>
				</form>
			</div>
		</div>
		<!-- End Sidebar -->

		<!-- Begin Content -->
		<div id="content">
			<div class="line"></div>
			<html>
<head>
</head>
<body>
	
</body>
			</html>
			<div class="line"></div>
			<div id="footer">
				<H2>აირჩიეთ მარშრუტის საწყისი და საბოლოო პუნქტები.</H2>
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