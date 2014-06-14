<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
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


<script>
	$(document).ready(function() {
		$("#datepicker").DateTimePicker();
	});
</script>

<script type="text/javascript">
	function CheckAlerts() {
		if ((registerform.title.value).length == 0
				|| (registerform.title.value).length > 100) {
			alert("სახელი უნდა იყოს 1-დან 100 სიმბოლომდე ზომის");
			return false;
		}
		var checkedValue = 0;
		var everyDay = false;
		var once = false;
		var inputElements = document.getElementsByTagName('input');
		for (var i = 0; inputElements[i]; i++) {
			if (inputElements[i].name == "everyday" && inputElements[i].checked) {
				everyDay = true;
				checkedValue++;
			} else if (inputElements[i].name == "once"
					&& inputElements[i].checked) {
				once = true;
				checkedValue++;
			}
		}
		if (checkedValue == 2 || checkedValue == 0) {
			alert("მონიშნული უნდა იყოს ან მარტო ყოველდღიური ან მარტო ერთჯერადი");
			return false;
		}
		if (once) {
			if (((registerform.datetimeStart.value).length == 0)
					|| ((registerform.datetimeFinish.value).length == 0)
					|| ((registerform.startTime.value).length == 0)
					|| ((registerform.endTime.value).length == 0)) {
				alert("ერთ ველი მაინც ერთჯერადისთვის ცარიელია");
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
			if(index == 0){
				alert("არც ერთი დღე არ არის მონიშნული");
				return false;
			}
			for (var i = 0; i < index; i++) {
				var temp1 = 'start' + array[i];
				var temp2 = 'end' + array[i];
				var inputElements = document.getElementsByTagName(temp1);
				var inputElements1 = document.getElementsByTagName(temp2);
				var sum = 0;
				for (var j = 0; inputElements[j]; j++) {
					if(inputElements[j].value == "") {
						sum++;
					}
				}
				for (var k = 0; inputElements1[k]; k++) {
					if(inputElements1[k].value == "") {
						sum++;
					}
				}
				if(sum == 2){
					alert("მონიშნულ დღეზე ერთი ველი მაინც ცარიელია");
					return false;
				}
			}
		}
		return true;
	}
</script>

</head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							დეტალური ინფრომაცია</a></li>
				</ul>
			</div>
			<!-- End Menu -->
			<!--Begin Login & Registration -->

			<div id="registermodal" style="display: none;">
				<h3 class="txt">გთხოვთ შეავსოთ ან ერთჯერადი ან ყოველდღიური
					ნაწილი</h3>
				<form action="WantToGoServlet" id="registerform" name="registerform"
					method="post">
					<p>
						<input type="text" name="title" placeholder="სახელი " tabindex="1"></input>
					</p>
					<p>
						ყოველდღიური: <input type="checkbox" name="everyday" tabindex="5"></input>
					</p>
					<p>
						ორშაბათი: <input type="checkbox" name="0" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start0"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end0"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						სამშაბათი: <input type="checkbox" name="1" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start1"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end1"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ოთხშაბათი: <input type="checkbox" name="2" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start2"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end2"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ხუთშაბათი: <input type="checkbox" name="3" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start3"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end3"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						პარასკევი: <input type="checkbox" name="4" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start4"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end4"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						შაბათი: <input type="checkbox" name="5" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start5"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end5"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						კვირა: <input type="checkbox" name="6" tabindex="5"></input>
						საწყისი დრო:<input type="text" data-field='time' name="start6"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="end6"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
					<p>
						ერთჯერადი <input type="checkbox" name="once" tabindex="5"></input>
					</p>
					<p>
						საწყისი თარიღი<input type="text" data-field='date'
							name="datetimeStart" placeholder="თარიღი" tabindex="4"></input>
						საბოლოო თარიღი<input type="text" data-field='date'
							name="datetimeFinish" placeholder="თარიღი" tabindex="4"></input>
					</p>
					<p>
						საწყისი დრო:<input type="text" data-field='time' name="startTime"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
						საბოლოო დრო: <input type="text" data-field='time' name="endTime"
							placeholder="დრო" tabindex="4" class="SmallInput"></input>
					</p>
					<div id="datepicker"></div>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$('.timepicker').timepicker();
		});
	</script>
</body>
</html>