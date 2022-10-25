package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class PresentRequestProcInDao {
	private static PresentRequestProcInDao presentRequestProcInDao;
	private Connection conn;
	private PresentRequestProcInDao() {}

	public static PresentRequestProcInDao getInstance() {
		if (presentRequestProcInDao == null) presentRequestProcInDao = new PresentRequestProcInDao();
		return presentRequestProcInDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public int presentRequestInsert(GiftInfo giftInfo) {
		int result = 0;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "insert into t_gift_info(mi_mail, gi_brand, gi_name, gi_price, gi_tag1, gi_content, gi_img1, gi_img2, gi_img3, gi_isview) " + 
					" values ('" + giftInfo.getMi_mail() + "', '" + giftInfo.getGi_brand() + "', '" + giftInfo.getGi_name() + "', '" + 
					giftInfo.getGi_price()  + "', '" + giftInfo.getGi_tag1() + "', '" + giftInfo.getGi_content() + "', '" + giftInfo.getGi_img1() + "', '" + giftInfo.getGi_img2() + "', '" + giftInfo.getGi_img3() + "', 'n')";
			result = stmt.executeUpdate(sql);			
		}catch(Exception e) {
			System.out.println("PresentRequestProcInDao 클래스의 presentRequestInsert() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
				
	}
}
