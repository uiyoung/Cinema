package views.movieinfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controllers.MovieMgr;
import models.MovieBean;
import views.CinemaFrame;
import views.MainMenu;
import views.reservation.Reservation;

public class MovieInfo extends CinemaFrame implements ActionListener {
	MovieMgr mgr = new MovieMgr();
	MovieBean bean;
	ArrayList<MovieBean> list;

	JLabel lblTitle, lblGenre, lblRunningTime;
	JTextArea taDescription;
	JButton btnBack, btnReserve;

	private ImageIcon poster = null;
	private final JPanel cards;

	public MovieInfo() {
		list = mgr.getMovie();

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());

		// cards panel에 add할 movie panel 생성
		JPanel[] movies = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			movies[i] = new JPanel();
			movies[i].setLayout(null);

			JLabel lblPoster = new JLabel();
			poster = new ImageIcon("images/" + list.get(i).getTitle() + ".jpg");
			lblPoster.setIcon(poster);
			lblPoster.setBounds(50, 80, 320, 452);

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

			movies[i].add(lblPoster);
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

		JPanel btnPanel = new JPanel();
		btnBack = new JButton("Back");
		btnReserve = new JButton("Reserve");
		btnPanel.add(btnBack);
		btnPanel.add(btnReserve);

		btnReserve.addActionListener(this);
		btnBack.addActionListener(this);

		add(btnPrev, BorderLayout.LINE_START);
		add(btnNext, BorderLayout.LINE_END);
		add(cards, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.PAGE_END);

		setTitle("상영영화 정보");
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
			new Reservation();
			dispose();

			// TODO : 해당 영화가 Reserve 페이지에서 선택되어지게 매개변수로 넘기기.
			// new Reserve(title);
		}
		if (e.getSource() == btnBack) {
			new MainMenu();
			dispose();
		}
	}
}
