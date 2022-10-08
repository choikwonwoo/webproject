package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class CardDao {
	private static CardDao cardDao;
	private Connection conn;
	private CardDao() {}

	public static CardDao getInstance() {
		if (cardDao == null)	cardDao = new CardDao();
		return cardDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public int sendUp(CoupleMailing cm) {
		Statement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			
			
			
			
			// 연인 이름 가져오는 쿼리
			sql = "select mi_name from t_member_info where mi_mail =  + '" + cm.getCm_mail_r() + "'" ;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// 상대방 이멜이 존재하면
				String mi_name_r = rs.getString("mi_name");
				sql = "insert into t_couple_mailing (cm_mail_s, mi_name_s, cm_mail_r, mi_name_r, cm_content, cm_jdate) values('" + cm.getCm_mail_s() + "', '" + cm.getMi_name_s() + "', '" + cm.getCm_mail_r() + "', '" + mi_name_r + "', '" + cm.getCm_content() + "', '" + cm.getCm_sdate() + "') ";
				System.out.println(sql);
				result += stmt.executeUpdate(sql);
			}else {
				// 상대 이멜 없으면
				return 0;
			}
			
			
			if (result>0) {
				sql = "update t_member_info set mi_dding = 'a' where mi_mail =  '" + cm.getCm_mail_s() + "'";
				result += stmt.executeUpdate(sql);
				sql = "update t_member_info set mi_dding = 'b' where mi_mail =  '" + cm.getCm_mail_r() + "'";
				result += stmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			System.out.println("CardDao 클래스의 sendUp() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return result;
	}

	public CoupleMailing getCoupleMail(String mimail) {
		Statement stmt = null;
		ResultSet rs = null;
		CoupleMailing cm = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			sql = "select * from t_couple_mailing where mi_name_r =  + '" + mimail + "'" ;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				cm.setCm_mail_s(rs.getString("cm_mail_s"));
				cm.setMi_name_s(rs.getString("mi_name_s"));
				cm.setCm_content(rs.getString("cm_content"));
				cm.setCm_jdate(rs.getString("cm_jdate"));
				cm.setCm_isok(rs.getString("cm_isok"));
				cm.setCm_sdate(rs.getString("cm_sdate"));
			}
		}catch(Exception e) {
			System.out.println("CardDao 클래스의 getCoupleMail() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return cm;
	}
}
