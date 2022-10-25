package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class PresentRequestProcInSvc {
	public int presentRequestInsert(GiftInfo giftInfo) {
		int result = 0;
		Connection conn = getConnection();
		PresentRequestProcInDao presentRequestProcInDao = PresentRequestProcInDao.getInstance();
		presentRequestProcInDao.setConnection(conn);
		
		result = presentRequestProcInDao.presentRequestInsert(giftInfo);
		if(result == 1) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}
}
