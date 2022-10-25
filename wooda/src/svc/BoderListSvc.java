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
	
	public ArrayList<BorderInfo> getBoderList(String where, int cpage, int psize, String orderBy){
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		borderList = boderListDao.getBoderList(where, cpage, psize, orderBy);
		close(conn);

		return borderList;
	}
	public ArrayList<BorderInfo> getCoupleBoderList(String where, int cpage, int psize, String ci_idx, String mail){
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		borderList = boderListDao.getCoupleBoderList(where, cpage, psize, ci_idx, mail);
		close(conn);

		return borderList;
	}
	public int getCoupleBoderListCount(String where, String ci_idx, String mi_mail) {
		int rcnt = 0;
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		rcnt = boderListDao.getCoupleBoderListCount(where, ci_idx, mi_mail);
		close(conn);
		
		return rcnt;
	}
	public String getDiaryPage(String ci_idx, String mi_mail) {
		String rcnt = "";
		Connection conn = getConnection();
		BoderListDao boderListDao = BoderListDao.getInstance();
		boderListDao.setConnection(conn);

		rcnt = boderListDao.getDiaryPage(ci_idx, mi_mail);
		close(conn);
		
		return rcnt;
	}
}
