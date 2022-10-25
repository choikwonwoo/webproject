package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/member_proc_del")
public class AdMemberProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdMemberProcDelCtrl() {  super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mimail = request.getParameter("mimail");
		
		HttpSession session = request.getSession();
		
		String where = " where ";
		if (mimail.indexOf(',') > 0) {	// 여러 회원을 삭제할 경우
			String[] arr = mimail.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0)	where += " mi_mail = " + " '" + arr[i] + "' ";
				else		where += " or mi_mail = " + " '" + arr[i] + "' ";
			}
		} else {	// 한명의 회원만 삭제할 경우
			where += " mi_mail =  '"+ mimail +"' ";
		}
		System.out.println(where);
		AdMemberProcDelSvc memberProcDelSvc = new AdMemberProcDelSvc();
		int result = memberProcDelSvc.memberDelete(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
