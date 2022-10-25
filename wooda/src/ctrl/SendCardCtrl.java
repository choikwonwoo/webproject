package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/s_card")
public class SendCardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendCardCtrl() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
		String mi_name_s = request.getParameter("mi_name_s");
		String writerEmail = request.getParameter("writerEmail");
		String inputEmail = request.getParameter("inputEmail");
		String dDay = request.getParameter("dDay");
		String shortMsg = request.getParameter("shortMsg");
		
		CoupleMailing cm = new CoupleMailing();
		cm.setMi_name_s(mi_name_s);
		System.out.println(cm.getMi_name_s());
		cm.setCm_mail_r(inputEmail);
		cm.setCm_mail_s(writerEmail);
		cm.setCm_sdate(dDay);
		cm.setCm_content(shortMsg);
		
		SendCardSvc sendCardSvc = new SendCardSvc();
		int result = sendCardSvc.sendUp(cm);
		if (result == 3) {
			mi.setMi_dding("a");
			response.setContentType("text/html; charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('신청되었습니다.');");
	         out.println("opener.document.location.reload();");
	         out.println("window.close();");
	         out.println("</script>");
	         out.close();
		}else {
			// 글등록 문제 발생
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('연인 등록 실패')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
	}

}
