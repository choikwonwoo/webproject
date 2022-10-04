package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BoderProcInDao {

	private static BoderProcInDao boderProcInDao;
	private Connection conn;
	private BoderProcInDao() {}

	public static BoderProcInDao getInstance() {
		if (boderProcInDao == null)	boderProcInDao = new BoderProcInDao();
		return boderProcInDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int borderInsert(BorderInfo borderInfo) {
		int idx = 1;	int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select max(bs_num) from t_board_story";
			rs = stmt.executeQuery(sql);
			if(rs.next()) 	idx = rs.getInt(1) + 1;
			
			sql = "insert into t_board_story(bs_num, mi_mail, mi_nick, bs_area, bs_title, bs_start, bs_end, bs_content, bs_visit, bs_gender, bs_lat1, bs_lng1, bs_place1, bs_lat2, bs_lng2, bs_place2, bs_lat3, bs_lng3, " + 
					" bs_place3, bs_img1, bs_astatus, bs_cstatus, bs_ip) " + 
					" values (" + idx + ", 'wooda@naver.com', '우다', '"+ borderInfo.getBs_area() +"' , '" + borderInfo.getBs_title() + "' , '" + 
					borderInfo.getBs_start() + "' , '" + borderInfo.getBs_end() + "', '" + borderInfo.getBs_content() + "', '" + borderInfo.getBs_visit() + "', '" + borderInfo.getBs_gender() + "', '" + borderInfo.getBs_lat1() + "' , '" + borderInfo.getBs_lng1() + "' , '" + borderInfo.getBs_place1() + "' , '" + 
					borderInfo.getBs_lat2() + "', '" + borderInfo.getBs_lng2() + "' , '" + borderInfo.getBs_place2() + "' , '" + borderInfo.getBs_lat3() + "' , '" + borderInfo.getBs_lng3() + "' , '" + borderInfo.getBs_place3() + "' , '" + 
					borderInfo.getBs_img1() + "' , '" + borderInfo.getBs_astatus() + "' , '" + borderInfo.getBs_cstatus() + "' , '123.123.123.123' )";
					result = stmt.executeUpdate(sql);
					if(result == 0) return result;
					else			result = idx;
					
		}catch(Exception e) {
			System.out.println("BoderProcInDao 클래스의 borderInser() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return idx;
	}
	
}
