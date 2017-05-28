package views.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.MDBMgr;
import models.AdminBean;
import views.MFrame;
import views.MainMenu;

public class Login extends MFrame implements ActionListener {
	ImageIcon img = new ImageIcon("main3.png");
	JLabel labimg = new JLabel(img);
	JPanel cpn = new JPanel();

	JPanel pan = new JPanel();

	JLabel idlab = new JLabel("ID  ");
	JTextField txtf = new JTextField(13);
	JLabel pwlab = new JLabel("  PW  ");
	JTextField txtf1 = new JPasswordField(13);

	JPanel pan1 = new JPanel();

	JButton login = new JButton("ログイン");
	JButton close = new JButton("取り消す");
	JPanel pan2 = new JPanel();
	MDBMgr mgr = new MDBMgr();
	ArrayList<AdminBean> list;
	AdminBean mm;

	public Login() {
		this.setTitle("管理者ログイン");
		list = mgr.login();
		init();

	}

	private void init() {
		Container con = this.getContentPane();

		idlab.setFont(new Font("HY강B", Font.BOLD, 18));
		pwlab.setFont(new Font("HY강B", Font.BOLD, 18));
		login.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.BOLD, 18));
		close.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.BOLD, 18));
		txtf.setFont(new Font("HY강B", Font.BOLD, 18));
		txtf1.setFont(new Font("serif", Font.BOLD, 18));

		// 버튼에 색상과 폰트 지정
		login.setBackground(Color.DARK_GRAY);
		login.setForeground(Color.white);
		login.setFont(new Font("serif", Font.BOLD, 18));
		close.setBackground(Color.DARK_GRAY);
		close.setForeground(Color.white);
		close.setFont(new Font("serif", Font.BOLD, 18));

		pan1.add(labimg);
		pan2.add(idlab);
		pan2.add(txtf);
		pan2.add(pwlab);
		pan2.add(txtf1);
		pan2.add(login);
		pan2.add(close);

		con.add("North", pan);
		con.add("Center", pan1);
		con.add("South", pan2);

		txtf.requestFocus();
		login.addActionListener(this);
		close.addActionListener(this);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("ログイン")) {
			String id = txtf.getText();
			String pw = txtf1.getText();
			if (id.equals(list.get(0).getId()) && pw.equals(list.get(0).getPw())) {
				new MainMenu();
				dispose();

			} else {
				String[] buttons = { "確認", "取り消す" };
				JOptionPane.showOptionDialog(null, "ログイン エラー...", "エラーメッセージ", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, buttons, "確認");
				txtf.setText("");
				txtf1.setText("");
			}
		} else {
			txtf.setText(null);
			txtf1.setText(null);
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
