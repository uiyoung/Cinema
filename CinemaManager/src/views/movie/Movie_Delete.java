package views.movie;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;

public class Movie_Delete extends MFrame implements ActionListener {
	JLabel lab = new JLabel("映画削除");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("タイトル");
	JTextField txtf = new JTextField(10);
	JPanel cpan = new JPanel();

	JButton bt = new JButton("削除");
	JButton bt1 = new JButton("取り消す");
	JPanel span = new JPanel();
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;

	public Movie_Delete() {
		this.setTitle("映画削除");
		this.setSize(550, 350);
		init();
	}

	public void init() {
		Container con = this.getContentPane();
		con.setLayout(null);
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.WHITE);
		pan.add(lab);

		// 라벨과 버튼에 대한 색상과 폰트지정
		bt.setBackground(Color.DARK_GRAY);
		bt.setForeground(Color.WHITE);
		bt1.setBackground(Color.DARK_GRAY);
		bt1.setForeground(Color.WHITE);
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		lab1.setBounds(100, 60, 150, 50);
		txtf.setBounds(210, 75, 200, 25);

		bt.setBounds(100, 170, 120, 40);
		bt1.setBounds(300, 170, 120, 40);

		con.add(lab1);
		con.add(txtf);
		con.add(bt);
		con.add(bt1);

		bt.addActionListener(this);
		bt1.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("削除")) {
			// 메시지 창뜨기
			int result = JOptionPane.showConfirmDialog(null, "本当に削除しますか。", "メッセージ", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.CLOSED_OPTION) {
			} else if (result == JOptionPane.YES_OPTION) {
				String title = txtf.getText();
				list = mgr.delete_Movie(title);

				/* 열려있는 모든 창 닫기 */
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
					win[i] = null;
				}
				new ManageMovie();
			}
		} else if (e.getActionCommand().equals("取り消す")) {
			txtf.setText(" ");
			dispose();
		}
	}

}