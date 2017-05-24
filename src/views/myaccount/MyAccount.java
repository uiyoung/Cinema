package views.myaccount;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.DBMgr;
import models.MemberBean;
import views.CinemaFrame;
import views.login.Login;
import views.myticket.MyTicket;

public class MyAccount extends CinemaFrame implements ActionListener {
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;
	MemberBean bean;

	JButton bt = new JButton("예매 정보 확인");
	JButton bt2 = new JButton("회원 탈퇴");
	JButton bt3 = new JButton("비밀번호 변경");
	JPanel pan1 = new JPanel();

	JButton bt1 = new JButton("회원 정보 수정");
	JLabel lab = new JLabel("비밀번호 : ");
	JTextField txtf = new JPasswordField(10);
	JButton bt4 = new JButton("확인");
	JPanel pan2 = new JPanel();

	JLabel lab1 = new JLabel("\n  ID");
	JTextField txtf1 = new JTextField(10);
	JLabel lab2 = new JLabel("\n  이름");
	JTextField txtf2 = new JTextField(10);
	JLabel lab3 = new JLabel("\n  생년월일");
	JTextField txtf3 = new JTextField(10);
	JLabel lab4 = new JLabel("\n  핸드폰 번호");
	JTextField txtf4 = new JTextField(10);
	JLabel lab5 = new JLabel("\n  잔여포인트");
	JTextField txtf5 = new JTextField(10);
	JButton bt5 = new JButton("저장");
	JButton bt6 = new JButton("닫기");
	JPanel pan3 = new JPanel();

	public MyAccount() {
		this.setSize(400, 270);
		this.setTitle("MyAccount");
		init();
	}

	private void init() {
		txtf1.setText(Login.staticId);
		txtf2.setText(Login.staticName);
		txtf3.setText(Login.staticBirthdate);
		txtf4.setText(Login.staticPhone);
		txtf5.setText(Login.staticPoint + "点");

		Container con = this.getContentPane();
		pan1.add(bt);
		pan1.add(bt3);
		pan1.add(bt2);

		pan2.add(bt1);
		pan2.add(lab);
		pan2.add(txtf);
		pan2.add(bt4);
		txtf.setEditable(false);

		pan3.setLayout(new GridLayout(6, 1));
		pan3.add(lab1);
		pan3.add(txtf1);
		pan3.add(lab2);
		pan3.add(txtf2);
		pan3.add(lab3);
		pan3.add(txtf3);
		pan3.add(lab4);
		pan3.add(txtf4);
		pan3.add(lab5);
		pan3.add(txtf5);
		pan3.add(bt5);
		pan3.add(bt6);

		txtf1.setEditable(false);
		txtf2.setEditable(false);
		txtf3.setEditable(false);
		txtf4.setEditable(false);
		txtf5.setEditable(false);

		bt.addActionListener(this);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt6.addActionListener(this);

		con.add("North", pan1);
		con.add("Center", pan2);
		con.add("South", pan3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("예매 정보 확인")) {
			/* 열려있는 모든 창 닫기 */
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
				win[i] = null;
			}

			new MyTicket();
		}
		if (e.getActionCommand().equals("비밀번호 변경")) {
			new UpdatePW();
			dispose();
		}
		if (e.getActionCommand().equals("회원 탈퇴")) {
			new DeleteMember();
			dispose();
		}
		if (e.getActionCommand().equals("회원 정보 수정")) {
			txtf.setEditable(true);
			bt4.addActionListener(this);
		}
		if (e.getActionCommand().equals("확인")) {

			String password = txtf.getText();
			if (Login.staticPassword.equals(password)) {
				txtf2.setEditable(true);
				txtf3.setEditable(true);
				txtf4.setEditable(true);
				bt5.addActionListener(this);
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요");
			}
		}
		if (e.getActionCommand().equals("저장")) {
			String id = Login.staticId;
			String name = txtf2.getText();
			String birthdate = txtf3.getText();
			String phone = txtf4.getText();

			list = mgr.updateMember(id, name, birthdate, phone);
			JOptionPane.showMessageDialog(null, "회원정보가 저장되었습니다");
			txtf.setEditable(false);
			txtf2.setEditable(false);
			txtf3.setEditable(false);
			txtf4.setEditable(false);
			// txtf.setText("");
			// txtf2.setText(list.get(0).getName());
			// txtf3.setText(list.get(0).getBirthdate());
			// txtf4.setText(list.get(0).getPhone());

			dispose();
			Login.staticName = txtf2.getText();
			Login.staticBirthdate = txtf3.getText();
			Login.staticPhone = txtf4.getText();
			new MyAccount();
		}
		if (e.getActionCommand().equals("닫기")) {
			dispose();
		}
	}
}
