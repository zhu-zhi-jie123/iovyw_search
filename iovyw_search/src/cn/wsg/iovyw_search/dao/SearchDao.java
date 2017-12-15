package cn.wsg.iovyw_search.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �־ò�
 * @author zhuzhijie
 *
 */
public class SearchDao {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * �õ�����
	 * @return
	 */
	private Connection getConnection() {
		// ����mysql���ݿ�
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://mt.iovyw.com:33060/e3g", "root", "maipue3g");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * ͨ��sn��ѯdn
	 */
	public String findDnBySn(String sn) {
		String dn = null;
		try {
			connection = this.getConnection();
			// ִ�е�sql���
			String sql = "select d.sn,c.dn from e3g_device d,e3g_customer c where d.company=c.id and d.del=0 and d.sn=?";
			// ����Ԥ�������
			ps = connection.prepareStatement(sql);
			ps.setString(1, sn);
			rs = ps.executeQuery();
			// �ӽ������ȡ����
			while (rs.next()) {
				dn = rs.getString("dn");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//�ر�����
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dn;
	}
	
	/**
	 * ͨ��IMSI��ѯdn
	 */
	public String findDnByIMSI(String imsi) {
		String dn = null;
		try {
			connection = this.getConnection();
			// ִ�е�sql���
			String sql = "select s.imsi,c.dn from e3g_sim s,e3g_customer c where s.company=c.id and s.del=0 and s.imsi=?";
			// ����Ԥ�������
			ps = connection.prepareStatement(sql);
			ps.setString(1, imsi);
			rs = ps.executeQuery();
			// �ӽ������ȡ����
			while (rs.next()) {
				dn = rs.getString("dn");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//�ر�����
				rs.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dn;
	}

}
