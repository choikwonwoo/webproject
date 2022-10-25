package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class BoderListDao {
	private static BoderListDao boderListDao;
	private Connection conn;
	private BoderListDao() {}

	public static BoderListDao getInstance() {
		if (boderListDao == null)	boderListDao = new BoderListDao();
		return boderListDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public int getBoderListCount(String where) {
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "select count(*) cnt from t_board_story where bs_astatus = 'y'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next())	rcnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rcnt;
	}
	
	public ArrayList<BorderInfo> getBoderList(String where, int cpage, int psize, String orderBy){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		BorderInfo bi = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select bs_num, bs_img1, bs_title, bs_start, bs_end, bs_place1, bs_place2, bs_place3 from t_board_story  "
			+ where + orderBy + 
			" limit " + ((cpage - 1) * psize) + ", " + psize;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bi = new BorderInfo();
				bi.setBs_num(rs.getInt("bs_num"));
				bi.setBs_title(rs.getString("bs_title"));
				bi.setBs_img1(rs.getString("bs_img1"));
				bi.setBs_start(rs.getString("bs_start"));
				bi.setBs_end(rs.getString("bs_end"));
				bi.setBs_place1(rs.getString("bs_place1"));
				bi.setBs_place2(rs.getString("bs_place2"));
				bi.setBs_place3(rs.getString("bs_place3"));
				borderList.add(bi);
			}
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return borderList;
	}
	public ArrayList<BorderInfo> getCoupleBoderList(String where, int cpage, int psize, String ci_idx, String mail){
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BorderInfo> borderList = new ArrayList<BorderInfo>();
		BorderInfo bi = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			if (ci_idx != null) {
				sql = "select bs_num, bs_img1, bs_title, bs_start, bs_end, bs_place1, bs_place2, bs_place3 from t_board_story where mi_mail = '" + mail + "'" + 
						"union select bs_num, bs_img1, bs_title, bs_start, bs_end, bs_place1, bs_place2, bs_place3 from t_board_story where "
						+ where + " bs_cstatus = 'y' and ci_idx = '" + ci_idx + "' " + " order by bs_num desc " + 
						" limit " + ((cpage - 1) * psize) + ", " + psize;
			}else {
				sql = "select bs_num, bs_img1, bs_title, bs_start, bs_end, bs_place1, bs_place2, bs_place3 from t_board_story where "
						+ where + " mi_mail = '" + mail + "' " + " order by bs_num desc " + 
						" limit " + ((cpage - 1) * psize) + ", " + psize;
			}
			
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bi = new BorderInfo();
				bi.setBs_num(rs.getInt("bs_num"));
				bi.setBs_title(rs.getString("bs_title"));
				bi.setBs_img1(rs.getString("bs_img1"));
				bi.setBs_start(rs.getString("bs_start"));
				bi.setBs_end(rs.getString("bs_end"));
				bi.setBs_place1(rs.getString("bs_place1"));
				bi.setBs_place2(rs.getString("bs_place2"));
				bi.setBs_place3(rs.getString("bs_place3"));
				borderList.add(bi);
			}
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderList() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return borderList;
	}
	public int getCoupleBoderListCount(String where, String ci_idx, String mi_mail) {
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			if (ci_idx != null) {
				sql = "select sum(cnt) cnt from (select count(*) cnt from t_board_story where mi_mail = '" + mi_mail + "' " +
						"union select count(*) cnt from t_board_story where bs_cstatus = 'y' and ci_idx = '" + ci_idx + "'" + where + ") cnt";
				
			} else {
				sql = "select count(*) cnt from t_board_story where mi_mail = '" + mi_mail + "'" + where;
			}

			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			if(rs.next())	rcnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rcnt;
	}
	public int getCoupleBoderListCount(String ci_idx, String mi_mail) {
		Statement stmt = null;
		ResultSet rs = null;
		int rcnt = 0;
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			if (ci_idx != null) {
				sql = "select count(*) cnt from t_board_story where bs_cstatus = 'y' and ci_idx = '" + ci_idx + "'";
			} else {
				sql = "select count(*) cnt from t_board_story where mi_mail = '" + mi_mail + "'";
			}
			rs = stmt.executeQuery(sql);
			
			if(rs.next())	rcnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rcnt;
	}
	public String getDiaryPage(String ci_idx, String mi_mail) {
		Statement stmt = null;
		ResultSet rs = null;
		String rstr = "";
		
		try {
			stmt = conn.createStatement();
			String sql = "";
			if (ci_idx != null) {
				// 연인인 경우의 나 + 너의 페이지 수
				sql = "select count(*) cnt from t_board_story where mi_mail = '" + mi_mail + "'";
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					rstr += rs.getString("cnt");
					rstr +=":";
					close(rs);
				}	
				sql = "select count(*) cnt from t_board_story where bs_cstatus = 'y' and ci_idx = '" + ci_idx + "' and mi_mail != '" + mi_mail + "' ";
				rs = stmt.executeQuery(sql);
				if(rs.next())	rstr += rs.getString("cnt");
				System.out.println(rstr);
			} else {
				// 솔로인 경우
				sql = "select count(*) cnt from t_board_story where mi_mail = '" + mi_mail + "'";
				rs = stmt.executeQuery(sql);
				if(rs.next())	{
					rstr += rs.getString("cnt");
					rstr += ":0";
				}
			}		
		}catch(Exception e) {
			System.out.println("BoderListDao 클래스의 getBoderListCount() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return rstr;
	}
	
	
	
	
}
