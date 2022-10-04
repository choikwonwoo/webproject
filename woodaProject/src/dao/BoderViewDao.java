package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BoderViewDao {
	private static BoderViewDao boderViewDao;
	private Connection conn;
	private BoderViewDao() {}

	public static BoderViewDao getInstance() {
		if (boderViewDao == null)	boderViewDao = new BoderViewDao();
		return boderViewDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int readUpdate(int idx) {
		// 지정한 게시글의 조회수를 증가시키는 메소드
			Statement stmt = null;
			int result = 0;

			try {
				stmt = conn.createStatement();
				String sql = "update t_board_story set bs_read = bs_read + 1 " + 
				" where bs_num = " + idx;
				result = stmt.executeUpdate(sql);

			} catch(Exception e) {
				System.out.println("BoderViewDao 클래스의 readUpdate() 메소드 오류");
				e.printStackTrace();
			} finally {
				close(stmt);
			}

			return result;
		}
	
	public BorderInfo getBoderInfo(int idx) {
		BorderInfo borderInfo = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_board_story where bs_num = " + idx;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				borderInfo = new BorderInfo();
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
				borderInfo.setBs_content(rs.getString("bs_content"));
				borderInfo.setBs_lat1(rs.getDouble("bs_lat1"));
				borderInfo.setBs_lat2(rs.getDouble("bs_lat2"));
				borderInfo.setBs_lat3(rs.getDouble("bs_lat3"));
				borderInfo.setBs_lng1(rs.getDouble("bs_lng1"));
				borderInfo.setBs_lng2(rs.getDouble("bs_lng2"));
				borderInfo.setBs_lng3(rs.getDouble("bs_lng3"));
				borderInfo.setBs_read(rs.getInt("bs_read"));
				borderInfo.setMi_nick(rs.getString("mi_nick"));
				borderInfo.setBs_date(rs.getString("bs_date"));
			}
		}catch(Exception e) {
			System.out.println("BoderViewDao 클래스의 getBoderInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return borderInfo;
	}
}
