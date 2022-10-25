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
    	// 회원 목록을 받아 저장할 ArrayList<MemberTest> 생성 
    	
    	int cpage = 1, psize = 10, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
    	if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
    	
    	String schtype = request.getParameter("schtype");	// 검색조건(제목, 내용, 작성자, 제목+내용)
		String keyword = request.getParameter("keyword");	// 검색어
		String where = "";		// 검색 조건이 있을 경우 where절을 저장할 변수
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// 검색조건과 검색어가 있을 경우
			URLEncoder.encode(keyword, "UTF-8");
			// 쿼리스트링으로 주고 받는 검색어가 한글일 경우 IE에서 간혹 문제가 발생할 수 있으므로 유니코드로 변환시킴
			if (schtype.equals("tc")) {	// 검색조건이 '제목+내용'일 경우
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
		// 페이징에 필요한 정보들과 검색 조건을 PageInfo형 인스턴스에 저장
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("memberList", memberList);
    	// request 영역 객체의 속성으로 받아온 회원 목록 ArrayList객체를 저장 
    	// RequestDispatcher 방식으로 이동하면 request객체를 공유하므로 이동한 곳에서 회원목록 객체를 자유롭게 사용할 수 있음 
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
