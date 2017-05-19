package views;

import java.awt.Font;
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

public class Login extends JFrame implements ActionListener {
	static String staticId, staticPassword, staticName, staticBirthdate, staticPhone;
	DBMgr mgr = new DBMgr(); // DAO
	MemberBean bean; // DTO
	ArrayList<MemberBean> list;

	JPanel loginPanel;
	JLabel lblId, lblPassword;
	JTextField tfId, tfPassword;
	JButton btnLogin, btnCancel, btnSignUp, btnFind;

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
		btnCancel = new JButton("Cancel");
		btnSignUp = new JButton("Sign Up");
		btnFind = new JButton("Find");
		btnLogin.setBounds(100, 250, 220, 30);
		btnLogin.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnSignUp.setBounds(250, 350, 100, 40);
		btnSignUp.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnFind.setBounds(250, 400, 100, 40);
		btnFind.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnCancel.setBounds(250, 450, 100, 40);
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnLogin.addActionListener(this);
		btnSignUp.addActionListener(this);
		btnFind.addActionListener(this); // id/pw 찾기
		btnCancel.addActionListener(this);

		loginPanel.add(lblId);
		loginPanel.add(lblPassword);
		loginPanel.add(tfId);
		loginPanel.add(tfPassword);
		loginPanel.add(btnLogin);
		loginPanel.add(btnCancel);
		loginPanel.add(btnSignUp);
		loginPanel.add(btnFind);
		add(loginPanel);
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
					staticId = id;
					staticPassword = list.get(i).getPassword();
					staticName = list.get(i).getName();
					staticBirthdate = list.get(i).getBirthdate();
					staticPhone = list.get(i).getPhone();
					JOptionPane.showMessageDialog(null, staticName + "님 환영합니다.", "Login", JOptionPane.DEFAULT_OPTION);

					new MainMenu();
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
			// new Find();
		}

		if (e.getSource() == btnCancel) {
			dispose();
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
