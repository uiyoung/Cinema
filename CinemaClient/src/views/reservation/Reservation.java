package views.reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.DBMgr;
import controllers.MovieMgr;
import models.MovieBean;
import models.TheaterBean;
import views.CinemaFrame;
import views.MainMenu;
import views.movieinfo.MovieInfo;
import views.seat.Seat;

public class Reservation extends CinemaFrame implements ActionListener {
	DBMgr dbMgr = new DBMgr();
	MovieMgr movieMgr = new MovieMgr();
	ArrayList<MovieBean> movies;
	ArrayList<TheaterBean> theaters;
	ArrayList<String> times;
	String title, date, theater, time, numOfTicket;

	JPanel firstPanel, secondPanel, thirdPanel;
	JList<String> listMovie, listTheater, listTime;
	DefaultListModel<String> theaterListModel, timesListModel;
	JComboBox<String> cb;
	JScrollPane spSchedule = new JScrollPane();
	ImageIcon poster;
	JLabel lblPoster = new JLabel();
	JLabel lblTitle, lblTheater, lblDate, lblTicketAmount, lblTime;
	JButton btnBackToMain, btnMovieInfo, btnSeat;

	// Calendar things
	JPanel calendarPanel, monthControlPanel, daysPanel;
	Calendar today, cal;
	int year, month, day, memoday;
	String[] WEEK_DAY_NAMES = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
	JButton btnPrevMonth, btnNextMonth;
	JButton[] btnDay = new JButton[49];
	JLabel lblMonth, lblYear;

	public Reservation() {
		setTitle("Reservation");
		setLayout(new GridLayout(1, 3));
		initFirstPanel();
		initSecondPanel();
		initThirdPanel();
		setVisible(true);
	}

