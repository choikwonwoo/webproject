package dao;

import vo.ReportInfo;import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class ReportDao {
	private static ReportDao reportDao;
	private Connection conn;
	private ReportDao() {}

	public static ReportDao getInstance() {
		if (reportDao == null)	reportDao = new ReportDao();
		return reportDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public int reportInsert(ReportInfo reportInfo) {
		int idx = 1;	int result = 0;
		Statement stmt = null;
		ResultSet rs = null;		
		
		try {
			stmt = conn.createStatement();
			String sql = "select max(b1_idx) from t_board_112";
			rs = stmt.executeQuery(sql);
			if(rs.next()) 	idx = rs.getInt(1) + 1;
			
			
			sql = "Insert into t_board_112(b1_idx, bs_num, mi_mail, b1_content, b1_ip) values(" + idx + ", '" + reportInfo.getBs_num() + "', 'wooda@naver.com', '" + reportInfo.getB1_content() + "', '" + reportInfo.getB1_ip() + "')";
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("ReportDao 클래스의 reportInsert() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		
		return result;
	}
}
