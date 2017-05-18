package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.DBMgr;
import models.MemberBean;
import models.SeatBean;

public class Seat extends CinemaFrame implements ActionListener {
	DBMgr mgr = new DBMgr(); // DAO
	MemberBean bean; // DTO
	ArrayList<SeatBean> list;

	int num = 0;
	String c[] = { "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

	String title, theater, date, time, ticket;
	// String id = Login.staticid;
	String seat;

	JButton btnPayment, btnPrev;
	JPanel seatPanel;
	JLabel lblSeatNumber = new JLabel();
	JCheckBox[][] seats;
	String[] seatNumbers;

	public Seat(String title, String theater, String date, String time, String ticket) {
		this.title = title;
		this.theater = theater;
		this.time = time;
		this.date = date;
		this.ticket = ticket;

		setLayout(null);
		setTitle("좌석선택");

		// test
		list = mgr.getSeats(theater, date, time);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getSeatNo() + "," + list.get(i).getState());
		}

		// TODO : db에서 좌석정보 불러와서 체크박스 만들기
		// TODO : initSeats에 집어넣기
		seatNumbers = new String[list.size()];
		for (int i = 0; i < seatNumbers.length; i++) {
			seatNumbers[i] = list.get(i).getSeatNo();
			System.out.println(seatNumbers[i]);
		}

		initScreenLabel();
		initSeats();
		initButtons();

		setVisible(true);
		repaint();
	}

	private void initScreenLabel() {
		JLabel lblScreen = new JLabel("SCREEN");
		lblScreen.setFont(new Font(null, Font.BOLD, 25));
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreen.setBackground(Color.ORANGE);
		lblScreen.setForeground(Color.WHITE);
		lblScreen.setOpaque(true);
		lblScreen.setBounds(600, 30, 500, 50);
		add(lblScreen);
	}

	private void initSeats() {
		seatPanel = new JPanel(null);
		seatPanel.setBounds(450, 140, 762, 500);

		// TODO: seatSelected 색상 추가하기
		ImageIcon available = new ImageIcon("images/seatAvailable.png");
		ImageIcon sold = new ImageIcon("images/seatSold.png");

		// Labels for show seat number
		// int x = 0, y = 0;
		// for (int i = 0; i < seatNumbers.length; i++) {
		// lblSeatNumber = new JLabel(seatNumbers[i]);
		//
		// if (i % 10 == 0 && i != 0) {
		// x = 0;
		// y += 50;
		// }
		// lblSeatNumber.setBounds(x + 12, y, 75, 45);
		// x += 77;
		// seatPanel.add(lblSeatNumber);
		// }

		// CheckBoxes for select seats
		seats = new JCheckBox[list.size() / 10][10];
		// list.size()/10 : db에저장된 좌석의 수/10은 db의 좌석의 행의 수를 의미
		int posX = 0, posY = 0;
		for (int i = 0; i < seats.length; i++) { // 행
			for (int j = 0; j < seats[i].length; j++) { // 열
				seats[i][j] = new JCheckBox(available);
				// seats[i][j] = new JCheckBox();
				// seats[i][j].setIcon(available);
				/* seat[i][j] 번째의 좌석번호는 db에 저장된 ij번째 값과 같다. */
				String seatNo = list.get(Integer.parseInt(i + "" + j)).getSeatNo();
				seats[i][j].setText(seatNo);
				seats[i][j].setToolTipText("좌석번호:" + seatNo);
				seats[i][j].setRolloverIcon(sold);
				seats[i][j].setSelectedIcon(sold);
				seats[i][j].setOpaque(false);
				seats[i][j].setBounds(posX, posY, 70, 45);
				posX += 77;
				seatPanel.add(seats[i][j]);
			}
			posX = 0;
			posY += 50;
		}

		// TODO : sold 상태의 좌석의 체크박스는 disable 상태로 만들기

		// list = mgr.seatload(title, date, time);
		//
		// for (int t = 0; t < list.size(); t++) {
		// int yy=list.get(t).getSeaty();
		// int xx=list.get(t).getSeatx();
		// seats[yy][xx].setEnabled(false);
		// }

		add(seatPanel);
	}

	// TODO : 좌석상태 정보 아이콘(Available, Sold, Selected 정보 표시)
	private void initSeatInfo() {
		// ImageIcon i1 = new ImageIcon("images/i1.gif");
		// ImageIcon i2 = new ImageIcon("images/i2.gif");
		// ImageIcon i3 = new ImageIcon("images/i3.gif");
		//
		// JLabel s1 = new JLabel();
		// s1.setBounds(960, 210, 95, 50);
		// s1.setIcon(i1);
		// JLabel s2 = new JLabel();
		// s2.setBounds(960, 270, 95, 50);
		// s2.setIcon(i2);
		// JLabel s3 = new JLabel();
		// s3.setBounds(960, 330, 95, 50);
		// s3.setIcon(i3);
		// add(s1);
		// add(s2);
		// add(s3);
	}

	private void initButtons() {
		btnPayment = new JButton("payment");
		btnPrev = new JButton("prev");
		btnPayment.addActionListener(this);
		btnPrev.addActionListener(this);
		btnPayment.setBounds(1200, 650, 130, 65);
		btnPrev.setBounds(40, 650, 130, 65);
		add(btnPayment);
		add(btnPrev);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPrev) {
			new Reservation();
			dispose();
		}

		if (e.getSource() == btnPayment) {
			// 체크여부 확인 TODO:ItemListener사용해서 체크여부 확인
			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					if (seats[i][j].isSelected())
						System.out.print("선택된 좌석:" + seats[i][j].getText());
				}
			}

			int result = JOptionPane.showConfirmDialog(null, "예약하시겠습니까?", "예약", JOptionPane.YES_NO_OPTION);
			// DB에서 중복검사필요
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "예약되었습니다.", "예약완료", JOptionPane.INFORMATION_MESSAGE);

				for (int i = 0; i < seats.length; i++) {
					for (int j = 0; j < seats[i].length; j++) {
						if (seats[i][j].isSelected()) {
							seats[i][j].setEnabled(false);
							seat = c[i] + Integer.toString(j);
							System.out.println(seat);
							// mgr.seatinsert(title, date, time, i, j);
							// mgr.inmovie(date, title, time, theater, seat,
							// num, id);
						}
					}
				}
				// 예약이 완료되면 seat[i]를 setEnable(false)처리
			}

			// TODO: 좌석이 선택안된경우 에러메세지
			// JOptionPane.showMessageDialog(null, "좌석을 선택해 주세요", "error",
			// JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new Seat("美女と野獣", "tokyo", "201752", "14:00", "1");
		// new Seat("美女と野獣", "11", "11", "11");
	}
}