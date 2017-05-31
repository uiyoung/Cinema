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
import models.SeatBean;
import models.Selected;
import views.CinemaFrame;
import views.payment.Payment;
import views.reservation.Reservation;

public class Seat extends CinemaFrame implements ActionListener {
	private DBMgr mgr = new DBMgr(); // DAO
	private ArrayList<SeatBean> list;

	private String title, theater, date, time, ticket, seat;
	private int selectionCounter = 0;
	private JButton btnPayment, btnReset, btnPrev;
	private JPanel seatPanel;
	private JCheckBox[][] seats;

	ImageIcon available = new ImageIcon("images/seat/seatAvailable.png");
	ImageIcon sold = new ImageIcon("images/seat/seatSold.png");
	ImageIcon selected = new ImageIcon("images/seat/seatSelected.png");

	public Seat(String title, String theater, String date, String time, String ticket) {
		this.title = title;
		this.theater = theater;
		this.time = time;
		this.date = date;
		this.ticket = ticket;

		Selected.seats.clear();

		setLayout(null);
		setTitle("좌석선택");
		initScreenLabel();
		initSeats();
		initButtons();
		initSeatInfo();

		JLabel la = new JLabel(new ImageIcon("images/seat/seatBackground.png"));
		la.setBounds(0, 0, 1366, 768);
		add(la);
		setVisible(true);
		repaint();
	}

	private void initScreenLabel() {
		JLabel lblScreen = new JLabel("SCREEN");
		lblScreen.setFont(new Font("Consolas", Font.BOLD, 30));
		lblScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblScreen.setBackground(new Color(246, 204, 42));
		lblScreen.setForeground(Color.black);
		lblScreen.setOpaque(true);
		lblScreen.setBounds(460, 30, 500, 50);
		add(lblScreen);
	}

	private void initSeats() {
		seatPanel = new JPanel(null);
		seatPanel.setBackground(Color.BLACK);
		seatPanel.setBounds(310, 140, 765, 498);

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
				seats[i][j].setRolloverIcon(selected);
				seats[i][j].setSelectedIcon(selected);
				seats[i][j].setOpaque(false);
				seats[i][j].addItemListener(new ItemListener() {
					private final int MAX_SELECTIONS = Integer.parseInt(ticket);

					@Override
					public void itemStateChanged(ItemEvent e) {
						JCheckBox source = (JCheckBox) e.getSource();

						if (source.isSelected()) {
							selectionCounter++;
						} else {
							selectionCounter--;
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
						} else {
							for (int i = 0; i < seats.length; i++) {
								for (int j = 0; j < seats[i].length; j++) {
									if (!seats[i][j].isSelected()) {
										seats[i][j].setEnabled(true);
									}
								}
							}
							checkSoldSeats();
						}
						System.out.println(selectionCounter);
					}
				});
				seats[i][j].setBounds(posX, posY, 70, 45);
				posX += 77;

				seatPanel.add(seats[i][j]);
			}
			posX = 0;
			posY += 50;
		}

		checkSoldSeats();
		add(seatPanel);
	}

	// sold 상태의 좌석의 체크박스는 disable 상태로 만들기
	private void checkSoldSeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				String seatNo = list.get(Integer.parseInt(i + "" + j)).getSeat_no();
				if (list.get(Integer.parseInt(i + "" + j)).getState().equals("y")) {
					seats[i][j].setEnabled(false);
					seats[i][j].setToolTipText("좌석번호:" + seatNo + " sold");
				}
			}
		}
	}

	// TODO : 좌석상태 정보 아이콘(Available, Sold, Selected 정보 표시)
	private void initSeatInfo() {

		JLabel s1 = new JLabel();
		s1.setBounds(1100, 460, 60, 40);
		s1.setIcon(available);
		JLabel s2 = new JLabel();
		s2.setBounds(1100, 520, 60, 40);
		s2.setIcon(sold);
		JLabel s3 = new JLabel();
		s3.setBounds(1100, 580, 60, 40);
		s3.setIcon(selected);

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setForeground(Color.white);
		lblAvailable.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblAvailable.setBounds(1170, 460, 100, 40);

		JLabel lblSold = new JLabel("Sold");
		lblSold.setForeground(Color.white);
		lblSold.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblSold.setBounds(1170, 520, 100, 40);

		JLabel lblSelected = new JLabel("Selected");
		lblSelected.setForeground(Color.white);
		lblSelected.setFont(new Font("Consolas", Font.PLAIN, 20));
		lblSelected.setBounds(1170, 580, 100, 40);

		add(s1);
		add(lblAvailable);
		add(s2);
		add(lblSold);
		add(s3);
		add(lblSelected);
	}

	private void initButtons() {
		btnPrev = new JButton("prev");
		btnReset = new JButton("reset");
		btnPayment = new JButton("payment");
		btnPrev.setIcon(new ImageIcon("images/seat/prev.png"));
		btnReset.setIcon(new ImageIcon("images/seat/reset.png"));
		btnPayment.setIcon(new ImageIcon("images/seat/pay.png"));
		btnPrev.setContentAreaFilled(false);
		btnReset.setContentAreaFilled(false);
		btnPayment.setContentAreaFilled(false);
		btnPrev.addActionListener(this);
		btnReset.addActionListener(this);
		btnPayment.addActionListener(this);
		btnPrev.setBounds(40, 650, 140, 65);
		btnReset.setBounds(1050, 650, 140, 65);
		btnPayment.setBounds(1200, 650, 140, 65);
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
			if (Selected.seats.size() < Integer.parseInt(ticket)) {
				JOptionPane.showMessageDialog(null, ticket + "개의 좌석을 선택해 주세요", "error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			new Payment(title, theater, date, time, ticket, Selected.seats);
			dispose();
		}

		if (e.getSource() == btnReset) {
			for (int i = 0; i < seats.length; i++) {
				for (int j = 0; j < seats[i].length; j++) {
					seats[i][j].setEnabled(true);
					seats[i][j].setSelected(false);
				}
			}
			Selected.seats.clear();
			selectionCounter = 0;
			checkSoldSeats();
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