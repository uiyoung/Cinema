package views.myticket;

import java.awt.Color;
import java.awt.Container;
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

public class UpdatePW extends CinemaFrame implements ActionListener {
	JLabel lab = new JLabel("비밀번호 변경");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("기존 비밀번호 : ");
	JTextField txtf1 = new JPasswordField(10);
	JButton bt = new JButton("확인");
	JLabel lab2 = new JLabel("새 비밀번호 : ");
	JTextField txtf2 = new JPasswordField(10);
	JPanel cpan = new JPanel();

	JButton bt1 = new JButton("변경");
	JButton bt2 = new JButton("취소");
	JPanel span = new JPanel();
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;

	public UpdatePW() {
		this.setSize(300, 200);
		this.setTitle("비밀번호 변경");
		init();
	}

	private void init() {
		Container con = this.getContentPane();
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.white);
		pan.add(lab);

		bt.addActionListener(this);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		cpan.add(lab1);
		cpan.add(txtf1);
		cpan.add(bt);
		cpan.add(lab2);
		cpan.add(txtf2);
		cpan.add(bt1);
		span.add(bt2);

		con.add("North", pan);
		con.add("Center", cpan);
		con.add("South", span);
	}// init

	// public static void main(String[] args) {
	// new Delete_Mb();
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("확인")) {
			String id = Login.staticId;
			String password = txtf1.getText();
			list = mgr.UpdatePw(id, password);
			txtf2.setText(list.get(0).getPassword());
		} else if (e.getActionCommand().equals("취소")) {
			txtf1.setText(" ");
			new MyTicket();
			dispose();
		} else if (e.getActionCommand().equals("변경")) {
			String password = txtf2.getText();
			mgr.UpdatePw2(password);
			new MyTicket();
			dispose();
			JOptionPane.showMessageDialog(null, "성공적으로 변경되었습니다");

		}
	}
}