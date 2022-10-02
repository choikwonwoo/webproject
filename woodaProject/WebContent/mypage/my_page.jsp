<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_inc/inc_head.jsp" %>
<%

// 회원 전용 페이지 로그인 확인
if (!isLogin){
	out.println("<script> alert('로그인 전용 페이지 입니다.'); history.back(); </script>");
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
#profileImg {width: 150px; height: 150px; }
#profileDiv {width: 150px; height: 150px;  border-radius: 70%; overflow: hidden;}
#profileZone { height:350px; background-color:lightgreen;}
#profileTable {border:solid 1px black; width:1150px; height:330px; margin-left:auto; margin-right:auto; text-align:center;}
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
    openWin = window.open("send_card.jsp","sendCard","top=" + _top + ",left=" + _left + ",width=400,height=400");
 
}
</Script>
</head>
<body>
<h2>마이 페이지</h2>
<div id="profileZone">
<table id="profileTable" border="1">
<tr>
<td rowspan="3" width="40%"><div id="profileDiv"><img id="profileImg" src="../img/basic_profile.png"/></div></td><td align="left"><%=loginInfo.getMi_name() %></td><td rowspan="3" width="15%">나의일기</td><td rowspan="3" width="15%">너의일기</td>
</tr>
<tr>
<td align="left"><%=loginInfo.getMi_mail() %></td>
</tr>
<tr>
<td align="left"><input type="button" value="연인을 등록하세요" onclick="openSendCard()"/></td>
</tr>
</table>
<input type=button name=profileModify value="프로필 수정" />
</div>
<hr/>
</body>
</html>