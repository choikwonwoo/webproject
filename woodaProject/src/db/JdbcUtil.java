package db;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class JdbcUtil {
// db관련 연결 및 공용 메소드 들을 정의한 클래스 - 모든 메소드는 public static으로 선언됨
	public static Connection getConnection() {
		// db연결 커넥션 객체를 리턴하는 메소드
		Connection conn = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			// 쿼리가 자동으로 commit 되는 것을 막는 명령으로 트랜잭션을 시작 시킨다.
		} catch(Exception e) {
			System.out.println("db연결 실패");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {conn.close();} catch(Exception e) {e.printStackTrace();}
		//connection 객체를 닫아 db연결 해제
	}
	public static void close(Statement stmt) {
		// preparedstatement와 callabledstatement는 statement를 상속받기에 따로 close메소드가 필요없다.
		try {stmt.close();} catch(Exception e) {e.printStackTrace();}
		//statement 객체를 닫아 db연결 해제
	}
	public static void close(ResultSet rs) {
		try {rs.close();} catch(Exception e) {e.printStackTrace();}
		//ResultSet 객체를 닫아 db연결 해제
	}
	public static void commit(Connection conn) {
		// transaction commit 시키는 메소드
		try {
			conn.commit();
			System.out.println("쿼리 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		// transaction commit 시키는 메소드
		try {
			conn.rollback();
			System.out.println("쿼리 실패");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
