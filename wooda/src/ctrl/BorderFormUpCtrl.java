package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/diary_form_up")
public class BorderFormUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorderFormUpCtrl() { super(); }
    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bs_num = Integer.parseInt(request.getParameter("idx"));
		
		String where = " where bs_num = " + bs_num + " and mi_mail = 'wooda@naver.com'";
		// + 회원 아이디session으로 받아와서 바로 출력 가능하게 where 절 추가 하여 이동
		BorderFormUpSvc borderFormUpSvc = new BorderFormUpSvc();
		BorderInfo borderInfo = borderFormUpSvc.getBorderInfo(where);
		
		if (borderInfo != null) {	// 수정하려는 게시글이 있으면
    		request.setAttribute("borderInfo", borderInfo);
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("diary/diary_write_up.jsp");
			dispatcher.forward(request, response);

    	} else {	// 수정하려는 게시글이 없으면
    		response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		out.println("<script>");
    		out.println("alert('잘못된 경로로 들어오셨습니다.');");
    		out.println("history.back();");
    		out.println("</script>");
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}
