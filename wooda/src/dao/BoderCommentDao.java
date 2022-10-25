package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BoderCommentDao {
	private static BoderCommentDao boderCommentDao;
	private Connection conn;
	private BoderCommentDao() {}

	public static BoderCommentDao getInstance() {
		if (boderCommentDao == null) boderCommentDao = new BoderCommentDao();
		return boderCommentDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int commitInsert(BorderComment borderComment) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "insert into t_board_reply(bs_num, mi_nick, br_content, br_ip) " + 
			" values('" + borderComment.getBs_num() + "','" + borderComment.getMi_nick() + "','" + borderComment.getBr_content() + "','" + borderComment.getBr_ip() + "') ";	
			result = stmt.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println("BoderCommentDao 클래스의 commitInsert() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
	
}
