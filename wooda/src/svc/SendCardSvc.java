package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class SendCardSvc {
	public int sendUp(CoupleMailing cm) {
		int result = 0;
		Connection conn = getConnection();
		CardDao cardDao = CardDao.getInstance();
		cardDao.setConnection(conn);
		result = cardDao.sendUp(cm);
		
		if (result == 3) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
	}
	public int refuse(String senderEmail, String receiverEmail) {
		int result = 0;
		Connection conn = getConnection();
		CardDao cardDao = CardDao.getInstance();
		cardDao.setConnection(conn);
		result = cardDao.refuse(senderEmail, receiverEmail);
		System.out.println(result);
		if (result == 3) commit(conn);
		else rollback(conn);
		close(conn);		
		return result;
	}
}
