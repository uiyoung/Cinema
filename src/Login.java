import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	JTextField tfId = new JTextField();
	JTextField tfPassword = new JPasswordField();
	JLabel lblId = new JLabel("ID");
	JLabel lblPassword = new JLabel("PW");
	JButton btnLogin = new JButton("Login");
	JButton btnCancel = new JButton("Cancel");
	JButton btnSignUp = new JButton("Sign Up");
	JButton btnFind = new JButton("Find");
	JPanel loginPanel = new JPanel();

	static String staticId;
	static String staticName;
	static String staticPhone;
	static String staticPassword;

	DBMgr mgr = new DBMgr(); // DAO
	MemberBean bean; // DTO
	ArrayList<MemberBean> list;

	public Login() {
		list = mgr.login();

		ImageIcon icon = new ImageIcon("images/login.gif");
		JLabel logo = new JLabel();
		logo.setBounds(0, 0, 1100, 800);
		logo.setIcon(icon);

		loginPanel.setLayout(null);

		lblId.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblId.setBounds(80, 130, 70, 30);
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblPassword.setBounds(80, 180, 70, 30);

		tfId.setBounds(100, 150, 220, 30);
		tfPassword.setBounds(100, 200, 220, 30);

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
		loginPanel.add(logo);
		add(loginPanel);
		tfId.requestFocus();

		setSize(400, 600);
		setTitle("Login");
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
					staticPhone = list.get(i).getPhone();
					staticName = list.get(i).getName();
					staticPassword = list.get(i).getPassword();
					JOptionPane.showMessageDialog(null, staticName + "님 환영합니다.", "Login", JOptionPane.DEFAULT_OPTION);
					new Reservation();
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
