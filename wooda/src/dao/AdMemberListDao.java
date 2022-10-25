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
		// �Խ��� ��Ͽ��� ����� ���ڵ�(�Խñ�) �� ������ �����ϴ� �޼ҵ�
			Statement stmt = null;	// ������ DB�� ���� ��ü ����
			ResultSet rs = null;	// ����(select)�� ����� ������ ��ü ����
			int rcnt = 0;			// �� �޼ҵ��� ����� ������ ������ ������ ���̱⵵ ��

			try {
				stmt = conn.createStatement();
				String sql = "select count(*) cnt from t_member_info " + where;
				rs = stmt.executeQuery(sql);
				if (rs.next())	rcnt = rs.getInt("cnt");

			} catch(Exception e) {
				System.out.println("MemberListDao Ŭ������ getMemberListCount() �޼ҵ� ����");
				e.printStackTrace();
			} finally {
				close(rs);	close(stmt);
			}

			return rcnt;
		}

	public ArrayList<MemberList> getMemberList(String where, int cpage, int psize){
		ArrayList<MemberList> memberList = new ArrayList<MemberList>();
		Statement stmt = null;
		ResultSet rs = null;	// select ������ rs �Ǵ�
		MemberList ml = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select mi_mail, mi_nick, mi_pw, mi_name, mi_join, mi_status from t_member_info" +
						  where + " order by mi_status desc " + " limit " + ((cpage - 1) * psize) + ", " + psize;
			
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ml = new MemberList();
				// �� ���� ȸ�� ������ ������ �ν��Ͻ��� ������ ArrayList�� memberTestList�� ����� 
				ml.setMi_mail(rs.getString("mi_mail"));
				ml.setMi_nick(rs.getString("mi_nick"));
				ml.setMi_pw(rs.getString("mi_pw"));
				ml.setMi_name(rs.getString("mi_name"));
				ml.setMi_join(rs.getString("mi_join"));
				ml.setMi_status(rs.getString("mi_status"));
				memberList.add(ml);
				
			}
			
		} catch (Exception e) {
			System.out.println("MemberListDao Ŭ������ getMemberList() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return memberList;
	}
}
