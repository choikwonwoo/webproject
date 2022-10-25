package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdLoginDao {
// �α��ο� ���õ� ���� �۾��� ó���ϴ� Ŭ����
	private static AdLoginDao loginDao;	// ������ LoginDao�� �ν��Ͻ��� ������ ���
	private Connection conn;
	private AdLoginDao() {}
	// �⺻ �����ڸ� private���� �����Ͽ� �ܺο��� �Ժη� �ν��Ͻ��� �������� ���ϰ� ����
	
	public static AdLoginDao getInstance() {
	// LoginDaoŬ������ �ν��Ͻ��� ������ �ִ� �޼ҵ�� �̹� �ν��Ͻ��� ������ ��� ������ �ν��Ͻ��� ����
	// LoginDaoŬ������ �ν��Ͻ��� �ϳ��� �����Ͽ� ����ϰ� �ϴ� �̱��� ���
		if (loginDao == null)	loginDao = new AdLoginDao();
		// �̹� ������ LoginDaoŬ������ �ν��Ͻ��� ������ ���Ӱ� LoginDaoŬ������ �ν��Ͻ��� ����

		return loginDao;
	}
	
	public void setConnection(Connection conn) {
	// �� DaoŬ�������� ����� Ŀ�ؼ� ��ü�� �޾ƿͼ� ������ �ִ� �޼ҵ�
		this.conn = conn;
	}

	public AdminInfo getLoginAdmin(String uid, String pwd) {
	// �޾ƿ� ���̵�� ��ȣ�� �α��� �۾��� ó���� �� ȸ�������� AdminInfo�� �ν��Ͻ��� ��� �����ϴ� �޼ҵ�
		Statement stmt = null;	// �α��� ó���� ���� ������ DB�� ���� Statement ����
		ResultSet rs = null;	// �α��� ó���� ���� ������ ���� ����� ���� ResultSet ����
		AdminInfo loginInfo = null;	// rs�� ��ƿ� ����� ������ AdminInfo�� �ν��Ͻ� ����

		try {
			String sql = "select * from t_admin_info where " + 
				" ai_id = '" + uid + "' and ai_pw = '" + pwd + "' ";
			stmt = conn.createStatement();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {	// rs�� �����Ͱ� ������(�α����� �������� ���)
				loginInfo = new AdminInfo();	// ȸ�� ������ ������ �ν��Ͻ� ����
				loginInfo.setAi_id(uid);	
				loginInfo.setAi_pw(pwd);
			}	// rs�� ������� else ���� loginInfo �ν��Ͻ��� null�� �ִ� ���·� ������

		} catch(Exception e) {
			System.out.println("LoginDao Ŭ������ getLoginAdmin() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}

		return loginInfo;
	}
}
