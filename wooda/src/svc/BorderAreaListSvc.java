package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BorderAreaListSvc {
	public int getBoderListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		BorderAreaListDao borderAreaListDao = BorderAreaListDao.getInstance();
		borderAreaListDao.setConnection(conn);

		rcnt = borderAreaListDao.getBoderListCount(where);
		close(conn);
		
		return rcnt;
	}
	
	public ArrayList<BorderInfo> getBoderList(String where, int cpage, int psize, String orderBy){
		ArrayList<BorderInfo> AreaList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		BorderAreaListDao borderAreaListDao = BorderAreaListDao.getInstance();
		borderAreaListDao.setConnection(conn);

		AreaList = borderAreaListDao.getBoderList(where, cpage, psize, orderBy);
		close(conn);

		return AreaList;
	}
}
