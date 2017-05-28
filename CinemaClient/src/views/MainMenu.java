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
		ImageIcon im = new ImageIcon("images/mainMenu/main13.png");
		JLabel la = new JLabel(im);


		btnMovieInfo = new JButton();
		btnReserve = new JButton();
		btnMyTicket = new JButton();
		btnMyAccount = new JButton();
		btnLogout = new JButton();
		btnExit = new JButton();

		btnMovieInfo.setIcon(new ImageIcon("images/mainMenu/bt1_2.png"));
		btnReserve.setIcon(new ImageIcon("images/mainMenu/bt3_2.png"));
		btnMyTicket.setIcon(new ImageIcon("images/mainMenu/bt2_2.png"));
		btnMyAccount.setIcon(new ImageIcon("images/mainMenu/bt5_2.png"));
		btnLogout.setIcon(new ImageIcon("images/mainMenu/out2.png"));
		btnExit.setIcon(new ImageIcon("images/mainMenu/bt4_2.png"));

		btnMovieInfo.setRolloverIcon(new ImageIcon("images/mainMenu/info_2.png"));
		btnReserve.setRolloverIcon(new ImageIcon("images/mainMenu/seat_2.png"));
		btnMyTicket.setRolloverIcon(new ImageIcon("images/mainMenu/ti_2.png"));
		btnMyAccount.setRolloverIcon(new ImageIcon("images/mainMenu/my_2.png"));
		btnLogout.setRolloverIcon(new ImageIcon("images/mainMenu/out3.png"));
		btnExit.setRolloverIcon(new ImageIcon("images/mainMenu/EXIT.png"));

		btnMovieInfo.setContentAreaFilled(false);
		btnReserve.setContentAreaFilled(false);
		btnMyTicket.setContentAreaFilled(false);
		btnMyAccount.setContentAreaFilled(false);
		btnLogout.setContentAreaFilled(false);
		btnExit.setContentAreaFilled(false);

		// btnMovieInfo.setBounds(1100, 350, 200, 60);
		// btnReserve.setBounds(1100, 410, 200, 60);
		// btnMyTicket.setBounds(1100, 470, 200, 60);
		// btnMyAccount.setBounds(1100, 530, 200, 60);
		// btnLogout.setBounds(1100, 590, 100, 60);
		// btnExit.setBounds(1200, 590, 100, 60);
		
		btnMovieInfo.setBounds(820, 270, 250, 135);
		btnReserve.setBounds(1085, 270, 250, 135);
		btnMyTicket.setBounds(820, 415, 250, 135);
		btnMyAccount.setBounds(1085, 415, 250, 135);
		btnLogout.setBounds(820, 560, 250, 135);
		btnExit.setBounds(1085, 560, 250, 135);

		btnMovieInfo.addActionListener(this);
		btnReserve.addActionListener(this);
		btnMyTicket.addActionListener(this);
		btnMyAccount.addActionListener(this);
		btnLogout.addActionListener(this);
		btnExit.addActionListener(this);

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
