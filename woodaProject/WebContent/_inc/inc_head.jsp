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
function onlyNum(obj){ // 이거 키보드 입력 할 때 숫자만 받아오게 하는 함수 인데 일단 두겠습니다.!
	if (isNaN(obj.value)){
		obj.value = "";
	}
}
</script>
</head>
<body>

<div id="topmenu">
	<p>
		<a href="main_wooda" id="logo"><span>Woo Da</span></a>
		<a href="main_wooda" class="bold" id="retrip">여행지 추천</a>
		<a href="diary_write_in.jsp" class="bold" id="diary_in">다이어리 쓰기</a>
		<a href="diary_write_view" class="bold" id="diary_view">다이어리 공유</a>
		<a href="/wooDa/gift" class="bold" id="gift_view">선물추천</a>

		<% if (isLogin) {%>
		<a href = "my_page">프로필</a>&nbsp;&nbsp;&nbsp;&nbsp; 
		<% }else { %>
		 <!-- 로그인이 안 된 상태이면 -->
		<a href = "login_form.jsp" class="bold">로그인</a>
		<% } %>
		

	</p>
</div>






</body>
</html>



