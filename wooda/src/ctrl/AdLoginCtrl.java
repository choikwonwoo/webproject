package ctrl;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/ad_login")
public class AdLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdLoginCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ddd");
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$',  '&');
		// 쿼리스트링이 있는 url 값은 '&'를 '$'로 변경시켜 받아오기 때문에 다시 '$'를 '&'로 변경시켜야 함
		
		AdLoginSvc loginSvc = new AdLoginSvc();
		AdminInfo adminInfo = loginSvc.getLoginAdmin(uid, pwd);
		
		if (adminInfo != null) {	// 로그인 성공시
			HttpSession session = request.getSession();
			// JSP가 아니므로 세션 객체를 사용하려면 직접 HttpSession 클래스의 인스턴스를 생성해야 함
			session.setAttribute("adminInfo", adminInfo);
			// 로그인한 회원의 정보를 담은 loginInfo 인스턴스를 세션의 속성으로 저장
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
	    	dispatcher.forward(request, response);
		} else {	// 로그인 실패시
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('아이디와 암호를 확인하세요.'); history.back(); </script>");
		}
	}
}
