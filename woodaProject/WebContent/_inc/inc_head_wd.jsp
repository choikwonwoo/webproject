<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import ="vo.*" %>
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
body, th, td, div, p { font-size:12px; }
a:link { text-decoration:none; color:black; }
a:visited { text-decoration:none; color:black; }
a:hover { text-decoration:underline; color:red; }
#logo { font-weight:bold; color:brown; margin-right:120px; }
.hand { cursor:pointer; }
.bold { font-weight:bold; margin-left:50px; }
#topmenu {
  position: fixed; top: 0; left: 0; right: 0; height: 30px; padding: 1rem; color: white;
  background: #ffea92; font-weight: bold; display: flex; justify-content: space-between;
  align-items: center; align-text:center; 
}
#topmenu p { width:1150px; text-align:center; font-size:20px; }
#wrapper { width:99%; align:center; margin:0; }
#trip { width:90%; border:1px black solid; margin:30px 0 30px 0; }
#board { width:90%; border:1px black solid; }

</style>
<script src="/mvcSite/js/jquery-3.6.1.js"></script>
<script>
function onlyNum(obj) {
	if (isNaN(obj.value)) {	// isNaN(Not a Number) :숫자 아니면
		obj.value = "";
	}
}
</script>
</head>
<body>
<div id="topmenu">
	<p>
		<a href="main_wooda" id="logo"><span>Woo Da</span></a>
		<a href="main_wooda" class="bold" id="retrip">여행지 추천</a> Servlet(main_wooda)
		<a href="/wooDa/diary_write_in.jsp" class="bold" id="diary_in">다이어리 쓰기</a> Servlet(diary_in)
		<a href="/wooDa/diary_board.jsp" class="bold" id="diary_view">다이어리 공유</a> Servlet(diary_view)
		<a href="/wooDa/gift.jsp" class="bold" id="gift_view">선물추천</a> Servlet(gift_view)
<% if (isLogin) { %> <!-- 로그인 상태이면 -->
		<a href="D:\sypark\html\img\a.jpg"></a>
		<a href="/mvcSite/logout.jsp">D+day:일</a>&nbsp;
		<a href="/mvcSite/member/info_form.jsp">닉네임</a>
<% } else { %> <!-- 로그인이 안 된 상태이면 -->
		<a href="/mvcSite/login_form.jsp" class="bold">로그인</a>
<% } %>
	</p>
</div>

