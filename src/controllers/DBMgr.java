package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.MemberBean;
import models.SeatBean;
import models.TheaterBean;
import models.TicketBean;

public class DBMgr {
	DBConnection db;
	final int EVENT_POINT = 5000; // 회원가입 이벤트 point

	public DBMgr() {
		db = new DBConnection();
	}

	// ticket 취소
	public void cancelTicket(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM `cinemadb`.`ticket_tb` WHERE `no`=?";

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}
	
	
	
	// 티켓 생성
	public void insertTicket(String title, String theater, String date, String time, String seat, int price,
			String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO `cinemadb`.`ticket_tb` (`title`, `theater_name`, `date`, `time`, `seat_no`, `price`, `user_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, theater);
			pstmt.setString(3, date);
			pstmt.setString(4, time);
			pstmt.setString(5, seat);
			pstmt.setInt(6, price);
			pstmt.setString(7, user_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}

	// ~영화를 상영하는 ~극장의 ~일자의 상영시간 리스트 불러오기
	public ArrayList<String> getTime(String title, String theater, String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select time from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no where m.title=?and t.name=? and s.date=?";

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, theater);
			pstmt.setString(3, date);
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

	// ~영화를 상영하고 있는 극장 리스트 불러오기
	public ArrayList<TheaterBean> getTheaters(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TheaterBean> list = new ArrayList<>();
		String sql = "select distinct t.name from movie_tb m left join schedule_tb s on m.no = s.movie_no right join theater_tb t on s.theater_no = t.no  where m.title = ?";
		TheaterBean bean;

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TheaterBean();
				bean.setName(rs.getString("name"));
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

	// ~극장의 ~일자 좌석정보 불러오기
	public ArrayList<SeatBean> getSeats(String theater, String date, String time) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SeatBean> list = new ArrayList<>();
		String sql = "select seat_no, date,time, state from theater_tb join seat_tb where theater_tb.name=? and date=? and time=?";
		SeatBean bean;

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theater);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SeatBean();
				bean.setTheater_no("theater_no");
				bean.setDate(rs.getString("date"));
				bean.setTime(rs.getString("time"));
				bean.setSeat_no(rs.getString("seat_no"));
				bean.setState(rs.getString("state"));
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

	public ArrayList<MemberBean> login() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		MemberBean bean;
		String sql = "SELECT * FROM MEMBER_TB";

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

	/* --------------------------우재----------------------------------- */
	public ArrayList<MemberBean> updateMember(String id, String name, String birthdate,
			String phone/* ,String point */) {
		Connection con = null; // 내 pc의 db에 접속
		PreparedStatement pstmt = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "update MEMBER_TB set NAME = ?, birthdate = ?, phone = ? where id=?";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birthdate);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}

	public ArrayList<MemberBean> UpdatePw(String id, String password) {
		Connection con = null; // 내 pc의 db에 접속
		Statement stmt = null; // db에 sql을 적을 수 있는 판을 만듬
		ResultSet rs = null; // sql한 결과를 담는 그릇을 만든다.
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "select *from MEMBER_TB where id = '" + id + "'"; // AND
																		// PASSWORD
																		// = '"
																		// +
																		// password
																		// +
																		// "'";
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setPassword(rs.getString("PASSWORD"));
				list.add(bean);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}


	public ArrayList<MemberBean> UpdatePw2(String password) {
		Connection con = null; // 내 pc의 db에 접속
		PreparedStatement pstmt = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "update MEMBER_TB set PASSWORD = ?";
		try {
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}

	public ArrayList<MemberBean> deleteMb(String id, String password) {
		Connection con = null;
		Statement stmt = null;
		ArrayList<MemberBean> list = new ArrayList<MemberBean>();
		String sql = "delete from MEMBER_TB where id = '" + id + "' AND PASSWORD = '" + password + "'";
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch
		return list;
	}
	/* ----------------------end of 우재----------------------------------- */

	// ~극장 ~일자 ~시간의 ~좌석의 예매상태 y로 바꾸기
	public void reserveSeat(String theater, String date, String time, String seat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update seat_tb as s join theater_tb as t on s.theater_no = t.no and t.name=? and s.date=? and s.time=? and s.seat_no=? set s.state='y'";

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theater);
			pstmt.setString(2, date);
			pstmt.setString(3, time);
			pstmt.setString(4, seat);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}
	
	// ~극장 ~일자 ~시간의 ~좌석의 예매상태 n으로 바꾸기
		public void cancelSeat(String theater, String date, String time, String seat) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "update seat_tb as s join theater_tb as t on s.theater_no = t.no and t.name=? and s.date=? and s.time=? and s.seat_no=? set s.state='n'";

			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, theater);
				pstmt.setString(2, date);
				pstmt.setString(3, time);
				pstmt.setString(4, seat);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
		}

	// ~아이디의 티켓정보를 불러온다.
	public ArrayList<TicketBean> getTicketInfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TicketBean> list = new ArrayList<>();
		String sql = "select * from ticket_tb where user_id=?";
		TicketBean bean;

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TicketBean();
				bean.setNo(rs.getInt("no"));
				bean.setTitle(rs.getString("title"));
				bean.setTheater_name(rs.getString("theater_name"));
				bean.setDate(rs.getString("date"));
				bean.setTime(rs.getString("time"));
				bean.setSeat_no(rs.getString("seat_no"));
				bean.setPrice(rs.getInt("price"));
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
