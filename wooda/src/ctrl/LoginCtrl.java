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
		//String url = request.getParameter("url").replace('$', '&');
		// ���� ��Ʈ���� �ִ� url ���� &�� $�� ������ �޾ƿ��� ������ �ٽ� ����ȯ ���ش�.
		LoginSvc loginSvc = new LoginSvc();
		MemberInfo loginInfo = loginSvc.getLoginMember(uid, pwd);
		if (loginInfo != null) {
			//�α��� ����
			HttpSession session = request.getSession();
			//jsp�� �ƴϹǷ� ���� ��ü�� ����Ϸ��� ���� �ν��Ͻ��� �����ؾ��Ѵ�.
			session.setAttribute("loginInfo", loginInfo);
			PrintWriter out = response.getWriter();
			// �α��� �� ȸ���� ������ ���� loginInfo �ν��Ͻ��� ������ �Ӽ����� ����
			if(loginInfo.getMi_iscouple().equals("y")) {
				CoupleInfoSvc cs = new CoupleInfoSvc();
				CoupleInfo ci = cs.getCoupleInfo(loginInfo.getCi_idx(), loginInfo.getMi_mail());
				session.setAttribute("coupleInfo", ci);

			}
			response.sendRedirect("diary_main_list");
		} else {
			//�α��� ����
			
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.println("1");
			out.println("<script> alert('���̵�� ��ȣ�� Ȯ�� �ϼ���.'); history.back(); </script>");
		}
	}
}