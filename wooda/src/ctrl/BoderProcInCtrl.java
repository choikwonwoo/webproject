package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;
import java.util.*;

@WebServlet("/write_proc_in")
public class BoderProcInCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoderProcInCtrl() {  super();  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
   	 	MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
   	 	
		String mi_mail = "";
		String mi_nick = "";
		if(loginInfo != null) {
			mi_mail = loginInfo.getMi_mail();
			mi_nick = loginInfo.getMi_nick();
		}
		String ci_idx = loginInfo.getCi_idx();
		String bs_title = request.getParameter("bs_title").trim().replace("'", "''").replace("<", "&lt;");
		String bs_content = request.getParameter("bs_content").trim().replace("'", "''").replace("<", "&lt;");
		String bs_area = request.getParameter("bs_area");
		String bs_start = request.getParameter("bs_start");
		String bs_end = request.getParameter("bs_end");
		String bs_ip = request.getRemoteAddr();
		String bs_place1 = "";
		String bs_place2 = "";
		String bs_place3 = "";
		double bs_lat1 = 0;
		double bs_lat2 = 0;
		double bs_lat3 = 0;
		double bs_lng1 = 0;
		double bs_lng2 = 0;
		double bs_lng3 = 0;
		if(request.getParameter("bs_place1") != null)  bs_place1 = request.getParameter("bs_place1");

		if(request.getParameter("bs_place2") != null)  bs_place2 = request.getParameter("bs_place2");
		
		if(request.getParameter("bs_place3") != null)  bs_place3 = request.getParameter("bs_place3");

		if(request.getParameter("bs_lat1") != null)  bs_lat1 = Double.parseDouble(request.getParameter("bs_lat1"));
		
		if(request.getParameter("bs_lat2") != null)  bs_lat2 = Double.parseDouble(request.getParameter("bs_lat2"));
		
		if(request.getParameter("bs_lat3") != null)  bs_lat3 = Double.parseDouble(request.getParameter("bs_lat3"));
		
		if(request.getParameter("bs_lng1") != null)  bs_lng1 = Double.parseDouble(request.getParameter("bs_lng1"));
		
		if(request.getParameter("bs_lng2") != null)  bs_lng2 = Double.parseDouble(request.getParameter("bs_lng2"));
		
		if(request.getParameter("bs_lng3") != null)  bs_lng3 = Double.parseDouble(request.getParameter("bs_lng3"));
				
		String bs_astatus = request.getParameter("bs_astatus");
		String bs_cstatus = request.getParameter("bs_cstatus");
		String bs_img1 = request.getParameter("bs_img1");
		String bs_visit = request.getParameter("bs_visit");
		String bs_gender = request.getParameter("bs_gender");
			
		BorderInfo borderInfo = new BorderInfo();
		borderInfo.setMi_mail(mi_mail);		borderInfo.setMi_nick(mi_nick);
		borderInfo.setBs_title(bs_title);		borderInfo.setBs_content(bs_content);
		borderInfo.setBs_area(bs_area);		borderInfo.setBs_start(bs_start);
		borderInfo.setBs_end(bs_end);		borderInfo.setBs_place1(bs_place1);
		borderInfo.setBs_place2(bs_place2);		borderInfo.setBs_place3(bs_place3);
		borderInfo.setBs_lat1(bs_lat1);		borderInfo.setBs_lat2(bs_lat2);
		borderInfo.setBs_lat3(bs_lat3);		borderInfo.setBs_lng1(bs_lng1);
		borderInfo.setBs_lng2(bs_lng2);		borderInfo.setBs_lng3(bs_lng3);
		borderInfo.setBs_astatus(bs_astatus);		borderInfo.setBs_cstatus(bs_cstatus);
		borderInfo.setBs_img1(bs_img1);		borderInfo.setBs_gender(bs_gender);
		borderInfo.setBs_visit(bs_visit);	borderInfo.setBs_ip(bs_ip);
		borderInfo.setCi_idx(ci_idx);
		
		BoderProcInSvc boderProcInSvc = new BoderProcInSvc();
		int idx = boderProcInSvc.borderInsert(borderInfo);
		
		if(idx > 0) {
			response.sendRedirect("diary_write_view?cpage=1&idx=" + idx);
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 등록에 실패했습니다. 다시 시도해 보세요.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}
