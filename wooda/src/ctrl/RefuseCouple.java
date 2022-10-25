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
		// �������̺� �����, ���� dding �ٲٰ�,  �� dding �ٲٰ�  db�� �ٲٰ� 
		// ��� ���̵� �� ���̵�
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
	         out.println("alert('���� �Ǿ����ϴ�.');");
	         out.println("opener.document.location.reload();");
	         out.println("window.close();");
	         out.println("</script>");
	         out.close();
		}else {
			// �۵�� ���� �߻�
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���� ����')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
	}

}
