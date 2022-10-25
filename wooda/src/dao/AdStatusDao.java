package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdStatusDao {
	private static AdStatusDao statusDao;
	private Connection conn;
	private AdStatusDao() {}

	public static AdStatusDao getInstance() {
		if (statusDao == null)	statusDao = new AdStatusDao();
		return statusDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int statusUpdate(String where) {
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "update t_member_info set mi_status = if(mi_status='Y', 'N', 'Y') " + where;
			
			result = stmt.executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println("StatusDao 클래스의 statusUpdate() 메소드 오류");
			
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
	
	public int memberDelete(String where) {
		Statement stmt = null;
		int result = 0;
		
		try {
			String sql = "delete from t_member_info " + where;
			System.out.println(sql);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);

		} catch(Exception e) {
			System.out.println("StatusDao 클래스의 memberDelete() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return result;
	}
}
