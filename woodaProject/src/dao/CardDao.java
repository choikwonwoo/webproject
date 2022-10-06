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
		
		try {
			stmt = conn.createStatement();
			String sql = "insert into t_couple_mailing (cm_mail_s, cm_mail_r, cm_content, cm_jdate) values('" + cm.getCm_mail_s() + "', '" + cm.getCm_mail_r() + "', '" + cm.getCm_content() + "', '" + cm.getCm_sdate() + "') ";
			System.out.println(sql);
			result = stmt.executeUpdate(sql);
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
			close(stmt);
		}
		
		return result;
	}
}
