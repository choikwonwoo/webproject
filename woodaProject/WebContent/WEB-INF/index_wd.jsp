<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if (isLogin) { %>
<a href="cart_view">장바구니</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="schedule.sch">일정관리</a>
<% } else { %>
<a href="login_form.jsp?url=cart_list.ord">장바구니</a>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="login_form.jsp?url=schedule.sch">일정관리</a>
<% } %>
<hr />
<a href="free_list">자유 게시판</a>
<hr />
<h2>상품 목록</h2>
<form name="frm" action="product_list.pdt" method="get">
<input type="text" name="key" placeholder="상품명 검색" size="8" />
<input type="button" value="검색" />
</form>
</body>
</html>

