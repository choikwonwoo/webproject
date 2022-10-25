package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginDao {
// �α��ο� ���õ� ���� �۾��� ó���ϴ� Ŭ����
   private static LoginDao LoginDao;   // ������ LoginDao�� �ν��Ͻ��� ������ ���
   private Connection conn;
   private LoginDao() {}
   // �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ���� 
   
   public static LoginDao getInstance() {
   // LoginDaoŬ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ��� ������ �ν��Ͻ��� ����
   // LoginDaoŬ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� ��Ŭ�� ���
      if(LoginDao == null) {
         LoginDao = new LoginDao();
         // �̹� ������ LoginDaoŬ������ �ν��Ͻ��� ������ ���Ӱ� LoginDaoŬ������ �ν��Ͻ��� ����
      }
      return LoginDao;
   }
   
   public void setConnection(Connection conn) {
   // �� DaoŬ�������� ����� Connection��ü�� �޾ƿͼ� ������ �ִ� �޼ҵ�
      this.conn = conn;
   }
   
   public MemberInfo getLoginMember(String uid, String pwd) {
   // �޾ƿ� ���̵�� ��ȣ�� �α��� �۾��� ó���� �� ȸ�������� MemberInfo�� �ν��Ͻ��� ��� �����ϴ� �޼ҵ�
      Statement stmt = null;   // �α��� ó���� ���� ������ DB�� ���� Statement ����
      ResultSet rs = null;   // �α��� ó���� ���� ������ ���� ����� ���� ResultSet ����
      MemberInfo loginInfo = null;   // rs�� ��ƿ� ����� ������ MemberInfo�� �ν��Ͻ� ����
      
      try {
         String sql ="select * from t_member_info where mi_status = 'a' " + 
               " and mi_mail = '" + uid + "' and mi_pw = '" + pwd + "' ";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if(rs.next()) {   //rs�� �����Ͱ� ������(�α����� �������� ���)
            loginInfo = new MemberInfo();   //ȸ�������� ������ �ν��Ͻ� ����
            loginInfo.setMi_mail(uid); 
            loginInfo.setMi_nick(rs.getString("mi_nick"));
            loginInfo.setMi_pw(pwd);
            loginInfo.setMi_name(rs.getString("mi_name"));
            loginInfo.setMi_gender(rs.getString("mi_gender"));
            loginInfo.setMi_birth(rs.getString("mi_birth"));
            loginInfo.setMi_iscouple(rs.getString("mi_iscouple"));
            loginInfo.setMi_mail(rs.getString("mi_mail"));
            loginInfo.setMi_pimg(rs.getString("mi_pimg"));
            loginInfo.setMi_join(rs.getString("mi_join"));
            loginInfo.setMi_last(rs.getString("mi_last"));
            loginInfo.setMi_status(rs.getString("mi_status"));
            loginInfo.setCi_idx(rs.getString("ci_idx"));
            loginInfo.setMi_dding(rs.getString("mi_dding"));
            
         }   // rs�� ������� else ���� loginInfo �ν��Ͻ��� null�� �ִ� ���·� ������   
      }catch(Exception e) {
         System.out.println("LoginDao Ŭ������ getLoginMember() �޼ҵ� ����");
         e.printStackTrace();
      }finally {
         close(rs);    close(stmt);
      }
      
      return loginInfo;
   }
}