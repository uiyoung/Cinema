package controllers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.MovieBean;

public class MovieMgr {
	DBConnection db;

	public MovieMgr() {
		db = new DBConnection();
	}

	public ArrayList<MovieBean> getMovie() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "SELECT * FROM MOVIE_TB";
		MovieBean bean;

		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bean = new MovieBean();
				bean.setTitle(rs.getString("title"));
				bean.setGenre(rs.getString("genre"));
				bean.setReleaseDate(rs.getInt("releaseDate"));
				bean.setRunningTime(rs.getInt("runningTime"));
				bean.setDescription(rs.getString("description"));
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
}
