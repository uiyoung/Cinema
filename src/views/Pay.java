package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pay extends CinemaFrame implements ActionListener {
	JLabel title, theater, datetime, personNum, seatNum, payment, money, money1;
	JLabel titleT, theaterT, datetimeT, personNumT, seatNumT;
	JLabel posterLabel = new JLabel();
	ImageIcon poster = new ImageIcon("images/" + "美女と野獣.jpg");
	JButton jb1, jb2, jb3, jb4;
	JButton prebt = new JButton("이전");
	JButton paybt = new JButton("결제");
	JPanel firstPanel = new JPanel();
	JPanel secondPanel = new JPanel();

	public Pay() {
		setTitle("결제");
		setLayout(new GridLayout(1, 2));
		firstPanel.setLayout(null);
		secondPanel.setLayout(null);

		posterLabel.setIcon(poster);

		titleT = new JLabel("영화");
		datetimeT = new JLabel("시간");
		theaterT = new JLabel("관람극장");
		personNumT = new JLabel("인원");
		seatNumT = new JLabel("좌석번호");

		title = new JLabel("美女と野獣");
		datetime = new JLabel("14:00");
		theater = new JLabel("tokyo");
		personNum = new JLabel("1");
		seatNum = new JLabel("A01");
		payment = new JLabel("결제수단");
		money = new JLabel("총결제금액");
		money1 = new JLabel("18000원");

		titleT.setFont(new Font("굴림", Font.BOLD, 25));
		datetimeT.setFont(new Font("굴림", Font.BOLD, 25));
		theaterT.setFont(new Font("굴림", Font.BOLD, 25));
		personNumT.setFont(new Font("굴림", Font.BOLD, 25));
		seatNumT.setFont(new Font("굴림", Font.BOLD, 25));
		title.setFont(new Font("굴림", Font.BOLD, 25));
		datetime.setFont(new Font("굴림", Font.BOLD, 25));
		theater.setFont(new Font("굴림", Font.BOLD, 25));
		personNum.setFont(new Font("굴림", Font.BOLD, 25));
		seatNum.setFont(new Font("굴림", Font.BOLD, 25));
		payment.setFont(new Font("굴림", Font.BOLD, 30));
		money.setFont(new Font("굴림", Font.BOLD, 30));
		money1.setFont(new Font("굴림", Font.BOLD, 40));

		titleT.setBounds(200, 500, 200, 100);
		datetimeT.setBounds(200, 530, 200, 100);
		theaterT.setBounds(200, 560, 200, 100);
		personNumT.setBounds(200, 590, 200, 100);
		seatNumT.setBounds(200, 620, 200, 100);
		title.setBounds(320, 500, 200, 100);
		datetime.setBounds(320, 530, 200, 100);
		theater.setBounds(320, 560, 200, 100);
		personNum.setBounds(320, 590, 200, 100);
		seatNum.setBounds(320, 620, 200, 100);
		posterLabel.setBounds(200, 60, 320, 452);
		payment.setBounds(100, 30, 200, 100);
		money.setBounds(100, 450, 200, 100);
		money1.setBounds(100, 500, 270, 100);
		prebt.setBounds(10, 690, 70, 40);
		paybt.setBounds(600, 690, 70, 40);

		jb1 = new JButton("신용카드");
		jb2 = new JButton("핸드폰결제");
		jb3 = new JButton("카카오페이");
		jb4 = new JButton("계좌이체");

		jb1.setBounds(100, 150, 270, 50);
		jb2.setBounds(100, 220, 270, 50);
		jb3.setBounds(100, 290, 270, 50);
		jb4.setBounds(100, 360, 270, 50);

		firstPanel.add(prebt);
		firstPanel.add(posterLabel);
		firstPanel.add(titleT);
		firstPanel.add(datetimeT);
		firstPanel.add(theaterT);
		firstPanel.add(personNumT);
		firstPanel.add(seatNumT);
		firstPanel.add(title);
		firstPanel.add(datetime);
		firstPanel.add(theater);
		firstPanel.add(personNum);
		firstPanel.add(seatNum);
		firstPanel.add(posterLabel);

		secondPanel.add(paybt);
		secondPanel.add(payment);
		secondPanel.add(money);
		secondPanel.add(money1);
		secondPanel.add(jb1);
		secondPanel.add(jb2);
		secondPanel.add(jb3);
		secondPanel.add(jb4);

		add(firstPanel);
		add(secondPanel);

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Pay();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			new PayByCredit();
		}
		if (e.getSource() == jb2) {
			new PayByPhone();
		}
		if (e.getSource() == jb3) {
			new PayByAccount();
		}
		if (e.getSource() == jb4) {
			new PayByAccount();
		}

	}

}
