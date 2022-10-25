package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BorderProcDelDao {
	private static BorderProcDelDao borderProcDelDao;
	private Connection conn;
	private BorderProcDelDao() {}

	public static BorderProcDelDao getInstance() {
		if (borderProcDelDao == null)	borderProcDelDao = new BorderProcDelDao();
		return borderProcDelDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int deleteBorder(int bs_num, String mi_mail) {
		int result = 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "update t_board_story set bs_astatus = 'n', bs_cstatus ='n', bs_mstatus = 'n' where bs_num = " + bs_num + " and mi_mail = '" + mi_mail + "' ";
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("BorderProcDelDao 클래스의 deleteBorder() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}
