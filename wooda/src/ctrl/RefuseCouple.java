package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/refuse_couple")
public class RefuseCouple extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RefuseCouple() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 메일테이블 지우고, 상대방 dding 바꾸고,  내 dding 바꾸고  db도 바꾸고 
		// 상대 아이디 내 아이디
		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
		String senderEmail = request.getParameter("senderEmail");
		String receiverEmail = request.getParameter("receiverEmail");
		SendCardSvc sendCardSvc = new SendCardSvc();
		int result = sendCardSvc.refuse(senderEmail, receiverEmail);
		if (result == 3) {
			mi.setMi_dding("n");
			response.setContentType("text/html; charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('거절 되었습니다.');");
	         out.println("opener.document.location.reload();");
	         out.println("window.close();");
	         out.println("</script>");
	         out.close();
		}else {
			// 글등록 문제 발생
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('거절 실패')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
	}

}
