package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BorderSuggestSvc {
	public ArrayList<BorderInfo> getMainList(String where){
		ArrayList<BorderInfo> mainList = new ArrayList<BorderInfo>();
		Connection conn = getConnection();
		BorderSuggestDao borderSuggestDao = BorderSuggestDao.getInstance();
		borderSuggestDao.setConnection(conn);

		mainList = borderSuggestDao.getMainList(where);
		close(conn);

		return mainList;
	}
}
