package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MemberProcSvc {
	public int MemberProc(MemberInfo memberInfo) {
		Connection conn = getConnection();
		MemberProcDao memberProcDao = MemberProcDao.getInstance();
		memberProcDao.setConnection(conn);
		
		int result = 0;
		
		result = memberProcDao.memberInsert(memberInfo);
		
		
		if (result == 1)	commit(conn);
		else			rollback(conn);
		close(conn);

		return result;
	}
}


