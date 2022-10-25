package dao;

import static db.JdbcUtil.*;
import java.sql.*;


public class DupIdDao {
	private static DupIdDao dupIdDao;   // ������ LoginDao�� �ν��Ͻ��� ������ ���
	   private Connection conn;
	   private DupIdDao() {}
	   // �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ���� 
	   
	   public static DupIdDao getInstance() {
	   // LoginDaoŬ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ��� ������ �ν��Ͻ��� ����
	   // LoginDaoŬ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� ��Ŭ�� ���
	      if(dupIdDao == null) {
	    	  dupIdDao = new DupIdDao();
	         // �̹� ������ LoginDaoŬ������ �ν��Ͻ��� ������ ���Ӱ� LoginDaoŬ������ �ν��Ͻ��� ����
	      }
	      return dupIdDao;
	   }
	   public void setConnection(Connection conn) {
		   // �� DaoŬ�������� ����� Connection��ü�� �޾ƿͼ� ������ �ִ� �޼ҵ�
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
			   System.out.println("DupIdDaoŬ������ chkDupID ����");
			   e.printStackTrace();
		   } finally {
			   close(rs); close(stmt);
		   }
		   return result;
	   }
}
