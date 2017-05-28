package models;

public class SeatBean {
	private String theater_no;
	private String date;
	private String time;
	private String seat_no;
	private String state;

	// Getters and Setters
	public String getTheater_no() {
		return theater_no;
	}

	public void setTheater_no(String theater_no) {
		this.theater_no = theater_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
