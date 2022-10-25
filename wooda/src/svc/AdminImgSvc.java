package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdminImgSvc {

	public ArrayList<AdminImg> getImgInfo() {
		ArrayList<AdminImg> adminImg = new ArrayList<AdminImg>();
		Connection conn = getConnection();
		AdminImgDao adminImgDao = AdminImgDao.getInstance();
		adminImgDao.setConnection(conn);

		adminImg = adminImgDao.getImgInfo();
		close(conn);

		return adminImg;
	}

	public int updateAdimg(String[] arr1, String[] arr2) {
		Connection conn = getConnection();
		AdminImgDao adminImgDao = AdminImgDao.getInstance();
		adminImgDao.setConnection(conn);

		int result = adminImgDao.updateAdimg(arr1, arr2);
		if (result >= 0)	commit(conn);	
		else				rollback(conn);
		close(conn);


		return result;
	}
	

}
