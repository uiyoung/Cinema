package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.MemberBean;
import models.Selected;

public class DBMgr {
	DBConnection db;

	final int EVENT_POINT = 5000; // 회원가입 이벤트 point

	public DBMgr() {
		db = new DBConnection();
	}

	// 달력에서 날짜를 누르면 그 날짜에 상영하는 회차 목록(극장이름, 시간)이 return되는 메서드
	public ArrayList<String> getSchedule(String title, String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select t.name, s.time from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no where m.title = ? and s.date = ?";
		Selected selected;

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("name") + ", " + rs.getString("time"));
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// if (conn != null) {
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// }
		}
		return list;
	}

	public ArrayList<MemberBean> signUp(String id, String password, String name, String birthdate, String phone) {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "INSERT INTO MEMBER_TB VALUES (null, '" + id + "','" + password + "','" + name + "','" + birthdate
				+ "','" + phone + "','" + EVENT_POINT + "')";

		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public ArrayList<MemberBean> login() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "SELECT * FROM MEMBER_TB";
		MemberBean bean;

		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
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
			// if (conn != null) {
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
			// }
		}
		return list;
	}
}
