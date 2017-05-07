package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {

	public static void main(String[] args) {
		// String date_s = "2017-01-18";
		// Date date = null;
		// try {
		// date = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// SimpleDateFormat dt1 = new SimpleDateFormat("yyMMdd");
		// System.out.println(dt1.format(date));

		// Date today = new Date();
		// String oldDate = "2017-01-18";
		//
		// SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		// SimpleDateFormat time = new SimpleDateFormat("hh:mm ss a");
		//
		// System.out.println("오늘 날짜는 " + date.format(today)); // -> 2006/10/03
		// System.out.println("현재 시간은 " + time.format(today)); // -> 04:22 15 오후

		// SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분
		// ss초");

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

		String today = df.format(c.getTime());
		System.out.println(today);

	}

}
