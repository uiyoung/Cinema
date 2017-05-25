package views.movieinfo;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import views.CinemaMenu;
import views.reservation.Reservation;

public class MovieInfo2 extends CinemaFrame implements ActionListener {
	MovieMgr mgr = new MovieMgr();
	MovieBean bean;
	ArrayList<MovieBean> list;

	JLabel movieinfo = new JLabel("映画情報");

	JButton btnBack, btnReserve;

	JLabel lblTitle;
	JLabel genre, lblGenre;
	JLabel runningTime, lblRunningTime;
	JLabel type, lblType;
	JLabel director, lblDirector;
	JLabel actor, lblActor;
	JLabel releasedate, lblReleasedate;
	JLabel description;
	JTextArea taDescription;

	private ImageIcon poster = null;
	private final JPanel cards;

	public MovieInfo2() {

		list = mgr.getMovie();

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());

		// cards panel에 add할 movie panel 생성
		JPanel[] movies = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			movies[i] = new JPanel(null);
			movies[i].setBackground(Color.WHITE);

			JLabel lblPoster = new JLabel();
			poster = new ImageIcon("images/" + list.get(i).getTitle() + ".jpg");
			Image resizedPoster = poster.getImage().getScaledInstance(400, 565, java.awt.Image.SCALE_SMOOTH);
			lblPoster.setIcon(new ImageIcon(resizedPoster));
			lblPoster.setBounds(130, 50, 400, 565);

			// html 태그로 포스터 불러올 수 있다는 것
			// JLabel label = new JLabel("<html><img height='80' width='100'
			// src='https://developer.cdn.mozilla.net/media/img/mdn-logo-sm.png'
			// /></html>");
			// movies[i].add(label);
			// label.setBounds(500, 300, 300, 300);

			lblTitle = new JLabel(list.get(i).getTitle());
			lblTitle.setFont(new Font("Yu Gothic", Font.BOLD, 30));
			lblTitle.setBounds(670, 80, 600, 50);

			lblGenre = new JLabel(list.get(i).getGenre());
			lblGenre.setBounds(700, 200, 120, 30);
			lblGenre.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			lblRunningTime = new JLabel(list.get(i).getRunningTime() + "分");
			lblRunningTime.setBounds(700, 230, 100, 30);
			lblRunningTime.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			taDescription = new JTextArea(list.get(i).getDescription());
			taDescription.setEditable(false);
			taDescription.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행
			taDescription.setBounds(600, 300, 650, 200);
			taDescription.setFont(new Font("Yu Gothic", Font.BOLD, 17));

			lblType = new JLabel("자막/더빙/3D");
			lblDirector = new JLabel("빌 콘돈");
			lblActor = new JLabel("엠마 왓슨, 댄 스티븐스, 루크 에반스");
			lblReleasedate = new JLabel("2017.03.06");

			lblType.setBounds(700, 260, 100, 30);
			lblDirector.setBounds(700, 290, 100, 30);
			lblActor.setBounds(700, 320, 200, 30);
			lblReleasedate.setBounds(700, 350, 100, 30);

			lblType.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblDirector.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblActor.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblReleasedate.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			genre = new JLabel("ジャンル");
			runningTime = new JLabel("상영시간");
			type = new JLabel("타입");
			director = new JLabel("감독");
			actor = new JLabel("출연");
			releasedate = new JLabel("개봉일자");
			// description = new JLabel("あらすじ");

			genre.setBounds(570, 220, 120, 30);
			runningTime.setBounds(600, 230, 100, 30);
			type.setBounds(600, 260, 100, 30);
			director.setBounds(600, 290, 100, 30);
			actor.setBounds(600, 320, 100, 30);
			releasedate.setBounds(600, 350, 100, 30);
			// description.setBounds(60,550,100,30);

			genre.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			runningTime.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			type.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			director.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			actor.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			releasedate.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			// description.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			JLabel star = new JLabel(new ImageIcon("Star.png"));
			star.setBounds(500, 130, 300, 50);

			movies[i].add(lblType);
			movies[i].add(lblDirector);
			movies[i].add(lblActor);
			movies[i].add(lblReleasedate);

			movies[i].add(genre);
			movies[i].add(runningTime);
			movies[i].add(type);
			movies[i].add(director);
			movies[i].add(actor);
			movies[i].add(releasedate);
			// movies[i].add(description);

			movies[i].add(lblPoster);
			movies[i].add(lblTitle);
			movies[i].add(lblGenre);
			movies[i].add(lblRunningTime);
			movies[i].add(taDescription);

			ImageIcon age = new ImageIcon("age.png");
			JLabel ageImg = new JLabel(age);
			ageImg.setBounds(580, 60, 100, 100);
			movies[i].add(ageImg);
			movies[i].add(star);

			JButton btnNext = new JButton("NEXT");
			btnNext.setActionCommand("NEXT");
			btnNext.setFocusPainted(false);
			btnNext.addActionListener(this);
			btnNext.setBounds(700, 650, 100, 60);
			movies[i].add(btnNext);

			JButton btnPrev = new JButton("PRE");
			btnPrev.setBounds(500, 650, 100, 60);
			btnPrev.setActionCommand("PREVIOUS");
			btnPrev.setFocusPainted(false);
			btnPrev.addActionListener(this);
			movies[i].add(btnPrev);

			btnBack = new JButton("Back");
			btnBack = new JButton(new ImageIcon("mainmenu.png"));
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new CinemaMenu();
					dispose();
				}
			});
			btnBack.setBounds(50, 650, 100, 50);

			// ImageIcon ticket = new ImageIcon("ticket.png");
			btnReserve = new JButton(new ImageIcon("reserve.png"));
			btnReserve.setBorderPainted(false);
			btnReserve.setBackground(Color.WHITE);
			btnReserve.setOpaque(false);
			btnReserve.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Reservation();
					dispose();
					// TODO : 해당 영화가 Reserve 페이지에서 선택되어지게 매개변수로 넘기기.
					// new Reserve(title);
				}
			});
			btnReserve.setBounds(1000, 200, 150, 80);

			movies[i].add(btnBack);
			movies[i].add(btnReserve);

			cards.add(movies[i]);
		}

		// JButton btnFirst = new JButton("<<");
		// btnFirst.setActionCommand("FIRST");
		// btnFirst.addActionListener(this);
		// JButton btnLast = new JButton(">>");
		// btnLast.setActionCommand("LAST");
		// btnLast.addActionListener(this);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBackground(Color.WHITE);
		// btnPanel.setBounds(0, 0, 500, 200);*/

		add(movieinfo, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.SOUTH);

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
			repaint();
		} else if (cmd.equals("NEXT")) {
			cl.next(cards);
			repaint();
		} else if (cmd.equals("LAST")) {
			cl.last(cards);
		}
	}

	public static void main(String[] args) {
		new MovieInfo2();
	}
}