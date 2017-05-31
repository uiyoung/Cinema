package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import views.login.Login;
import views.movieinfo.MovieInfo;
import views.myaccount.MyAccount;
import views.myticket.MyTicket;
import views.reservation.Reservation;

public class MainMenu extends CinemaFrame implements ActionListener {
	private JButton btnMovieInfo, btnReserve, btnMyTicket, btnMyAccount, btnLogout, btnExit;

	public MainMenu() {
		setTitle("cinema");
		init();
	}

	private void init() {
		setLayout(null);


		btnMovieInfo = new JButton();
		btnReserve = new JButton();
		btnMyTicket = new JButton();
		btnMyAccount = new JButton();
		btnLogout = new JButton();
		btnExit = new JButton();

		btnMovieInfo.setIcon(new ImageIcon("images/mainMenu/btnMovieInfo.png"));
		btnReserve.setIcon(new ImageIcon("images/mainMenu/btnReserve.png"));
		btnMyTicket.setIcon(new ImageIcon("images/mainMenu/btnMyTicket.png"));
		btnMyAccount.setIcon(new ImageIcon("images/mainMenu/btnMyAccount.png"));
		btnLogout.setIcon(new ImageIcon("images/mainMenu/btnLogout.png"));
		btnExit.setIcon(new ImageIcon("images/mainMenu/btnExit.png"));
		btnMovieInfo.setRolloverIcon(new ImageIcon("images/mainMenu/btnMovieInfo2.png"));
		btnReserve.setRolloverIcon(new ImageIcon("images/mainMenu/btnReserve2.png"));
		btnMyTicket.setRolloverIcon(new ImageIcon("images/mainMenu/btnMyTicket2.png"));
		btnMyAccount.setRolloverIcon(new ImageIcon("images/mainMenu/btnMyAccount2.png"));
		btnLogout.setRolloverIcon(new ImageIcon("images/mainMenu/btnLogout2.png"));
		btnExit.setRolloverIcon(new ImageIcon("images/mainMenu/btnExit2.png"));

		btnMovieInfo.setContentAreaFilled(false);
		btnReserve.setContentAreaFilled(false);
		btnMyTicket.setContentAreaFilled(false);
		btnMyAccount.setContentAreaFilled(false);
		btnLogout.setContentAreaFilled(false);
		btnExit.setContentAreaFilled(false);

		btnMovieInfo.setBounds(750, 200, 280, 150);
		btnReserve.setBounds(1035, 200, 280, 150);
		btnMyTicket.setBounds(750, 355, 280, 150);
		btnMyAccount.setBounds(1035, 355, 280, 150);
		btnLogout.setBounds(750, 510, 280, 150);
		btnExit.setBounds(1035, 510, 280, 150);

		btnMovieInfo.addActionListener(this);
		btnReserve.addActionListener(this);
		btnMyTicket.addActionListener(this);
		btnMyAccount.addActionListener(this);
		btnLogout.addActionListener(this);
		btnExit.addActionListener(this);

		ImageIcon im = new ImageIcon("images/mainMenu/mainMenu_backgorund.jpg");
		JLabel la = new JLabel(im);
		la.setBounds(0, 0, 1366, 768);


		add(btnMovieInfo);
		add(btnReserve);
		add(btnMyTicket);
		add(btnMyAccount);
		add(btnLogout);
		add(btnExit);
		add(la);
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

		if (e.getSource() == btnLogout) {
			int choice = JOptionPane.showConfirmDialog(null, "wanna logout?", "exit", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				new Login();
				dispose();
			}
		}

		if (e.getSource() == btnExit) {
			int choice = JOptionPane.showConfirmDialog(null, "wanna exit?", "exit", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	public static void main(String[] args) {
		new MainMenu();
	}
}
