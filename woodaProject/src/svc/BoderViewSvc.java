package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class BoderViewSvc {

	public int readUpdate(int idx) {
		// ������ �Խñ��� ��ȸ���� ������Ű�� �޼ҵ�
			int result = 0;
			Connection conn = getConnection();
			BoderViewDao boderViewDao = BoderViewDao.getInstance();
			boderViewDao.setConnection(conn);

			result = boderViewDao.readUpdate(idx);
			if (result > 0)	commit(conn);
			else			rollback(conn);
			close(conn);

			return result;
		}
	
	public BorderInfo getBoderInfo(int idx) {
		// ������ �Խñ��� ��� ������ FreeList�� �ν��Ͻ��� �����ϴ� �޼ҵ�
			BorderInfo borderInfo = null;
			Connection conn = getConnection();
			BoderViewDao boderViewDao = BoderViewDao.getInstance();
			boderViewDao.setConnection(conn);

			borderInfo = boderViewDao.getBoderInfo(idx);
			close(conn);

			return borderInfo;
		}
	
}
