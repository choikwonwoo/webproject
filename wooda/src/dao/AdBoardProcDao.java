package dao;

import static db.JdbcUtil.close;
import java.sql.Connection;
import java.sql.Statement;

public class AdBoardProcDao {
	private static AdBoardProcDao boardProcDao;
	private Connection conn;
	private AdBoardProcDao() {}

	public static AdBoardProcDao getInstance() {
		if (boardProcDao == null)	boardProcDao = new AdBoardProcDao();
		return boardProcDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int boardDelete(String where) {
		Statement stmt = null;
		int result = 0;

		try {
			String sql = "delete from t_board_story " + where;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println("BoardProcDao 클래스의 boardDelete() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}

	public int boardIsviewUpdate(String where) {
		Statement stmt = null;
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "update t_board_story set bs_isview = if(bs_isview='Y', 'N', 'Y') " + where;
			
			result = stmt.executeUpdate(sql);
			
		} catch(Exception e) {
			System.out.println("BoardProcDao 클래스의 boardIsviewUpdate() 메소드 오류");
			
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}
}
