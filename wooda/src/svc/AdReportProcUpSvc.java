package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdReportProcUpSvc {
	public int reportUpdate(String where) {
		int result = 0;
		Connection conn = getConnection();
		AdReportProcDao reportProcDao = AdReportProcDao.getInstance();
		reportProcDao.setConnection(conn);

		result = reportProcDao.reportUpdate(where);
		if (result >= 1)	commit(conn);
		else				rollback(conn);
		close(conn);

		return result;
	}
}
