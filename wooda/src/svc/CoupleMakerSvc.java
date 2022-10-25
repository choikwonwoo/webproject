package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class CoupleMakerSvc {

	public int coupleMaker(String jdate, String senderEmail, String receiverEmail) {
		Connection conn = getConnection();
		CardDao cardDao = CardDao.getInstance();
		cardDao.setConnection(conn);
		int result = 0;
		result = cardDao.coupleMaker(jdate, senderEmail, receiverEmail);
		System.out.println(result);
		if(result == 4) {
			commit(conn);
		}
		else rollback(conn);
		close(conn);
		
		return result;
	}

}
