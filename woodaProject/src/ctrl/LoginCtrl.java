package ctrl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import vo.*;
import svc.*;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		// 쿼리 스트링이 있는 url 값은 &를 $로 변경해 받아오기 때문에 다시 역변환 해준다.
		LoginSvc loginSvc = new LoginSvc();
		MemberInfo loginInfo = loginSvc.getLoginMember(uid, pwd);
		if (loginInfo != null) {
			//로그인 성공
			HttpSession session = request.getSession();
			//jsp가 아니므로 세션 객체를 사용하려면 직접 인스턴스를 생성해야한다.
			session.setAttribute("loginInfo", loginInfo);
			// 로그인 한 회원의 정보를 담은 loginInfo 인스턴스를 세션의 속성으로 저장
			response.sendRedirect(url);
		} else {
			//로그인 실패
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script> alert('아이디와 암호를 확인 하세요.'); history.back(); </script>");
		}
	}
}
