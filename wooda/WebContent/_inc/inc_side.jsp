<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
boolean isLogin = false;
if (adminInfo != null)	isLogin = true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
th, td, div, p { font-size:12px; }
a:link { text-decoration:none; color:black; }
a:visited { text-decoration:none; color:black; }
body { margin:0; }
.hand { cursor:pointer; }
.bold { font-weight:bold; }

ul { 
	list-style-type:none; background-color:#eee; width:150px; padding:0; 
	margin:0; position:fixed; height:100%; 
}
 li a { 
 	text-decoration:none; display:block; color:#000; text-align:center; 
 	padding:15px 8px 15px 8px; font-weight:bold; font-size:12px;
}

li a:hover { background: #333; color: #fff; }

.content { margin-left: 180px; width:1200px; text-align:center;}

#list th, #list td { padding:8px 3px; }
#list th { border-bottom:double black 3px; }
#list td { border-bottom:dotted black 1px; }




.loginout { font-size:15px; font-weight:bold; }

#log { margin-left: 180px; width:1200px; text-align:right;}

.btnmemtop { width:1200px; margin-top:50px; margin-bottom:20px; text-align:right; }
.btnYN { width:100px; height:40px; font-size:12px; font-weight:bold; margin-right:10px; }
</style>

<script src="/wdAdmin/js/jquery-3.6.1.js"></script>
<script>
function onlyNum(obj) {
	if (isNaN(obj.value)) {
		obj.value = "";
	}
}


</script>

</head>
<body>
	<ul>
		<li style="margin-top: 50px;"><a href="index_mem">회원 관리</a></li>
		<li><a href="index_board">게시물 공유 관리</a></li>
		<li><a href="index_report">신고 관리</a></li>
		<li><a href="#">선물 등록 관리</a></li>
		<li><a href="admin_main_img">메인 페이지 관리</a></li>
	</ul>


	<div align="left" id="log">
<% if (isLogin) { %>
		<a href="/wdAdmin/ad_logout.jsp" class="loginout">로그아웃</a><br/>
<% } else { %>
		<a href="login" class="loginout" >로그인</a><br/>
<% } %>
	</div>


