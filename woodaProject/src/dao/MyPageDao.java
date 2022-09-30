package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MyPageDao {
	private static MyPageDao myPageDao;
	private Connection conn;
	private MyPageDao() {}
	
	public static MyPageDao getInstance() {
		if (myPageDao == null)	myPageDao = new MyPageDao();
		return myPageDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public MemberInfo getMemberInfo(String mimail) {
		Statement stmt = null;
		ResultSet rs = null;
		MemberInfo mi = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_member_info where mi_mail = " + mimail;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				mi = new MemberInfo();
				mi.setMi_mail(rs.getString("mi_mail"));
				mi.setMi_name(rs.getString("mi_name"));
				mi.setMi_nick(rs.getString("mi_nick"));
				mi.setMi_iscouple(rs.getString("mi_iscouple"));
				mi.setMi_pimg(rs.getString("mi_pimg"));
				mi.setMi_pw(rs.getString("mi_pw"));	
			}
			
		}catch(Exception e){
			System.out.println("MyPageDao 의  getMemberInfo메소드 오류");
			e.printStackTrace();
		}finally {
			close(rs); close(stmt);
		}
		return mi;
	}
}
