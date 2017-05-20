package views.myticket;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.DBMgr;
import models.MemberBean;
import views.CinemaFrame;
import views.login.Login;

public class DeleteMember extends CinemaFrame implements ActionListener {
	JLabel lab = new JLabel("회원 탈퇴");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("비밀번호 : ");
	JTextField txtf = new JTextField(10);
	JPanel cpan = new JPanel();

	JButton bt = new JButton("탈퇴");
	JButton bt1 = new JButton("취소");
	JPanel span = new JPanel();
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;

	public DeleteMember() {
		this.setSize(300, 200);
		this.setTitle("회원 탈퇴");
		init();
	}

	private void init() {
		Container con = this.getContentPane();
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.white);
		pan.add(lab);

		bt.addActionListener(this);
		bt1.addActionListener(this);
		cpan.add(lab1);
		cpan.add(txtf);
		span.add(bt);
		span.add(bt1);

		con.add("North", pan);
		con.add("Center", cpan);
		con.add("South", span);
	}// init

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("탈퇴")) {
			String id = Login.staticId;
			String password = txtf.getText();
			list = mgr.deleteMb(id, password);
			// password가 맞을 경우
			// JOptionPane.showMessageDialog(null, "탈퇴되었습니다");
			// password가 틀릴 경우
			// JOptionPane.showMessageDialog(null, "비밀번호를 확인하여 주십시오");
			dispose();

		} else if (e.getActionCommand().equals("취소")) {
			txtf.setText(" ");
			new MyTicket();
			dispose();
		}

	}

}
