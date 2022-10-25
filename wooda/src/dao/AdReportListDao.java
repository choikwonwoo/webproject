package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdReportListDao {
	private static AdReportListDao reportListDao; 	
	private Connection conn; 
	private AdReportListDao() {}

	public static AdReportListDao getInstance() {
		if (reportListDao == null) reportListDao = new AdReportListDao();
		return reportListDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int getReportListCount(String where) {
		Statement stmt = null;	
		ResultSet rs = null;	
		int rcnt = 0;			

		try {
			stmt = conn.createStatement();
			String sql = "select count(*) cnt from t_board_112 " + where;
			rs = stmt.executeQuery(sql);
			if (rs.next())	rcnt = rs.getInt("cnt");

		} catch(Exception e) {
			System.out.println("ReportListDao 클래스의 getReportListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}

		return rcnt;
	}

	public ArrayList<ReportInfo> getReportList(String where, int cpage, int psize) {
		ArrayList<ReportInfo> reportInfo = new ArrayList<ReportInfo>();
	
		Statement stmt = null;
		ResultSet rs = null;	// select 유무로 rs 판단
		ReportInfo ri = null;
		
		
		try {
			stmt = conn.createStatement();
			String sql = "select a.b1_idx, b.bs_title, a.b1_content, a.mi_mail, a.b1_ip, b.bs_isview from t_board_112 a, t_board_story b " +
						 " where a.bs_num = b.bs_num " + where + " order by b.bs_isview desc " + " limit " + ((cpage - 1) * psize) + ", " + psize;
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ri = new ReportInfo();
				ri.setB1_idx(rs.getInt("b1_idx"));
				ri.setBs_title(rs.getString("bs_title"));
				ri.setB1_content(rs.getString("b1_content"));
				ri.setMi_mail(rs.getString("mi_mail"));
				ri.setB1_ip(rs.getString("b1_ip"));
				reportInfo.add(ri);
			}
			
		} catch (Exception e) {
			System.out.println("ReportListDao 클래스의 getReportList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return reportInfo;
	}
}














