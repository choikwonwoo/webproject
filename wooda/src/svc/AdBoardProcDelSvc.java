package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;


public class AdBoardProcDelSvc {
	public int boardDelete(String where) {
		int result = 0;
		Connection conn = getConnection();
		AdBoardProcDao boardProcDao = AdBoardProcDao.getInstance();
		boardProcDao.setConnection(conn);

		result = boardProcDao .boardDelete(where);
		if (result >= 1)	commit(conn);	
		else				rollback(conn);
		close(conn);

		return result;
	}
}
