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

public class UpdatePW extends CinemaFrame implements ActionListener {
	JLabel lab = new JLabel("パスワードの変更");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("現在のパスワード : ");
	JTextField txtf1 = new JTextField(10);
	JButton bt = new JButton("確認");
	JLabel lab2 = new JLabel("新しいパスワード : ");
	JTextField txtf2 = new JTextField(10);
	JPanel cpan = new JPanel();

	JButton bt1 = new JButton("変更");
	JButton bt2 = new JButton("キャンセル");
	JPanel span = new JPanel();
	DBMgr mgr = new DBMgr();
	ArrayList<MemberBean> list;

	public UpdatePW() {
		this.setSize(400, 200);
		this.setTitle("パスワードの変更");
		init();
	}

	private void init() {
		Container con = this.getContentPane();
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.white);
		pan.add(lab);

		bt.addActionListener(this);

		bt2.addActionListener(this);
		cpan.add(lab1);
		cpan.add(txtf1);
		cpan.add(bt);
		cpan.add(lab2);
		cpan.add(txtf2);
		cpan.add(bt1);
		span.add(bt2);
		txtf2.setEditable(false);

		con.add("North", pan);
		con.add("Center", cpan);
		con.add("South", span);
	}// init

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("確認")) {
			String id = MemberInfo.ID;
			String password = txtf1.getText();
			if (MemberInfo.PW.equals(password)) {
				txtf1.setEditable(false);
				bt1.addActionListener(this);
				txtf2.setEditable(true);
				list = mgr.UpdatePw(id, password);
				txtf2.setText(list.get(0).getPassword());
			} else {
				JOptionPane.showMessageDialog(null, "パスワードを確認してください。");
			}
		} else if (e.getActionCommand().equals("キャンセル")) {
			txtf1.setText("");
			new MyAccount();
			dispose();
		} else if (e.getActionCommand().equals("変更")) {
			String id = MemberInfo.ID;
			String password = txtf2.getText();
			mgr.UpdatePw2(id, password);
			MemberInfo.PW = txtf2.getText();
			JOptionPane.showMessageDialog(null, "正常に変更されました。");
			new MyAccount();
			dispose();
		}
	}
}