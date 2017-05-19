package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Payment extends CinemaFrame implements ActionListener {
	String title, theater, date, time, ticket, seat;

	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();
	JLabel titleT, theaterT, timeT, personNumT, seatNumT, moneyT;
	JLabel lblTitle, lblTheater, lblTime, lblPersonNum, lblSeatNo, lblPayment, lblPrice;
	JLabel posterLabel = new JLabel();
	ImageIcon poster;
	JButton btnPayByCreditcard, btnPayByPhone, btnPayByKakao, btnPayByAccount;
	JButton btnPrev, btnReserve;

	public Payment(String title, String theater, String date, String time, String ticket, String seat) {
		this.title = title;
		this.theater = theater;
		this.date = date;
		this.time = time;
		this.ticket = ticket;
		this.seat = seat;

		setTitle("결제");
		setLayout(new GridLayout(1, 2));
		firstPanel.setLayout(null);
		secondPanel.setLayout(null);

		poster = new ImageIcon("images/" + title + ".jpg");
		posterLabel.setIcon(poster);
		titleT = new JLabel("영화");
		timeT = new JLabel("시간");
		theaterT = new JLabel("관람극장");
		personNumT = new JLabel("인원");
		seatNumT = new JLabel("좌석번호");

		lblTitle = new JLabel(title);
		lblTheater = new JLabel(theater);
		lblTime = new JLabel(time);
		lblPersonNum = new JLabel(ticket);
		lblSeatNo = new JLabel(seat);

		lblPayment = new JLabel("결제수단");
		moneyT = new JLabel("총결제금액");
		lblPrice = new JLabel((Integer.parseInt(ticket) * 18000) + "원");

		btnPrev = new JButton("이전");
		btnReserve = new JButton("예매");

		titleT.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		timeT.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		theaterT.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		personNumT.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		seatNumT.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblTitle.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblTime.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblTheater.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblPersonNum.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblSeatNo.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblPayment.setFont(new Font("Yu Gothic", Font.BOLD, 30));
		moneyT.setFont(new Font("Yu Gothic", Font.BOLD, 30));
		lblPrice.setFont(new Font("Yu Gothic", Font.BOLD, 40));

		titleT.setBounds(200, 500, 200, 100);
		timeT.setBounds(200, 530, 200, 100);
		theaterT.setBounds(200, 560, 200, 100);
		personNumT.setBounds(200, 590, 200, 100);
		seatNumT.setBounds(200, 620, 200, 100);
		lblTitle.setBounds(320, 500, 200, 100);
		lblTime.setBounds(320, 530, 200, 100);
		lblTheater.setBounds(320, 560, 200, 100);
		lblPersonNum.setBounds(320, 590, 200, 100);
		lblSeatNo.setBounds(320, 620, 200, 100);
		posterLabel.setBounds(200, 60, 320, 452);
		lblPayment.setBounds(100, 30, 200, 100);
		moneyT.setBounds(100, 450, 200, 100);
		lblPrice.setBounds(100, 500, 270, 100);
		btnPrev.setBounds(10, 690, 70, 40);
		btnReserve.setBounds(600, 690, 70, 40);

		btnPayByCreditcard = new JButton("신용카드");
		btnPayByPhone = new JButton("핸드폰결제");
		btnPayByKakao = new JButton("카카오페이");
		btnPayByAccount = new JButton("계좌이체");

		btnPayByCreditcard.setBounds(100, 150, 270, 50);
		btnPayByPhone.setBounds(100, 220, 270, 50);
		btnPayByKakao.setBounds(100, 290, 270, 50);
		btnPayByAccount.setBounds(100, 360, 270, 50);

		firstPanel.add(btnPrev);
		firstPanel.add(posterLabel);
		firstPanel.add(titleT);
		firstPanel.add(timeT);
		firstPanel.add(theaterT);
		firstPanel.add(personNumT);
		firstPanel.add(seatNumT);
		firstPanel.add(lblTitle);
		firstPanel.add(lblTime);
		firstPanel.add(lblTheater);
		firstPanel.add(lblPersonNum);
		firstPanel.add(lblSeatNo);
		firstPanel.add(posterLabel);

		secondPanel.add(btnReserve);
		secondPanel.add(lblPayment);
		secondPanel.add(moneyT);
		secondPanel.add(lblPrice);
		secondPanel.add(btnPayByCreditcard);
		secondPanel.add(btnPayByPhone);
		secondPanel.add(btnPayByKakao);
		secondPanel.add(btnPayByAccount);

		add(firstPanel);
		add(secondPanel);

		btnPayByCreditcard.addActionListener(this);
		btnPayByPhone.addActionListener(this);
		btnPayByKakao.addActionListener(this);
		btnPayByAccount.addActionListener(this);
		btnPrev.addActionListener(this);
		btnReserve.addActionListener(this);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPayByCreditcard) {
			new PayByCredit();
		}
		if (e.getSource() == btnPayByPhone) {
			new PayByPhone();
		}
		if (e.getSource() == btnPayByKakao) {
			new PayByAccount();
		}
		if (e.getSource() == btnPayByAccount) {
			new PayByAccount();
		}

		if (e.getSource() == btnReserve) {
			// TODO : ticket_tb에 예매정보 insert하기 (title, theater, date, time,
			// ticket, seat, price)
		}
		if (e.getSource() == btnPrev) {
			new Reservation();
		}
	}

	public static void main(String[] args) {
		new Payment("美女と野獣", "tokyo", "201752", "14:00", "1", "A02");
	}
}
