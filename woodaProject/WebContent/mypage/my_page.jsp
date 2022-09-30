<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
// 회원 전용 페이지 로그인 확인
//if (!isLogin){
//	out.println("<script> alert('잘못된 경로'); history.back(); </script>");
//	out.close();
//}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
hr {width:1150px;}
#profilezone { height:350px; background-color:lightgreen;}
#profiletable {border:solid 1px black; width:1150px; height:330px; margin-left:auto; margin-right:auto; text-align:center;}
</style>
</head>
<body>
<h2>마이 페이지</h2>
<div id="profilezone">
<table id="profiletable" border="1">
<tr>
<td rowspan="3"><img src="../img/basic_profile.png"/></td><td align="left">사용자 닉네임</td><td rowspan="3" width="15%">나의일기</td><td rowspan="3" width="15%">너의일기</td>
</tr>
<tr>
<td align="left">사용자 계정 정보</td>
</tr>
<tr>
<td align="left">연인을 등록하세요</td>
</tr>
</table>
<input type=button name=profile_modify value="프로필 수정" />
</div>
<hr/>
</body>
</html>