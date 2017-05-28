package views.theater;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;

public class DeleteTheater extends MFrame implements ActionListener {
	JLabel lab = new JLabel("映画削除");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("삭제할 극장 번호");
	JPanel cpan = new JPanel();

	JButton bt = new JButton("削除");
	JButton bt1 = new JButton("取り消す");
	JPanel span = new JPanel();
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;
	private int theaterNo;

	public DeleteTheater() {
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
		// txtf.setBounds(210, 75, 200, 25);

		/* combobox for select theater_no */
		ArrayList<Integer> theaterNoList = mgr.getTheaterNo();
		JComboBox<Integer> cbTheaterNo = new JComboBox<>();
		for (int i = 0; i < theaterNoList.size(); i++) {
			cbTheaterNo.addItem(theaterNoList.get(i));
		}
		theaterNo = (int) cbTheaterNo.getSelectedItem(); // default value
		cbTheaterNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theaterNo = (int) cbTheaterNo.getSelectedItem();
				System.out.println(theaterNo);
			}
		});
		cbTheaterNo.setBounds(250, 75, 200, 25);

		bt.setBounds(100, 170, 120, 40);
		bt1.setBounds(300, 170, 120, 40);

		con.add(lab1);
		con.add(cbTheaterNo);
		con.add(bt);
		con.add(bt1);

		bt.addActionListener(this);
		bt1.addActionListener(this);

		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("削除")) {
			mgr.deleteTheater(theaterNo);
			/* 열려있는 모든 창 닫기 */
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
				win[i] = null;
			}
			new ManageTheater();
		} else if (e.getActionCommand().equals("取り消す")) {
			dispose();
		}
	}

	public static void main(String[] args) {
		new DeleteTheater();
	}

}