package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/board_proc_up")
public class AdBoardProcUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdBoardProcUpCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bsnum = request.getParameter("bsnum");
		
		HttpSession session = request.getSession();

		
		String where = " where ";
		
		if (bsnum.indexOf(',') > 0) {	
			String[] arr = bsnum.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0) {
					where += " bs_num = " + " '" + arr[i] + "' ";
				}
				else {
					where += " or bs_num = " + " '" + arr[i] + "' ";
				}
			}
		} else {	

			
			where += " bs_num = '" + bsnum + "' ";
		}

		AdBoardProcUpSvc boardProcUpSvc = new AdBoardProcUpSvc();
		int result = boardProcUpSvc.boardIsviewUpdate(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