	private void initFirstPanel() {
		JPanel firstPanel = new JPanel(null);
		firstPanel.setBackground(Color.GRAY);
		JLabel label1 = new JLabel("1. 映画選択");
		label1.setBounds(60, 10, 120, 30);
		label1.setFont(new Font("MS Gothic", Font.BOLD, 18));

		// JList for select movie
		movies = movieMgr.getMovie();
		DefaultListModel<String> moviesListModel = new DefaultListModel<>();
		for (int i = 0; i < movies.size(); i++) {
			moviesListModel.addElement(movies.get(i).getTitle());
		}
		listMovie = new JList<>(moviesListModel);
		listMovie.setSelectedIndex(0); // 기본 선택값
		title = listMovie.getSelectedValue();
		showPoster();
		listMovie.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					listTheater.setEnabled(true);
					title = listMovie.getSelectedValue();
					lblTitle.setText(title);
					poster = new ImageIcon("./images/" + listMovie.getSelectedValue() + ".jpg");

					// update 영화관, 시간선택 list
					theaterListModel.removeAllElements();
					theaters = dbMgr.getTheaters(title);

					lblTitle.setText(title);
					lblPoster.setIcon(poster);
					lblPoster.setBounds(60, 170, 320, 452);

					if (theaters.size() == 0) {
						theaterListModel.addElement("選択された映画を上映中の劇場はありません。");
						listTheater.setEnabled(false);
						theater = null;
						lblTheater.setText(theater);
						return;
					} else {
						listTheater.setEnabled(true);
					}

					for (int i = 0; i < theaters.size(); i++) {
						theaterListModel.addElement(theaters.get(i).getName());
					}
				}
			}
		});
		JScrollPane spMovie = new JScrollPane(listMovie);
		spMovie.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spMovie.setBounds(60, 50, 320, 100);

		btnMovieInfo = new JButton();
		btnMovieInfo.setIcon(new ImageIcon("images/reservation/btnMovieInfo.jpg"));
		btnMovieInfo.setBounds(300, 650, 130, 65);
		btnMovieInfo.addActionListener(this);

		btnBackToMain = new JButton();
		btnBackToMain.setIcon(new ImageIcon("images/reservation/btnMainMenu.jpg"));
		btnBackToMain.setBounds(30, 650, 130, 65);
		btnBackToMain.addActionListener(this);

		firstPanel.add(label1);
		firstPanel.add(spMovie);
		firstPanel.add(lblPoster);
		firstPanel.add(btnBackToMain);
		firstPanel.add(btnMovieInfo);
		add(firstPanel);
	}

	private void showPoster() {
		poster = new ImageIcon("./images/" + listMovie.getSelectedValue() + ".jpg");
		lblPoster.setIcon(poster);
		lblPoster.setBounds(60, 170, 320, 452);
	}

	private void initSecondPanel() {
		secondPanel = new JPanel(null);
		JLabel label2 = new JLabel("2. 劇場選択");
		label2.setFont(new Font("MS Gothic", Font.BOLD, 18));
		label2.setBounds(40, 10, 120, 30);

		// JList for select Theater
		theaterListModel = new DefaultListModel<>();
		// theaterListModel.addElement("영화를 선택해 주세요");
		/* 기본 선택값 */
		theaters = dbMgr.getTheaters(title);
		for (int i = 0; i < theaters.size(); i++) {
			theaterListModel.addElement(theaters.get(i).getName());
		}
		/* end of 기본 선택값 */
		listTheater = new JList<>(theaterListModel);
		// listTheater.setEnabled(false);
		listTheater.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				theater = listTheater.getSelectedValue();
				lblTheater.setText(theater);
				date = null;
				time = null;
				lblDate.setText(date);
				lblTime.setText(time);
				timesListModel.removeAllElements(); // 시간 목록 초기화
				time = null;
			}
		});
		JScrollPane spTheater = new JScrollPane(listTheater);
		spTheater.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spTheater.setBounds(40, 50, 350, 100);

		JLabel label3 = new JLabel("3. 日時選択");
		label3.setFont(new Font("MS Gothic", Font.BOLD, 18));
		label3.setBounds(40, 200, 200, 30);

		// Calendar for select date
		initCalendar();

		// JList for select time
		timesListModel = new DefaultListModel<>();
		timesListModel.addElement("日付を選んでください");
		listTime = new JList<>(timesListModel);
		listTime.setEnabled(false);
		listTime.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && listTime.hasFocus()) {
					time = listTime.getSelectedValue();
					lblTime.setText(time);
				}
			}
		});
		JScrollPane spTime = new JScrollPane(listTime);
		spTime.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spTime.setBounds(40, 460, 350, 100);

		secondPanel.add(label2);
		secondPanel.add(spTheater);
		secondPanel.add(label3);
		secondPanel.add(calendarPanel);
		secondPanel.add(spTime);
		add(secondPanel);
	}

	private void initThirdPanel() {
		thirdPanel = new JPanel(null);
		thirdPanel.setBackground(Color.LIGHT_GRAY);

		JLabel label4 = new JLabel("4. 人数選択");
		label4.setFont(new Font("MS Gothic", Font.BOLD, 18));
		label4.setBounds(40, 10, 120, 30);

		// combo box for select num of tickets
		String[] numOfTickets = { "1", "2", "3", "4", "5" };
		cb = new JComboBox<String>(numOfTickets);
		numOfTicket = (String) cb.getSelectedItem();
		cb.addActionListener(this);
		cb.setBounds(30, 50, 200, 30);

		JLabel label5 = new JLabel("5. 予約情報");
		label5.setFont(new Font("MS Gothic", Font.BOLD, 18));
		lblTitle = new JLabel(title);
		lblTheater = new JLabel();
		lblDate = new JLabel();
		lblTicketAmount = new JLabel("1人");
		lblTime = new JLabel();
		label5.setBounds(40, 250, 120, 30);
		lblTitle.setBounds(40, 300, 300, 30);
		lblTheater.setBounds(40, 330, 200, 30);
		lblDate.setBounds(40, 360, 100, 30);
		lblTime.setBounds(40, 390, 100, 30);
		lblTicketAmount.setBounds(40, 420, 100, 30);

		JLabel lblTicket = new JLabel(new ImageIcon("images/reservation/ticket.png"));
		lblTicket.setBounds(15, 155, 250, 420);

		btnSeat = new JButton();
		btnSeat.setIcon(new ImageIcon("images/reservation/btnSeat.jpg"));
		btnSeat.setBounds(300, 650, 130, 65);
		btnSeat.addActionListener(this);

		thirdPanel.add(label4);
		thirdPanel.add(cb);
		thirdPanel.add(label5);
		thirdPanel.add(lblTitle);
		thirdPanel.add(lblDate);
		thirdPanel.add(lblTheater);
		thirdPanel.add(lblTime);
		thirdPanel.add(lblTicketAmount);
		thirdPanel.add(btnSeat);

		thirdPanel.add(lblTicket);
		add(thirdPanel);
	}

	/////////////////////// CALENDAR THINGS //////////////////////////
	public void initCalendar() {
		calendarPanel = new JPanel(new BorderLayout());
		today = Calendar.getInstance(); // 디폴트 타입 존 및 로케일을 사용해 달력생성
		cal = new GregorianCalendar();
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;// 1월 값이 0
		monthControlPanel = new JPanel();
		monthControlPanel.add(btnPrevMonth = new JButton("<"));
		monthControlPanel.add(lblYear = new JLabel(year + "年"));
		monthControlPanel.add(lblMonth = new JLabel(month + "月"));
		monthControlPanel.add(btnNextMonth = new JButton(">"));
		btnPrevMonth.setContentAreaFilled(false);
		btnNextMonth.setContentAreaFilled(false);
		btnPrevMonth.setFocusPainted(false);
		btnNextMonth.setFocusPainted(false);
		btnPrevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				daysPanel.removeAll();
				updateMonth(-1);
			}
		});
		btnNextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				daysPanel.removeAll();
				updateMonth(1);
			}
		});
		lblYear.setFont(new Font("Yu Gothic", Font.PLAIN, 18));
		lblMonth.setFont(new Font("Yu Gothic", Font.PLAIN, 18));
		calendarPanel.add(monthControlPanel, BorderLayout.PAGE_START);

		// 달력 날에 해당
		daysPanel = new JPanel(new GridLayout(7, 7));
		gridInit();
		calSet();
		hideInit();
		calendarPanel.add(daysPanel, BorderLayout.CENTER);
		calendarPanel.setBounds(40, 240, 360, 220);
	}

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
			btnDay[kk + 7].setText("");
		}
		for (int i = cal.getMinimum(Calendar.DAY_OF_MONTH); i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++) {
			cal.set(Calendar.DATE, i);
			if (cal.get(Calendar.MONTH) != month - 1) {
				break;
			}

			if (memoday == 1) {
				btnDay[i + 6 + hopping].setForeground(new Color(0, 255, 0));
			} else {
				btnDay[i + 6 + hopping].setForeground(new Color(0, 0, 0));
				if ((i + hopping - 1) % 7 == 0) {// 일요일
					btnDay[i + 6 + hopping].setForeground(new Color(255, 0, 0));
				}
				if ((i + hopping) % 7 == 0) {// 토요일
					btnDay[i + 6 + hopping].setForeground(new Color(0, 0, 255));
				}
			}
			/*
			 * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고 인덱스가 0부터 시작이니 -1을 해준 값으로
			 * 연산을 해주고 버튼을 변경
			 */
			btnDay[i + 6 + hopping].setText((i) + "");
		}
	}// end Calset()

	// 일이 찍히지 않은 나머지 버튼을 비활성화
	public void hideInit() {
		for (int i = 0; i < btnDay.length; i++) {
			if ((btnDay[i].getText()).equals("")) {
				btnDay[i].setEnabled(false);
				btnDay[i].setVisible(false);
			}
		}
	}

	public void gridInit() {
		// 일월화수목금토 버튼
		for (int i = 0; i < WEEK_DAY_NAMES.length; i++) {
			daysPanel.add(btnDay[i] = new JButton(WEEK_DAY_NAMES[i]));
			btnDay[i].setContentAreaFilled(false);
			btnDay[i].setBorderPainted(false);
			btnDay[i].setOpaque(true);
			btnDay[i].setFocusPainted(false);
			btnDay[i].setForeground(Color.WHITE);

			if (i == 0) // sun
				btnDay[i].setBackground(new Color(200, 50, 50));
			else if (i == 6) // sat
				btnDay[i].setBackground(new Color(50, 100, 200));
			else // mo, tu, we, th, fr
				btnDay[i].setBackground(new Color(150, 150, 150));
		}
		// 날짜 버튼
		for (int i = WEEK_DAY_NAMES.length; i < 49; i++) {
			daysPanel.add(btnDay[i] = new JButton(""));
			btnDay[i].setContentAreaFilled(false);
			btnDay[i].setBorderPainted(false);
			btnDay[i].setFocusPainted(true);
			btnDay[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (title == null) {
						JOptionPane.showMessageDialog(null, "映画を選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
						return;
					} else if (theater == null) {
						JOptionPane.showMessageDialog(null, "劇場を選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= 31) {
						day = Integer.parseInt(e.getActionCommand());
						date = year + "" + month + "" + day + "";
						// TODO : 날짜버튼을 누르면 yyMMdd 형식으로 나오게 구현

						// TODO : 선택된 날의 배경색 하이라이트
						// btnDay[Integer.parseInt(e.getActionCommand()) +
						// 7].setBackground(Color.GREEN);
						// btnDay[Integer.parseInt(e.getActionCommand()) +
						// 7].setOpaque(true);

						// update 시간선택 JList
						timesListModel.removeAllElements(); // 시간 list 초기화
						times = dbMgr.getTime(title, theater, date);
						if (times.size() == 0) {
							date = null;
							time = null;
							lblDate.setText(date);
							lblTime.setText(time);
							timesListModel.addElement("選択された日は上映スケジュールがありません.");
							listTime.setEnabled(false);
							return;
						} else {
							listTime.setEnabled(true);
						}
						for (int i = 0; i < times.size(); i++) {
							timesListModel.addElement(times.get(i));
						}
						lblDate.setText(year + "年" + month + "月" + day + "日");
						// calSet();
					}
				}
			});
		}
	}

	public void panelInit() {
		GridLayout gridLayout = new GridLayout(7, 7);
		daysPanel.setLayout(gridLayout);
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
		repaint();
	}
	///////////////////////// END of CALENDAR THINGS //////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cb) {
			numOfTicket = (String) cb.getSelectedItem();
			lblTicketAmount.setText(numOfTicket + "人");
		}

		if (e.getSource() == btnSeat) {
			if (title == null) {
				JOptionPane.showMessageDialog(null, "映画を選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (theater == null) {
				JOptionPane.showMessageDialog(null, "劇場を選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (time == null) {
				JOptionPane.showMessageDialog(null, "時間を選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (numOfTicket == null) {
				JOptionPane.showMessageDialog(null, "人数選んでください。", "予約ミス", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// TODO : 해당 title, theater, date, time 의 좌석 정보를 불러와서 좌석이 꽉 찼거나
			// 좌석정보가 없는경우 에러메세지 ex)mgr.checkSeat(title, theater, date, time);

			new Seat(title, theater, date, time, numOfTicket);
			dispose();
		}

		if (e.getSource() == btnMovieInfo) {
			new MovieInfo();
			dispose();
		}

		if (e.getSource() == btnBackToMain) {
			new MainMenu();
			dispose();
		}
	}

	public static void main(String[] args) {
		new Reservation();
	}
}