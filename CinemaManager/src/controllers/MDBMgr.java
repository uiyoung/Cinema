package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.AdminBean;
import models.MovieBean;
import models.RevenuBean;
import models.ScheduleBean;
import models.TheaterBean;
import models.TicketBean;

public class MDBMgr {
	MDBCon conn;

	public MDBMgr() {
		conn = new MDBCon();
	}

	// login
	public ArrayList<AdminBean> login() {
		Connection con = null; // 내 PC의 DB에 접속한다.
		Statement stmt = null; // db에게 sql를 적을 수 있는 판을 만든다.
		ResultSet rs = null;
		ArrayList<AdminBean> list = new ArrayList<AdminBean>();
		String sql = "select*from admin"; // DB의 admin 테이블에서 가져옴
		AdminBean mm;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				mm = new AdminBean();
				mm.setId(rs.getString(1));
				mm.setPw(rs.getString(2));
				list.add(mm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// movie
	public ArrayList<MovieBean> allmovie() {
		Connection con = null; // 내 PC의 DB에 접속
		Statement stmt = null; // DB에 sql을 적을 수 있는 판을 만든다.
		ResultSet rs = null; // sql한 결과를 담는 그릇을 만든다.
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "select * from MOVIE_TB";

		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNo(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setGenre(rs.getString(3));
				bean.setType(rs.getString(4));
				bean.setDirector(rs.getString(5));
				bean.setCast(rs.getString(6));
				bean.setReleaseDate(rs.getString(7));
				bean.setRunningTime(rs.getString(8));
				bean.setDescription(rs.getString(9));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<MovieBean> input_movie(String title, String genre, String releaseDate, String runningTime,
			String description, String type, String director, String cast) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "INSERT INTO `cinemadb`.`movie_tb` (`title`, `genre`, `releaseDate`, `runningTime`, `description`, `type`, `director`, `cast`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, no);
			pstmt.setString(1, title);
			pstmt.setString(2, genre);
			pstmt.setString(3, releaseDate);
			pstmt.setString(4, runningTime);
			pstmt.setString(5, description);
			pstmt.setString(6, type);
			pstmt.setString(7, director);
			pstmt.setString(8, cast);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public ArrayList<MovieBean> search_Movie(String title) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "select * from  MOVIE_TB where title = " + title + "'";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNo(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setGenre(rs.getString(3));
				bean.setReleaseDate(rs.getString(4));
				bean.setRunningTime(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setType(rs.getString(7));
				bean.setDirector(rs.getString(8));
				bean.setCast(rs.getString(9));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<MovieBean> delete_Movie(String title) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "delete from MOVIE_TB where title = '" + title + "'";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<MovieBean> getMovieInfo(String title) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MovieBean> list = new ArrayList<MovieBean>();
		String sql = "select * from MOVIE_TB where title = '" + title + "'";

		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNo(rs.getInt(1));
				bean.setTitle(rs.getString(2));
				bean.setGenre(rs.getString(3));
				bean.setReleaseDate(rs.getString(4));
				bean.setRunningTime(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setType(rs.getString(7));
				bean.setDirector(rs.getString(8));
				bean.setCast(rs.getString(9));

				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void updateMovie(int no, String title, String genre, String releaseDate, String runningTime,
			String description, String type, String director, String cast) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update MOVIE_TB set title = ?, genre = ? , releaseDate = ? , runningTime = ? , description = ?, type = ?, director = ?, cast = ? where no =?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, genre);
			pstmt.setString(3, releaseDate);
			pstmt.setString(4, runningTime);
			pstmt.setString(5, description);
			pstmt.setString(6, type);
			pstmt.setString(7, director);
			pstmt.setString(8, cast);
			pstmt.setInt(9, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// schedule
	public ArrayList<ScheduleBean> allmschedule() {// getterandsetter만들어줘야함
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ScheduleBean> list = new ArrayList<ScheduleBean>();
		String sql = "select * from schedule_tb2";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ScheduleBean sch = new ScheduleBean();
				sch.setNo(rs.getInt("no"));
				sch.setDate(rs.getString("date"));
				sch.setTime(rs.getString("time"));
				sch.setMovie_no(rs.getInt("movie_no"));
				sch.setTheater_no(rs.getInt("theater_no"));

				list.add(sch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 상영시간입력
	public ArrayList<ScheduleBean> insertSchedule(String date, String time, int movie_no, int theater_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleBean> list = new ArrayList<ScheduleBean>();
		String sql = "INSERT INTO `cinemadb`.`schedule_tb2` (`date`, `time`, `movie_no`, `theater_no`) VALUES (?, ?, ?, ?)";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			pstmt.setInt(3, movie_no);
			pstmt.setInt(4, theater_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// 상영시간 삭제
	public void deleteSchedule(int no) {
		Connection con = null;
		Statement stmt = null;
		String sql = "delete from schedule_tb2 where no = '" + no + "'";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ScheduleBean> getScheduleInfo(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ScheduleBean> list = new ArrayList<>();
		ScheduleBean bean;
		String sql = "select * from schedule_tb2 where no=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new ScheduleBean();
				bean.setNo(rs.getInt("no"));
				bean.setDate(rs.getString("date"));
				bean.setTime(rs.getString("time"));
				bean.setMovie_no(rs.getInt("movie_no"));
				bean.setTheater_no(rs.getInt("theater_no"));

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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public void updateSchedule(String date, String time, String movie_no, String theater_no, int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update schedule_tb2 set date = ? , time = ? , movie_no = ? , theater_no = ? where no =?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			pstmt.setString(3, movie_no);
			pstmt.setString(4, theater_no);
			pstmt.setInt(5, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Reserve
	public ArrayList<TicketBean> allreserve() {// getterandsetter만들어줘야함
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TicketBean> list = new ArrayList<TicketBean>();
		String sql = "select*from ticket_tb";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TicketBean mr = new TicketBean();
				mr.setNo(rs.getString(1));
				mr.setTitle(rs.getString(2));
				mr.setTheater_name(rs.getString(3));
				mr.setDate(rs.getString(4));
				mr.setTime(rs.getString(5));
				mr.setSeat_no(rs.getString(6));
				mr.setPrice(rs.getString(7));
				mr.setUser_id(rs.getString(8));

				list.add(mr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 정산
	public ArrayList<RevenuBean> allcal() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<RevenuBean> list = new ArrayList<RevenuBean>();
		String sql = "SELECT title, price, COUNT(title) AS sold FROM ticket_tb GROUP BY title ORDER BY title ASC;";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				RevenuBean mc = new RevenuBean();

				mc.setTitle(rs.getString(1));
				mc.setPrice(rs.getString(2));
				mc.setSold(rs.getString(3));

				list.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*--------------------------------Uiyoung-----------------------------------------------*/

	// movie title 목록 불러오기
	public ArrayList<String> getMovieTitles() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		String sql = "select title from MOVIE_TB";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// theater 목록 불러오기
	public ArrayList<String> getTheaterNames() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		String sql = "select * from theater_tb";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// theater 번호 불러오기
	public ArrayList<Integer> getTheaterNo() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "select * from theater_tb";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getInt("no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 스케줄의 ~극장의 date 목록 불러오기
	public ArrayList<String> getDates(String theater) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select distinct date from schedule_tb2 where theater_no=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theater);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("date"));
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
		}
		return list;
	}

	// 스케줄의 ~극장, ~날짜의 상영시간 목록 불러오기
	public ArrayList<String> getTimes(String theater, String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select time from schedule_tb2 where theater_no=? and date=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theater);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("time"));
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
		}
		return list;
	}

	public void insertSeats(String theater, String seatNo, String date, String time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO `cinemadb`.`seat_tb2` (`theater_no`, `seat_no`, `date`, `time`) VALUES (?, ?, ?, ?)";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theater);
			pstmt.setString(2, seatNo);
			pstmt.setString(3, date);
			pstmt.setString(4, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ~극장, ~날짜, ~시간에 이미 좌석이 생성되어있는지 체크
	public boolean isSeatInserted(String theater, String date, String time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select * from `cinemadb`.`seat_tb2` where theater_no=? and date=? and time=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theater);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return true;
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
		}
		return false;
	}

	// ~일, ~시간, ~극장에 이미 스케줄이 생성되었는지 체크
	public boolean isDuplicateSchedule(String date, String time, int theather_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule_tb2 where date=? and time=? and theater_no=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, time);
			pstmt.setInt(3, theather_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return true;
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
		}
		return false;
	}

	// theater 목록 불러오기
	public ArrayList<TheaterBean> getTheaters() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TheaterBean> list = new ArrayList<>();
		String sql = "select * from theater_tb";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TheaterBean bean = new TheaterBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setAddress(rs.getString("address"));
				bean.setPhone(rs.getString("phone"));
				bean.setCapacity(rs.getInt("capacity"));
				bean.setDescription(rs.getString("description"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void insertTheater(int theaterNo, String theaterName, String theaterAddress, String theaterPhone,
			int theaterCapacity, String theaterDescription) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO `cinemadb`.`theater_tb` (`no`, `name`, `address`, `phone`, `capacity`, `description`) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, theaterAddress);
			pstmt.setString(4, theaterPhone);
			pstmt.setInt(5, theaterCapacity);
			pstmt.setString(6, theaterDescription);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateTheater(int theaterNo, String theaterName, String theaterAddress, String theaterPhone,
			int theaterCapacity, String theaterDescription) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE `cinemadb`.`theater_tb` SET `name`=?, `address`=?, `phone`=?, `capacity`=?, `description`=? WHERE `no`=?";

		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, theaterName);
			pstmt.setString(2, theaterAddress);
			pstmt.setString(3, theaterPhone);
			pstmt.setInt(4, theaterCapacity);
			pstmt.setString(5, theaterDescription);
			pstmt.setInt(6, theaterNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<TheaterBean> getTheaterInfo(int theaterNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TheaterBean> list = new ArrayList<>();
		TheaterBean bean;
		String sql = "select * from theater_tb where no=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TheaterBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setAddress(rs.getString("address"));
				bean.setPhone(rs.getString("phone"));
				bean.setCapacity(rs.getInt("capacity"));
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public void deleteTheater(int theaterNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM `cinemadb`.`theater_tb` WHERE  `no`=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Integer> getScheduleNo() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "select * from schedule_tb2";
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getInt("no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void deleteSeats(int theaterNo, String date, String time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from seat_tb2 where theater_no=? and date=? and time=?";
		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateSeats(int newTheaterNo, String newDate, String newTime, int theaterNo, String date, String time) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE `cinemadb`.`seat_tb2` SET  theater_no=?,date=?, time=?  where theater_no=? and date=? and time=?";

		try {
			con = conn.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newTheaterNo);
			pstmt.setString(2, newDate);
			pstmt.setString(3, newTime);
			pstmt.setInt(4, theaterNo);
			pstmt.setString(5, date);
			pstmt.setString(6, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				// con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
