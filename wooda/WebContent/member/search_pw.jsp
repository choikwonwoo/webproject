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
<title>���츮���� ���̾��</title>
</head>
<body>
 <div class="main">
        <h4 class="logo">�α��ο� ������ �ֳ��� ? </h4>
       
        <div class="container">
            <input type="text" placeholder="������ �Է����ּ���." id="id" class="account">
            <button id="login" class="account">�α��� ��ũ ������</button>
            <hr />
            <button id="pw" class="account">�����������</button>
            <button id="join" class="account">�α������� ���ư���</button>
            
            <p id="alert" class="account"> </p>
        </div>
    </div>   
    <script src="script.js"></script>

</body>
</html>