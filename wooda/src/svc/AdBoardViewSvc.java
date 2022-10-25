package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class AdBoardViewSvc {
	public int readUpdate(int idx) {
		// ������ �Խñ��� ��ȸ���� ������Ű�� �޼ҵ�
			int result = 0;
			Connection conn = getConnection();
			AdBoardViewDao boardViewDao = AdBoardViewDao.getInstance();
			boardViewDao.setConnection(conn);

			result = boardViewDao.readUpdate(idx);
			if (result > 0)	commit(conn);
			else			rollback(conn);
			close(conn);

			return result;
		}
	
	public BorderInfo getBoderInfo(int idx) {
		// ������ �Խñ��� ��� ������ FreeList�� �ν��Ͻ��� �����ϴ� �޼ҵ�
			BorderInfo borderInfo = null;
			Connection conn = getConnection();
			AdBoardViewDao boardViewDao = AdBoardViewDao.getInstance();
			boardViewDao.setConnection(conn);

			borderInfo = boardViewDao.getBoderInfo(idx);
			close(conn);

			return borderInfo;
		}
	
	public int getBorderLike (int idx, String mi_mail) {
		int likeTotal = 0;
		Connection conn = getConnection();
		AdBoardViewDao boardViewDao = AdBoardViewDao.getInstance();
		boardViewDao.setConnection(conn);

		likeTotal = boardViewDao.getBorderLike(idx, mi_mail);
		close(conn);
		
		return likeTotal;
	}
	
	public int getBorderComment (int idx) {
		int commentTotal = 0;
		Connection conn = getConnection();
		AdBoardViewDao boardViewDao = AdBoardViewDao.getInstance();
		boardViewDao.setConnection(conn);

		commentTotal = boardViewDao.getBorderComment(idx);
		close(conn);
		
		return commentTotal;
	}

	public ArrayList<BorderComment> getBorderList(int idx) {
		ArrayList<BorderComment> CommentList = new ArrayList<BorderComment>();
		Connection conn = getConnection();
		AdBoardViewDao boardViewDao = AdBoardViewDao.getInstance();
		boardViewDao.setConnection(conn);

		CommentList = boardViewDao.getBorderList(idx);
		close(conn);
		return CommentList;
	}
}
