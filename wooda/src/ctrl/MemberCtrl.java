package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/member_join")	// ������ 4���ڰ� '.mem'�� ��� ��û�� �ްڴٴ� �ǹ�
public class MemberCtrl extends HttpServlet {
// ȸ�� ���� �۾�(����, ��������, Ż��)�� ����(Model�� View)�� ����ϴ� ��Ʈ�ѷ�
	private static final long serialVersionUID = 1L;
    public MemberCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	MemberInfo memberInfo = new MemberInfo();
    	memberInfo.setMi_mail(request.getParameter("mi_mail"));
    	memberInfo.setMi_pw(request.getParameter("pwd"));
    	memberInfo.setMi_name(request.getParameter("name"));
    	memberInfo.setMi_nick(request.getParameter("nick"));
    	memberInfo.setMi_gender(request.getParameter("gender"));
    	memberInfo.setMi_birth(request.getParameter("birth"));
    	
    	MemberProcSvc MemberProcSvc = new MemberProcSvc();
    	
    	int result = MemberProcSvc.MemberProc(memberInfo);
    	if(result == 1) {
    		RequestDispatcher dispatcher = 
        			request.getRequestDispatcher("login_form.jsp");
        		dispatcher.forward(request, response);
    	}
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
