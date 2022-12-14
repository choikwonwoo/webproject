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
		// 전체게시판 글 목록을 저장하기 위한 ArrayList로 안에 저장될 데이터는 BorderInfo형 인스턴스만 허용
		
		int cpage = 1, psize = 3, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 레코드(게시글) 개수, 페이지 개수, 시작페이지 등을 저장할 변수들
		if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
		// cpage 값이 있으면 받아서 int형으로 형변환 시킴(보안상의 이유와 산술연산을 해야 하기 때문)

		String schtype = request.getParameter("schtype");	// 검색조건(제목, 내용, 작성자, 제목+내용)
		String keyword = request.getParameter("keyword");	// 검색어
		String where = "";		// 검색 조건이 있을 경우 where절을 저장할 변수
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// 검색조건과 검색어가 있을 경우
			URLEncoder.encode(keyword, "UTF-8");
			// 쿼리스트링으로 주고 받는 검색어가 한글일 경우 IE에서 간혹 문제가 발생할 수 있으므로 유니코드로 변환시킴
			if (schtype.equals("tc")) {	// 검색조건이 '제목+내용'일 경우
				where += " and b.bs_title like '%" + keyword + "%' " + 
				" or b.bs_content like '%" + keyword + "%' ";
			} else {
				where += " and b.mi_" + schtype + " like '%" + keyword + "%' ";
			}
		}
		
		BoderListSvc boderListSvc = new BoderListSvc();
		rcnt = boderListSvc.getCoupleBoderListCount(where , mi_mail.getCi_idx(), mail);
		// 검색된 게시글의 총 개수로 게시글 일련번호 출력과 전체 페이지수 계산을 위해 필요한 값
		borderList = boderListSvc.getCoupleBoderList(where, cpage, psize, mi_mail.getCi_idx(), mail);
		String[] pageCount = boderListSvc.getDiaryPage(mi_mail.getCi_idx(), mail).split(":");
		
		
		// 목록화면에서 보여줄 게시글 목록을 ArrayList형으로 받아옴 - 필요한 만큼만 받아오기 위해 cpage와 psize 필요
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
		pageInfo.setCouple_page(pageCount[1]);
		pageInfo.setSoloPage(pageCount[0]);
		
		// 페이징에 필요한 정보들과 검색 조건을 PageInfo형 인스턴스에 저장
	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("borderList", borderList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mypage/my_page.jsp");
		dispatcher.forward(request, response);
	}

}
