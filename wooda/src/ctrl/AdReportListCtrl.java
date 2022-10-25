 package ctrl;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;
import svc.*;
import vo.*;

@WebServlet("/index_report")
public class AdReportListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdReportListCtrl() { super(); }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	ArrayList<ReportInfo> reportInfo = new ArrayList<ReportInfo>();
    	
    	int cpage = 1, psize = 5, bsize = 4, rcnt = 0, pcnt = 0, spage = 0;
    	if (request.getParameter("cpage") != null)
			cpage = Integer.parseInt(request.getParameter("cpage"));

    	String schtype = request.getParameter("schtype");	// 검색조건(글제목, 신고내용, 제목+내용)
		String keyword = request.getParameter("keyword");	// 검색어
		String where = "";		// 검색 조건이 있을 경우 where절을 저장할 변수
		if (schtype == null || keyword == null) {
			schtype = "";	keyword = "";
		} else if (!schtype.equals("") && !keyword.equals("")) {	// 검색조건과 검색어가 있을 경우
			URLEncoder.encode(keyword, "UTF-8");
			
			if (schtype.equals("tc")) {	// 검색조건이 '제목+내용'일 경우
				where = " or bs_title like '%" + keyword + "%' " + 
				" or b1_content like '%" + keyword + "%' ";
			} else {
				where = " or " + schtype + " like '%" + keyword + "%' ";
			}
		}
		
		AdReportListSvc reportListSvc = new AdReportListSvc();
		rcnt = reportListSvc.getReportListCount(where);
		reportInfo = reportListSvc.getReportList(where, cpage, psize);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setBsize(bsize);		pageInfo.setCpage(cpage);
		pageInfo.setPcnt(pcnt);			pageInfo.setPsize(psize);
		pageInfo.setRcnt(rcnt);			pageInfo.setSpage(spage);
		pageInfo.setSchtype(schtype);	pageInfo.setKeyword(keyword);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("reportInfo", reportInfo);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index_report.jsp");
    	dispatcher.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
