package svc;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import dao.*;
import vo.*;

public class ReceiveCardSvc {

	public CoupleMailing getCoupleMail(String mimail) {
		CoupleMailing cm = new CoupleMailing();
		Connection conn = getConnection();
		CardDao cardDao = CardDao.getInstance();
		cardDao.setConnection(conn);
		cm = cardDao.getCoupleMail(mimail);
		close(conn);
		
		return cm;
	}

}
