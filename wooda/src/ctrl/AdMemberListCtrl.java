package ctrl;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/index_mem")
public class AdMemberListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdMemberListCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	ArrayList<MemberList> memberList = new ArrayList<MemberList>();
    	// ȸ�� ����� �޾� ������ ArrayList<MemberTest> ���� 
    	
    	int cpage = 1, psize = 10, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
    	if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
    	
    	String schtype = request.getParameter("schtype");	// �˻�����(����, ����, �ۼ���, ����+����)
		String keyword = request.getParameter("keyword");	// �˻���
		String where = "";		// �˻� ������ ���� ��� where���� ������ ����
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// �˻����ǰ� �˻�� ���� ���
			URLEncoder.encode(keyword, "UTF-8");
			// ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
			if (schtype.equals("tc")) {	// �˻������� '����+����'�� ���
				where = " where mi_mail like '%" + keyword + "%' " + 
				" or mi_nick like '%" + keyword + "%' ";
			} else {
				where = " where mi_" + schtype + " like '%" + keyword + "%' ";
			}
		}
    	
    	AdMemberListSvc memberListSvc = new AdMemberListSvc();
    	rcnt = memberListSvc.getMemberListCount(where);
    	memberList = memberListSvc.getMemberList(where, cpage, psize);
    	
    	PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
		// ����¡�� �ʿ��� ������� �˻� ������ PageInfo�� �ν��Ͻ��� ����
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("memberList", memberList);
    	// request ���� ��ü�� �Ӽ����� �޾ƿ� ȸ�� ��� ArrayList��ü�� ���� 
    	// RequestDispatcher ������� �̵��ϸ� request��ü�� �����ϹǷ� �̵��� ������ ȸ����� ��ü�� �����Ӱ� ����� �� ���� 
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index_mem.jsp");
    	dispatcher.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
