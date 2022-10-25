package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class CoupleInfoSvc {
	public CoupleInfo getCoupleInfo(String ci_idx, String mi_mail) {
		Connection conn = getConnection();
		ProfileDao profileDao = ProfileDao.getInstance();
		profileDao.setConnection(conn);
		CoupleInfo ci = null;
		ci = profileDao.getCoupleInfo(ci_idx, mi_mail);

		if(ci != null) {
			commit(conn);
		}
		else rollback(conn);
		close(conn);
		
		return ci;
	}
}
