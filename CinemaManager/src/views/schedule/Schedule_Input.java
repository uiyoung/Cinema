package views.schedule;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.MDBMgr;
import models.MovieBean;
import models.ScheduleBean;
import views.MFrame;

public class Schedule_Input extends MFrame implements ActionListener {
	MDBMgr mgr = new MDBMgr();
	ArrayList<ScheduleBean> scheduleList;
	ArrayList<MovieBean> movieList;
	private String date, time, theater, seatNo;
	private int movieNo, theaterNo, numOfSeats;

	JLabel schlab = new JLabel("上映情報");
	JPanel schpan = new JPanel();

	JLabel schlab2 = new JLabel("上映日");// 상영날짜
	JLabel schlab3 = new JLabel("上映時間");// 상영시간
	JLabel schlab4 = new JLabel("映画");// 영화
	JLabel schlab5 = new JLabel("劇場");// 극장
	JLabel lblNumofSeats = new JLabel("座席数");// 극장

	JTextField tfYear = new JTextField();
	JTextField tfMonth = new JTextField();
	JTextField tfDay = new JTextField();

	JButton btnConfirm = new JButton("確認");
	JButton btnCancel = new JButton("取り消す");

	public Schedule_Input() {
		this.setTitle("上映情報");
		this.setSize(550, 550);
		init();
	}

