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
		// ������Ʈ���� �ִ� url ���� '&'�� '$'�� ������� �޾ƿ��� ������ �ٽ� '$'�� '&'�� ������Ѿ� ��
		
		AdLoginSvc loginSvc = new AdLoginSvc();
		AdminInfo adminInfo = loginSvc.getLoginAdmin(uid, pwd);
		
		if (adminInfo != null) {	// �α��� ������
			HttpSession session = request.getSession();
			// JSP�� �ƴϹǷ� ���� ��ü�� ����Ϸ��� ���� HttpSession Ŭ������ �ν��Ͻ��� �����ؾ� ��
			session.setAttribute("adminInfo", adminInfo);
			// �α����� ȸ���� ������ ���� loginInfo �ν��Ͻ��� ������ �Ӽ����� ����
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
	    	dispatcher.forward(request, response);
		} else {	// �α��� ���н�
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('���̵�� ��ȣ�� Ȯ���ϼ���.'); history.back(); </script>");
		}
	}
}
