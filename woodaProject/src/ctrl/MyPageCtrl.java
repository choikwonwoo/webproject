package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/my_page")
public class MyPageCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPageCtrl() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mimail = request.getParameter("mimail"); //my_page.jsp form 에서 받아와야 해
		
		MyPageSvc myPageSvc = new MyPageSvc();
		MemberInfo memberInfo = myPageSvc.getMemberInfo(mimail);
		request.setAttribute("memberInfo", memberInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("my_page.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
