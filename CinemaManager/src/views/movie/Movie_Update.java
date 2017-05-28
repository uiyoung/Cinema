package views.movie;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;

public class Movie_Update extends MFrame implements ActionListener {
	JLabel lab = new JLabel("映画修正");
	JTextField txtf = new JTextField(10);
	JButton bt = new JButton("照会");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("一連番号");
	JLabel lab2 = new JLabel("映画のタイトル");
	JLabel lab3 = new JLabel("映画のジャンル");
	JLabel lab4 = new JLabel("タイプ");
	JLabel lab5 = new JLabel("監督");
	JLabel lab6 = new JLabel("出演");
	JLabel lab7 = new JLabel("公開日");
	JLabel lab8 = new JLabel("上映時間");
	JLabel lab9 = new JLabel("ストーリー");

	JTextField txtf1 = new JTextField(10);
	JTextField txtf2 = new JTextField(10);
	JTextField txtf3 = new JTextField(10);
	JTextField txtf4 = new JTextField(10);
	JTextField txtf5 = new JTextField(10);
	JTextField txtf6 = new JTextField(10);
	JTextField txtf7 = new JTextField(10);
	JTextField txtf8 = new JTextField(10);

	JTextArea txtf9 = new JTextArea();

	JButton bt1 = new JButton("修正");
	JButton bt2 = new JButton("取り消す");

	JTable table;
	JScrollPane Scpan;
	String col[] = { "一連番号", "映画のタイトル", "映画のジャンル", "タイプ", "監督", "出演", "公開日", "上映時間", "ストーリー" };
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;
	MovieBean bean;

	String title;

	public Movie_Update() {
		this.setTitle("映画修正");
		table = new JTable();
		JTableHeader th = table.getTableHeader();
		th.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		th.setBackground(Color.darkGray);
		th.setForeground(Color.white);
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
		bt2.setBackground(Color.DARK_GRAY);
		bt.setForeground(Color.WHITE);
		bt1.setForeground(Color.WHITE);
		bt2.setForeground(Color.WHITE);

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
		lab9.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		txtf9.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		pan.setBounds(0, 0, 550, 35);

		/* combobox for select title */
		ArrayList<String> titleList = mgr.getMovieTitles();
		JComboBox<String> cbTitles = new JComboBox<>();
		for (int i = 0; i < titleList.size(); i++) {
			cbTitles.addItem(titleList.get(i));
		}
		title = cbTitles.getSelectedItem().toString(); // default value
		cbTitles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				title = cbTitles.getSelectedItem().toString();
				System.out.println(title);
			}
		});
		cbTitles.setBounds(120, 50, 200, 25);
		con.add(cbTitles);

		bt.setBounds(340, 45, 80, 35);
		lab1.setBounds(50, 80, 150, 50);
		txtf1.setEditable(false);
		txtf1.setBounds(200, 95, 250, 25);

		lab2.setBounds(50, 120, 150, 50);
		txtf2.setBounds(200, 135, 250, 25);

		lab3.setBounds(50, 160, 150, 50);
		txtf3.setBounds(200, 175, 250, 25);

		lab4.setBounds(50, 200, 150, 50);
		txtf4.setBounds(200, 215, 250, 25);

		lab5.setBounds(50, 240, 150, 50);
		txtf5.setBounds(200, 255, 250, 25);

		lab6.setBounds(50, 280, 150, 50);
		txtf6.setBounds(200, 295, 250, 25);

		lab7.setBounds(50, 320, 150, 50);
		txtf7.setBounds(200, 335, 250, 25);

		lab8.setBounds(50, 360, 150, 50);
		txtf8.setBounds(200, 375, 250, 25);

		lab9.setBounds(50, 400, 150, 50);
		txtf9.setBounds(50, 440, 400, 150);
		txtf9.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행

		bt1.setEnabled(false); // 조회버튼 누르기 전에는 안눌러지게
		bt1.setBounds(100, 615, 120, 40);
		bt2.setBounds(300, 615, 120, 40);

		con.add(pan);

		con.add(txtf);
		con.add(bt);
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
		con.add(lab9);
		con.add(txtf9);
		con.add(bt1);
		con.add(bt2);

		bt.addActionListener(this);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("照会")) {
			list = mgr.getMovieInfo(title);

			txtf1.setText(Integer.toString(list.get(0).getNo()));
			txtf2.setText(list.get(0).getTitle());
			txtf3.setText(list.get(0).getGenre());
			txtf4.setText(list.get(0).getType());
			txtf5.setText(list.get(0).getDirector());
			txtf6.setText(list.get(0).getCast());
			txtf7.setText(list.get(0).getReleaseDate());
			txtf8.setText(list.get(0).getRunningTime());
			txtf9.setText(list.get(0).getDescription());

			bt1.setEnabled(true);
		}
		if (e.getActionCommand().equals("修正")) {
			int no = Integer.parseInt(txtf1.getText());
			String title = txtf2.getText();
			String genre = txtf3.getText();
			String type = txtf4.getText();
			String director = txtf5.getText();
			String cast = txtf6.getText();
			String releasDate = txtf7.getText();
			String runningTime = txtf8.getText();
			String description = txtf9.getText();
			System.out.println(txtf.getText());

			// 메시지 창뜨기
			int result = JOptionPane.showConfirmDialog(null, "本当に修正しますか。", "メッセージ", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				mgr.updateMovie(no, title, genre, releasDate, runningTime, description, type, director, cast);
				JOptionPane.showConfirmDialog(null, "수정되었습니다.", "メッセージ", JOptionPane.DEFAULT_OPTION);

				/* 열려있는 모든 창 닫기 */
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
					win[i] = null;
				}
				new ManageMovie();
			}

		} else if (e.getActionCommand().equals("取り消す")) {
			dispose();
		}
	}

	public static void main(String[] args) {
		new Movie_Update();
	}
}