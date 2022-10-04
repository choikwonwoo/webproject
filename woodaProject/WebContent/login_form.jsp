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
<style>
.main {
   margin: auto;
     width: 40%;
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    border: 1px solid #ffea92;
    border-radius: 5px;
}

.logo {
    margin-top: 0px;
    margin-bottom: 40px;
}

#login, #pwd, #join {
    width: 100%;
    background-color: #ffea92 ;
    border-color: transparent;
    color: black;
    font-color: white;
}

.account {
    display: block;
    margin-bottom: 3px;
    padding: 3px;
    border: 1px solid lightgray;
    border-radius: 3px;
}

#alert {
    border-color: transparent;
}

#uid, #pwd {
    width: 95%;
}
</style>
<meta charset="EUC-KR">
<title>♡우리들의 다이어리♥</title>
</head>
<body>
<form name="frmLogin" action="login" method="post">
<input type="hidden" name="url" value="<%=url %>"/>
 <div class="main">
        <h1 class="logo">로고위치 </h1>
        <div class="container">
            <input type="text" name="uid" id="uid" value="wooda@naver.com" class="account"/>
            <input type="password" name="pwd" id="pwd" value="1234" class="account"/>
            <input type="submit" id="login" value="로그인" class="account"/>
            <hr />
            <button id="pw" class="account">비밀번호 찾기</button>
            <button id="join" class="account">회원가입</button>
            
            <p id="alert" class="account"> </p>
        </div>
    </div>   
    <script src="script.js"></script>
</form>
</body>
</html>