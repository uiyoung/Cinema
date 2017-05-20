package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import views.movieinfo.MovieInfo;
import views.myaccount.MyAccount;
import views.myticket.MyTicket;
import views.reservation.Reservation;

public class CinemaMenu extends CinemaFrame implements ActionListener {
	JButton btnMovieInfo, btnReserve, btnMyTicket, btnMyAccount, btnExit;

	public CinemaMenu() {
		setTitle("cinema");
		init();
	}

	private void init() {
		setLayout(null);
		btnMovieInfo = new JButton("상영영화 정보");
		btnReserve = new JButton("영화 예매");
		btnMyTicket = new JButton("MyTicket");
		btnMyAccount = new JButton("MyAccount");
		btnExit = new JButton("Exit");

		btnMovieInfo.setBounds(1100, 350, 200, 60);
		btnReserve.setBounds(1100, 410, 200, 60);
		btnMyTicket.setBounds(1100, 470, 200, 60);
		btnMyAccount.setBounds(1100, 530, 200, 60);
		btnExit.setBounds(1100, 590, 200, 60);

		btnMovieInfo.addActionListener(this);
		btnReserve.addActionListener(this);
		btnMyTicket.addActionListener(this);
		btnMyAccount.addActionListener(this);
		btnExit.addActionListener(this);

		add(btnMovieInfo);
		add(btnReserve);
		add(btnMyTicket);
		add(btnMyAccount);
		add(btnExit);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMovieInfo) {
			new MovieInfo();
			dispose();
		}
		if (e.getSource() == btnReserve) {
			new Reservation();
			dispose();
		}
		if (e.getSource() == btnMyTicket) {
			new MyTicket();
			dispose();
		}
		if (e.getSource() == btnMyAccount) {
			new MyAccount();
		}
		if (e.getSource() == btnExit) {
			int choice = JOptionPane.showConfirmDialog(null, "wanna exit?", "exit", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
}
