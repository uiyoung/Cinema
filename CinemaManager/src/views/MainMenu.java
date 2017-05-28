package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.MDBMgr;
import views.login.Login;
import views.movie.ManageMovie;
import views.reservations.Reservations;
import views.revenue.Revenue;
import views.schedule.ManageSchedule;
import views.theater.ManageTheater;

public class MainMenu extends MFrame implements ActionListener {
	ButtonGroup mgroup = new ButtonGroup();
	JButton btnTheater = new JButton("①シネマ管理");
	JButton btnInsertMovie = new JButton("②映画管理");// 映画情報
	JButton btnInsertSchedule = new JButton("③スケジュール管理");// 上映情報
	JButton reconfirm = new JButton("予約情報");// 予約情報
	JButton cal = new JButton("精算");// 精算
	JButton exit = new JButton("終了");
	JPanel pan = new JPanel();
	JPanel pan1 = new JPanel();
	JPanel pan1_1 = new JPanel();
	ImageIcon listImg = new ImageIcon("m_list.png");
	JLabel mlab = new JLabel(listImg);
	JPanel pan2 = new JPanel();
	MDBMgr li_mgr = new MDBMgr();

	public MainMenu() {
		this.setTitle("管理者ログイン");
		// this.setBounds(100,200,300,300);
		init();
	}

	private void init() {
		Container mcon = this.getContentPane();
		// pan.setLayout(new GridLayout(3,1));
		pan1.setBackground(Color.WHITE);

		btnTheater.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnInsertMovie.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnInsertSchedule.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		cal.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		reconfirm.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		exit.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		btnTheater.setBackground(Color.WHITE);
		btnInsertSchedule.setBackground(Color.WHITE);
		btnInsertMovie.setBackground(Color.WHITE);
		reconfirm.setBackground(Color.white);
		cal.setBackground(Color.white);
		exit.setBackground(Color.WHITE);
		pan1.add(pan1_1);

		btnTheater.addActionListener(this);
		btnInsertMovie.addActionListener(this);
		btnInsertSchedule.addActionListener(this);
		cal.addActionListener(this);
		reconfirm.addActionListener(this);
		exit.addActionListener(this);
		pan1.add(btnTheater);
		pan1.add(btnInsertMovie);// 영화정보
		pan1.add(btnInsertSchedule);// 상영정보
		pan1.add(reconfirm);// 예약확인및수정
		// pan1.add(mfconfirm);// 매점
		pan1.add(cal);
		pan1.add(exit);
		pan.add(mlab);

		mcon.add("North", pan1);
		mcon.add("South", pan);
		// mcon.add("South", pan2);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if (e.getSource() == btnTheater) {
			new ManageTheater();
			dispose();
		}
		
		if (e.getSource() == btnInsertMovie) {
			new ManageMovie();
			dispose();
		}
		if (e.getSource() == btnInsertSchedule) {
			new ManageSchedule();
			dispose();
		}
		if (button.equals(reconfirm.getText())) {
			new Reservations();
			dispose();
		}
		if (button.equals(cal.getText())) {
			new Revenue();
			dispose();
		}
		if (button.equals(exit.getText())) {
			int choice = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "exit", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}