package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/diary_write_list")
public class BoderListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoderListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		// ��ü�Խ��� �� ����� �����ϱ� ���� ArrayList�� �ȿ� ����� �����ʹ� BorderInfo�� �ν��Ͻ��� ���
		
		int cpage = 1, psize = 3, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ���� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���������� ���� ������ ������
		if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage ���� ������ �޾Ƽ� int������ ����ȯ ��Ŵ(���Ȼ��� ������ ��������� �ؾ� �ϱ� ����)

		String schtype = request.getParameter("schtype");	// �˻�����(����, ����, �ۼ���, ����+����)
		String keyword = request.getParameter("keyword");	// �˻���
		String where = "where bs_astatus = 'y' ";		// �˻� ������ ���� ��� where���� ������ ����
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// �˻����ǰ� �˻�� ���� ���
			URLEncoder.encode(keyword, "UTF-8");
			// ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
			if (schtype.equals("tc")) {	// �˻������� '����+����'�� ���
				where += " and bs_title like '%" + keyword + "%' " + 
				" or bs_content like '%" + keyword + "%' ";
			} else {
				where += " and bs_" + schtype + " like '%" + keyword + "%' ";
			}
			
		}
		
		
		String o = request.getParameter("o");
		if (o == null || o.equals("")) o = "a";
		String orderBy = "";
		switch (o) {
		case "a" : orderBy = " order by bs_date desc "; break; // �ֱ� �Խñ�
		case "b" : orderBy = " order by bs_date "; break; // ������ �Խñ�
		case "c" : orderBy = " order by bs_like desc "; break; // �α�Խñ�
		
		}
		
		BoderListSvc boderListSvc = new BoderListSvc();
		rcnt = boderListSvc.getBoderListCount(where);
		// �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��
		borderList = boderListSvc.getBoderList(where, cpage, psize, orderBy);
		// ���ȭ�鿡�� ������ �Խñ� ����� ArrayList������ �޾ƿ� - �ʿ��� ��ŭ�� �޾ƿ��� ���� cpage�� psize �ʿ�
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
		pageInfo.setO(o);
		// ����¡�� �ʿ��� ������� �˻� ������ PageInfo�� �ν��Ͻ��� ����
		
		
		
		
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("borderList", borderList);

		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("diary/diary_write_list.jsp");
		dispatcher.forward(request, response);
	}

}