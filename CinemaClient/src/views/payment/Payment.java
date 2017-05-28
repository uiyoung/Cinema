package views.payment;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.DBMgr;
import models.MemberInfo;
import models.Selected;
import views.CinemaFrame;
import views.MainMenu;
import views.seat.Seat;

public class Payment extends CinemaFrame implements ActionListener {
	private final int MOVIE_PRICE = 1800;

	private DBMgr mgr = new DBMgr(); // DAO
	private String title, theater, date, time, ticket;
	private ArrayList<String> seats;
	private JPanel firstPanel = new JPanel();
	private JPanel secondPanel = new JPanel();
	private JPanel thirdPanel = new JPanel();
	private JLabel titleT, theaterT, timeT, personNumT, seatNumT, moneyT, infoT, posterT;
	private JLabel lblTitle, lblTheater, lblTime, lblPersonNum, lblSeatNo, lblPayment, lblPrice;
	private JLabel posterLabel = new JLabel();
	private ImageIcon poster;
	private JButton btnPayByCreditcard, btnPayByPhone, btnPayByKakao, btnPayByAccount, btnPayByAu;
	private JButton btnPrev, btnReserve;
	ImageIcon im = new ImageIcon("images/payment/a1.png");
	ImageIcon im1 = new ImageIcon("images/payment/a2.png");
	JLabel la = new JLabel(im);
	JLabel la1 = new JLabel(im1);

