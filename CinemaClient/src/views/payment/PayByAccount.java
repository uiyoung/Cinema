package views.payment;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PayByAccount extends JFrame implements ActionListener {
	Choice choice;
	JButton bt, bt1;
	JLabel accountpay, account, number, passwd;
	JTextField number1, passwdT;

	public PayByAccount() {
		this.setTitle("AccountPay");
		this.setLayout(null);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		choice = new Choice();
		choice.add("Rakuten");
        choice.add("Yahoo");
        choice.add("ANA");
        choice.add("Parco");

		bt = new JButton("完了");
		bt1 = new JButton("取り消し");
		accountpay = new JLabel("AccountPay");
		account = new JLabel("決済口座");
		number = new JLabel("口座選択");
		passwd = new JLabel("パスワード");
		number1 = new JTextField(10);
		passwdT = new JPasswordField(20);

		accountpay.setFont(new Font("MSMincho", Font.BOLD, 20));

		bt.setBounds(100, 180,80, 30);
		bt1.setBounds(200, 180, 80, 30);
		accountpay.setBounds(50, 10, 150, 50);
		account.setBounds(50, 50, 100, 50);
		number.setBounds(50, 80, 100, 50);
		passwd.setBounds(50, 110, 100, 50);
		choice.setBounds(150, 65, 120, 15);
		number1.setBounds(150, 95, 100, 20);
		passwdT.setBounds(150, 125, 80, 20);
		bt.addActionListener(this);
		bt1.addActionListener(this);
		
		add(bt);
		add(bt1);
		add(accountpay);
		add(choice);
		add(account);
		add(number);
		add(passwd);
		add(number1);
		add(passwdT);

		// set location in center of screen
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim2 = this.getSize();
		int x, y;
		x = (int) (dim1.getWidth() / 2 - dim2.getWidth() / 2);
		y = (int) (dim1.getHeight() / 2 - dim2.getHeight() / 2);
		setLocation(x, y);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt) {
			dispose();
		}
		if (e.getSource() == bt1) {
			dispose();
		}
		
	}
}
