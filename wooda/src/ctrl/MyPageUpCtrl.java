package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;


@WebServlet("/my_page_up")
public class MyPageUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyPageUpCtrl() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberInfo mi = (MemberInfo)session.getAttribute("loginInfo");
		CoupleInfo ci = (CoupleInfo)session.getAttribute("coupleInfo");
		String kind = request.getParameter("kind");
		MyPageUpSvc mu = null;
		int result = 0;
		if(kind.equals("a")) {
			// 닉네임, 커플 닉네임 , day 받아오고 db update 수정
			
			String mi_mail = request.getParameter("mi_mail");
			String nick = request.getParameter("nick");
			String cnick = request.getParameter("cnick");
			String jdate = request.getParameter("jdate");
			
			mu = new MyPageUpSvc();
			result = mu.mypageUp(mi_mail, nick, cnick, jdate);
			result = mu.mypageUp(mi_mail, nick, cnick, jdate);
			if(result !=1) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			} else {
				mi.setMi_nick(nick);
				mi.setCi_nick(cnick);
				ci.setCi_nick(cnick);
				ci.setCm_jdate(jdate);
				response.sendRedirect("my_page");
			}
		}else if(kind.equals("b")) {
			// 이름,  비밀번호,  받아와서 수정 update
			String mi_mail = request.getParameter("mi_mail");
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			mu = new MyPageUpSvc();
			result = mu.mypageUp(mi_mail, name, pwd);
			mi.setMi_name(name);
			mi.setMi_pw(pwd);
			
			if(result !=1) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}
			response.sendRedirect("my_page");
		}else if(kind.equals("c")) {
			System.out.println("뭐야구");
			// 연인정보 해제 : 테이블 mi_dding(알림), mi_iscouple(연인 유무)/ 멤버 인포, ci_idx(커플 일련번호) 멤버 인포 +커플 테이블 , 메일링 테이블 delete
			String mi_mail1 = request.getParameter("mi_mail");
			String ci_idx = request.getParameter("ci_idx");
			mu = new MyPageUpSvc();
			result = mu.broke(ci.getCi_mail_s(),ci.getCi_mail_r(), ci_idx);
			
			if(result ==0) {
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
			}else {
				mi.setMi_dding("n");
				mi.setMi_iscouple("n");
				response.sendRedirect("my_page");
			}
		}
	}

}
