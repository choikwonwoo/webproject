<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");


request.setCharacterEncoding("utf-8");
String url = request.getParameter("url");	// 로그인 후 이동할 페이지 주소
if (url == null)	url = "index.jsp";
// 로그인 후 이동할 페이지 주소가 없는 경우 메인 화면으로 지정
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.wrap {
  display: flex; justify-content: center; align-items: center; min-height: 100vh; }

.loginBox {
  font-family: system-ui, serif; font-size: 2rem;
  padding: 3rem; background: #eee; border:1px solid black; margin-top: 10px;
  font-size: 18px; font-weight: bold; 
}
  
.loginBox form input[type="submit"] { margin-top: 10px;
  background-color: #1f9a50; color: #FFF; font-size: 18px; 
  font-weight: bold; cursor: pointer; width:100%; border: 1px solid #111; }
</style>
</head>
<body>
<div class="wrap">
	<div class="loginBox">	<!-- 로그인 영역 시작  --> 
		<form name="frmLogin" action="ad_login" method="post">
			<input type="hidden" name="url" value="<%=url %>" />
			<input type="text" name="uid" id="uid" placeholder="아이디 입력" value="admin1" /><br>
			<input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력" value="1234" /><br>
			<input type="submit" value="로그인">
		</form>
	</div>	<!-- 로그인 영역 끝  -->
</div>	 
</body>
</html>
