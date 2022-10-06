<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import = "vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
boolean isLogin = false; 
if (loginInfo != null) isLogin = true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.no-boxshadow .work-section .section-header {
	border-top: 1px solid rgb(204, 204, 204);
}
.work-section .section-header > .inner {
	margin: auto; width: 1150px; position: relative;
}
.work-section h2 {
	float: left; font-size: 30px; font-weight: normal; letter-spacing: 1px;
}
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
.work-section {  position:sticky; top:0px;}
</style>

</head>
<body>
<section class="work-section" id="work">
	<header class="section-header">
			<div class="inner clearfix">
				<h2>Woo Da</h2>
				<ul class="tabs-nav">
					<li><a href="#work01">여행지 추천</a></li>
					<li><a href="/woodaProject/diary/diary_write_in.jsp">다이어리 쓰기</a></li>
               		<li><a href="/woodaProject/diary_write_list">다이어리 공유</a></li>
					<li><a href="#work04">선물추천</a></li>
					<% if (isLogin) {%>
					<li><a href = "my_page">프로필</a></li>
					<% } else { %>
					 <!-- 로그인이 안 된 상태이면 -->
					<li><a href = "login_form.jsp" class="bold">로그인</a></li>
					<% } %>
				</ul>
				<span class="tabs-highlight"></span>
			</div>
	</header>
</section>

