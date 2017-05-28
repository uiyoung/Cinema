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
import views.MainMenu;
import views.reservation.Reservation;

public class MovieInfo extends CinemaFrame implements ActionListener {
	private MovieMgr mgr = new MovieMgr();
	private ArrayList<MovieBean> list;

	private JLabel movieinfo;
	private JButton btnBack, btnReserve;

	private JLabel lblTitle;
	private JLabel genre, lblGenre;
	private JLabel runningTime, lblRunningTime;
	private JLabel type, lblType;
	private JLabel director, lblDirector;
	private JLabel actor, lblActor;
	private JLabel releasedate, lblReleasedate;
	private JLabel rating;
	private JTextArea taDescription;
	private JLabel line, line2;
	private JLabel threeD, imax;

	private ImageIcon poster = null;
	private final JPanel cards;

	public MovieInfo() {
		// this.setBackground(Color.black);
		list = mgr.getMovie();

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());

		// cards panel에 add할 movie panel 생성
		JPanel[] movies = new JPanel[list.size()];
		for (int i = 0; i < list.size(); i++) {
			movies[i] = new JPanel();
			movies[i].setLayout(null);
			movies[i].setBackground(Color.WHITE);

			JLabel lblPoster = new JLabel();
			poster = new ImageIcon("images/" + list.get(i).getTitle() + ".jpg");
			Image resizedPoster = poster.getImage().getScaledInstance(400, 565, java.awt.Image.SCALE_SMOOTH);
			lblPoster.setIcon(new ImageIcon(resizedPoster));
			lblPoster.setBounds(120, 60, 440, 565);

			// JButton movieinfo = new JButton(1100);

			// html 태그로 포스터 불러올 수 있다는 것
			// JLabel label = new JLabel("<html><img height='80' width='100'
			// src='https://developer.cdn.mozilla.net/media/img/mdn-logo-sm.png'
			// /></html>");
			// movies[i].add(label);
			// label.setBounds(500, 300, 300, 300);

			lblTitle = new JLabel(list.get(i).getTitle());
			lblTitle.setFont(new Font("Yu Gothic", Font.BOLD, 30));
			lblTitle.setBounds(660, 55, 600, 40);

			lblGenre = new JLabel(list.get(i).getGenre());
			lblGenre.setBounds(700, 170, 120, 30);
			lblGenre.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			lblRunningTime = new JLabel(list.get(i).getRunningTime() + "分");
			lblRunningTime.setBounds(700, 200, 100, 30);
			lblRunningTime.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			taDescription = new JTextArea(list.get(i).getDescription());
			taDescription.setEditable(false);
			taDescription.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행
			taDescription.setBounds(600, 380, 670, 150);
			taDescription.setFont(new Font("Yu Gothic", Font.BOLD, 17));

			lblType = new JLabel(list.get(i).getType());
			lblDirector = new JLabel(list.get(i).getDirector());
			lblActor = new JLabel(list.get(i).getCast());
			lblReleasedate = new JLabel(list.get(i).getReleaseDate());

			lblType.setBounds(700, 230, 300, 30);
			lblDirector.setBounds(700, 260, 100, 30);
			lblActor.setBounds(700, 290, 500, 30);
			lblReleasedate.setBounds(700, 320, 200, 30);

			lblType.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblDirector.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblActor.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			lblReleasedate.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			genre = new JLabel("ジャンル");
			runningTime = new JLabel("上映時間");
			type = new JLabel("タイプ");
			director = new JLabel("監督");
			actor = new JLabel("キャスト");
			releasedate = new JLabel("封切り日");

			genre.setBounds(600, 170, 120, 30);
			runningTime.setBounds(600, 200, 100, 30);
			type.setBounds(600, 230, 100, 30);
			director.setBounds(600, 260, 100, 30);
			actor.setBounds(600, 290, 100, 30);
			releasedate.setBounds(600, 320, 100, 30);

			genre.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			runningTime.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			type.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			director.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			actor.setFont(new Font("Yu Gothic", Font.BOLD, 15));
			releasedate.setFont(new Font("Yu Gothic", Font.BOLD, 15));

			JLabel stars = new JLabel(new ImageIcon("images/movieInfo/Star.png"));
			stars.setBounds(670, 105, 300, 50);

			JLabel ageImg = new JLabel(new ImageIcon("images/movieInfo/age1.jpg"));
			ageImg.setBounds(570, 30, 100, 100);

			rating = new JLabel("評価　8.1");
			rating.setFont(new Font("Yu Gothic", Font.BOLD, 25));
			rating.setBounds(600, 115, 120, 30);

			line = new JLabel(new ImageIcon("images/movieInfo/line1.png"));
			line.setBounds(600, 155, 700, 10);

			line2 = new JLabel(new ImageIcon("images/movieInfo/line1.png"));
			line2.setBounds(600, 360, 700, 10);

			threeD = new JLabel(new ImageIcon("images/movieInfo/3d.png"));
			threeD.setBounds(1170, 80, 100, 80);

			imax = new JLabel(new ImageIcon("images/movieInfo/imax.png"));
			imax.setBounds(1070, 80, 100, 80);

			movieinfo = new JLabel(new ImageIcon("images/movieInfo/movieinfo.png"));
			movieinfo.setBounds(-315, 0, 1360, 50);

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

			movies[i].add(lblPoster);
			movies[i].add(lblTitle);
			movies[i].add(lblGenre);
			movies[i].add(lblRunningTime);
			movies[i].add(taDescription);

			movies[i].add(stars);
			movies[i].add(ageImg);
			movies[i].add(movieinfo);
			movies[i].add(rating);
			movies[i].add(line);
			movies[i].add(line2);
			movies[i].add(threeD);
			movies[i].add(imax);

			btnBack = new JButton("Back");
			btnBack = new JButton(new ImageIcon("images/movieInfo/mainmenu.png"));
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new MainMenu();
					dispose();
				}
			});
			btnBack.setBounds(1130, 540, 135, 80);

			btnReserve = new JButton(new ImageIcon("images/movieInfo/reserve.png"));
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
			btnReserve.setBounds(960, 540, 135, 80);

			movies[i].add(btnBack);
			movies[i].add(btnReserve);

			JButton btnNext = new JButton(new ImageIcon("images/movieInfo/next.png"));
			btnNext.setRolloverIcon(new ImageIcon("images/movieInfo/next_2.png"));
			btnNext.setActionCommand("NEXT");
			btnNext.setFocusPainted(false);
			btnNext.addActionListener(this);
			btnNext.setBorderPainted(false);
			btnNext.setOpaque(false);
			btnNext.setBounds(700, 650, 100, 80);
			movies[i].add(btnNext);

			JButton btnPrev = new JButton(new ImageIcon("images/movieInfo/prev.png"));
			btnPrev.setRolloverIcon(new ImageIcon("images/movieInfo/prev_2.png"));
			btnPrev.setBounds(500, 655, 100, 80);
			btnPrev.setActionCommand("PREVIOUS");
			btnPrev.setFocusPainted(false);
			btnPrev.addActionListener(this);
			movies[i].add(btnPrev);

			cards.add(movies[i]);
		}

		add(cards, BorderLayout.CENTER);

		setTitle("上映映画情報");
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

	public static void main(String[] args) {
		new MovieInfo();
	}
}