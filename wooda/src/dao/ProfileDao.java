package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;
import java.time.*;

public class ProfileDao {
	private static ProfileDao profileDao;
	private Connection conn;

	private ProfileDao() {
	}

	public static ProfileDao getInstance() {
		if (profileDao == null)
			profileDao = new ProfileDao();
		return profileDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public int mypageUp(String mi_mail, String nick, String cnick, String jdate) {
		int result = 0;
		Statement stmt = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			System.out.println(mi_mail);
			sql = "update t_member_info set mi_nick = '" + nick + "', ci_nick = '" + cnick + "' where mi_mail = '"
					+ mi_mail + "'";
			result = stmt.executeUpdate(sql);
			if (cnick == null || cnick.equals("")) {

			} else {
				sql = "update t_couple_info set ci_nick = '" + nick + "', cm_jdate = '" + jdate
						+ "' where cm_mail_s = '" + mi_mail + "' or cm_mail_r = '" + mi_mail + "' ";
				result = stmt.executeUpdate(sql);
			}

		} catch (Exception e) {
			System.out.println("ProfileDao 클래스의 mypageUp(1) 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}

	public int mypageUp(String mi_mail, String name, String pwd) {
		int result = 0;
		Statement stmt = null;
		String sql = "";
		try {
			stmt = conn.createStatement();

			sql = "update t_member_info set mi_name = '" + name + "', mi_pw = '" + pwd + "' where mi_mail = '" + mi_mail
					+ "' ";
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("ProfileDao 클래스의 mypageUp(2) 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}

	public int broke(String mi_mail1, String mi_mail2, String ci_idx) {
		int result = 0;
		Statement stmt = null;
		String sql = "";

		try {

			stmt = conn.createStatement();
			sql = "update t_member_info set ci_idx = '', mi_iscouple = 'n', mi_dding = 'n' where mi_mail = '" + mi_mail1
					+ "' or mi_mail = '" + mi_mail2 + "' ";

			result += stmt.executeUpdate(sql);
			if (result != 0) {

				sql = "delete from t_couple_info where  ci_idx = '" + ci_idx + "'";
				System.out.println(sql);
				result += stmt.executeUpdate(sql);
				sql = "delete from t_couple_mailing where  ci_idx = '" + ci_idx + "'";
				System.out.println(sql);
				result += stmt.executeUpdate(sql);
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("ProfileDao 클래스의 broke() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return result;
	}

	public CoupleInfo getCoupleInfo(String ci_idx, String mi_mail) {
		Statement stmt = null;
		ResultSet rs = null;
		CoupleInfo ci = null;
		String cix = ci_idx;
		String sql = "";
		try {
			stmt = conn.createStatement();

			if (ci_idx == null) {
				sql = "select ci_idx from t_member_info where mi_mail = '" + mi_mail + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					cix = rs.getString("ci_idx");
				}
				close(rs);
			}
			sql = "select * from t_couple_info where ci_idx = '" + cix + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ci = new CoupleInfo();
				ci.setCi_mail_s(rs.getString("ci_mail_s"));
				ci.setCi_mail_r(rs.getString("ci_mail_r"));
				ci.setCm_jdate(rs.getString("cm_jdate"));
				ci.setCi_nick(rs.getString("ci_nick"));
			}
		} catch (Exception e) {
			System.out.println("ProfileDao 클래스의 getCoupleInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}

		return ci;
	}

	public int imgUp(String uploadFileName, String mi_mail) {
		int result = 0;
		Statement stmt = null;
		String sql = "";

		try {
			stmt = conn.createStatement();
			sql = "update t_member_info set mi_pimg = '" + uploadFileName + "' where mi_mail = '" + mi_mail + "'";
			result += stmt.executeUpdate(sql);
			if(result ==0) {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("ProfileDao 클래스의 imgUp() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return result;
	}

}
