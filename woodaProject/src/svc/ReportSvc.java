package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ReportSvc {
	
	public int reportInsert(ReportInfo reportInfo) {
		int result = 0;
		Connection conn = getConnection();
		ReportDao reportDao = ReportDao.getInstance();
		reportDao.setConnection(conn);

		result = reportDao.reportInsert(reportInfo);
		if (result > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return result;
	}
}
