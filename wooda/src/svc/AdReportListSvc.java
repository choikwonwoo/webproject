package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdReportListSvc {

	public int getReportListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		AdReportListDao reportListDao = AdReportListDao.getInstance();
		reportListDao.setConnection(conn);

		rcnt = reportListDao.getReportListCount(where);
		close(conn);

		return rcnt;
	}

	public ArrayList<ReportInfo> getReportList(String where, int cpage, int psize) {
		ArrayList<ReportInfo> reportInfo = new ArrayList<ReportInfo>();
		Connection conn = getConnection();
		AdReportListDao reportListDao = AdReportListDao.getInstance();
		reportListDao.setConnection(conn);

		reportInfo = reportListDao.getReportList(where, cpage, psize);
		close(conn);

		return reportInfo;
	}
}
