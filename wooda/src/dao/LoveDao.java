package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class LoveDao {
	private static LoveDao loveDao;   
	private Connection conn;
	private LoveDao() {}
	   // 기본 생성자를 private으로 선언하여 외부에서 함부로 인스턴스를 생성하지 못하게 막음 
	   
	public static LoveDao getInstance() {
	   if(loveDao == null) {
		   loveDao = new LoveDao();
	   }
	   return loveDao;
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int loveInsert(int bs_num, int bs_like, String mi_mail) {
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "insert into t_board_like(bs_num, mi_mail, bl_like) values("+ bs_num +", '" + mi_mail + "', "+ count + 1 + ")";
			result = stmt.executeUpdate(sql);
				if(result == 1) {
					sql = "update t_board_story set bs_like = " + (bs_like + 1) + " where bs_num = " + bs_num;
					result = stmt.executeUpdate(sql);
				}
		}catch(Exception e) {
		   System.out.println("CartDao 의 cartInsert 에서 메소드 오류");
		   e.printStackTrace();
		}finally {
		   close(stmt);
		}
		
		return result;
	}

	public int loveDelete(int bs_num, int bs_like) {
		int result = 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
				String sql = "delete from t_board_like where bs_num = " + bs_num + " and  mi_mail = 'wooda@naver.com' ";
				result = stmt.executeUpdate(sql);
				if(result == 1) {
					sql = "update t_board_story set bs_like = " + (bs_like - 1) + " where bs_num = " + bs_num;
					result = stmt.executeUpdate(sql);
				}
		}catch(Exception e) {
		   System.out.println("CartDao 의 cartInsert 에서 메소드 오류");
		   e.printStackTrace();
		}finally {
		   close(stmt);
		}
		
		return result;
	}
}
