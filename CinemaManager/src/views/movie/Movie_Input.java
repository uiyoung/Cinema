package views.movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;

public class Movie_Input extends MFrame implements ActionListener {
	JLabel lab = new JLabel("映画登録");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("映画のタイトル");
	JLabel lab2 = new JLabel("映画のジャンル");
	JLabel lab3 = new JLabel("タイプ");
	JLabel lab4 = new JLabel("監督");
	JLabel lab5 = new JLabel("出演");
	JLabel lab6 = new JLabel("公開日");
	JLabel lab7 = new JLabel("上映時間");
	JLabel lab8 = new JLabel("ストーリー");

	JTextField txtf1 = new JTextField(10);
	JTextField txtf2 = new JTextField(10);
	JTextField txtf3 = new JTextField(10);
	JTextField txtf4 = new JTextField(10);
	JTextField txtf5 = new JTextField(10);
	JTextField txtf6 = new JTextField(10);
	JTextField txtf7 = new JTextField(10);

	JTextArea txtf8 = new JTextArea("内容を入力してください。", 10, 30);

	JButton bt = new JButton("確認");
	JButton bt1 = new JButton("取り消す");
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;

	public Movie_Input() {
		this.setTitle("映画登録");
		this.setSize(550, 750);
		init();

	}

	public void init() {
		Container con = this.getContentPane();
		con.setLayout(null);
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.WHITE);
		pan.add(lab);

		// 버튼의 색상과 폰트지정
		bt.setBackground(Color.DARK_GRAY);
		bt1.setBackground(Color.DARK_GRAY);
		bt.setForeground(Color.WHITE);
		bt1.setForeground(Color.WHITE);

		// 라벨과 버튼에 대한 폰트지정
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab5.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab6.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab7.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab8.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		txtf8.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		// contentpane에 라벨과 텍스트필드 위치지정
		pan.setBounds(0, 0, 550, 35);

		lab1.setBounds(50, 85, 150, 50);
		txtf1.setBounds(200, 100, 250, 25);

		lab2.setBounds(50, 130, 150, 50);
		txtf2.setBounds(200, 145, 250, 25);

		lab3.setBounds(50, 175, 150, 50);
		txtf3.setBounds(200, 190, 250, 25);

		lab4.setBounds(50, 220, 150, 50);
		txtf4.setBounds(200, 235, 250, 25);

		lab5.setBounds(50, 265, 150, 50);
		txtf5.setBounds(200, 280, 250, 25);

		lab6.setBounds(50, 310, 150, 50);
		txtf6.setBounds(200, 325, 250, 25);

		lab7.setBounds(50, 355, 150, 50);
		txtf7.setBounds(200, 380, 250, 25);

		lab8.setBounds(50, 400, 150, 50);
		txtf8.setBounds(50, 440, 400, 150);
		txtf8.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행

		bt.setBounds(100, 615, 120, 40);
		bt1.setBounds(300, 615, 120, 40);

		con.add(pan);

		con.add(lab1);
		con.add(txtf1);
		con.add(lab2);
		con.add(txtf2);
		con.add(lab3);
		con.add(txtf3);
		con.add(lab4);
		con.add(txtf4);
		con.add(lab5);
		con.add(txtf5);
		con.add(lab6);
		con.add(txtf6);
		con.add(lab7);
		con.add(txtf7);
		con.add(lab8);
		con.add(txtf8);
		con.add(bt);
		con.add(bt1);

		bt.addActionListener(this);
		bt1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("確認")) {
			String title = txtf1.getText();
			String genre = txtf2.getText();
			String releaseDate = txtf6.getText();
			String runningTime = txtf7.getText();
			String description = txtf8.getText();
			String type = txtf3.getText();
			String director = txtf4.getText();
			String cast = txtf5.getText();
			
			mgr.input_movie(title, genre, releaseDate, runningTime, description, type, director, cast);
			/* 열려있는 모든 창 닫기 */
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
				win[i] = null;
			}
			new ManageMovie();
		} else if (e.getActionCommand().equals("取り消す")) {
			dispose();
		}
	}
}
