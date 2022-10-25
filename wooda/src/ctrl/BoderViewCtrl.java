package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;
import java.util.*;

@WebServlet("/diary_write_view")

public class BoderViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoderViewCtrl() { super(); }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		String mi_mail = "";
		
		if(loginInfo != null) {
			mi_mail = loginInfo.getMi_mail();
		}
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoderViewSvc boderViewSvc = new BoderViewSvc();
		int result = boderViewSvc.readUpdate(idx);
		
		BorderInfo borderInfo = boderViewSvc.getBoderInfo(idx);
		
		int likeTotal = boderViewSvc.getBorderLike(idx, mi_mail);
		
		int commentTotal = boderViewSvc.getBorderComment(idx);
		
		ArrayList<BorderComment> CommentList = new ArrayList<BorderComment>();
		CommentList = boderViewSvc.getBorderList(idx);
		
		
		
		if (borderInfo != null) {	// ������ �Խñ��� ������
			request.setAttribute("likeTotal", likeTotal);
			request.setAttribute("commentTotal", commentTotal);
			request.setAttribute("borderInfo", borderInfo);
			request.setAttribute("CommentList", CommentList);
			// request��ü�� 'borderInfo'��� �̸��� �Ӽ��� borderInfo ���� ������ �Ͽ� ���� �� ����

			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("diary/diary_write_view.jsp");
			dispatcher.forward(request, response);
			// Redirect ����� �ƴ� Dispatcher ������� �̵� ��Ŵ
			// Dispatcher ������� �̵��ϹǷ� url�� ������� �ʰ�, 
			// ������ request�� response ��ü�� �̵��� ���Ͽ����� �����Ӱ� ����� �� �ְ� ��

		} else {	// ������ �Խñ��� ������
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�߸��� ��η� �����̽��ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}
