package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BoderProcInSvc {

	public int borderInsert(BorderInfo borderInfo) {
		int idx = 0;
		Connection conn = getConnection();
		BoderProcInDao boderProcInDao = BoderProcInDao.getInstance();
		boderProcInDao.setConnection(conn);

		idx = boderProcInDao.borderInsert(borderInfo);
		if (idx > 0)	commit(conn);
		else			rollback(conn);
		close(conn);
		
		return idx;
	}
	
}
