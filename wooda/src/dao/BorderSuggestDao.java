package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BorderSuggestDao {
	private static BorderSuggestDao borderSuggestDao;
	private Connection conn;
	private BorderSuggestDao() {}

	public static BorderSuggestDao getInstance() {
		if (borderSuggestDao == null)	borderSuggestDao = new BorderSuggestDao();
		return borderSuggestDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<BorderInfo> getMainList(String where){
		ArrayList<BorderInfo> mainList = new ArrayList<BorderInfo>();
		Statement stmt = null;
		ResultSet rs = null;
		BorderInfo bi = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_board_story " + where;
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bi = new BorderInfo();
				bi.setBs_title(rs.getString("bs_title"));
				bi.setBs_read(rs.getInt("bs_read"));
				bi.setBs_like(rs.getInt("bs_like"));
				bi.setBs_img1(rs.getString("bs_img1"));
				bi.setBs_place1(rs.getString("bs_place1"));
				bi.setBs_place2(rs.getString("bs_place2"));
				bi.setBs_place3(rs.getString("bs_place3"));
				mainList.add(bi);
			}
		}catch(Exception e) {
			System.out.println("BorderSuggestDao 클래스의 getMainList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return mainList;
	}

}
