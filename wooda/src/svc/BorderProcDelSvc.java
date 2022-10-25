package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BorderProcDelSvc {
	public int deleteBorder(int bs_num, String mi_mail) {
		int result = 0;
		Connection conn = getConnection();
		BorderProcDelDao borderProcDelDao = BorderProcDelDao.getInstance();
		borderProcDelDao.setConnection(conn);
		
		result = borderProcDelDao.deleteBorder(bs_num, mi_mail);
		if(result == 1) commit(conn);
		else 			rollback(conn);
		close(conn);
		return result;
	}

}
