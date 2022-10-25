package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdMemberListSvc {
	public int getMemberListCount(String where) {
		int rcnt = 0;
		Connection conn = getConnection();
		AdMemberListDao memberListDao = AdMemberListDao.getInstance();
		memberListDao.setConnection(conn);

		rcnt = memberListDao.getMemberListCount(where);
		close(conn);

		return rcnt;
	}
	
	public ArrayList<MemberList> getMemberList(String where, int cpage, int psize) {
		ArrayList<MemberList> memberList = new ArrayList<MemberList>();
		Connection conn = getConnection();
		AdMemberListDao memberListDao = AdMemberListDao.getInstance();
		memberListDao.setConnection(conn);

		memberList = memberListDao.getMemberList(where, cpage, psize);
		close(conn);

		return memberList;
	}
}
