package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BoderCommentSvc {
	public int commitInsert(BorderComment borderComment) {
		int result = 0;
		Connection conn = getConnection();
		BoderCommentDao boderCommentDao = BoderCommentDao.getInstance();
		boderCommentDao.setConnection(conn);
		
		result = boderCommentDao.commitInsert(borderComment);
		if(result == 1) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
