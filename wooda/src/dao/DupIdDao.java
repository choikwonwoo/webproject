package dao;

import static db.JdbcUtil.*;
import java.sql.*;


public class DupIdDao {
	private static DupIdDao dupIdDao;   // 생성된 LoginDao의 인스턴스를 저장할 멤버
	   private Connection conn;
	   private DupIdDao() {}
	   // 기본 생성자를 private으로 선언하여 외부에서 함부로 인스턴스를 생성하지 못하게 막음 
	   
	   public static DupIdDao getInstance() {
	   // LoginDao클래스의 인스턴스를 생성해 주는 메소드로 이미 인스턴스가 존재할 경우 기존의 인스턴스를 리턴
	   // LoginDao클래스의 인스턴스를 하나만 생성하여 사용하게 하는 싱클톤 방식
	      if(dupIdDao == null) {
	    	  dupIdDao = new DupIdDao();
	         // 이미 생성된 LoginDao클래스의 인스턴스가 없으면 새롭게 LoginDao클래스의 인스턴스를 생성
	      }
	      return dupIdDao;
	   }
	   public void setConnection(Connection conn) {
		   // 현 Dao클래스에서 사용할 Connection객체를 받아와서 생성해 주는 메소드
		      this.conn = conn;
		   }
	   public int chkDupId(String uid) {
		   Statement stmt = null;
		   ResultSet rs = null;
		   int result =0;
		   
		   try {
			   String sql = "select count(*) from t_member_info where mi_mail = '" + uid + "'";
			   stmt = conn.createStatement();
			   rs = stmt.executeQuery(sql);
			   rs.next();
			   result = rs.getInt(1);
			   
		   } catch(Exception e) {
			   System.out.println("DupIdDao클래스의 chkDupID 오류");
			   e.printStackTrace();
		   } finally {
			   close(rs); close(stmt);
		   }
		   return result;
	   }
}
