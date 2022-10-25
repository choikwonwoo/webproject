package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoveDeletecSvc {
	public int loveDelete(int bs_num, int bs_like) {
		int result = 0;
		Connection conn = getConnection();
		LoveDao loveDao = LoveDao.getInstance();
		loveDao.setConnection(conn);
		
		result = loveDao.loveDelete(bs_num, bs_like);
		if(result == 1) commit(conn);
		else 			rollback(conn);
		close(conn);
		return result;
	}
}
