package views.seat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import models.Selected;
import views.CinemaFrame;
import views.payment.Payment;
import views.reservation.Reservation;

public class Seat extends CinemaFrame implements ActionListener {
	private DBMgr mgr = new DBMgr(); // DAO
	private MemberBean bean; // DTO
	private ArrayList<SeatBean> list;

	private String c[] = { "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	private String title, theater, date, time, ticket, seat;
	private int selectionCounter = 0;
	private JButton btnPayment, btnReset, btnPrev;
	private JPanel seatPanel;
	private JLabel lblSeatNumber = new JLabel();
	private JCheckBox[][] seats;
	private String[] seatNumbers;

	public Seat(String title, String theater, String date, String time, String ticket) {
		this.title = title;
		this.theater = theater;
		this.time = time;
		this.date = date;
		this.ticket = ticket;

		setLayout(null);
		setTitle("좌석선택");
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
		// seatNumbers = new String[list.size()];
		// for (int i = 0; i < seatNumbers.length; i++) {
		// seatNumbers[i] = list.get(i).getSeatNo();
		// System.out.println(seatNumbers[i]);
		// }
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
		list = mgr.getSeats(theater, date, time);
		seats = new JCheckBox[list.size() / 10][10];
		/* list.size()/10 : db에저장된 좌석의 수/10은 db의 좌석의 행의 수를 의미 */
		int posX = 0, posY = 0;
		for (int i = 0; i < seats.length; i++) { // 행
			for (int j = 0; j < seats[i].length; j++) { // 열
				seats[i][j] = new JCheckBox(available);
				/* seat[i][j] 번째의 좌석번호는 db에 저장된 ij번째 값과 같다. */
				String seatNo = list.get(Integer.parseInt(i + "" + j)).getSeat_no();
				seats[i][j].setText(seatNo);
				seats[i][j].setToolTipText("좌석번호:" + seatNo);
				seats[i][j].setRolloverIcon(sold);
				seats[i][j].setSelectedIcon(sold);
				seats[i][j].setOpaque(false);
				seats[i][j].addItemListener(new ItemListener() {
					private final int MAX_SELECTIONS = Integer.parseInt(ticket);

					@Override
					public void itemStateChanged(ItemEvent e) {
						JCheckBox source = (JCheckBox) e.getSource();

						if (source.isSelected()) {
							selectionCounter++;
						}

						if (selectionCounter >= MAX_SELECTIONS) {
							for (int i = 0; i < seats.length; i++) {
								for (int j = 0; j < seats[i].length; j++) {
									seats[i][j].setEnabled(false);
									if (seats[i][j].isSelected()) {
										seats[i][j].setEnabled(true);
									}
								}
							}
						}
						System.out.println(selectionCounter);
					}
				});
				seats[i][j].setBounds(posX, posY, 70, 45);
				posX += 77;

				// sold 상태의 좌석의 체크박스는 disable 상태로 만들기
				if (list.get(Integer.parseInt(i + "" + j)).getState().equals("y")) {
					seats[i][j].setEnabled(false);
					seats[i][j].setToolTipText("좌석번호:" + seatNo + " sold");
				}

				seatPanel.add(seats[i][j]);
			}
			posX = 0;
			posY += 50;
		}

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
		btnPrev = new JButton("prev");
		btnReset = new JButton("reset");
		btnPayment = new JButton("payment");
		btnPrev.addActionListener(this);
		btnReset.addActionListener(this);
		btnPayment.addActionListener(this);
		btnPrev.setBounds(40, 650, 130, 65);
		btnReset.setBounds(1050, 650, 130, 65);
		btnPayment.setBounds(1200, 650, 130, 65);
		add(btnPrev);
		add(btnReset);
		add(btnPayment);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPayment) {
			boolean isChecked = false;
			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					if (seats[i][j].isSelected()) {
						isChecked = true;
						seat = seats[i][j].getText();
						Selected.seats.add(seat);
					}
				}
			}
			if (!isChecked) {
				JOptionPane.showMessageDialog(null, "좌석을 선택해 주세요", "error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			new Payment(title, theater, date, time, ticket, seat);
			dispose();
		}

		if (e.getSource() == btnReset) {
			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					seats[i][j].setEnabled(true);
					seats[i][j].setSelected(false);
					selectionCounter = 0;
				}
			}

			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					// sold 상태의 좌석의 체크박스는 disable 상태로 만들기
					String seatNo = list.get(Integer.parseInt(i + "" + j)).getSeat_no();
					if (list.get(Integer.parseInt(i + "" + j)).getState().equals("y")) {
						seats[i][j].setEnabled(false);
						seats[i][j].setToolTipText("좌석번호:" + seatNo + " sold");
					}
				}
			}
		}
		if (e.getSource() == btnPrev) {
			new Reservation();
			dispose();
		}
	}

	public static void main(String[] args) {
		new Seat("美女と野獣", "tokyo", "201752", "14:00", "3");
	}
}