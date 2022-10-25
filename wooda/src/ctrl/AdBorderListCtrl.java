package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/index_board")
public class AdBorderListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdBorderListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		// 전체게시판 글 목록을 저장하기 위한 ArrayList로 안에 저장될 데이터는 BorderInfo형 인스턴스만 허용
		
		int cpage = 1, psize = 5, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
		// 현재 페이지 번호, 페이지 크기, 블록 크기, 레코드(게시글) 개수, 페이지 개수, 시작페이지 등을 저장할 변수들
		if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));
		

		String schtype = request.getParameter("schtype");	// 검색조건(제목, 내용, 작성자, 제목+내용)
		String keyword = request.getParameter("keyword");	// 검색어
		String where = "where bs_astatus = 'y' ";		// 검색 조건이 있을 경우 where절을 저장할 변수
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// 검색조건과 검색어가 있을 경우
			URLEncoder.encode(keyword, "UTF-8");
			// 쿼리스트링으로 주고 받는 검색어가 한글일 경우 IE에서 간혹 문제가 발생할 수 있으므로 유니코드로 변환시킴
			if (schtype.equals("tc")) {	// 검색조건이 '제목+내용'일 경우
				where += " and bs_title like '%" + keyword + "%' " + 
				" or bs_content like '%" + keyword + "%' ";
			} else {
				where += " and bs_ " + schtype + " like '%" + keyword + "%' ";
			}
		}
		
		String o = request.getParameter("o");
		if (o == null || o.equals("")) o = "a";
		String orderBy = "";
		switch (o) {
		case "a" : orderBy = " order by bs_isview desc, bs_date desc "; break; // 최근 게시글
		case "b" : orderBy = " order by bs_isview desc, bs_date "; break; // 오래된 게시글
		case "c" : orderBy = " order by bs_isview desc, bs_like desc "; break; // 인기게시글
		
		}
		
		AdBorderListSvc borderListSvc = new AdBorderListSvc();
		rcnt = borderListSvc.getBorderListCount(where);
		// 검색된 게시글의 총 개수로 게시글 일련번호 출력과 전체 페이지수 계산을 위해 필요한 값
		borderList = borderListSvc.getBorderList(where, cpage, psize, orderBy);
		// 목록화면에서 보여줄 게시글 목록을 ArrayList형으로 받아옴 - 필요한 만큼만 받아오기 위해 cpage와 psize 필요
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
		pageInfo.setO(o);
		// 페이징에 필요한 정보들과 검색 조건을 PageInfo형 인스턴스에 저장
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("borderList", borderList);

		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/admin/index_board.jsp");
		dispatcher.forward(request, response);
	}

}