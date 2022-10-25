package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdLoginSvc {
// �α��ο� �ʿ��� ���̵�� ��ȣ�� �޾ƿ� ����Ͻ� ������ ó��(DB�۾� ����)�ϴ� Ŭ����
	public AdminInfo getLoginAdmin(String uid, String pwd) {
		Connection conn = getConnection();
		AdLoginDao loginDao = AdLoginDao.getInstance();
		loginDao.setConnection(conn);
		
		AdminInfo loginInfo = loginDao.getLoginAdmin(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
}
