package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/love_proc_in")
public class LoveInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoveInsertCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		String mi_mail = "";
		if(loginInfo != null) {
		mi_mail = loginInfo.getMi_mail();
		}
		int bs_num = Integer.parseInt(request.getParameter("idx"));
		int bs_like = Integer.parseInt(request.getParameter("likeTotal"));
		
		int result = 0;
		
		LoveInsertcSvc  loveInsertcSvc = new LoveInsertcSvc();
		result = loveInsertcSvc.loveInsert(bs_num, bs_like, mi_mail);
		
		if(loginInfo == null) {
			result = -1;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
