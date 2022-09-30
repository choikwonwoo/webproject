package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MyPageSvc {
	public MemberInfo getMemberInfo(String mimail) {
		MemberInfo mi = new MemberInfo();
		MyPageDao myPageDao = MyPageDao.getInstance();
		Connection conn = getConnection();
		myPageDao.setConnection(conn);
		mi = myPageDao.getMemberInfo(mimail);
		return mi;
	}
	
}
