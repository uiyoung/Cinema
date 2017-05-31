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

public class PayByAu extends JFrame implements ActionListener {
	Choice choice;
	JButton bt, bt1;
	JLabel AuPay, Au, passwd;
	JTextField passwdT;

	public PayByAu() {
		this.setTitle("AuPay");
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
		AuPay = new JLabel("AuPay");
		Au = new JLabel("カード選択");
		passwd = new JLabel("パスワード");
		passwdT = new JPasswordField(20);

		AuPay.setFont(new Font("MSMincho", Font.BOLD, 20));

		bt.setBounds(100, 180, 80, 30);
		bt1.setBounds(200, 180, 80, 30);
		AuPay.setBounds(50, 10, 150, 50);
		Au.setBounds(50, 60, 100, 50);
		passwd.setBounds(50, 100, 100, 50);
		choice.setBounds(150, 75, 120, 15);
		passwdT.setBounds(150, 115, 80, 20);
		bt.addActionListener(this);
		bt1.addActionListener(this);

		add(bt);
		add(bt1);
		add(AuPay);
		add(choice);
		add(Au);
		add(passwd);
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
