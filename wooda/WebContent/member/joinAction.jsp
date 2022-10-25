 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>join 시 반응메세지</title>
</head>
<body>
    <%
        if(memberInfo.getMi_mail() == null || memberInfo.getMi_pw() == null || memberInfo.Mi_name() == null ||
        		memberInfo.getMi_nick() == null || memberInfo.getMi_gender() == null || memberInfo.getMi_birth()== null ){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('입력이 안된 사항이 있습니다.')");
            script.println("history.back()");
            script.println("</script>");
    } else {
    	MemberProcDao memberProcDao = new MemberProcDao();
        int result = MemberProcDao.join(memberInfo);
            if(result == -1){
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('이미 존재하는 아이디 입니다.')");
                script.println("history.back()");
                script.println("</script>");
            }
            else if(result == 0){
                session.setAttribute("memberInfo", memberInfo.getMi_mail());
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href= 'main.jsp'");
                script.println("</script>");
                }
        }
    %>
</body>
</html> 
