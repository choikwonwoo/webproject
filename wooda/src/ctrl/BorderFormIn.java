package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vo.MemberInfo;

@WebServlet("/diary_form_in")
public class BorderFormIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BorderFormIn() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
    	 MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
    	 if (loginInfo == null) {
 			response.setContentType("text/html; charset=utf-8");
 			PrintWriter out = response.getWriter();
 			out.println("<script>");
 			out.println("alert('�α��� �� ��� ������ ���� �Դϴ�')");
 			out.println("history.back();");
 			out.println("</script>");
 			out.close();
 		}
    	
    	 System.out.println(loginInfo.getMi_mail());
    	 
    		RequestDispatcher dispatcher = 
    	   		request.getRequestDispatcher("diary/diary_write_in.jsp");
    	 	dispatcher.forward(request, response);
    		    	// ��� ���� ���̹Ƿ� ���� Service�� Dao Ŭ���� ���� �ٷ� View�� �ش��ϴ� jsp ���Ϸ� �̵�

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}

