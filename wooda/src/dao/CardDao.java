package dao;

import static db.JdbcUtil.*;
import java.util.*;
import java.sql.*;
import vo.*;
import java.time.*;
public class CardDao {
	private static CardDao cardDao;
	private Connection conn;
	private CardDao() {}

	public static CardDao getInstance() {
		if (cardDao == null)	cardDao = new CardDao();
		return cardDao;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	public int sendUp(CoupleMailing cm) {
		Statement stmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			System.out.println(cm.getMi_name_s());
			
			
			
			// ���� �̸� �������� ����
			sql = "select mi_name from t_member_info where mi_mail =  + '" + cm.getCm_mail_r() + "'" ;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// ���� �̸��� �����ϸ�
				String mi_name_r = rs.getString("mi_name");
				sql = "insert into t_couple_mailing (cm_mail_s, mi_name_s, cm_mail_r, mi_name_r, cm_content, cm_jdate) values('" + cm.getCm_mail_s() + "', '" + cm.getMi_name_s() + "', '" + cm.getCm_mail_r() + "', '" + mi_name_r + "', '" + cm.getCm_content() + "', '" + cm.getCm_sdate() + "') ";
				System.out.println(sql);
				result += stmt.executeUpdate(sql);
			}else {
				// ��� �̸� ������
				return 0;
			}
			
			
			if (result>0) {
				sql = "update t_member_info set mi_dding = 'a' where mi_mail =  '" + cm.getCm_mail_s() + "'";
				result += stmt.executeUpdate(sql);
				sql = "update t_member_info set mi_dding = 'b' where mi_mail =  '" + cm.getCm_mail_r() + "'";
				result += stmt.executeUpdate(sql);
			}
		}catch(Exception e) {
			System.out.println("CardDao Ŭ������ sendUp() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return result;
	}

	public CoupleMailing getCoupleMail(String mimail) {
		Statement stmt = null;
		ResultSet rs = null;
		CoupleMailing cm = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			System.out.println(mimail);
			sql = "select * from t_couple_mailing where cm_mail_r = '" + mimail + "'" ;
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				cm = new CoupleMailing();
				cm.setCm_mail_s(rs.getString("cm_mail_s"));
				cm.setMi_name_s(rs.getString("mi_name_s"));
				cm.setCm_content(rs.getString("cm_content"));
				cm.setCm_jdate(rs.getString("cm_jdate"));
				cm.setCm_isok(rs.getString("cm_isok"));
				cm.setCm_sdate(rs.getString("cm_sdate"));
			}
		}catch(Exception e) {
			System.out.println("CardDao Ŭ������ getCoupleMail() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(rs); close(stmt);
		}
		
		return cm;
	}
	public String getId() {
		// ���ο� �ֹ���ȣ(yymmdd + �������� + �Ϸù�ȣ3�ڸ�101)�� �����Ͽ� �����ϴ� �޼ҵ�
		
		Statement stmt = null;
		ResultSet rs = null;
		String ci_idx = null;
		try {
			
			
			stmt =conn.createStatement();
			Random rnd = new Random();
			
			LocalDate today = LocalDate.now(); //yyyy-mm-dd
			String td = (today + "").substring(2).replace("-", ""); //yymmdd
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char rn = alpha.charAt(rnd.nextInt(26));
			String sql = "select right(ci_idx,3) from t_couple_info where left(ci_idx, 6) = '" + td + 
				"' order by cm_jdate desc limit 0,1"; // ������ �Էµ� �ֹ���ȣ �� ���� �ֱ� ���� ������
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				//���� �������  Ŀ��
				int num = Integer.parseInt(rs.getString(1)) +1 ;
				ci_idx = td + rn + num;
			}else {
				//���� ù ������ ���
				ci_idx = td + rn + "101";
			}
			
			   
			}catch(Exception e) {
				System.out.println("CardDao �� getId ���� �޼ҵ� ����");
				e.printStackTrace();
			}finally {
				close(rs); close(stmt);
			}
		
		return ci_idx;
		
	}

	public int coupleMaker(String jdate, String senderEmail, String receiverEmail) {
		int result = 0;
		String ci_idx = getId();
		Statement stmt = null;
		String sql = "";
		

		try {
			
			stmt = conn.createStatement();
			sql = "insert into t_couple_info(ci_idx, ci_nick, ci_mail_r, ci_mail_s, ci_break, cm_jdate) values( '" + ci_idx + "', '�ײ��κ�', '" + receiverEmail + "', '" + senderEmail + "', 'n', '" + jdate + "')" ;
			result += stmt.executeUpdate(sql);
			if(result != 0) {
				
				sql = "update t_member_info set ci_idx = '" + ci_idx + "', mi_iscouple = 'y' where mi_mail = '" + receiverEmail + "' or mi_mail = '" + senderEmail + "' ";
				result += stmt.executeUpdate(sql);
				sql = "update t_couple_mailing set ci_idx = '" + ci_idx + "', cm_isok = 'y' where cm_mail_r = '" + receiverEmail + "'";
				result += stmt.executeUpdate(sql);
			}else return 0;
		}catch(Exception e) {
			System.out.println("CardDao Ŭ������ coupleMaker() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
		return result;
	}

	public int refuse(String senderEmail, String receiverEmail) {
		int result = 0;
		Statement stmt = null;
		String sql = "";
		try {
			stmt = conn.createStatement();
			sql = "delete from t_couple_mailing where cm_mail_s = '" + senderEmail + "'";
			System.out.println(sql);
			result += stmt.executeUpdate(sql);
			if(result != 0) {
				sql = "update t_member_info set mi_dding = 'n' where mi_mail = '" + senderEmail + "' or mi_mail = '" + receiverEmail + "'";
				result += stmt.executeUpdate(sql);
				System.out.println(sql);
			}else return 0;
		}catch(Exception e) {
			System.out.println("CardDao Ŭ������ refuse() �޼ҵ� ����");
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}
}
