package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoveInsertcSvc {
	public int loveInsert(int bs_num, int bs_like, String mi_mail) {
		int result = 0;
		Connection conn = getConnection();
		LoveDao loveDao = LoveDao.getInstance();
		loveDao.setConnection(conn);
		
		result = loveDao.loveInsert(bs_num, bs_like, mi_mail);
		if(result == 1) commit(conn);
		else rollback(conn);
		close(conn);
		
		
		return result;
	}
}
