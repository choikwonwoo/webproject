package db;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class JdbcUtil {
// db���� ���� �� ���� �޼ҵ� ���� ������ Ŭ���� - ��� �޼ҵ�� public static���� �����
	public static Connection getConnection() {
		// db���� Ŀ�ؼ� ��ü�� �����ϴ� �޼ҵ�
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			// ������ �ڵ����� commit �Ǵ� ���� ���� ������� Ʈ������� ���� ��Ų��.
		} catch(Exception e) {
			System.out.println("db���� ����");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {conn.close();} catch(Exception e) {e.printStackTrace();}
		//connection ��ü�� �ݾ� db���� ����
	}
	public static void close(Statement stmt) {
		// preparedstatement�� callabledstatement�� statement�� ��ӹޱ⿡ ���� close�޼ҵ尡 �ʿ����.
		try {stmt.close();} catch(Exception e) {e.printStackTrace();}
		//statement ��ü�� �ݾ� db���� ����
	}
	public static void close(ResultSet rs) {
		try {rs.close();} catch(Exception e) {e.printStackTrace();}
		//ResultSet ��ü�� �ݾ� db���� ����
	}
	public static void commit(Connection conn) {
		// transaction commit ��Ű�� �޼ҵ�
		try {
			conn.commit();
			System.out.println("���� ����");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		// transaction commit ��Ű�� �޼ҵ�
		try {
			conn.rollback();
			System.out.println("���� ����");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
