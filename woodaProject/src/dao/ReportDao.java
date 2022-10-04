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
		int result = 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "Insert into t_board_112(bs_num, mi_mail, b1_content, b1_ip) values('" + reportInfo.getBs_num() + "', 'wooda@naver.com', '" + reportInfo.getB1_content() + "', '123.123.123.123')";
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
