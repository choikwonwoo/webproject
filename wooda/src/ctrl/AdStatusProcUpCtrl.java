package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/status_proc_up")
public class AdStatusProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdStatusProcUpCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mimail = request.getParameter("mimail");
		
		HttpSession session = request.getSession();

		
		String where = " where ";
		
		if (mimail.indexOf(',') > 0) {	
			String[] arr = mimail.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0) {
					where += " mi_mail = " + " '" + arr[i] + "' ";
				}
				else {
					where += " or mi_mail = " + " '" + arr[i] + "' ";
				}
			}
		} else {	

			
			where += " mi_mail = '" + mimail + "' ";
		}

		System.out.println(where);
		AdStatusProcUpSvc statusProcUpSvc = new AdStatusProcUpSvc();
		int result = statusProcUpSvc.statusUpdate(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
