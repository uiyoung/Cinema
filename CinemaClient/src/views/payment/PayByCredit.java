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

public class PayByCredit extends JFrame implements ActionListener{
	Choice choice;
	JButton bt, bt1;
	JLabel creditpay, card, number, passwd;
	JTextField number1, number2, number3, number4, passwdT;
	public PayByCredit(){
		this.setTitle("CreditPay");
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
		creditpay = new JLabel("クレジットカード");
		card = new JLabel("カード選択");
		number = new JLabel("カード番号");
		passwd = new JLabel("パスワード");
		number1 = new JTextField(10);
		number2 = new JTextField(10);
		number3 = new JTextField(10);
		number4 = new JTextField(10);
		passwdT = new JPasswordField(20);
		
		creditpay.setFont(new Font("MSMincho", Font.BOLD, 20));
		
		bt.setBounds(100,180,80,30);
		bt1.setBounds(200,180,80,30);
		creditpay.setBounds(50, 10, 150, 50);
		card.setBounds(50, 50, 100, 50);
		number.setBounds(50, 80, 100, 50);
		passwd.setBounds(50, 110, 100, 50);
		choice.setBounds(150, 65, 120, 15);
		number1.setBounds(150, 95, 40, 20);
		number2.setBounds(200, 95, 40, 20);
		number3.setBounds(250, 95, 40, 20);
		number4.setBounds(300, 95, 40, 20);
		passwdT.setBounds(150, 125, 80, 20);
		
		bt.addActionListener(this);
		bt1.addActionListener(this);
		
		add(bt);
		add(bt1);
		add(creditpay);
		add(choice);
		add(card);
		add(number);
		add(passwd);
		add(number1);
		add(number2);
		add(number3);
		add(number4);
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
		if(e.getSource()==bt){
			dispose();
		}
		if(e.getSource()==bt1){
			dispose();
		}
	}
}

