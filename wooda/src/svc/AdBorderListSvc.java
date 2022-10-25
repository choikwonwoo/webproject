package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdBorderListSvc {
	public int getBorderListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		AdBorderListDao borderListDao = AdBorderListDao.getInstance();
		borderListDao.setConnection(conn);

		rcnt = borderListDao.getBorderListCount(where);
		close(conn);
		
		return rcnt;
	}
	
	public ArrayList<BorderInfo> getBorderList(String where, int cpage, int psize, String orderBy){
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		AdBorderListDao borderListDao = AdBorderListDao.getInstance();
		borderListDao.setConnection(conn);

		borderList = borderListDao.getBorderList(where, cpage, psize, orderBy);
		close(conn);

		return borderList;
	}
}