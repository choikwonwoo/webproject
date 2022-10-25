package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BorderFormUpDao {
	private static BorderFormUpDao borderFormUpDao;
	private Connection conn;
	private BorderFormUpDao() {}

	public static BorderFormUpDao getInstance() {
		if (borderFormUpDao == null)	borderFormUpDao = new BorderFormUpDao();
		return borderFormUpDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public BorderInfo getBorderInfo(String where) {
		BorderInfo borderInfo = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_board_story " + where;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				borderInfo = new BorderInfo();
				borderInfo.setBs_num(rs.getInt("bs_num"));
				borderInfo.setBs_title(rs.getString("bs_title"));
				borderInfo.setBs_start(rs.getString("bs_start"));
				borderInfo.setBs_end(rs.getString("bs_end"));
				borderInfo.setBs_place1(rs.getString("bs_place1"));
				borderInfo.setBs_place2(rs.getString("bs_place2"));
				borderInfo.setBs_place3(rs.getString("bs_place3"));
				borderInfo.setBs_area(rs.getString("bs_area"));
				borderInfo.setBs_gender(rs.getString("bs_gender"));
				borderInfo.setBs_visit(rs.getString("bs_visit"));
				borderInfo.setBs_img1(rs.getString("bs_img1"));
				borderInfo.setBs_img2(rs.getString("bs_img2"));
				borderInfo.setBs_img3(rs.getString("bs_img3"));
				borderInfo.setBs_img4(rs.getString("bs_img4"));
				borderInfo.setBs_img5(rs.getString("bs_img5"));
				borderInfo.setBs_content(rs.getString("bs_content"));
				borderInfo.setBs_astatus(rs.getString("bs_astatus"));
				borderInfo.setBs_cstatus(rs.getString("bs_cstatus"));
				borderInfo.setBs_lat1(rs.getDouble("bs_lat1"));
				borderInfo.setBs_lat2(rs.getDouble("bs_lat2"));
				borderInfo.setBs_lat3(rs.getDouble("bs_lat3"));
				borderInfo.setBs_lng1(rs.getDouble("bs_lng1"));
				borderInfo.setBs_lng2(rs.getDouble("bs_lng2"));
				borderInfo.setBs_lng3(rs.getDouble("bs_lng3"));
			}
		}catch(Exception e) {
			System.out.println("BorderFormUpDao 클래스의 getBorderInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		
		return borderInfo;
	}
}
