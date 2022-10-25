package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdMemberListDao {
	private static AdMemberListDao memberListDao; 	
	private Connection conn; 
	private AdMemberListDao() {}

	public static AdMemberListDao getInstance() {
		if (memberListDao == null) memberListDao = new AdMemberListDao();
		return memberListDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getMemberListCount(String where) {
		// 게시판 목록에서 사용할 레코드(게시글) 총 개수를 리턴하는 메소드
			Statement stmt = null;	// 쿼리를 DB로 보낼 객체 선언
			ResultSet rs = null;	// 쿼리(select)의 결과를 저장할 객체 선언
			int rcnt = 0;			// 현 메소드의 결과를 저장할 변수로 리턴할 값이기도 함

			try {
				stmt = conn.createStatement();
				String sql = "select count(*) cnt from t_member_info " + where;
				rs = stmt.executeQuery(sql);
				if (rs.next())	rcnt = rs.getInt("cnt");

			} catch(Exception e) {
				System.out.println("MemberListDao 클래스의 getMemberListCount() 메소드 오류");
				e.printStackTrace();
			} finally {
				close(rs);	close(stmt);
			}

			return rcnt;
		}

	public ArrayList<MemberList> getMemberList(String where, int cpage, int psize){
		ArrayList<MemberList> memberList = new ArrayList<MemberList>();
		Statement stmt = null;
		ResultSet rs = null;	// select 유무로 rs 판단
		MemberList ml = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select mi_mail, mi_nick, mi_pw, mi_name, mi_join, mi_status from t_member_info" +
						  where + " order by mi_status desc " + " limit " + ((cpage - 1) * psize) + ", " + psize;
			
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ml = new MemberList();
				// 한 명의 회원 정보를 저장할 인스턴스로 생성후 ArrayList인 memberTestList에 저장됨 
				ml.setMi_mail(rs.getString("mi_mail"));
				ml.setMi_nick(rs.getString("mi_nick"));
				ml.setMi_pw(rs.getString("mi_pw"));
				ml.setMi_name(rs.getString("mi_name"));
				ml.setMi_join(rs.getString("mi_join"));
				ml.setMi_status(rs.getString("mi_status"));
				memberList.add(ml);
				
			}
			
		} catch (Exception e) {
			System.out.println("MemberListDao 클래스의 getMemberList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return memberList;
	}
}
