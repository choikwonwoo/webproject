package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdLoginSvc {
// 로그인에 필요한 아이디와 암호를 받아와 비즈니스 로직을 처리(DB작업 제외)하는 클래스
	public AdminInfo getLoginAdmin(String uid, String pwd) {
		Connection conn = getConnection();
		AdLoginDao loginDao = AdLoginDao.getInstance();
		loginDao.setConnection(conn);
		
		AdminInfo loginInfo = loginDao.getLoginAdmin(uid, pwd);
		close(conn);
		
		return loginInfo;
	}
}
