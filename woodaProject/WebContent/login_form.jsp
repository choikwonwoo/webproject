<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
if (loginInfo != null){
	out.println("<script> alert('잘못된 경로로 들어옴.'); history.back(); </script>");
	out.close();
}
request.setCharacterEncoding("utf-8");
String url = request.getParameter("url"); // 로그인 후 이동할 페이지 주소

if (url == null) url = "index.jsp"; // 주소가 없는 경우 메인화면 지정

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그린 폼</h2>
<form name="frmLogin" action="login" method="post">
<input type="hidden" name="url" value="<%=url %>"/>
<label for="uid">아이디 : </label>
<input type="text" name="uid" id="uid" value="wooda@naver.com"/><br/>
<label for="pwd">비밀번호 : </label>
<input type="password" name="pwd" id="pwd" value="1234"/><br/>
<input type="submit" value="로그링"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" value="다시입력"/>
</form>
</body>
</html>