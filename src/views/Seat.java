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
	ArrayList<SeatBean> list;
	MemberBean bean; // DTO

	int num = 0;
	String c[] = { "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

	String title, date, theater;
	// String id = Login.staticid;
	String seat;

	JButton btnPayment, btnPrev;
	JPanel seatPanel;
	JLabel lblSeatNumber = new JLabel();
	JCheckBox[][] seats = new JCheckBox[10][10];
	String[] seatNumbers;
	// String[] seatNumbers = { "A01", "A02", "A03", "A04", "A05", "A06", "A07",
	// "A08", "A09", "A10", "B01", "B02", "B03",
	// "B04", "B05", "B06", "B07", "B08", "B09", "B10", "C01", "C02", "C03",
	// "C04", "C05", "C06", "C07", "C08",
	// "C09", "C10", "D01", "D02", "D03", "D04", "D05", "D06", "D07", "D08",
	// "D09", "D10", "E01", "E02", "E03",
	// "E04", "E05", "E06", "E07", "E08", "E09", "E10", "F01", "F02", "F03",
	// "F04", "F05", "F06", "F07", "F08",
	// "F09", "F10", "G01", "G02", "G03", "G04", "G05", "G06", "G07", "G08",
	// "G09", "G10", "H01", "H02", "H03",
	// "H04", "H05", "H06", "H07", "H08", "H09", "H10", "I01", "I02", "I03",
	// "I04", "I05", "I06", "I07", "I08",
	// "I09", "I10", "J01", "J02", "J03", "J04", "J05", "J06", "J07", "J08",
	// "J09", "J10" };

	public Seat(String title, String date, String theater, String ticket) {
		this.title = title;
		this.date = date;
		this.theater = theater;

		setLayout(null);
		setTitle("좌석선택");

		// test
		list = mgr.getSeats(theater, date);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getSeatNo() + "," + list.get(i).getState());
		}

		// TODO : db에서 좌석정보 불러와서 체크박스 만들기
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
		// seatPanel.setLayout(null);
		seatPanel = new JPanel(null);
		seatPanel.setBounds(450, 140, 762, 500);

		// TODO: seatSelected 색상 추가하기
		ImageIcon available = new ImageIcon("images/seatAvailable.png");
		ImageIcon sold = new ImageIcon("images/seatSold.png");

		// Labels for show seat number
		int x = 0, y = 0;
		for (int i = 0; i < seatNumbers.length; i++) {
			lblSeatNumber = new JLabel(seatNumbers[i]);

			if (i % 10 == 0 && i != 0) {
				x = 0;
				y += 50;
			}
			lblSeatNumber.setBounds(x + 12, y, 75, 45);
			x += 77;
			seatPanel.add(lblSeatNumber);
		}

		// CheckBoxes for select seats
		int posXpanSeat = 0, posYpanSeat = 0;
		for (int i = 0; i < seats.length; i++) { // 행
			for (int j = 0; j < seats[i].length; j++) { // 열
				seats[i][j] = new JCheckBox(available);
				seats[i][j].setBounds(posXpanSeat, posYpanSeat, 70, 45);
				posXpanSeat += 77;
				seats[i][j].setRolloverIcon(sold);
				seats[i][j].setSelectedIcon(sold);
				seats[i][j].setOpaque(false);
				seatPanel.add(seats[i][j]);
			}
			posXpanSeat = 0;
			posYpanSeat += 50;
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
			/*
			 * 체크여부 확인 필요 boolean checked = false; for (int i = 1; i <= 10; i++)
			 * { for (int j = 1; j <= 10; j++) {
			 * seats[i][j].setSelected(checked); } } if(checked){
			 * System.out.println("a"); } else System.out.println("b");
			 */

			int result = JOptionPane.showConfirmDialog(null, "예약하시겠습니까?", "예약", JOptionPane.YES_NO_OPTION);
			// DB에서 중복검사필요
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "예약되었습니다.", "예약완료", JOptionPane.INFORMATION_MESSAGE);
				for (int i = 1; i <= 10; i++) {
					for (int j = 1; j <= 10; j++) {
						if (seats[i][j].isSelected()) {
							seats[i][j].setEnabled(false);
							seat = c[i] + Integer.toString(j);

							// mgr.seatinsert(title, date, time, i, j);
							// mgr.inmovie(date, title, time, theater, seat,
							// num, id);
						}
					}
				}
				// 예약이 완료되면 seat[i]를 setEnable(false)처리
			}

			// TODO: 좌석이 선택안된경우 에러메세지

			// JOptionPane.showMessageDialog(null, "좌석을 선택해 주세요", "좌석선택안함",
			// JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		// new Seat(title, date, theater, ticket);
		// new Seat("11", "11", "11", "11");
	}
}
