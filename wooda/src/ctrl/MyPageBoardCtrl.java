package ctrl;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/my_page")
public class MyPageBoardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageBoardCtrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		HttpSession session = request.getSession();
		MemberInfo mi_mail = (MemberInfo)session.getAttribute("loginInfo");
		String mail = mi_mail.getMi_mail();
		// ��ü�Խ��� �� ����� �����ϱ� ���� ArrayList�� �ȿ� ����� �����ʹ� BorderInfo�� �ν��Ͻ��� ���
		
		int cpage = 1, psize = 3, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
		// ���� ������ ��ȣ, ������ ũ��, ��� ũ��, ���ڵ�(�Խñ�) ����, ������ ����, ���������� ���� ������ ������
		if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage ���� ������ �޾Ƽ� int������ ����ȯ ��Ŵ(���Ȼ��� ������ ��������� �ؾ� �ϱ� ����)

		String schtype = request.getParameter("schtype");	// �˻�����(����, ����, �ۼ���, ����+����)
		String keyword = request.getParameter("keyword");	// �˻���
		String where = "";		// �˻� ������ ���� ��� where���� ������ ����
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// �˻����ǰ� �˻�� ���� ���
			URLEncoder.encode(keyword, "UTF-8");
			// ������Ʈ������ �ְ� �޴� �˻�� �ѱ��� ��� IE���� ��Ȥ ������ �߻��� �� �����Ƿ� �����ڵ�� ��ȯ��Ŵ
			if (schtype.equals("tc")) {	// �˻������� '����+����'�� ���
				where += " and b.bs_title like '%" + keyword + "%' " + 
				" or b.bs_content like '%" + keyword + "%' ";
			} else {
				where += " and b.mi_" + schtype + " like '%" + keyword + "%' ";
			}
		}
		
		BoderListSvc boderListSvc = new BoderListSvc();
		rcnt = boderListSvc.getCoupleBoderListCount(where , mi_mail.getCi_idx(), mail);
		// �˻��� �Խñ��� �� ������ �Խñ� �Ϸù�ȣ ��°� ��ü �������� ����� ���� �ʿ��� ��
		borderList = boderListSvc.getCoupleBoderList(where, cpage, psize, mi_mail.getCi_idx(), mail);
		String[] pageCount = boderListSvc.getDiaryPage(mi_mail.getCi_idx(), mail).split(":");
		
		
		// ���ȭ�鿡�� ������ �Խñ� ����� ArrayList������ �޾ƿ� - �ʿ��� ��ŭ�� �޾ƿ��� ���� cpage�� psize �ʿ�
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
		pageInfo.setCouple_page(pageCount[1]);
		pageInfo.setSoloPage(pageCount[0]);
		
		// ����¡�� �ʿ��� ������� �˻� ������ PageInfo�� �ν��Ͻ��� ����
	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("borderList", borderList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/my_page.jsp");
		dispatcher.forward(request, response);
	}

}
