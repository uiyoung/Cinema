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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;

public class CreateTheater extends MFrame implements ActionListener {
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;
	String theaterName, theaterAddress, theaterPhone, theaterDescription;
	int theaterNo, theaterCapacity;

	JLabel lab = new JLabel("劇場登録");
	JPanel pan = new JPanel();

	JLabel lbl0 = new JLabel("劇場番号");
	JLabel lab1 = new JLabel("劇場の名前");
	JLabel lab2 = new JLabel("住所");
	JLabel lab3 = new JLabel("電話番号");
	JLabel lab4 = new JLabel("立ち見客数");
	JLabel lab8 = new JLabel("劇場情報");

	JTextField tfTheaterNo = new JTextField(10);
	JTextField tfTheaterName = new JTextField(10);
	JTextField tfTheaterAddress = new JTextField(10);
	JTextField tfTheaterPhone = new JTextField(10);
	// JTextField tfTheaterCapacity = new JTextField(10);
	JTextArea taTheaterDescription = new JTextArea("内容を入力してください。", 10, 30);

	JButton bt = new JButton("確認");
	JButton bt1 = new JButton("取り消す");

	public CreateTheater() {
		this.setTitle("劇場修正");
		this.setSize(530, 680);
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
		lbl0.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab8.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		taTheaterDescription.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		// contentpane에 라벨과 텍스트필드 위치지정
		pan.setBounds(0, 0, 550, 35);

		lbl0.setBounds(50, 75, 150, 50);
		tfTheaterNo.setBounds(200, 90, 250, 25);
		lab1.setBounds(50, 125, 150, 50);
		tfTheaterName.setBounds(200, 140, 250, 25);
		lab2.setBounds(50, 170, 150, 50);
		tfTheaterAddress.setBounds(200, 185, 250, 25);
		lab3.setBounds(50, 215, 150, 50);
		tfTheaterPhone.setBounds(200, 230, 250, 25);
		lab4.setBounds(50, 260, 150, 50);
		// tfTheaterCapacity.setBounds(200, 275, 250, 25);

		/*-------------------- num of seats combobox ----------------------*/
		JComboBox<Integer> cbCapacityList = new JComboBox<>();
		cbCapacityList.addItem(100);
		cbCapacityList.addItem(80);
		cbCapacityList.addItem(60);

		cbCapacityList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theaterCapacity = cbCapacityList.getItemAt(cbCapacityList.getSelectedIndex());
				System.out.println("numOfSeats : " + theaterCapacity);
			}
		});
		theaterCapacity = cbCapacityList.getItemAt(cbCapacityList.getSelectedIndex());
		System.out.println("numOfSeats : " + theaterCapacity);
		cbCapacityList.setBounds(200, 275, 250, 25);

		lab8.setBounds(50, 320, 150, 50);
		taTheaterDescription.setBounds(50, 370, 400, 150);
		taTheaterDescription.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행

		bt.setBounds(100, 550, 120, 40);
		bt1.setBounds(300, 550, 120, 40);

		con.add(pan);

		con.add(lbl0);
		con.add(tfTheaterNo);
		con.add(lab1);
		con.add(tfTheaterName);
		con.add(lab2);
		con.add(tfTheaterAddress);
		con.add(lab3);
		con.add(tfTheaterPhone);
		con.add(lab4);
		con.add(cbCapacityList);
		con.add(lab8);
		con.add(taTheaterDescription);
		con.add(bt);
		con.add(bt1);

		bt.addActionListener(this);
		bt1.addActionListener(this);

		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("確認")) {
			theaterNo = Integer.parseInt(tfTheaterNo.getText());
			theaterName = tfTheaterName.getText();
			theaterAddress = tfTheaterAddress.getText();
			theaterPhone = tfTheaterPhone.getText();
			//theaterCapacity = Integer.parseInt(tfTheaterCapacity.getText());
			theaterDescription = taTheaterDescription.getText();

			mgr.insertTheater(theaterNo, theaterName, theaterAddress, theaterPhone, theaterCapacity,
					theaterDescription);

			JOptionPane.showMessageDialog(null, theaterName + "劇場が生成されました。", "メッセージ", JOptionPane.DEFAULT_OPTION);
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
}
