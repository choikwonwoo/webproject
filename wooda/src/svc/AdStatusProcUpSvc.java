package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdStatusProcUpSvc {
	public int statusUpdate(String where) {
		int result = 1;
		Connection conn = getConnection();
		AdStatusDao statusDao = AdStatusDao.getInstance();
		statusDao.setConnection(conn);

		result = statusDao.statusUpdate(where);
		if (result >= 1)	commit(conn);
		else				rollback(conn);
		close(conn);
		

		return result;
	}
}
