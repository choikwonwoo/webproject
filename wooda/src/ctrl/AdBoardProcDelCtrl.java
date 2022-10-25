package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/board_proc_del")
public class AdBoardProcDelCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdBoardProcDelCtrl() {  super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bsnum = request.getParameter("bsnum");
		
		HttpSession session = request.getSession();
		
		String where = " where ";
		if (bsnum.indexOf(',') > 0) {	// 여러 회원을 삭제할 경우
			String[] arr = bsnum.split(",");
			for (int i = 0 ; i < arr.length ; i++) {
				if (i == 0)	where += " bs_num = " + " '" + arr[i] + "' ";
				else		where += " or bs_num = " + " '" + arr[i] + "' ";
			}
		} else {	// 한명의 회원만 삭제할 경우
			where += " bs_num =  '"+ bsnum +"' ";
		}
		AdBoardProcDelSvc boardProcDelSvc = new AdBoardProcDelSvc();
		int result = boardProcDelSvc.boardDelete(where);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
