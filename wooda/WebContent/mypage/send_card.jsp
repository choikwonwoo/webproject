<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
// session을 MembeInfo로 형변환하여 loginInfo 인스턴스에 저장
boolean isLogin = false;
if (loginInfo != null)   isLogin = true;
%>
<%
// 연인 등록을 위한 이메일 보내기
// 입력  : 이메일, 짧은 메세지, d+day, 
// 동작 : 확인 버튼 누르면 post 후 창닫기
if (!isLogin){ // 연인등록을 이미 보내거나 받은경우  버튼 보이지 않기
	out.println("<script> alert('잘못된 경로입니다.'); history.back(); </script>");
	out.close();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/mvcSite/js/jquery-3.6.1.js"></script>
<script>
function btnClick(msg){
	if(msg =='y'){
		if($("#dupChk").val()=='y'){
			document.frmSend.submit();
			
		} else {
			alert("존재하지 않는 이메일 입니다.");
		}
	} else
		window.close();
}
function chkDupId(uid){
	$.ajax({
		type : "POST", //데이터 전송 방법
		url : "/woodaProject/dupIdCtrl", // 전송핧 데이터를 받을 서버의 url 컨트롤러를 의미
		data : {"uid" : uid}, // 지정한 url로 보낼 데이터릐 이름과 값
		success : function(chkRs){ // 데이러를 보내 실행한 결과를 chkRs라는 이름을 받아옴
			var msg = " ";// 사용자엑세 보여줄 메세지
			if (chkRs==0){ // uiod롸 동일한 아이디가 없으면
				msg="<span>존재하지 않는 이메일.</span>"
			} else{
				$("#dupChk").val('y');
			}
			$("#idMsg").html(msg);	
		}
	});
}
</script>
</head>
<body>

<form name="frmSend" action="../s_card" method="post">
<input type="hidden" id="dupChk" value="n"/>
<input type="hidden" name="mi_name_s" value="<%=loginInfo.getMi_name() %>"/>
<input type="hidden" name="writerEmail" name="writerEmail" value="<%=loginInfo.getMi_mail()%>"/>
<input type="hidden" name="dding" name="dding" value="<%=loginInfo.getMi_dding()%>"/>
<table width="390" height="380" Style="border:solid 1px black; margin-left:auto; margin-right:auto; text-align:center">
<tr><td id="sendTitle">우리들의 다이어리</td></tr>
<tr><td id="sendInfoMsg">등록할 연인의 정보를 입력해주세요.</td></tr>
<tr><td><input type="text" name="inputEmail" id="inputEmail" placeholder="연인의 이메일을 입력하세요." onkeyUp="chkDupId(this.value)"/><br/><span id="idMsg" style="font-size:0.3em; color:red"> </span></td></tr>
<tr><td>연인이 된 날은 언제 인가요? : <input type="date" name="dDay"/></td></tr>
<tr><td><input type="textarea" name="shortMsg" placeholder="상대방에게 하고 싶은 말을 전하세요"/></td></tr>
<tr><td><hr/></td></tr>
<tr><td>
<input type="button" name="sendBtn" value="보내기" onclick="btnClick('y')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="resetBtn" value="취소" onclick="btnClick('n')"/>
</td></tr>
</table>
</form>

</body>
</html>