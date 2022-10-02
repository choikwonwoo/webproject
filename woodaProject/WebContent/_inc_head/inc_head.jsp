<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import = "vo.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body, th, td, div, p { font-size:20px; }
a:link { text-decoration:none; color:black; }
a:visited { text-decoration:none; color:black; }
a:hover { text-decoration:underline; color:red; }
#logo { font-weight:bold; color:brown; }
.hand { cursor:pointer; }
.bold { font-weight:bold; }
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
<table width="1150" border="1" cellpadding="0" cellspacing="0" id="inchead">
<tr height="40">

<th width="15%">
	<a href="/mvcSite/index.jsp" id="logo">Woo Da</a>
</th>
<th width="15%">
	<a href="/mvcSite/index.jsp" id="empty"></a>
</th>
<th width="15%">
	<a href="product_list?pcb=AA" class="bold" id="retrip">여행지 추천</a>
</th>
<th width="15%">
	<a href="product_list?pcb=BB" class="bold" id="wrdiary">다이어리 쓰기</a>
</th>
<th width="15%">
	<a href="product_list?pcb=CC" class="bold" id="shdiary">다이어리 공유</a>
</th>
<th width="15%">
	<a href="product_list?pcb=CC" class="bold" id="regift">선물추천</a>
</th>
<th width="10%">
	<a href="D:\sypark\html\img\a.jpg"></a>
	<a href="/mvcSite/logout.jsp">D+day:일</a>&nbsp;
	<a href="/mvcSite/member/info_form.jsp">닉네임</a>

</th>
</tr>

</body>
</html>

