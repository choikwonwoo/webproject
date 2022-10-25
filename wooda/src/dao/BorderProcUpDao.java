package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BorderProcUpDao {
	private static BorderProcUpDao borderProcUpDao;
	private Connection conn;
	private BorderProcUpDao() {}

	public static BorderProcUpDao getInstance() {
		if (borderProcUpDao == null)	borderProcUpDao = new BorderProcUpDao();
		return borderProcUpDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int borderUpdate(BorderInfo borderInfo) {
		int result = 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "update t_board_story set bs_title = '" + borderInfo.getBs_title() + "', bs_start = '" + borderInfo.getBs_start() + "', bs_end = '" + borderInfo.getBs_end() + "', bs_place1 = '" + borderInfo.getBs_place1() + "', bs_place2 = '" + borderInfo.getBs_place2() + "', bs_place3 = '" + borderInfo.getBs_place3() + "', " + 
					" bs_lat1 = '" + borderInfo.getBs_lat1() + "', bs_lat2 = '" + borderInfo.getBs_lat2() + "', bs_lat3 = '" + borderInfo.getBs_lat3() + "', bs_lng1 = '" + borderInfo.getBs_lng1() + "', bs_lng2 = '" + borderInfo.getBs_lng2() + "', bs_lng3 = '" + borderInfo.getBs_lng3() + "', bs_area = '" + borderInfo.getBs_area() + "', bs_visit = '" + borderInfo.getBs_visit() + "', bs_gender = '" + borderInfo.getBs_gender() + "', " + 
					" bs_img1 = '" + borderInfo.getBs_img1() + "', " + 
					" bs_content = '" +  borderInfo.getBs_content() + "', bs_astatus = '" + borderInfo.getBs_astatus() + "', bs_mstatus = '" + borderInfo.getBs_cstatus() + "' where bs_num = " + borderInfo.getBs_num();
				result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("BorderProcUpDao 클래스의 borderUpdate() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}
