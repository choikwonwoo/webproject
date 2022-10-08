<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*" %>
<%
MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
CoupleMailing cm = (CoupleMailing)request.getAttribute("cm");
// session을 MembeInfo로 형변환하여 loginInfo 인스턴스에 저장
boolean isLogin = false;
if (loginInfo != null)   isLogin = true;
%>
<%
// 연인 등록을 위한 이메일 보내기
// 입력  : 이메일, 짧은 메세지, d+day, 
// 동작 : 확인 버튼 누르면 post 후 창닫기
if (!isLogin){ // 연인등록을 이미 보내거나 받은경우  버튼 보이지 않기
	out.println("<script> alert('잘못된 경로입니다.');</script>");
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
		// 수락하면 바뀌어야 할 db :
		// 멤버 테이블 : ci_idx, mi_iscouple
		// 연인 메일링 : cm_isok(수락여부),  cm_ydate(수락 날짜?)
		// 커플 테이블 : insert 
		document.frmSend.submit();
	} else
		window.close();
}
</script>
</head>
<body>
<%
// 연인 등록을 위한 이메일 보내기
// 입력  : 이메일, 짧은 메세지, d+day, 
// 동작 : 확인 버튼 누르면 post 후 창닫기
if (cm==null){ // 연인등록을 이미 보내거나 받은경우  버튼 보이지 않기
	out.println("<script> alert('기간이 만료된 요청입니다.');</script>");
	out.close();
}else if (cm.getCm_isok()=="y"){ // 연인등록을 이미 보내거나 받은경우  버튼 보이지 않기
	out.println("<script> alert('잘못된 경로 입니다.');</script>");
	out.close();
}
%>
<form name="frmSend" action="../r_card" method="post">
<input type="hidden" id="receiverEmail" name="receiverEmail" value="<%=loginInfo.getMi_mail()%>"/>
<table width="390" height="380" Style="border:solid 1px black; margin-left:auto; margin-right:auto; text-align:center">
<tr><td id="sendTitle">우리들의 다이어리</td></tr>
<tr><td id="sendInfoMsg"><%=cm.getMi_name_s()%>님이 커플 등록을 요청 하셨어요<br/> 등록을 원하시면 수락 버튼을 눌러주세요!</td></tr>
<tr><td><span id="shortMsg"><%=cm.getCm_content() %></span></td></tr>
<tr><td><span id="senderEmail"><%=cm.getCm_mail_s() %></span></td></tr>
<tr><td><span id="jday">우리의 시작일 : <%=cm.getCm_jdate() %></span></td></tr>
<tr><td><hr/></td></tr>
<tr><td><span class="msg">메세지의 유효기간은 3 일 입니다.</span></td></tr>
<tr><td>
<input type="button" name="yesBtn" value="수락" onclick="btnClick('y')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name="noBtn" value="거절" onclick="btnClick('n')"/>
</td></tr>
</table>
</form>

</body>
</html>