package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/report_proc_in")
public class ReportCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bl_content = 
		request.getParameter("bl_content").trim().replace("'", "''").replace("<", "&lt;");
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		System.out.println(bs_num);
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setB1_content(bl_content);
		reportInfo.setBs_num(bs_num);
		
		ReportSvc reportSvc = new ReportSvc();
		int result = reportSvc.reportInsert(reportInfo);
		
		if (result > 0) {	// 정상적으로 글이 등록되었으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('신고가 접수되었습니다.');");
			out.println("window.close();");
			out.println("</script>");
			out.close();
		} else {	// 글 등록시 문제가 발생했으면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 신고한 게시글 입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}
