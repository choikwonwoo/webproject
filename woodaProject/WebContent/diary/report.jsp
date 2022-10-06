<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int idx = Integer.parseInt(request.getParameter("idx"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
td{ width:*; text-align:center }
</style>
<Script>
var idx = opener.idx;
$( '#num' ).append( '<input type="hidden" name="bs_num" value="' +idx +'" id="bs_num"  />' );
</Script>
</head>
<body>
<form name="frm" action="../report_proc_in" method="post" >
<span id="num"></span>
<table width="900px">
<tr>
<th width="100%"> 신고하기</th>
</tr>
<tr>
<td>
<input type="hidden" name="bs_num" value="<%=idx %>" />
<textarea cols="60px" rows="10px" name="bl_content"></textarea>
</td>
</tr>
</table>
<p align="center">
<input type="submit" name="submit" value="제출하기 " />
<input type="button" name="cancel" value="취소하기" onclick=window.close(); />
</p>
</form> 
</body>
</html>