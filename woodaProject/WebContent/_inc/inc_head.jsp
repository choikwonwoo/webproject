<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
// session을 MembeInfo로 형변환하여 loginInfo 인스턴스에 저장
boolean isLogin = false;
if (loginInfo != null)   isLogin = true;
%>
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
function onlyNum(obj){ // 이거 키보드 입력 할 때 숫자만 받아오게 하는 함수 인데 일단 두겠습니다.!
	if (isNaN(obj.value)){
		obj.value = "";
	}
}
</script>
</head>
<body>
