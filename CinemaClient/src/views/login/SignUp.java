package views.login;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.DBMgr;
import models.MemberBean;

public class SignUp extends JFrame implements ActionListener {
	JLabel lblId = new JLabel("ID");
	JLabel lblPassword = new JLabel("PW");
	JLabel lblName = new JLabel("Name");
	JLabel lblBirthdate = new JLabel("Birthdate");
	JLabel lblPhone = new JLabel("Phone");

	JTextField tfId = new JTextField();
	JTextField tfPassword = new JPasswordField();
	JTextField tfName = new JTextField();
	JTextField tfBirthdate = new JTextField();
	JTextField tfPhone = new JTextField();

	JButton btnSignUp = new JButton("Sign Up");
	JButton btnCancel = new JButton("cancel");

	DBMgr mgr = new DBMgr();
	MemberBean bean;
	ArrayList<MemberBean> list;

	public SignUp() {
		list = mgr.login();

		setTitle("Sign Up");
		setResizable(false);
		setSize(400, 600);
		Container c = getContentPane();
		c.setLayout(null);

		JLabel lblTitle = new JLabel("Sign Up");
		lblTitle.setBounds(80, 30, 300, 100);
		lblTitle.setFont(new Font("Consolas", Font.BOLD, 55));

		lblId.setBounds(60, 150, 70, 30);
		lblPassword.setBounds(60, 200, 70, 30);
		lblName.setBounds(60, 250, 70, 30);
		lblBirthdate.setBounds(60, 300, 70, 30);
		lblPhone.setBounds(60, 350, 70, 30);

		tfId.setBounds(120, 150, 220, 30);
		tfPassword.setBounds(120, 200, 220, 30);
		tfName.setBounds(120, 250, 220, 30);
		tfBirthdate.setBounds(120, 300, 220, 30);
		tfPhone.setBounds(120, 350, 220, 30);

		btnSignUp.setBounds(60, 400, 280, 30);
		btnCancel.setBounds(270, 500, 80, 30);

		btnSignUp.addActionListener(this);
		btnCancel.addActionListener(this);

		c.add(lblTitle);
		c.add(lblId);
		c.add(lblPassword);
		c.add(lblName);
		c.add(lblBirthdate);
		c.add(lblPhone);
		c.add(tfId);
		c.add(tfPassword);
		c.add(tfName);
		c.add(tfBirthdate);
		c.add(tfPhone);
		c.add(btnSignUp);
		c.add(btnCancel);

		// set location in center of screen
		// Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		// Dimension dim2 = this.getSize();
		// int x, y;
		// x = (int) (dim1.getWidth() / 2 - dim2.getWidth() / 2);
		// y = (int) (dim1.getHeight() / 2 - dim2.getHeight() / 2);
		// setLocation(x, y);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSignUp) {
			String id = tfId.getText();
			String password = tfPassword.getText();
			String name = tfName.getText();
			String birthdate = tfBirthdate.getText();
			String phone = tfPhone.getText();

			for (int i = 0; i < list.size(); i++) {
				if (id.equals(list.get(i).getId())) {
					JOptionPane.showMessageDialog(null, "ID already exists");
					tfId.requestFocus();
					return;
				}
			}

			if (id.equals("")) {
				JOptionPane.showMessageDialog(null, "Please input id");
				tfId.requestFocus();
			} else if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please input password");
				tfPassword.requestFocus();
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Please input name");
				tfName.requestFocus();
			} else if (birthdate.equals("")) {
				JOptionPane.showMessageDialog(null, "Please input birthdate");
				tfBirthdate.requestFocus();
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(null, "Please input phone");
				tfPhone.requestFocus();
			} else {
				JOptionPane.showMessageDialog(null, "Your account has been created!");
				mgr.signUp(id, password, name, birthdate, phone);
				list = mgr.login();
				revalidate();
				dispose();
			}
		}

		if (e.getSource() == btnCancel)
			dispose();
	}

	public static void main(String[] args) {
		new SignUp();
	}
}
