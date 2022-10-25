package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.time.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/diary_main_list")
public class BorderSuggestCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BorderSuggestCtrl() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ArrayList<BorderInfo> mainList = new ArrayList<BorderInfo>();
		
		LocalDate nowDate = LocalDate.now();
	    String date = nowDate + "";
	    String year = date.substring(0, 4);
	    String month = date.substring(5, 7);
	    
	    String sdate = year + "-" + month + "-01";
	    String edate = year + "-" + month + "-31";

		
		String where = "";
		String alpha = "a";
		if(request.getParameter("alpha") != null && !request.getParameter("alpha").equals("")) {
		alpha = request.getParameter("alpha");
		}
		
		if(alpha.equals("a")) {
			where = " where bs_astatus = 'y' and bs_date between ('" + sdate + "') and ('"+ edate +"') order by bs_read limit 4; ";	// �̴��� ��õ �� ���
		}else if(alpha.equals("b")) {
			where = " where bs_astatus = 'y' order by bs_read  limit 4; ";	// ��ȸ�� �� 4��
		}else if(alpha.equals("c")) {
			where = " where bs_astatus = 'y' order by bs_date  limit 4; "; // �ֱ� ��¥ �� 4��
		}else if(alpha.equals("d")) {
			where = " where bs_astatus = 'y' order by bs_like  limit 4; "; // ���ƿ� �� 4��
		}else {
			where = "limit 4";
		}
		
		BorderSuggestSvc borderSuggestSvc = new BorderSuggestSvc();
		mainList = borderSuggestSvc.getMainList(where);
		
		// ���������� ��õ�Խù� ���� �� �˻���
		AdminImgSvc adminImgSvc = new AdminImgSvc();
		ArrayList<AdminImg> adminImg = (ArrayList<AdminImg>)adminImgSvc.getImgInfo();
		
		request.setAttribute("adminImg",adminImg);
		
		request.setAttribute("mainList", mainList);
		request.setAttribute("alpha", alpha);
		
		
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
