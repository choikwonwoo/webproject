package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/love_proc_del")
public class LoveDeleteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoveDeleteCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bs_num = Integer.parseInt(request.getParameter("idx"));
		int bs_like = Integer.parseInt(request.getParameter("likeTotal"));
		
		
		
		LoveDeletecSvc  loveDeletecSvc = new LoveDeletecSvc();
		int result = loveDeletecSvc.loveDelete(bs_num, bs_like);
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
