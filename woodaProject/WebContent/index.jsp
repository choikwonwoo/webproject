<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_inc/inc_head.jsp" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>메인 페이지</h2>

<!-- 얘도 나중 로그인 폼으로 보내야 함 -->

<% if (isLogin) {%>
<a href = "mypage/my_page.jsp">마이페이지</a>&nbsp;&nbsp;&nbsp;&nbsp; 
<% }else { %>
<a href = "login_form.jsp">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
<% } %>
<hr/>
</body>
</html>