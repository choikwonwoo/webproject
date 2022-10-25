package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;

public class AdminImgDao {
	private static AdminImgDao adminImgDao;
	private Connection conn;
	private AdminImgDao() {}

	public static AdminImgDao getInstance() {
		if (adminImgDao == null) adminImgDao = new AdminImgDao();
		return adminImgDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<AdminImg> getImgInfo() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<AdminImg> ImgInfoList = new ArrayList<AdminImg>();
		AdminImg ai = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "select * from t_admin_main where am_num != '' order by am_num";

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ai = new AdminImg();
				ai.setAm_code(rs.getString("am_code"));
				ai.setAm_img(rs.getString("am_img"));
				ai.setAm_name(rs.getString("am_name"));
				ai.setAm_num(rs.getString("am_num"));
				ImgInfoList.add(ai);
			}
		}catch(Exception e) {
			System.out.println("AdminImgDao 클래스의 getImgInfo() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(rs);	close(stmt);
		}
		
		return ImgInfoList;
	}

	public int updateAdimg(String[] arr1, String[] arr2) {
		CallableStatement cstmt = null;
		int result = 0;


		
		try {
			String sql = "{call sp_update_img(?,?,?,?,?,?,?,?,?,?)}";
			cstmt = conn.prepareCall(sql);
			for (int i=0; i<(arr1.length + arr2.length); i++) {
				if (i<arr1.length) {
					System.out.println(arr1[i]);
					cstmt.setString(i+1, arr1[i]);
					
				}else {
					cstmt.setString(i+1, arr2[i-5]);
					System.out.println(arr2[i-5]);
				}
			}
			cstmt.execute();
			
			
		}catch(Exception e) {
			System.out.println("AdminImgDao 클래스의 updateAdimg() 메소드 오류");
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		
		return 0;
	}

}
