package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/report_proc_up")
public class AdReportProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdReportProcUpCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String b1idx = request.getParameter("b1idx");
		
		HttpSession session = request.getSession();

		
		String where = " where ";
		
		if (b1idx.indexOf(',') > 0) {	
			String[] arr = b1idx.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0) {
					where += " b.b1_idx = " + " '" + arr[i] + "' ";
				}
				else {
					where += " or b.b1_idx = " + " '" + arr[i] + "' ";
				}
			}
		} else {	

			
			where += " b.b1_idx = '" + b1idx + "' ";
		}

		System.out.println(where);
		AdReportProcUpSvc reportProcUpSvc = new AdReportProcUpSvc();
		int result = reportProcUpSvc.reportUpdate(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
