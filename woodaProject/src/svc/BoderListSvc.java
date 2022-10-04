package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BoderListSvc {
	public int getBoderListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		rcnt = boderListDao.getBoderListCount(where);
		close(conn);
		
		return rcnt;
	}
	
	public ArrayList<BorderInfo> getBoderList(String where, int cpage, int psize){
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		borderList = boderListDao.getBoderList(where, cpage, psize);
		close(conn);

		return borderList;
	}
}
