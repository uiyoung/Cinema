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
import models.TheaterBean;
import views.MFrame;

public class UpdateTheater extends MFrame implements ActionListener {
	MDBMgr mgr = new MDBMgr();
	ArrayList<TheaterBean> list;
	String theaterName, theaterAddress, theaterPhone, theaterDescription;
	int theaterNo, theaterCapacity;

	JLabel lab = new JLabel("극장登録");
	JPanel pan = new JPanel();

	JLabel lbl0 = new JLabel("극장번호");
	JLabel lab1 = new JLabel("극장이름");
	JLabel lab2 = new JLabel("주소");
	JLabel lab3 = new JLabel("전화번호");
	JLabel lab4 = new JLabel("수용인원");
	JLabel lab8 = new JLabel("극장 정보");

	// JTextField tfTheaterNo = new JTextField(10);
	JTextField tfTheaterName = new JTextField(10);
	JTextField tfTheaterAddress = new JTextField(10);
	JTextField tfTheaterPhone = new JTextField(10);
	JTextField tfTheaterCapacity = new JTextField(10);
	JTextArea taTheaterDescription = new JTextArea("内容を入力してください。", 10, 30);

	JButton btnModify = new JButton("修正");
	JButton btnCancel = new JButton("取り消す");

	public UpdateTheater() {
		this.setTitle("극장 수정");
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
		btnModify.setBackground(Color.DARK_GRAY);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnModify.setForeground(Color.WHITE);
		btnCancel.setForeground(Color.WHITE);

		// 라벨과 버튼에 대한 폰트지정
		lbl0.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab8.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		taTheaterDescription.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnModify.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnCancel.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		btnModify.setEnabled(false);

		// contentpane에 라벨과 텍스트필드 위치지정
		pan.setBounds(0, 0, 550, 35);

		lbl0.setBounds(50, 75, 150, 50);
		// tfTheaterNo.setBounds(200, 90, 250, 25);

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
		cbTheaterNo.setBounds(200, 90, 150, 25);

		JButton btnSearch = new JButton("照会");
		btnSearch.setBackground(Color.DARK_GRAY);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list = mgr.getTheaterInfo(theaterNo);

				tfTheaterName.setText(list.get(0).getName());
				tfTheaterAddress.setText(list.get(0).getAddress());
				tfTheaterPhone.setText(list.get(0).getPhone());
				tfTheaterCapacity.setText(Integer.toString(list.get(0).getCapacity()));
				taTheaterDescription.setText(list.get(0).getDescription());

				btnModify.setEnabled(true);
			}
		});
		btnSearch.setBounds(370, 80, 80, 35);

		lab1.setBounds(50, 125, 150, 50);
		tfTheaterName.setBounds(200, 140, 250, 25);
		lab2.setBounds(50, 170, 150, 50);
		tfTheaterAddress.setBounds(200, 185, 250, 25);
		lab3.setBounds(50, 215, 150, 50);
		tfTheaterPhone.setBounds(200, 230, 250, 25);
		lab4.setBounds(50, 260, 150, 50);
		tfTheaterCapacity.setBounds(200, 275, 250, 25);
		lab8.setBounds(50, 320, 150, 50);
		taTheaterDescription.setBounds(50, 370, 400, 150);
		taTheaterDescription.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행


		btnModify.setBounds(100, 550, 120, 40);
		btnCancel.setBounds(300, 550, 120, 40);

		con.add(pan);

		con.add(lbl0);
		// con.add(tfTheaterNo);
		con.add(btnSearch);
		con.add(cbTheaterNo);
		con.add(lab1);
		con.add(tfTheaterName);
		con.add(lab2);
		con.add(tfTheaterAddress);
		con.add(lab3);
		con.add(tfTheaterPhone);
		con.add(lab4);
		con.add(tfTheaterCapacity);
		con.add(lab8);
		con.add(taTheaterDescription);
		con.add(btnModify);
		con.add(btnCancel);

		btnModify.addActionListener(this);
		btnCancel.addActionListener(this);

		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModify) {
			theaterName = tfTheaterName.getText();
			theaterAddress = tfTheaterAddress.getText();
			theaterPhone = tfTheaterPhone.getText();
			theaterCapacity = Integer.parseInt(tfTheaterCapacity.getText());
			theaterDescription = taTheaterDescription.getText();

			mgr.updateTheater(theaterNo, theaterName, theaterAddress, theaterPhone, theaterCapacity,
					theaterDescription);

			JOptionPane.showMessageDialog(null, theaterName + "극장정보가 수정되었습니다", "message", JOptionPane.DEFAULT_OPTION);
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
