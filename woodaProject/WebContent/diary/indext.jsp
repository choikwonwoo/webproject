<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr />
<a href="diary_write_list">자유 게시판</a>
<hr />
<h2>상품 목록</h2>
<form name="frm" action="product_list.pdt" method="get">

<input type="text" name="key" placeholder="상품명 검색" size="8" />
<input type="submit" value="검색" />
</form>
</body>
</html>