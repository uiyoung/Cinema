package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.DBMgr;
import controllers.MovieMgr;
import models.MovieBean;

public class Reservation extends CinemaFrame implements ActionListener, MouseListener {
	DBMgr dbMgr = new DBMgr();
	MovieMgr movieMgr = new MovieMgr();
	ArrayList<MovieBean> movies;
	ArrayList<String> schedules;
	String title, date, theater, time, selectedNumOfTicket;

	JPanel firstPanel, secondPanel, thirdPanel;
	CalendarPanel calendarPanel = new CalendarPanel();
	JList<String> listMovie, listSchedule;
	DefaultListModel<String> schedulesListModel;
	JComboBox<String> cb;
	JScrollPane spSchedule = new JScrollPane();
	ImageIcon poster;
	JLabel lblPoster = new JLabel();
	// TODO : JTextArea 사용할까?
	// TODO:예매 정보 : 영화제목, 일자, 시간, 인원, 금액 나오는 라벨 만들기
	JLabel lblTitle = new JLabel();
	JLabel lblDate = new JLabel();
	JLabel lblTheater = new JLabel();
	JLabel lblTicketAmount = new JLabel();
	JButton btnCancel = new JButton("Cancel");
	JButton btnSeat = new JButton("Select Seat");

	public Reservation() {
		setTitle("Reservation");
		setLayout(new GridLayout(1, 3));

		/* First Panel */
		JPanel firstPanel = new JPanel(null);
		firstPanel.setBackground(Color.gray);
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
					listSchedule.setEnabled(true);
					title = listMovie.getSelectedValue();
					lblTitle.setText(title);
					poster = new ImageIcon("./images/" + listMovie.getSelectedValue() + ".jpg");

					lblTitle.setText(title);
					lblPoster.setIcon(poster);

					// 이걸 달력의 날짜버튼액션리스너에 붙이고 날짜를 변수로 바꾸기
					schedulesListModel.removeAllElements();
					schedules = dbMgr.getSchedule(title, "170502");
					if (schedules.size() == 0) {
						schedulesListModel.addElement("상영 정보가 없습니다.");
						listSchedule.setEnabled(false);
						return;
					}

					for (int i = 0; i < schedules.size(); i++) {
						schedulesListModel.addElement(schedules.get(i));
					}
				}
			}
		});
		JScrollPane spMovie = new JScrollPane(listMovie);
		spMovie.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spMovie.setBounds(60, 50, 320, 100);

		lblPoster.setBounds(60, 170, 320, 452);
		btnCancel.setBounds(30, 650, 130, 65);
		btnCancel.addActionListener(this);

		firstPanel.add(label1);
		firstPanel.add(spMovie);
		firstPanel.add(lblPoster);
		firstPanel.add(btnCancel);
		add(firstPanel);

		/* Second Panel */
		secondPanel = new JPanel(null);
		JLabel label2 = new JLabel("2. 날짜 선택");
		label2.setBounds(40, 10, 100, 30);

		// Calendar for select date
		calendarPanel.setBounds(40, 35, 360, 220);
		// 아무래도 달력을 여기에 가져오는게 낫겠어

		JLabel label3 = new JLabel("3. 영화관, 시간 선택");
		label3.setBounds(40, 260, 200, 30);

		// JList for select schedule
		schedulesListModel = new DefaultListModel<>();
		schedulesListModel.addElement("영화와 날짜를 선택해 주세요");
		listSchedule = new JList<>(schedulesListModel);
		listSchedule.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && listSchedule.hasFocus()) {
					theater = listSchedule.getSelectedValue();

					lblTheater.setText(theater);
				}
			}
		});
		JScrollPane spSchedule = new JScrollPane(listSchedule);
		spSchedule.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		spSchedule.setBounds(40, 290, 350, 100);

		JLabel label4 = new JLabel("4. 인원 선택");
		label4.setBounds(40, 450, 100, 30);

		// combo box for select num of tickets
		String[] numOfTickets = { "1명", "2명", "3명", "4명", "5명" };
		cb = new JComboBox<String>(numOfTickets);
		selectedNumOfTicket = (String) cb.getSelectedItem();
		cb.addActionListener(this);
		cb.setBounds(30, 500, 200, 30);

		// todo : 요금정보 표시

		secondPanel.add(label2);
		secondPanel.add(calendarPanel);
		secondPanel.add(label3);
		secondPanel.add(spSchedule);
		secondPanel.add(label4);
		secondPanel.add(cb);

		// secondPanel.setVisible(false);
		add(secondPanel);

		/* Third Panel */
		thirdPanel = new JPanel(null);
		thirdPanel.setBackground(Color.LIGHT_GRAY);

		JLabel label5 = new JLabel("5. 예매 정보");
		label5.setBounds(40, 10, 100, 30);
		lblTitle.setBounds(40, 50, 300, 30);
		lblDate.setBounds(40, 80, 100, 30);
		lblTheater.setBounds(40, 110, 200, 30);
		lblTicketAmount.setBounds(40, 140, 100, 30);
		btnSeat.setBounds(300, 650, 130, 65);
		btnSeat.addActionListener(this);

		thirdPanel.add(label5);
		thirdPanel.add(lblTitle);
		thirdPanel.add(lblDate);
		thirdPanel.add(lblTheater);
		thirdPanel.add(lblTicketAmount);
		thirdPanel.add(btnSeat);
		add(thirdPanel);

		// thirdPanel.setVisible(false);

		// repaint();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSeat) {
			System.out.println(title);
			System.out.println(date);
			System.out.println(theater);
			System.out.println(selectedNumOfTicket);

			// 영화제목, 관람일, 영화관, 인원이 선택안된 경우 showMessageDialog(~를 선택해 주세요)
			// if (DATE == " " || title == " " || TIME == " " || theater == " "
			// || DATE == "" || title == ""
			// || TIME == "" || theater == "") {
			// JOptionPane.showMessageDialog(null, "예매정보가 올바르지 않습니다.", "예매오류",
			// JOptionPane.ERROR_MESSAGE);
			// return;
			// } else {
			// DATE = selectedDate.getText();
			// title = j13.getText();
			// TIME = j16.getText();
			// theater = j15.getText();
			// }

			// 영화제목, 관람일, 시간, 영화관이 선택된 경우 좌석선택 열기
			// new Seats(TITLE, DATE, TIME, THEATER);
			// dispose();
		}

		if (e.getSource() == btnCancel) {
			new MainMenu();
			dispose();
		}

		if (e.getSource() == cb) {
			selectedNumOfTicket = (String) cb.getSelectedItem();
			lblTicketAmount.setText(selectedNumOfTicket);

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// if (e.getSource() == listMovie) {
		//
		// title = movies.get(listMovie.getSelectedIndex()).getTitle();
		// poster = new ImageIcon("./images/" + listMovie.getSelectedValue() +
		// ".jpg");
		// lblTitle.setText("영화제목 : " + title);
		// lblPoster.setIcon(poster);
		// }
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
