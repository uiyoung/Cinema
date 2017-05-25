package views.login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.DBMgr;
import models.MemberBean;
import views.CinemaMenu;

public class Login extends JFrame implements ActionListener {
	public static String staticId, staticPassword, staticName, staticBirthdate, staticPhone;
	public static int staticPoint;
	private DBMgr mgr = new DBMgr(); // DAO
	private MemberBean bean; // DTO
	private ArrayList<MemberBean> list;

	private JPanel loginPanel;
	private JLabel lblId, lblPassword;
	private JTextField tfId, tfPassword;
	private JButton btnLogin, btnExit, btnSignUp, btnFind;

	public Login() {
		setTitle("Login");
		setSize(400, 600);
		init();
	}

	private void init() {
		list = mgr.login();
		loginPanel = new JPanel(null);

		lblId = new JLabel("ID");
		lblPassword = new JLabel("PW");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblId.setBounds(80, 130, 70, 30);
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblPassword.setBounds(80, 180, 70, 30);

		tfId = new JTextField();
		tfPassword = new JPasswordField();
		tfId.setBounds(100, 150, 220, 30);
		tfId.requestFocus();
		tfPassword.setBounds(100, 200, 220, 30);

		btnLogin = new JButton("Login");
		btnExit = new JButton("Exit");
		btnSignUp = new JButton("Sign Up");
		btnFind = new JButton("Find");
		btnLogin.setBounds(100, 250, 220, 30);
		btnLogin.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnSignUp.setBounds(250, 350, 100, 40);
		btnSignUp.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnFind.setBounds(250, 400, 100, 40);
		btnFind.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnExit.setBounds(250, 450, 100, 40);
		btnExit.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnLogin.addActionListener(this);
		btnSignUp.addActionListener(this);
		btnFind.addActionListener(this); // id/pw 찾기
		btnExit.addActionListener(this);

		loginPanel.add(lblId);
		loginPanel.add(lblPassword);
		loginPanel.add(tfId);
		loginPanel.add(tfPassword);
		loginPanel.add(btnLogin);
		loginPanel.add(btnExit);
		loginPanel.add(btnSignUp);
		loginPanel.add(btnFind);
		add(loginPanel);

		// set location in center of screen
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim2 = this.getSize();
		int x, y;
		x = (int) (dim1.getWidth() / 2 - dim2.getWidth() / 2);
		y = (int) (dim1.getHeight() / 2 - dim2.getHeight() / 2);
		setLocation(x, y);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();

		if (str.equals("Login")) {
			list = mgr.login();
			String id = tfId.getText();
			String pw = tfPassword.getText();
			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getId()) && pw.equals(list.get(i).getPassword())) {
					staticId = list.get(i).getId();
					staticPassword = list.get(i).getPassword();
					staticName = list.get(i).getName();
					staticBirthdate = list.get(i).getBirthdate();
					staticPhone = list.get(i).getPhone();
					staticPoint = list.get(i).getPoint();
					JOptionPane.showMessageDialog(null, staticName + "님 환영합니다.", "Login", JOptionPane.DEFAULT_OPTION);

					new CinemaMenu();
					dispose();

					return;
				}
			}
			JOptionPane.showMessageDialog(null, "wrong ID or PW", "Error", JOptionPane.DEFAULT_OPTION);
			tfId.setText("");
			tfPassword.setText("");
			tfId.requestFocus();
		}

		if (e.getSource() == btnSignUp) {
			new SignUp();
		}

		if (e.getSource() == btnFind) {
			new Find();
		}

		if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
