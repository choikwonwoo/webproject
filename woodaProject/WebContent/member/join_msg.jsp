<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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

#beck, #join {
    width: 100%;
    background-color: #ffea92 ;
    border-color: transparent;
    color: black;
    font-color: white;
}

.account {
    margin-bottom: 3px;
    padding: 3px;
    border-radius: 3px;
}

div
 {
width: 200px;
 border-color: transparent;

}
</style>
<meta charset="EUC-KR">
<title>가입축하 </title>
</head>
<body>
 <div class="main">
        <h1 class="join">로고위치 </h1> <hr/>
        <div class="container">

<p style="border:2px solid #ffea92";>우다다님 가입을 축하드립니다.
우다다님의 소중한 데이트를 기록해 보세요.
커플등록을 통해 연인과 데이트 기록을 공유할 수 있습니다.</p><br/>
        
            <hr />
            <button id="join" class="account">커플등록</button>
            <button id="beck" class="account">돌아가기</button>
            
            <p id="alert" class="account"> </p>
        </div>
    </div>   
    <script src="script.js"></script>
</body>
</html>