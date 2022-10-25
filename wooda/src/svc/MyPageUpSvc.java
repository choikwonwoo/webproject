package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class MyPageUpSvc {

	public int mypageUp(String mi_mail, String nick, String cnick, String jdate) {
		int result = 0;
		Connection conn = getConnection();
		ProfileDao profileDao = ProfileDao.getInstance();
		profileDao.setConnection(conn);
		result = profileDao.mypageUp(mi_mail, nick, cnick, jdate);
		if (result == 1) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
	}
	public int mypageUp(String mi_mail, String name, String pwd) {
		int result = 0;
		Connection conn = getConnection();
		ProfileDao profileDao = ProfileDao.getInstance();
		profileDao.setConnection(conn);
		result += profileDao.mypageUp(mi_mail, name, pwd);
		
		if (result == 1) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
		
	}
	public int broke(String mi_mail1, String mi_mail2, String ci_idx) {
		int result = 0;
		Connection conn = getConnection();
		ProfileDao profileDao = ProfileDao.getInstance();
		profileDao.setConnection(conn);
		result = profileDao.broke(mi_mail1, mi_mail2, ci_idx);
		if (result == 4) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
	}
	public int imgUp(String uploadFileName, String mi_mail) {
		int result = 0;
		Connection conn = getConnection();
		ProfileDao profileDao = ProfileDao.getInstance();
		profileDao.setConnection(conn);
		result = profileDao.imgUp(uploadFileName, mi_mail);
		if (result != 0) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
	}

}
