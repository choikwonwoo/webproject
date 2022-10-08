<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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

#login, #pw, #join {
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

#id, #password {
    width: 95%;
}
</style>
<meta charset="EUC-KR">
<title>♡우리들의 다이어리♥</title>
</head>
<body>
 <div class="main">
        <h4 class="logo">로그인에 문제가 있나요 ? </h4>
       
        <div class="container">
            <input type="text" placeholder="메일을 입력해주세요." id="id" class="account">
            <button id="login" class="account">로그인 링크 보내기</button>
            <hr />
            <button id="pw" class="account">새계정만들기</button>
            <button id="join" class="account">로그인으로 돌아가기</button>
            
            <p id="alert" class="account"> </p>
        </div>
    </div>   
    <script src="script.js"></script>

</body>
</html>