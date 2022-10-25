package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BorderFormUpSvc {
	public BorderInfo getBorderInfo(String where) {
		BorderInfo borderInfo = null;
		Connection conn = getConnection();
		BorderFormUpDao borderFormUpDao = BorderFormUpDao.getInstance();
		borderFormUpDao.setConnection(conn);

		borderInfo = borderFormUpDao.getBorderInfo(where);
		close(conn);
		
		return borderInfo;
	}

}
