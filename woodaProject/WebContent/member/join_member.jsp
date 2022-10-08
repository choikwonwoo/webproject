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
<script>
function chkDupId(uid) {
	if (uid.length >= 4) {
		$.ajax({
			type :"POST", 				
			url  :"/woodaProject/dupId", 	
			data :{"uid" : uid}, 		
			success : function(chkRs) {	
				var msg = "";			
				if (chkRs == 0) {		
					msg = "<span id='fontBlue'>사용하실 수 있는 이메일입니다.</span>";
					$("#idChk").val("y");	
				} else {				
					msg = "<span id='fontRed'>이미 사용중인  이메일입니다.</span>";
					$("#idChk").val("n");	
				}
				$("#idMsg").html(msg);
			}
		});
	} else {
		$("#idMsg").text("이메일을 입력해주세요.");
		$("#idChk").val("n");	
	}
}

function checkpw() {
	var frm = document.memberJoin;
	var pw1 = frm.pwd.value;
	var pw2 = frm.pwck.value;
	var msg = document.getElementById("pwChkMsg");
	if (pw1 == pw2) {
		msg.innerHTML = "비밀번호가 일치합니다.";
	} else {
		msg.innerHTML = "비밀번호가 일치하지 않습니다.";
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
<span id="idMsg" style="font-size:10px;">이메일을 입력해 주세요.</span>
<br />
    <div class="name">
        <input id="name" name="name"  type="text" placeholder="이름">
        <div id="nameError" class="error"></div>
    </div>
<br />   
     <div class="nick">
        <input id="nick" name="nick"  type="text" placeholder="닉네임">
        <div id="nickError" class="error"></div>
    </div>
<br />
    <div class="password">
        <input id="password" name="pwd" type="password" placeholder="비밀번호입력." onchange="checkpw()">
        <div id="passwordError" class="error"></div>
        <span id="pwMsg" style="font-size:10px;">비밀번호를 입력해 주세요.</span>
        
    </div>
<br />
    <div class="passwordCheck">
        <input id="passwordCheck" name="pwck" type="password"  placeholder="비밀번호 확인." onkeyup="checkpw()">
        <span id="pwChkMsg" class="error"></span>
    </div>
<br />
</span></td></tr>

    <div class="yymmdd">
        <input id="yyyy" name="year" type="text" size="3" maxlength="4" oninput="yy()" placeholder="yyyy"> -
        <input id="mm"	 name="month"type="text" size="3" maxlength="2" oninput="mm()" placeholder="mm"> -
        <input id="dd"	 name="day"  type="text" size="3" maxlength="2" oninput="dd()" placeholder="dd">
    </div>
<br />
        <div class="gender">
        <input id="gender_man" 	name="gender" type="radio" name="gender">남성  
        <input id="gender_woman" name="gender" type="radio" name="gender">여성
        <div   id="genderError" class="error"></div>
    </div>
<br />
    <div class="signUp">
        <input type="submit" value="가입하기" id="joinclick">        
    </div>
</div>
</form>
</body>
</html>
