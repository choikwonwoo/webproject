package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/diary_proc_del")
public class BorderProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BorderProcDelCtrl() { super(); }

    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
   	 	MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		
		String mi_mail = "";
		if(loginInfo != null) {
			mi_mail = loginInfo.getMi_mail();
		}
		
		int bs_num = Integer.parseInt(request.getParameter("idx"));
		
		int result = 0;
		
		BorderProcDelSvc borderProcDelSvc = new BorderProcDelSvc();
		result = borderProcDelSvc.deleteBorder(bs_num, mi_mail);
		
		if(loginInfo == null) {
			result = -1;
		}
		
		if(result == 1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제되었습니다.');");
			out.println("location.href='diary_write_list';");
			out.println("</script>");
			out.close();
		}else if(result == -1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('작성자만 삭제 가능한 글입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('오류가 났습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}