	public Payment(String title, String theater, String date, String time, String ticket, ArrayList<String> seats) {
		this.title = title;
		this.theater = theater;
		this.date = date;
		this.time = time;
		this.ticket = ticket;
		this.seats = seats;

		setTitle("결제");
		setLayout(new GridLayout(1, 3));
		firstPanel.setLayout(null);
		firstPanel.setBackground(Color.LIGHT_GRAY);
		secondPanel.setLayout(null);
		// secondPanel.setBackground(Color.white);
		thirdPanel.setLayout(null);
		thirdPanel.setBackground(Color.LIGHT_GRAY);

		poster = new ImageIcon("images/" + title + ".jpg");
		posterLabel.setIcon(poster);
		titleT = new JLabel("映画       :");
		timeT = new JLabel("時間       :");
		theaterT = new JLabel("観覧劇場:");
		personNumT = new JLabel("人員       :");
		seatNumT = new JLabel("座席番号:");
		posterT = new JLabel("お選びの映画");
		infoT = new JLabel("ご購入内容");

		lblTitle = new JLabel(title);
		lblTheater = new JLabel(theater);
		lblTime = new JLabel(time);
		lblPersonNum = new JLabel(ticket);
		lblSeatNo = new JLabel(seats.toString());

		lblPayment = new JLabel();
		lblPayment.setIcon(new ImageIcon("images/payment/pay2.png"));
		moneyT = new JLabel("決済金額");
		lblPrice = new JLabel(MOVIE_PRICE * Integer.parseInt(ticket) + "円");

		btnPrev = new JButton();
		btnPrev.setIcon(new ImageIcon("images/payment/per.jpg"));
		btnReserve = new JButton();
		btnReserve.setIcon(new ImageIcon("images/payment/reg.jpg"));

		titleT.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		timeT.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		theaterT.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		personNumT.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		seatNumT.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
		infoT.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
		posterT.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
		lblTitle.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblTime.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblTheater.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblPersonNum.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblSeatNo.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblPayment.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		moneyT.setFont(new Font("Arial Unicode MS", Font.BOLD, 30));
		lblPrice.setFont(new Font("Yu Gothic", Font.BOLD, 40));

		titleT.setBounds(100, 120, 200, 100);
		timeT.setBounds(100, 150, 200, 100);
		theaterT.setBounds(100, 180, 200, 100);
		personNumT.setBounds(100, 210, 200, 100);
		seatNumT.setBounds(100, 240, 200, 100);
		infoT.setBounds(100, 50, 200, 100);
		posterT.setBounds(70, 50, 200, 100);
		lblTitle.setBounds(230, 120, 200, 100);
		lblTime.setBounds(230, 150, 200, 100);
		lblTheater.setBounds(230, 180, 200, 100);
		lblPersonNum.setBounds(230, 210, 200, 100);
		lblSeatNo.setBounds(230, 240, 200, 100);
		posterLabel.setBounds(70, 150, 320, 452);
		lblPayment.setBounds(120, 80, 200, 100);
		moneyT.setBounds(100, 350, 200, 100);
		lblPrice.setBounds(150, 450, 270, 100);
		btnPrev.setBounds(70, 660, 150, 60);
		btnReserve.setBounds(250, 660, 150, 60);
		la.setBounds(2, 140, 450, 190);

		btnPayByCreditcard = new JButton();
		btnPayByCreditcard.setIcon(new ImageIcon("images/payment/sin.jpg"));
		btnPayByPhone = new JButton();
		btnPayByPhone.setIcon(new ImageIcon("images/payment/pon.jpg"));
		btnPayByKakao = new JButton();
		btnPayByKakao.setIcon(new ImageIcon("images/payment/rakutenPay.jpg"));
		btnPayByAccount = new JButton();
		btnPayByAccount.setIcon(new ImageIcon("images/payment/ban.jpg"));
		btnPayByAu = new JButton();
		btnPayByAu.setIcon(new ImageIcon("images/payment/auPay.jpg"));

		btnPayByCreditcard.setBounds(100, 190, 270, 50);
		btnPayByPhone.setBounds(100, 270, 270, 50);
		btnPayByKakao.setBounds(100, 350, 270, 50);
		btnPayByAccount.setBounds(100, 430, 270, 50);
		btnPayByAu.setBounds(100, 510, 270, 50);
		la1.setBounds(90, 430, 250, 150);

		firstPanel.add(posterLabel);
		firstPanel.add(posterT);

		thirdPanel.add(titleT);
		thirdPanel.add(timeT);
		thirdPanel.add(theaterT);
		thirdPanel.add(personNumT);
		thirdPanel.add(seatNumT);
		thirdPanel.add(infoT);
		thirdPanel.add(lblTitle);
		thirdPanel.add(lblTime);
		thirdPanel.add(lblTheater);
		thirdPanel.add(lblPersonNum);
		thirdPanel.add(lblSeatNo);
		thirdPanel.add(la);

		secondPanel.add(btnPayByCreditcard);
		secondPanel.add(btnPayByPhone);
		secondPanel.add(btnPayByKakao);
		secondPanel.add(btnPayByAccount);
		secondPanel.add(btnPayByAu);
		secondPanel.add(lblPayment);

		thirdPanel.add(lblPrice);
		thirdPanel.add(la1);
		thirdPanel.add(btnReserve);
		thirdPanel.add(btnPrev);
		thirdPanel.add(moneyT);

		add(firstPanel);
		add(secondPanel);
		add(thirdPanel);

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
			new PayByKaKao();
		}
		if (e.getSource() == btnPayByAccount) {
			new PayByAccount();
		}

		if (e.getSource() == btnReserve) {
			int result = JOptionPane.showConfirmDialog(null, "예매하시겠습니까?", "예매", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				for (int i = 0; i < Selected.seats.size(); i++) {
					/* ticket 테이블에 티켓 생성 */
					mgr.insertTicket(title, theater, date, time, Selected.seats.get(i), MOVIE_PRICE, MemberInfo.ID);
					/* 해당좌석 state y로 바꾸기 */
					mgr.reserveSeat(theater, date, time, Selected.seats.get(i));
				}
				Selected.seats.clear();
				JOptionPane.showMessageDialog(null, "예매되었습니다.", "예매", JOptionPane.INFORMATION_MESSAGE);
				new MainMenu();
				dispose();
			}
		}
		if (e.getSource() == btnPrev) {
			new Seat(title, theater, date, time, ticket);
			dispose();
		}
	}
}