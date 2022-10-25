<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<meta charset="EUC-KR">
<head>
<title>JSP BBS</title>
</head>
<body>
    <%
    MemberProcDao memberProcDao = new MemberProcDao();
        int result = MemberProcDao.loginInfo(MemberInfo.getuid(), MemberInfo.getpw());
        if (result ==1){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("location.href = 'index.jsp'");
            script.println("</script>");
        }
        else if (result == 0){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('비밀번호가 틀립니다.')");
            script.println("history.back()");    //로그인 페이지로 돌아감 
            script.println("</script>");
        }
        else if (result == -1){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('존재하지 않는 아이디입니다.')");
            script.println("history.back()");    // 로그인 페이지로 돌아감 
            script.println("</script>");
        }
        else if (result == -2){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('DB 오류가 발생했습니다.')");
            script.println("history.back()");    // 로그인 페이지로 돌아감 
            script.println("</script>");
        }
    %>
 
</body>
</html>
 
