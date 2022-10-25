package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;
import java.util.*;

@WebServlet("/comment_proc_in")
public class BoderCommentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoderCommentCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		String mi_nick = "";
		if(loginInfo != null) {
			mi_nick = loginInfo.getMi_nick();
		}
		
		
		String br_content = request.getParameter("br_content");
		String br_ip = request.getRemoteAddr();
		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		BorderComment borderComment = new BorderComment();
		borderComment.setBs_num(bs_num);
		borderComment.setMi_nick(mi_nick);
		borderComment.setBr_content(br_content);
		borderComment.setBr_ip(br_ip);
		
		int result = 0;
		
		BoderCommentSvc boderCommentSvc = new BoderCommentSvc();
		result = boderCommentSvc.commitInsert(borderComment);
		
		if(loginInfo == null) {
			result = -1;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}

}
