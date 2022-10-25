package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdBoardViewDao {
	private static AdBoardViewDao boardViewDao;
	private Connection conn;
	private AdBoardViewDao() {}

	public static AdBoardViewDao getInstance() {
		if (boardViewDao == null)	boardViewDao = new AdBoardViewDao();
		return boardViewDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int readUpdate(int idx) {
		// 지정한 게시글의 조회수를 증가시키는 메소드
			Statement stmt = null;
			int result = 0;

			try {
				stmt = conn.createStatement();
				String sql = "update t_board_story set bs_read = bs_read + 1 " + 
				" where bs_num = " + idx;
				result = stmt.executeUpdate(sql);

			} catch(Exception e) {
				System.out.println("BoardViewDao 클래스의 readUpdate() 메소드 오류");
				e.printStackTrace();
			} finally {
				close(stmt);
			}

			return result;
		}
	
	public BorderInfo getBoderInfo(int idx) {
		BorderInfo borderInfo = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_board_story where bs_num = " + idx;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				borderInfo = new BorderInfo();
				borderInfo.setBs_title(rs.getString("bs_title"));
				borderInfo.setBs_start(rs.getString("bs_start"));
				borderInfo.setBs_end(rs.getString("bs_end"));
				borderInfo.setBs_place1(rs.getString("bs_place1"));
				borderInfo.setBs_place2(rs.getString("bs_place2"));
				borderInfo.setBs_place3(rs.getString("bs_place3"));
				borderInfo.setBs_area(rs.getString("bs_area"));
				borderInfo.setBs_gender(rs.getString("bs_gender"));
				borderInfo.setBs_visit(rs.getString("bs_visit"));
				borderInfo.setBs_img1(rs.getString("bs_img1"));
				borderInfo.setBs_content(rs.getString("bs_content"));
				borderInfo.setBs_lat1(rs.getDouble("bs_lat1"));
				borderInfo.setBs_lat2(rs.getDouble("bs_lat2"));
				borderInfo.setBs_lat3(rs.getDouble("bs_lat3"));
				borderInfo.setBs_lng1(rs.getDouble("bs_lng1"));
				borderInfo.setBs_lng2(rs.getDouble("bs_lng2"));
				borderInfo.setBs_lng3(rs.getDouble("bs_lng3"));
				borderInfo.setBs_read(rs.getInt("bs_read"));
				borderInfo.setMi_nick(rs.getString("mi_nick"));
				borderInfo.setBs_date(rs.getString("bs_date"));
				borderInfo.setBs_like(rs.getInt("bs_like"));
			}
		}catch(Exception e) {
			System.out.println("BoardViewDao 클래스의 getBoderInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return borderInfo;
	}

	public int getBorderLike (int idx, String mi_mail) {
		int likeTotal = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) cnt from t_board_like where bs_num = " + idx + " and mi_mail = '" + mi_mail + "' ";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				likeTotal = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("BoardViewDao 클래스의 getBorderLike() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		return likeTotal;
	}

	public int getBorderComment(int idx) {
		int commentTotal = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) cnt from t_board_reply where bs_num = " + idx + " and mi_nick = '우다다닥' ";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				commentTotal = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("BoardViewDao 클래스의 getBorderComment() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return commentTotal;
	}

	public ArrayList<BorderComment> getBorderList(int idx) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BorderComment> CommentList = new ArrayList<BorderComment>();
		BorderComment bc = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select br_num, bs_num, mi_nick, br_content, br_ip, br_isview, br_date from t_board_reply where bs_num = " + idx + 
			" order by br_date";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bc = new BorderComment();
				bc.setBs_num(rs.getInt("bs_num"));
				bc.setBr_num(rs.getInt("br_num"));
				bc.setBr_content(rs.getString("br_content"));
				bc.setBr_date(rs.getString("br_date").substring(0, 11));
				bc.setMi_nick(rs.getString("mi_nick"));
				CommentList.add(bc);
			}
			
		}catch(Exception e) {
			System.out.println("BoardViewDao 클래스의 getBorderList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}		
		return CommentList;
	}
}
