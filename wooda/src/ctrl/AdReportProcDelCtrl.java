package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/report_proc_del")
public class AdReportProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdReportProcDelCtrl() {  super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String b1idx = request.getParameter("b1idx");
		
		HttpSession session = request.getSession();
		
		String where = " where ";
		if (b1idx.indexOf(',') > 0) {	// 여러 회원을 삭제할 경우
			String[] arr = b1idx.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0)	where += " b1_idx = " + " '" + arr[i] + "' ";
				else		where += " or b1_idx = " + " '" + arr[i] + "' ";
			}
		} else {	// 한명의 회원만 삭제할 경우
			where += " b1_idx =  '"+ b1idx +"' ";
		}
		
		AdReportProcDelSvc reportProcDelSvc = new AdReportProcDelSvc();
		int result = reportProcDelSvc.reportDelete(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
