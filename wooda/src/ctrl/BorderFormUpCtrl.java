package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/diary_form_up")
public class BorderFormUpCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorderFormUpCtrl() { super(); }
    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bs_num = Integer.parseInt(request.getParameter("idx"));
		
		String where = " where bs_num = " + bs_num + " and mi_mail = 'wooda@naver.com'";
		// + ȸ�� ���̵�session���� �޾ƿͼ� �ٷ� ��� �����ϰ� where �� �߰� �Ͽ� �̵�
		BorderFormUpSvc borderFormUpSvc = new BorderFormUpSvc();
		BorderInfo borderInfo = borderFormUpSvc.getBorderInfo(where);
		
		if (borderInfo != null) {	// �����Ϸ��� �Խñ��� ������
    		request.setAttribute("borderInfo", borderInfo);
			RequestDispatcher dispatcher = 
				request.getRequestDispatcher("diary/diary_write_up.jsp");
			dispatcher.forward(request, response);

    	} else {	// �����Ϸ��� �Խñ��� ������
    		response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		out.println("<script>");
    		out.println("alert('�߸��� ��η� �����̽��ϴ�.');");
    		out.println("history.back();");
    		out.println("</script>");
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}
