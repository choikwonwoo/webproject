package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginSvc {
// �α��ο� �ʿ��� ���̵�� ��ȣ�� �޾ƿ� �����Ͻ� ������ ó���ϴ� Ŭ���� db����
	public MemberInfo getLoginMember(String uid, String pwd) {
		Connection conn = getConnection();
		LoginDao loginDao = LoginDao.getInstance();
		loginDao.setConnection(conn);
		
		MemberInfo loginInfo = loginDao.getLoginMember(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
}
