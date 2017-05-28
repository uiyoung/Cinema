package views.myaccount;

import java.awt.Color;
import java.awt.Container;
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
import models.MemberInfo;
import views.CinemaFrame;
import views.login.Login;

public class DeleteMember extends CinemaFrame implements ActionListener {
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;

	JPanel pan = new JPanel();
	JLabel lab = new JLabel("会員脱退");

	JPanel cpan = new JPanel();
	JLabel lab1 = new JLabel("パスワード : ");
	JTextField txtf = new JTextField(10);

	JPanel span = new JPanel();
	JButton bt = new JButton("脱退");
	JButton bt1 = new JButton("キャンセル");

	public DeleteMember() {
		this.setSize(300, 200);
		this.setTitle("会員脱退");
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
		if (e.getActionCommand().equals("脱退")) {
			String id = MemberInfo.ID;
			String password = txtf.getText();
			list = mgr.deleteMb(id, password);
			if (MemberInfo.PW.equals(password)) {
				JOptionPane.showMessageDialog(null, "脱退しました。");
				/* 열려있는 모든 창 닫기 */
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
					win[i] = null;
				}
				new Login();
			} else {
				JOptionPane.showMessageDialog(null, "パスワードを確認してください。");
			}
		} else if (e.getActionCommand().equals("キャンセル")) {
			txtf.setText(" ");
			new MyAccount();
			dispose();
		}
	}
}
