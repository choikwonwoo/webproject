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
<title>�������� </title>
</head>
<body>
 <div class="main">
        <h1 class="join">�ΰ���ġ </h1> <hr/>
        <div class="container">

<p style="border:2px solid #ffea92";>��ٴٴ� ������ ���ϵ帳�ϴ�.
��ٴٴ��� ������ ����Ʈ�� ����� ������.
Ŀ�õ���� ���� ���ΰ� ����Ʈ ����� ������ �� �ֽ��ϴ�.</p><br/>
        
            <hr />
            <button id="join" class="account">Ŀ�õ��</button>
            <button id="beck" class="account">���ư���</button>
            
            <p id="alert" class="account"> </p>
        </div>
    </div>   
    <script src="script.js"></script>
</body>
</html>