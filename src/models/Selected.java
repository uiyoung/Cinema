package models;

public class Selected {
	public static String movieTitle;
	String theaterName;
	public static String date;

	public static String getMovieTitle() {
		return movieTitle;
	}

	public static void setMovieTitle(String movieTitle) {
		Selected.movieTitle = movieTitle;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		Selected.date = date;
	}

}
