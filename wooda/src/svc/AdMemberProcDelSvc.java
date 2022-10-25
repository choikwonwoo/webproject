package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdMemberProcDelSvc {
	public int memberDelete(String where) {
		int result = 0;
		Connection conn = getConnection();
		AdStatusDao statusDao = AdStatusDao.getInstance();
		statusDao.setConnection(conn);

		result = statusDao .memberDelete(where);
		if (result >= 1)	commit(conn);	
		else				rollback(conn);
		close(conn);

		return result;
	}
}
