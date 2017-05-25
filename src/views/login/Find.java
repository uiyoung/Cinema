package views.login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DBMgr;
import models.MemberBean;
import views.CinemaFrame;

public class Find extends CinemaFrame implements ActionListener {
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;
	MemberBean bean;

	JLabel lab1 = new JLabel("\n  名前 : ");
	JTextField txtf1 = new JTextField(10);
	JLabel lab2 = new JLabel("\n  生年月日 : ");
	JTextField txtf2 = new JTextField(10);
	JLabel lab3 = new JLabel("\n  携帯電話番号 : ");
	JTextField txtf3 = new JTextField(10);
	JPanel pan1 = new JPanel();

	JButton bt = new JButton("確認");
	JPanel pan2 = new JPanel();

	JLabel lab4 = new JLabel("\n   ID");
	JTextField txtf4 = new JTextField(10);
	JLabel lab5 = new JLabel("\n  パスワード");
	JTextField txtf5 = new JTextField(10);
	JButton bt2 = new JButton("完了");
	JButton bt3 = new JButton("閉じる");
	JPanel pan3 = new JPanel();

	public Find() {
		this.setSize(400, 220);
		this.setTitle("Find");
		init();
	}

	private void init() {
		Container con = this.getContentPane();

		pan1.setLayout(new GridLayout(3, 1));
		bt.addActionListener(this);
		pan1.add(lab1);
		pan1.add(txtf1);
		pan1.add(lab2);
		pan1.add(txtf2);
		pan1.add(lab3);
		pan1.add(txtf3);

		pan2.add(bt);

		pan3.setLayout(new GridLayout(3, 1));
		pan3.add(lab4);
		pan3.add(txtf4);
		pan3.add(lab5);
		pan3.add(txtf5);
		txtf4.setEditable(false);
		txtf5.setEditable(false);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		pan3.add(bt2);
		pan3.add(bt3);

		con.add("North", pan1);
		con.add("Center", pan2);
		con.add("South", pan3);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("確認")) {
			String name = txtf1.getText();
			String birthdate = txtf2.getText();
			String phone = txtf3.getText();
			try {
				list = mgr.find(name, birthdate, phone);
				if (name.equals(list.get(0).getName()) && birthdate.equals(list.get(0).getBirthdate())
						&& phone.equals(list.get(0).getPhone())) {
					txtf4.setText(list.get(0).getId());
					txtf5.setText(list.get(0).getPassword());
				}

			} catch (IndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "入力した情報を再度確認してください。");
			}
		}
		if (e.getActionCommand().equals("完了")) {
			JOptionPane.showMessageDialog(null, "確認された情報に再度ログインしてください。");
			dispose();
		}
		if (e.getActionCommand().equals("閉じる")) {
			dispose();
		}
	}
}
