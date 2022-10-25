package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;
import java.util.*;

@WebServlet("/board_view")
public class AdBoardViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdBoardViewCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
	
		String ai_id = "";
		
		if(adminInfo != null) {
			ai_id = adminInfo.getAi_id();
		}
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		AdBoardViewSvc boardViewSvc = new AdBoardViewSvc();
		int result = boardViewSvc.readUpdate(idx);
		
		BorderInfo borderInfo = boardViewSvc.getBoderInfo(idx);
		
		int likeTotal = boardViewSvc.getBorderLike(idx, ai_id);
		
		int commentTotal = boardViewSvc.getBorderComment(idx);
		
		ArrayList<BorderComment> CommentList = new ArrayList<BorderComment>();
		CommentList = boardViewSvc.getBorderList(idx);
		if (borderInfo != null) {	// ������ �Խñ��� ������
			request.setAttribute("likeTotal", likeTotal);
			request.setAttribute("commentTotal", commentTotal);
			request.setAttribute("borderInfo", borderInfo);
			request.setAttribute("CommentList", CommentList);
			// request��ü�� 'borderInfo'��� �̸��� �Ӽ��� borderInfo ���� ������ �Ͽ� ���� �� ����

			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/admin/board_view.jsp");
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
