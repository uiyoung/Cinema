import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMgr {
	DBCon conn;
	final int EVENT_POINT = 5000; // 회원가입 이벤트 point

	public DBMgr() {
		conn = new DBCon();
	}

	public ArrayList<MemberBean> signUp(String id, String password, String name, String birthdate, String phone) {
		Connection con = null;
		Statement stmt = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "INSERT INTO MEMBER_TB VALUES (null, '" + id + "','" + password + "','" + name + "','" + birthdate
				+ "','" + phone + "','" + EVENT_POINT + "')";

		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	public ArrayList<MemberBean> login() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "SELECT * FROM MEMBER_TB";
		MemberBean bean;

		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPassword(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setBirthdate(rs.getString("birthdate"));
				bean.setPhone(rs.getString("phone"));
				bean.setPoint(rs.getInt("point"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// if (con != null) {
			// try {
			// con.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// }
		}
		return list;
	}
}
