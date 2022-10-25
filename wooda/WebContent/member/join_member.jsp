<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%
if (isLogin) {
	out.println("<script> alert('잘못된 경로로 들어오셨습니다.'); history.back(); </script>");
	out.close();
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<style>
#joinForm{
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
#mi_mail, #name, #nick, 
#password, #passwordCheck
{ 
   display: block;
    margin-bottom: 3px;
    padding: 3px;
    border: 1px solid #ffea92 ;
    border-radius: 3px;
}

#joinclick { 
width: 80px;
    background-color: #ffea92 ;
    border-color: transparent;
    color: black;
    font-color: white;
}
#yyyy, #mm,#dd{
    border: 1px solid #ffea92 ;
}
#fontBlue { font-weight:bold; color:blue; }
#fontRed { font-weight:bold; color:red; }
</style>
<script src="../js/date_change.js"></script>
<script src="/woodaProject/js/jquery-3.6.1.js"></script>
<script>
function chkDupId(uid){
	$.ajax({
		type : "POST", //데이터 전송 방법
		url : "/woodaProject/dupIdCtrl", // 전송핧 데이터를 받을 서버의 url 컨트롤러를 의미
		data : {"uid" : uid}, // 지정한 url로 보낼 데이터릐 이름과 값
		success : function(chkRs){ // 데이러를 보내 실행한 결과를 chkRs라는 이름을 받아옴
			var msg = "";// 사용자엑세 보여줄 메세지
			if (chkRs!=0){ // uiod롸 동일한 아이디가 없으면
				
				msg="<span>사용중인 아이디 입니다.</span>"
			} else{
				msg="<span>사용 가능한 아이디 입니다.</span>"
			}
			$("#idMsg").html(msg);	
		}
	});
}

function checkpw() {
	var frm = document.memberJoin;
	var pw1 = frm.pwd.value;
	var pw2 = frm.pwck.value;
	var msg = document.getElementById("pwChkMsg");
	if (pw1 == pw2) {
		msg.innerHTML = "<span id='fontBlue'>비밀번호 일치</span>";
	} else {
		msg.innerHTML = "<span id='fontRed'>비밀번호 불일치</span>";
	}
}
</script>
</head>
<body>
<h2>배너 이미지</h2>
<hr/>
<form name="memberJoin" action="../member_join" method="post">
<input type="hidden" name="idChk" id="idChk" value="n"/>
<div class="title"><h1 style="font-size: 10px; text-align:center">회원가입</h1></div>   
     <div id="joinForm">
<input type="text" name="mi_mail" id="mi_mail" maxlength="20" placeholder="이메일주소" onkeyup="chkDupId(this.value);" />
<span id="idMsg" style="font-size:0.3em; color:red"> </span>
    <div class="nick">
        <input id="nick" name="nick"  type="text" placeholder="닉네임"/>
        <div id="nickError" class="error"></div>
    </div>
     <div class="password">
        <input id="password" name="pwd" type="password" placeholder="비밀번호입력." onchange="checkpw()"/>
        <div id="passwordError" class="error"></div>
    </div>
    <div class="passwordCheck">
        <input id="passwordCheck" name="pwck" type="password"  placeholder="비밀번호 확인." onkeyup="checkpw()"/>
        <span id="pwChkMsg" class="error"></span>
    </div>
    <div class="name">
        <input id="name" name="name"  type="text" placeholder="이름"/>
        <div id="nameError" class="error"></div>
    </div>
    <div class="birth">
        <input type="date" name="birth"/>
    </div>
        <div class="gender">
        <input id="gender_man" 	name="gender" type="radio" name="gender" value="m" checked="checked"/>남성  
        <input id="gender_woman" name="gender" type="radio" name="gender" value="f"/>여성
        <div   id="genderError" class="error"></div>
    </div>
    <div class="signUp">
        <input type="submit" value="가입하기" id="joinclick">        
    </div>
</div>
</form>
</body>
</html>
