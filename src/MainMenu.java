import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainMenu extends CinemaFrame implements ActionListener {
	ArrayList<MovieBean> list;
	MovieMgr mgr = new MovieMgr();
	MovieBean bean;

	JLabel lblTitle;
	JLabel lblGenre;
	JLabel lblRunningTime;
	JTextArea taDescription;

	JButton btnReserve = new JButton("Reserve");
	JButton btnMyTicket = new JButton("My Ticket");
	JButton btnExit = new JButton("Exit");

	private ImageIcon poster = null;
	final JPanel cards;

	public MainMenu() {
		list = mgr.getMovie();

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());

		// cards panel에 add할 movie panel 생성
		JPanel[] movies = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			movies[i] = new JPanel();
			movies[i].setLayout(null);

			JLabel posterLabel = new JLabel();
			poster = new ImageIcon("images/" + list.get(i).getTitle() + ".jpg");
			posterLabel.setIcon(poster);
			posterLabel.setBounds(50, 80, 320, 452);

			// html 태그로 포스터 불러올 수 있다는 것
			// JLabel label = new JLabel("<html><img height='80' width='100'
			// src='https://developer.cdn.mozilla.net/media/img/mdn-logo-sm.png'
			// /></html>");
			// movies[i].add(label);
			// label.setBounds(500, 300, 300, 300);

			lblTitle = new JLabel(list.get(i).getTitle());
			lblTitle.setFont(new Font("Yu Gothic", Font.BOLD, 30));
			lblTitle.setBounds(20, 10, 600, 30);

			lblGenre = new JLabel(list.get(i).getGenre());
			lblGenre.setBounds(400, 80, 120, 30);

			lblRunningTime = new JLabel(list.get(i).getRunningTime() + "分");
			lblRunningTime.setBounds(400, 120, 100, 30);

			taDescription = new JTextArea(list.get(i).getDescription());
			taDescription.setEditable(false);
			taDescription.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행
			taDescription.setBounds(400, 150, 700, 300);

			movies[i].add(posterLabel);
			movies[i].add(lblTitle);
			movies[i].add(lblGenre);
			movies[i].add(lblRunningTime);
			movies[i].add(taDescription);
			cards.add(movies[i]);
		}

		// JButton btnFirst = new JButton("<<");
		// btnFirst.setActionCommand("FIRST");
		// btnFirst.addActionListener(this);
		// JButton btnLast = new JButton(">>");
		// btnLast.setActionCommand("LAST");
		// btnLast.addActionListener(this);

		JButton btnNext = new JButton("▶");
		btnNext.setActionCommand("NEXT");
		btnNext.setFocusPainted(false);
		btnNext.addActionListener(this);

		JButton btnPrev = new JButton("◀");
		btnPrev.setActionCommand("PREVIOUS");
		btnPrev.setFocusPainted(false);
		btnPrev.addActionListener(this);

		// JPanel controlPanel = new JPanel();
		// controlPanel.add(btnFirst);
		// controlPanel.add(btnPrev);
		// controlPanel.add(btnNext);
		// controlPanel.add(btnLast);

		JPanel btnPanel = new JPanel();
		btnPanel.add(btnReserve);
		btnPanel.add(btnMyTicket);
		btnPanel.add(btnExit);

		btnReserve.addActionListener(this);
		btnMyTicket.addActionListener(this);
		btnExit.addActionListener(this);

		add(btnPrev, BorderLayout.LINE_START);
		add(btnNext, BorderLayout.LINE_END);
		add(cards, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.PAGE_END);

		setTitle("マネシネマ");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		String cmd = e.getActionCommand();

		if (cmd.equals("FIRST")) {
			cl.first(cards);
		} else if (cmd.equals("PREVIOUS")) {
			cl.previous(cards);
		} else if (cmd.equals("NEXT")) {
			cl.next(cards);
		} else if (cmd.equals("LAST")) {
			cl.last(cards);
		}

		if (e.getSource() == btnReserve) {
			new Login();
		}
		if (e.getSource() == btnMyTicket) {
			// new MyTicket();
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
