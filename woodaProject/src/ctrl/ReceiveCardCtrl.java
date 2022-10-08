package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/r_card")
public class ReceiveCardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReceiveCardCtrl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberInfo mi = new MemberInfo();
		CoupleMailing cm = new CoupleMailing();
		HttpSession session = request.getSession();
		mi = (MemberInfo)session.getAttribute("loginInfo");
		
		ReceiveCardSvc receiveCardSvc = new ReceiveCardSvc();
		cm = receiveCardSvc.getCoupleMail(mi.getMi_mail());
		if (cm != null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�Ⱓ�� �����ų� ��ϵ��� ���� �ʴ��� �Դϴ�.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			request.setAttribute("cm", cm);
			RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/receive_card.jsp");
			dispatcher.forward(request, response);
		}else {
			// cm �޾ƿ��� ���ϸ� 
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�Ⱓ�� �����ų� ��ϵ��� ���� �ʴ��� �Դϴ�.')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
	}

}
