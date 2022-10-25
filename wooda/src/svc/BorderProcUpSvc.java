package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BorderProcUpSvc {
	public int borderUpdate(BorderInfo borderInfo) {
		int result = 0;
		Connection conn = getConnection();
		BorderProcUpDao borderProcUpDao = BorderProcUpDao.getInstance();
		borderProcUpDao.setConnection(conn);

		result = borderProcUpDao.borderUpdate(borderInfo);
		if (result == 1)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}
}
