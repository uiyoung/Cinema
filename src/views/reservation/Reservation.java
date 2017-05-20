package views.reservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import views.movieinfo.MovieInfo;
import views.seat.Seat;

public class Reservation extends CinemaFrame implements ActionListener, MouseListener {
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
	// TODO:예매 정보 : 영화제목, 일자, 시간, 인원, 금액 나오는 라벨 만들기
	JLabel lblTitle, lblTheater, lblDate, lblTicketAmount, lblTime;
	JButton btnCancel = new JButton("Cancel");
	JButton btnSeat = new JButton("Select Seat");

	// Calendar things
	JPanel calendarPanel, monthControlPanel, daysPanel;
	Calendar today, cal;
	int year, month, day, memoday;
	String[] WEEK_DAY_NAMES = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
	JButton btnPrev, btnNext;
	JButton[] btnCalendar = new JButton[49];
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
		JLabel label1 = new JLabel("1. 영화 선택");
		label1.setBounds(60, 10, 100, 30);

		// JList for select movie
		movies = movieMgr.getMovie();
		DefaultListModel<String> moviesListModel = new DefaultListModel<>();
		for (int i = 0; i < movies.size(); i++) {
			moviesListModel.addElement(movies.get(i).getTitle());
		}
		listMovie = new JList<>(moviesListModel);
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

					if (theaters.size() == 0) {
						theaterListModel.addElement("해당 영화를 상영 중인 극장이 없습니다.");
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

		lblPoster.setBounds(60, 170, 320, 452);
		btnCancel.setBounds(30, 650, 100, 60);
		btnCancel.addActionListener(this);

		firstPanel.add(label1);
		firstPanel.add(spMovie);
		firstPanel.add(lblPoster);
		firstPanel.add(btnCancel);
		add(firstPanel);
	}

	private void initSecondPanel() {
		secondPanel = new JPanel(null);
		JLabel label2 = new JLabel("2. 극장 선택");
		label2.setBounds(40, 10, 100, 30);

		// JList for select Theater
		theaterListModel = new DefaultListModel<>();
		theaterListModel.addElement("영화를 선택해 주세요");
		listTheater = new JList<>(theaterListModel);
		listTheater.setEnabled(false);
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

		JLabel label3 = new JLabel("3. 날짜, 시간 선택");
		label3.setBounds(40, 200, 200, 30);

		// Calendar for select date
		initCalendar();

		// JList for select time
		timesListModel = new DefaultListModel<>();
		timesListModel.addElement("날짜를 선택해 주세요");
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

		JLabel label4 = new JLabel("4. 인원 선택");
		label4.setBounds(40, 10, 100, 30);

		// combo box for select num of tickets
		String[] numOfTickets = { "1", "2", "3", "4", "5" };
		cb = new JComboBox<String>(numOfTickets);
		numOfTicket = (String) cb.getSelectedItem();
		cb.addActionListener(this);
		cb.setBounds(30, 50, 200, 30);

		// todo : 요금정보 표시
		JLabel label5 = new JLabel("5. 예매 정보");
		lblTitle = new JLabel();
		lblTheater = new JLabel();
		lblDate = new JLabel();
		lblTicketAmount = new JLabel("1명");
		lblTime = new JLabel();
		label5.setBounds(40, 250, 100, 30);
		lblTitle.setBounds(40, 300, 300, 30);
		lblTheater.setBounds(40, 330, 200, 30);
		lblDate.setBounds(40, 360, 100, 30);
		lblTime.setBounds(40, 390, 100, 30);
		lblTicketAmount.setBounds(40, 420, 100, 30);
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
		add(thirdPanel);
	}

	///////////////////////// CALENDAR THINGS //////////////////////////
	public void initCalendar() {
		calendarPanel = new JPanel(new BorderLayout());
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
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				daysPanel.removeAll();
				updateMonth(-1);
			}
		});
		btnNext.addActionListener(new ActionListener() {
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
		// 날짜 버튼
		for (int i = WEEK_DAY_NAMES.length; i < 49; i++) {
			daysPanel.add(btnCalendar[i] = new JButton(""));
			btnCalendar[i].setContentAreaFilled(false);
			btnCalendar[i].setBorderPainted(false);
			btnCalendar[i].setFocusPainted(true);
			btnCalendar[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (title == null) {
						JOptionPane.showMessageDialog(null, "영화를 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
						return;
					} else if (theater == null) {
						JOptionPane.showMessageDialog(null, "극장을 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (Integer.parseInt(e.getActionCommand()) >= 1 && Integer.parseInt(e.getActionCommand()) <= 31) {
						day = Integer.parseInt(e.getActionCommand());
						date = year + "" + month + "" + day + "";
						// TODO : 날짜버튼을 누르면 yyMMdd 형식으로 나오게 구현

						// update 시간선택 JList
						timesListModel.removeAllElements(); // 시간 list 초기화
						times = dbMgr.getTime(title, theater, date);
						// System.out.println(times.get(0));
						if (times.size() == 0) {
							date = null;
							time = null;
							lblDate.setText(date);
							lblTime.setText(time);
							timesListModel.addElement("해당일에 상영 스케줄이 없습니다.");
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
	///////////////////////// END CALENDAR THINGS //////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		/* for combobox */
		if (e.getSource() == cb) {
			numOfTicket = (String) cb.getSelectedItem();
			lblTicketAmount.setText(numOfTicket + "명");
		}

		/* for buttons */
		if (e.getSource() == btnSeat) {
			if (title == null) {
				JOptionPane.showMessageDialog(null, "영화를 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (theater == null) {
				JOptionPane.showMessageDialog(null, "극장을 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (time == null) {
				JOptionPane.showMessageDialog(null, "시간을 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (numOfTicket == null) {
				JOptionPane.showMessageDialog(null, "인원을 선택해주세요.", "예매오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			System.out.println(title);
			System.out.println(theater);
			System.out.println(date);
			System.out.println(time);
			System.out.println(numOfTicket);

			new Seat(title, theater, date, time, numOfTicket);
			dispose();
		}

		if (e.getSource() == btnCancel) {
			new MovieInfo();
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		new Reservation();
	}
}
