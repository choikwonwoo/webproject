package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginDao {
// 로그인에 관련된 쿼리 작업을 처리하는 클래스
   private static LoginDao LoginDao;   // 생성된 LoginDao의 인스턴스를 저장할 멤버
   private Connection conn;
   private LoginDao() {}
   // 기본 생성자를 private으로 선언하여 외부에서 함부로 인스턴스를 생성하지 못하게 막음 
   
   public static LoginDao getInstance() {
   // LoginDao클래스의 인스턴스를 생성해 주는 메소드로 이미 인스턴스가 존재할 경우 기존의 인스턴스를 리턴
   // LoginDao클래스의 인스턴스를 하나만 생성하여 사용하게 하는 싱클톤 방식
      if(LoginDao == null) {
         LoginDao = new LoginDao();
         // 이미 생성된 LoginDao클래스의 인스턴스가 없으면 새롭게 LoginDao클래스의 인스턴스를 생성
      }
      return LoginDao;
   }
   
   public void setConnection(Connection conn) {
   // 현 Dao클래스에서 사용할 Connection객체를 받아와서 생성해 주는 메소드
      this.conn = conn;
   }
   
   public MemberInfo getLoginMember(String uid, String pwd) {
   // 받아온 아이디와 암호로 로그인 작업을 처리한 후 회원정보를 MemberInfo형 인스턴스에 담아 리턴하는 메소드
      Statement stmt = null;   // 로그인 처리를 위한 쿼리를 DB로 보낼 Statement 선언
      ResultSet rs = null;   // 로그인 처리를 위한 쿼리의 실행 결과를 받을 ResultSet 선언
      MemberInfo loginInfo = null;   // rs에 담아온 결과를 저장할 MemberInfo형 인스턴스 선언
      
      try {
         String sql ="select * from t_member_info where mi_status = 'a' " + 
               " and mi_mail = '" + uid + "' and mi_pw = '" + pwd + "' ";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if(rs.next()) {   //rs에 데이터가 있으면(로그인이 성공했을 경우)
            loginInfo = new MemberInfo();   //회원정보를 저장할 인스턴스 생성
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
            
         }   // rs가 비었으면 else 없이 loginInfo 인스턴스에 null이 있는 상태로 리턴함   
      }catch(Exception e) {
         System.out.println("LoginDao 클래스의 getLoginMember() 메소드 오류");
         e.printStackTrace();
      }finally {
         close(rs);    close(stmt);
      }
      
      return loginInfo;
   }
}