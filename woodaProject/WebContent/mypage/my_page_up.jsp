<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%

// 회원 전용 페이지 로그인 확인
if (!isLogin){
	out.println("<script> alert('로그인 전용 페이지 입니다.'); location.href='login_form.jsp'; </script>");
	out.close();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
hr {width:1150px;}
#list th { border-bottom:double black 3px; }
#profileImg {width: 150px; height: 150px; }
#profileDiv {width: 150px; height: 150px;  border-radius: 70%; overflow: hidden;}
#profileZone { height:350px; background-color:lightgreen;}
fieldset {border:0;}
table {border:solid 1px black; width:1150px; height:330px; margin-left:auto; margin-right:auto; text-align:center; box-sizing : border-box;}
table th, table td{border : 1px solid #ccc; padding : 10px;}
#profileTable3{height:100px;}
.profileTable { padding : 0 0px; box-sizing : border-box; }
.profileTable th, .profileTable td{ border : 1px solid #ccc; padding : 10px;}
</style>
<Script>
var openWin;            
function openSendCard() {
    var _width = '650';
    var _height = '380';
	// window.name = "부모창 이름";  
 	window.name = "myPage";  
    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var _left = Math.ceil(( window.screen.width - _width )/2);
    var _top = Math.ceil(( window.screen.height - _height )/2); 
 	// window.open("open할 window", "자식창 이름", "팝업창 옵션");
    openWin = window.open("mypage/send_card.jsp","sendCard","top=" + _top + ",left=" + _left + ",width=400,height=400");
}
function frmCom(kind){
	var frms = document.forms;
	if(kind == "a"){
		frms[0].submit();
	}else if (kind == "b"){
		frms[1].submit();
	}else if (kind == "c"){
		frms[2].submit();
	}
}
</Script>
</head>
<body>
<h2>프로필 수정</h2>
<div id="profileZone">
<form name="frmProfileA" action="my_page_up" method="post">
<input type="hidden" name="kind" value="a"/>
<table id="profileTable1" border="1" class="profileTable">
<tr>
<td rowspan="3" width="30%"><div id="profileDiv">
<img id="profileImg" src="../img/basic_profile.png"/></div><br/>
<input type="button" value="프로필 사진 변경"/>
</td>
<td align="left">닉네임 : <input type="text" name="nick" value="<%=loginInfo.getMi_nick() %>"/></td>

</tr>
<tr>
<td align="left">커플 닉네임 : <input type="text" name="cnick" value="<=CoupleInfo.getCi_nick() %>"/></td>
</tr>
<tr>
<td align="left">D + day : <input type="text" name="jdate" value="<=CoupleInfo.getCm_jdate() %>"/></td>
</tr>
</table >
</form>
<input type=button name=profileModify value="수정 완료" onclick="frmCom('a')"/>
</div>
<hr/>
<div id="profileZone">
<form name="frmProfileB" action="my_page_up" method="post">
<input type="hidden" c value="b"/>
<table id="profileTable2" border="1">
<tr>
<td rowspan="3" width="30%">
<span>개인정보 수정</span>
</td>
<td align="left">이름 : <input type="text" name="name" value="<%=loginInfo.getMi_name() %>"/></td>
</tr>
<tr>
<td align="left">비밀번호 : <input type="text" name="pwd" /></td>
</tr>
<tr>
<td align="left">비밀번호 확인 : <input type="text" name="pwd" /></td>
</tr>
</table>
</form>
<input type=button name=profileModify value="수정 완료" onclick="frmCom('b')"/>
</div>
<hr/>
<div id="profileZone">
<form name="frmProfileC" action="my_page_up" method="post">
<input type="hidden" name="kind" value="c"/>
<table id="profileTable3" border="1">
<tr>
<td rowspan="3" width="30%">
<span>연인정보 수정</span>
</td>
<td align="center" ><input type="button" value="연인 등록 해제"/></td>
</tr>
</table>
</form>
</div>


<br />
</body>
</html>