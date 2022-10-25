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
		
		
		
		if (borderInfo != null) {	// 보여줄 게시글이 있으면
			request.setAttribute("likeTotal", likeTotal);
			request.setAttribute("commentTotal", commentTotal);
			request.setAttribute("borderInfo", borderInfo);
			request.setAttribute("CommentList", CommentList);
			// request객체에 'borderInfo'라는 이름의 속성을 borderInfo 값을 가지게 하여 선언 및 생성

			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("diary/diary_write_view.jsp");
			dispatcher.forward(request, response);
			// Redirect 방식이 아닌 Dispatcher 방식으로 이동 시킴
			// Dispatcher 방식으로 이동하므로 url이 변경되지 않고, 
			// 기존의 request와 response 객체를 이동한 파일에서도 자유롭게 사용할 수 있게 됨

		} else {	// 보여줄 게시글이 없으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 경로로 들어오셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}
