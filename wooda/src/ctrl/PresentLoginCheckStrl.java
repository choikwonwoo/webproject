package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vo.MemberInfo;


@WebServlet("/present_login_check")
public class PresentLoginCheckStrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PresentLoginCheckStrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
   	 	MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
   	 	
   	 	int result = 0;
   	 	
   	 if (loginInfo == null) {
   		 result = 0;
   	 }else {
   		 result = 1;
   	 }
   	 
   	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	out.println(result);
	}

}
