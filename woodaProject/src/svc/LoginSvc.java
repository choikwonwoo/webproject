package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class LoginSvc {
// 로그인에 필요한 아이디와 암호를 받아와 비지니스 로직을 처리하는 클래스 db제외
	public MemberInfo getLoginMember(String uid, String pwd) {
		Connection conn = getConnection();
		LoginDao loginDao = LoginDao.getInstance();
		loginDao.setConnection(conn);
		
		MemberInfo loginInfo = loginDao.getLoginMember(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
}