	public void init() {
		movieList = mgr.allmovie();

		Container schcon = this.getContentPane();
		schcon.setLayout(null);
		schpan.setBackground(Color.DARK_GRAY);
		schlab.setForeground(Color.WHITE);
		schpan.add(schlab);

		// 라벨과 버튼에 대한 폰트지정
		schlab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		schlab2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		schlab3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		schlab4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		schlab5.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lblNumofSeats.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnConfirm.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnCancel.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		btnConfirm.setBackground(Color.DARK_GRAY);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnConfirm.setForeground(Color.WHITE);
		btnCancel.setForeground(Color.WHITE);
		// contentpane에 라벨과 텍스트필드 위치지정
		schpan.setBounds(0, 0, 550, 35);

		schlab2.setBounds(50, 130, 150, 50);

		tfYear.setText("2017");

		tfYear.setBounds(200, 145, 40, 25);
		tfMonth.setBounds(260, 145, 30, 25);
		tfDay.setBounds(310, 145, 30, 25);

		JLabel lblYear = new JLabel("年");
		JLabel lblMonth = new JLabel("月");
		JLabel lblDay = new JLabel("日");
		lblYear.setBounds(240, 145, 40, 25);
		lblMonth.setBounds(290, 145, 40, 25);
		lblDay.setBounds(340, 145, 40, 25);

		schlab3.setBounds(50, 175, 150, 50);

		/* combobox for select time */
		JComboBox<String> cbTimes = new JComboBox<>();
		cbTimes.addItem("14:00");
		cbTimes.addItem("19:00");
		cbTimes.addItem("22:00");
		time = cbTimes.getSelectedItem().toString();
		cbTimes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time = cbTimes.getSelectedItem().toString();
				System.out.println(time);
			}
		});
		cbTimes.setBounds(200, 190, 250, 25);

		schlab4.setBounds(50, 220, 150, 50);

		/* combobox for select title */
		ArrayList<String> titleList = mgr.getMovieTitles();
		JComboBox<String> cbTitles = new JComboBox<>();
		for (int i = 0; i < titleList.size(); i++) {
			cbTitles.addItem(titleList.get(i));
		}

		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getTitle().equals(cbTitles.getSelectedItem())) {
				movieNo = movieList.get(i).getNo();
				System.out.println("movieNo:" + movieNo);
				break;
			}
		}

		cbTitles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < movieList.size(); i++) {
					if (movieList.get(i).getTitle().equals(cbTitles.getSelectedItem())) {
						movieNo = movieList.get(i).getNo();
						System.out.println("movieNo:" + movieNo);
						break;
					}
				}
			}
		});
		cbTitles.setBounds(200, 235, 250, 25);

		schlab5.setBounds(50, 265, 150, 50);

		/* combobox for select theaters */
		ArrayList<String> theaterList = mgr.getTheaterNames();
		JComboBox<String> cbTheaters = new JComboBox<>();
		for (int i = 0; i < theaterList.size(); i++) {
			cbTheaters.addItem(theaterList.get(i));
		}
		theaterNo = cbTheaters.getSelectedIndex() + 1; // default value
		theater = cbTheaters.getSelectedItem().toString();
		cbTheaters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theaterNo = cbTheaters.getSelectedIndex() + 1;
				theater = cbTheaters.getSelectedItem().toString();
				System.out.println(theaterNo + ":" + theater);
			}
		});
		cbTheaters.setBounds(200, 280, 250, 25);

		lblNumofSeats.setBounds(50, 310, 150, 50);
		/*-------------------- num of seats combobox ----------------------*/
		JComboBox<Integer> cbNumOfSeats = new JComboBox<>();
		cbNumOfSeats.addItem(100);
		cbNumOfSeats.addItem(80);
		cbNumOfSeats.addItem(60);

		cbNumOfSeats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numOfSeats = cbNumOfSeats.getItemAt(cbNumOfSeats.getSelectedIndex());
				System.out.println("numOfSeats : " + numOfSeats);
			}
		});
		numOfSeats = cbNumOfSeats.getItemAt(cbNumOfSeats.getSelectedIndex());
		System.out.println("numOfSeats : " + numOfSeats);
		/*----------------end of num of seats combobox ----------------------*/
		cbNumOfSeats.setBounds(200, 325, 250, 26);

		btnConfirm.setBounds(100, 400, 120, 40);
		btnCancel.setBounds(300, 400, 120, 40);

		schcon.add(schpan);

		schcon.add(schlab2);
		schcon.add(tfYear);
		schcon.add(lblYear);
		schcon.add(tfMonth);
		schcon.add(lblMonth);
		schcon.add(tfDay);
		schcon.add(lblDay);
		schcon.add(schlab3);
		schcon.add(cbTimes);
		schcon.add(schlab4);
		schcon.add(cbTitles);
		schcon.add(schlab5);
		schcon.add(cbTheaters);
		schcon.add(lblNumofSeats);
		schcon.add(cbNumOfSeats);
		schcon.add(btnConfirm);
		schcon.add(btnCancel);

		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);

		tfMonth.requestFocus();

		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("確認")) {
			if (tfYear.getText().equals("") || tfMonth.getText().equals("") || tfDay.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "日付を入力してください。", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			date = tfYear.getText() + tfMonth.getText() + tfDay.getText();

			if (mgr.isDuplicateSchedule(date, time, theaterNo)) {
				JOptionPane.showMessageDialog(null, date + "日, " + time + "時に  " + theaterNo + "劇場ですでに映画を上映中です。",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			mgr.insertSchedule(date, time, movieNo, theaterNo); // 스케줄정보 입력

			/*----------------------------- 좌석정보 입력---------------------------------*/
			if (mgr.isSeatInserted(theater, date, time)) {
				JOptionPane.showMessageDialog(null, theater + "劇場, " + date + "日, " + time + "時に すでに座席が生成されています。",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String[] row = new String[numOfSeats / 10];
			for (int i = 0; i < row.length; i++) {
				row[i] = Character.toString((char) (65 + i)); // A~I
			}

			for (int i = 0; i < numOfSeats; i++) {
				if (i % 10 <= 8)
					seatNo = row[i / 10] + "0" + (i % 10 + 1); // A01~A09,
																// B01~B09, ...
				else
					seatNo = row[i / 10] + "10"; // A10,B10, ...

				mgr.insertSeats(Integer.toString(theaterNo), seatNo, date, time);
			}
			JOptionPane.showMessageDialog(null,
					theater + "劇場, " + date + "日, " + time + "時に " + numOfSeats + "個の座席が生成されました", "メッセージ",
					JOptionPane.DEFAULT_OPTION);
			/*---------------------------------end of 좌석정보입력---------------------------*/

		} else if (e.getActionCommand().equals("取り消す")) {
			/* 열려있는 모든 창 닫기 */
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
				win[i] = null;
			}
			new ManageSchedule();
		}
	}

	public static void main(String[] args) {
		new ManageSchedule();
	}
}