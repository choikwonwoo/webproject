package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.Statement;

public class AdReportProcDao {
	private static AdReportProcDao reportProcDao;
	private Connection conn;
	private AdReportProcDao() {}

	public static AdReportProcDao getInstance() {
		if (reportProcDao == null)	reportProcDao = new AdReportProcDao();
		return reportProcDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int reportDelete(String where) {
		Statement stmt = null;
		int result = 0;

		try {
			String sql = "delete from t_board_112 " + where;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			System.out.println("ReportProcDao 클래스의 reportDelete() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}

	public int reportUpdate(String where) {
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "update t_board_story a inner join t_board_112 b on a.bs_num = b.bs_num set a.bs_isview = if(a.bs_isview='Y', 'N', 'Y') " + where;
			
			result = stmt.executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println("ReportProcDao 클래스의 reportUpdate() 메소드 오류");
			
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}
