package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class MemberProcDao {
	private static MemberProcDao memberProcDao;
	private Connection conn;
	private MemberProcDao() {}
	
	public static MemberProcDao getInstance() {
		if (memberProcDao == null)	memberProcDao = new MemberProcDao();
		return memberProcDao;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int memberInsert(MemberInfo memberInfo) {
	// ����ڰ� �Է��� �����͵�� ȸ�� ������ �ϴ� �޼ҵ�
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "insert into t_member_info(mi_mail, mi_name, mi_nick, mi_pw, " + 
				" mi_birth, mi_gender) values " + 
				"(?, ?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getMi_mail());
			System.out.println(memberInfo.getMi_mail() + "di");
			pstmt.setString(2, memberInfo.getMi_pw());
			pstmt.setString(3, memberInfo.getMi_name());
			pstmt.setString(4, memberInfo.getMi_nick());
			pstmt.setString(5, memberInfo.getMi_gender());
			pstmt.setString(6, memberInfo.getMi_birth());
			result = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("MemberProcDao Ŭ������ memberInsert() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}