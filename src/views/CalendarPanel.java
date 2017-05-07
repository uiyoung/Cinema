package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Selected;

public class CalendarPanel extends JPanel implements ActionListener {
	String[] WEEK_DAY_NAMES = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
	int year, month, day, memoday = 0;
	Calendar today;
	Calendar cal;
	JButton btnPrev, btnNext;
	JButton[] btnCalendar = new JButton[49];
	JLabel lblMonth, lblYear;
	JPanel monthControlPanel, daysPanel;

	public CalendarPanel() {
		setLayout(new BorderLayout());
		today = Calendar.getInstance(); // 디폴트 타입 존 및 로케일을 사용해 달력생성
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1월 값이 0

		monthControlPanel = new JPanel();
		monthControlPanel.add(btnPrev = new JButton("<"));
		monthControlPanel.add(lblYear = new JLabel(year + "年"));
		monthControlPanel.add(lblMonth = new JLabel(month + "月"));
		monthControlPanel.add(btnNext = new JButton(">"));
		btnPrev.setContentAreaFilled(false);
		btnNext.setContentAreaFilled(false);
		btnPrev.setFocusPainted(false);
		btnNext.setFocusPainted(false);
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		lblYear.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblMonth.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		add(monthControlPanel, BorderLayout.PAGE_START);

		// 달력 날에 해당
		daysPanel = new JPanel(new GridLayout(7, 7));
		gridInit();
		calSet();
		hideInit();
		add(daysPanel, BorderLayout.CENTER);
	}// end constuctor

	public void calSet() {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		cal.set(Calendar.DATE, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		/*
		 * get 및 set을 위한 필드, 요일을 나타냄 값은 SUNDAY,MONDAY,TUESDAY,WEDNESDAY
		 * ,THURSDAY,FRIDAY, SATURDAY가 됨 get메소드에 의해 요일이 숫자로 변경
		 */
		int j = 0;
		int hopping = 0;
		for (int i = cal.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			j++;
		}
		// 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅
		hopping = j;
		for (int kk = 0; kk < hopping; kk++) {
			btnCalendar[kk + 7].setText("");
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			if (cal.get(Calendar.MONTH) != month - 1) {
				break;
			}

			if (memoday == 1) {
				btnCalendar[i + 6 + hopping].setForeground(new Color(0, 255, 0));
			} else {
				btnCalendar[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				if ((i + hopping - 1) % 7 == 0) {// 일요일
					btnCalendar[i + 6 + hopping].setForeground(new Color(255, 0, 0));
				}
				if ((i + hopping) % 7 == 0) {// 토요일
					btnCalendar[i + 6 + hopping].setForeground(new Color(0, 0, 255));
				}
			}
			/*
			 * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고 인덱스가 0부터 시작이니 -1을 해준 값으로
			 * 연산을 해주고 버튼을 변경
			 */
			btnCalendar[i + 6 + hopping].setText((i) + "");
		}
	}// end Calset()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPrev) {
			daysPanel.removeAll();
			updateMonth(-1);
		} else if (e.getSource() == btnNext) {
			daysPanel.removeAll();
			updateMonth(1);
		} else if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= 31) {
			day = Integer.parseInt(e.getActionCommand());
			System.out.println(year + "年" + month + "月" + day + "日");
			Selected.date = year + "" + month + "" + day + "";
			System.out.println(Selected.date);
			// TODO : 날짜버튼을 누르면 yyMMdd 형식으로 나오게 구현

			// setVisible(false);
			// calSet();
		}
	}

	// 일이 찍히지 않은 나머지 버튼을 비활성화
	public void hideInit() {
		for (int i = 0; i < btnCalendar.length; i++) {
			if ((btnCalendar[i].getText()).equals(""))
				btnCalendar[i].setEnabled(false);
		}
	}

	public void gridInit() {
		// 일월화수목금토 버튼
		for (int i = 0; i < WEEK_DAY_NAMES.length; i++) {
			daysPanel.add(btnCalendar[i] = new JButton(WEEK_DAY_NAMES[i]));
			btnCalendar[i].setContentAreaFilled(false);
			btnCalendar[i].setBorderPainted(false);
			btnCalendar[i].setOpaque(true);
			btnCalendar[i].setFocusPainted(false);
			btnCalendar[i].setForeground(Color.WHITE);

			if (i == 0) // sun
				btnCalendar[i].setBackground(new Color(200, 50, 50));
			else if (i == 6) // sat
				btnCalendar[i].setBackground(new Color(50, 100, 200));
			else // mo, tu, we, th, fr
				btnCalendar[i].setBackground(new Color(150, 150, 150));
		}
		// 날짜(숫자) 버튼
		for (int i = WEEK_DAY_NAMES.length; i < 49; i++) {
			daysPanel.add(btnCalendar[i] = new JButton(""));
			btnCalendar[i].setContentAreaFilled(false);
			btnCalendar[i].setBorderPainted(false);
			btnCalendar[i].setFocusPainted(true);
			btnCalendar[i].addActionListener(this);
		}
	}

	public void panelInit() {
		GridLayout gridLayout1 = new GridLayout(7, 7);
		daysPanel.setLayout(gridLayout1);
	}

	public void updateMonth(int gap) {
		month += (gap);
		if (month <= 0) {
			month = 12;
			year -= 1;
		} else if (month >= 13) {
			month = 1;
			year += 1;
		}

		gridInit();
		panelInit();
		calSet();
		hideInit();
		lblYear.setText(year + "年");
		lblMonth.setText(month + "月");
	}
}