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
		String kind = request.getParameter("kind");
		if(kind == "a") {
			// �г���, Ŀ�� �г��� , day �޾ƿ��� db update ����
			String nick = request.getParameter("nick");
			String cnick = request.getParameter("cnick");
			String jdate = request.getParameter("jdate");
		}else if(kind =="b") {
			// �̸�,  ��й�ȣ,  �޾ƿͼ� ���� update
			
		}else {
			// �������� ���� : ���̺� mi_dding(�˸�), mi_iscouple(���� ����)/ ��� ������, ci_idx(Ŀ�� �Ϸù�ȣ) ��� ���� +Ŀ�� ���̺�  ���� ����
			
		}
	}

}
