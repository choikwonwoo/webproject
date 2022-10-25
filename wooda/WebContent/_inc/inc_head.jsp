<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import = "vo.*" %>
<script src="/woodaProject/js/jquery-3.6.1.js"></script>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
CoupleInfo coupleInfo = (CoupleInfo)session.getAttribute("coupleInfo");

boolean isLogin = false; 
if (loginInfo != null) isLogin = true;
%>
<link rel="stylesheet" type="text/css" href="/woodaProject/reset.css" />
<link rel="stylesheet" type="text/css" href="/woodaProject/main.css" />

<style>
/* Base */
html {
	font-family: "Ropa Sans", "Hiragino Kaku Gothic ProN", "Meiryo", sans-serif;
	font-size: 16px; line-height: 1.5;
}
body {
	margin:0; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); 
	min-width: 1150px;
}
h1, h2, h3, p, ul { margin: 0; }
ul { padding-left: 0; }
ul li { list-style-type: none; }
a { color: inherit; text-decoration: none; } 
li a:hover { background: #333; color: #fff; }
img { vertical-align: middle; }
.clearfix:before, .clearfix:after { content: " "; display: table; }
.clearfix:after { clear: both; }
/* Work section */
.work-section {
	background-color: #ffea92; position: relative; z-index: 10; 
	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25);
			box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25);
}
.no-boxshadow .work-section { border-bottom: 1px solid rgb(24, 24, 24);}
.work-section .section-header {
	background:111; line-height: 70px; 
	text-transform: uppercase; position: relative;
	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25);
			box-shadow: 0 1px 1px rgba(0, 0, 0, 0.25);	
}
.no-boxshadow .work-section .section-header { border-top: 1px solid rgb(204, 204, 204); }
.work-section .section-header > .inner { margin: auto; width: 1150px; position: relative; }
.work-section h2 { float: left; font-size: 30px; font-weight: normal; letter-spacing: 1px; }
.work-section .tabs-nav {
		border-left: 1px solid rgb(218, 218, 218); float: right; letter-spacing: 1px;
		-webkit-box-shadow: inset 1px 0 rgb(255, 255, 255);
				box-shadow: inset 1px 0 rgb(255, 255, 255);
	}
.work-section .tabs-nav li {
		border-right: 1px solid rgb(218, 218, 218); float: left; position: relative;
		-webkit-box-shadow: 1px 0 rgb(255, 255, 255);
				box-shadow: 1px 0 rgb(255, 255, 255);
	}
.work-section .tabs-nav a {
		display: block; outline: none; position: relative; text-align: center;
		width: 120px; z-index: 10;
	}
.work-section .tabs-nav .ui-tabs-active a { color: rgb(255, 255, 255); }
.work-section { position:sticky; top:0px; }
/* Dropdown Button */
.dropbtn { background-color:#ffea92; color:black; padding:24px; font-size:16px; cursor:pointer; width:120px; border:none; }
/* Dropdown button on hover & focus */
.dropbtn:hover, .dropbtn:focus { background-color:#333; color:white; }
/* The container <div> - needed to position the dropdown content */
.dropdown { position:relative; display:inline-block; }
/* Dropdown Content (Hidden by Default) */
.dropdown-content { left:-180px; display:none; position:absolute; background-color:#f9f9f9; min-width:160px; box-shadow:0px 8px 16px 0px rgba(0,0,0,0.2); }
/* Links inside the dropdown */
.dropdown-content a { color:black; padding:12px 16px; text-decoration:none; display:block; }
/* Change color of dropdown links on hover */
.dropdown-content a:hover { background-color:#f1f1f1 }
/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show { display:block; }
</style>
<script src="/woodaProject/js/jquery-3.6.1.js"></script>
<script>
/* When the user clicks on the button, toggle between hiding and showing the dropdown content */
function myFunction() {
	document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
var openWin;            
function showMemo() {
    var _width = '650';
    var _height = '380';
	// window.name = "부모창 이름";  
 	window.name = "myPage";  
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var _left = Math.ceil(( window.screen.width - _width )/2);
    var _top = Math.ceil(( window.screen.height - _height )/2); 
 	// window.open("open할 window", "자식창 이름", "팝업창 옵션");
    openWin = window.open("r_card","receiveCard","top=" + _top + ",left=" + _left + ",width=400,height=400");
 
}

</script>


<section class="work-section" id="work">
	<header class="section-header">
		<div class="inner clearfix">
			<h2><a href="/woodaProject/diary_main_list">Woo Da</a></h2>
			<ul class="tabs-nav">
				<li><a href="/woodaProject/diary_main_list">여행지 추천</a></li>
				<li><a href="/woodaProject/diary_form_in">다이어리 쓰기</a></li>
				<li><a href="/woodaProject/diary_write_list">다이어리 공유</a></li>
				<li><a href="/woodaProject/present_form.jsp">선물추천</a></li>
				<% if (isLogin) {%>
				<!-- 로그인된 상태이면 -->
				<li><a>
				<div class="dropdown">
					<button onclick="myFunction()" class="dropbtn">프로필</button>
		  			
		  		<div id="myDropdown" class="dropdown-content">
					<table class="slideT1" width="250" height="100" border="0" cellspacing="1">
						<tr align="center">
						<td rowspan="2" width="45%" style="color: black;">프로필사진</td>
						<td style="color: black;">디데이</td>
						</tr>
						<tr align="center">
						<td style="color: black;">닉네임</td>
						</tr>
					</table>
					
					<table class="slideT2" width="250" height="150" border="0" cellspacing="1">
						<tr align="center">
						<td colspan="2" height="60%" style="color: black;">알림이미지</td>
						</tr>
						<tr align="center">
						<td colspan="2" height="20%" style="color: black;">알림메세지1</td>
						</tr>
						<tr align="center">
						<td colspan="2" height="20%" style="color: black;"><a href="#" onclick="showMemo();" >쪽지</a></td>
						</tr>
					</table>
					
					<table class="slideT3" width="300" height="60" border="0" cellspacing="1">
						<tr align="center">
						<td height="33%"><a href="my_page">마이페이지</a></td>
						</tr>
						<tr align="center">
						<td height="34%"><a href="/woodaProject/logout.jsp">로그아웃</a></td>
						</tr>
					</table>
		    		
		  			</div>
				</div></a></li>
				
				<% } else { %>
				<!-- 로그인이 안 된 상태이면 -->
				<li><a href = "login_form.jsp" class="bold">로그인</a></li>
				<% } %>
			</ul>
		</div>
	</header>
</section>




