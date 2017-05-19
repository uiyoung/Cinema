package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PayByAccount extends JFrame implements ActionListener{
	Choice choice;
	JButton bt;
	JLabel accountpay, account, number, passwd;
	JTextField number1, passwdT;
	public PayByAccount(){
		this.setTitle("계좌결제");
		this.setLayout(null);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		choice = new Choice();
        choice.add("NH");
        choice.add("Shinhan");
        choice.add("KB");
        choice.add("Woori");
		
		
		bt = new JButton("입력완료");
		accountpay = new JLabel("계좌결제");
		account = new JLabel("은행선택");
		number = new JLabel("계좌번호");
		passwd = new JLabel("비밀번호");
		number1 = new JTextField(10);
		passwdT = new JTextField(20);
		
		accountpay.setFont(new Font("굴림", Font.BOLD, 20));
		
		bt.setBounds(120,180,100,30);
		accountpay.setBounds(50, 10, 150, 50);
		account.setBounds(50, 50, 100, 50);
		number.setBounds(50, 80, 100, 50);
		passwd.setBounds(50, 110, 100, 50);
		choice.setBounds(150, 65, 120, 15);
		number1.setBounds(150, 95, 100, 20);
		passwdT.setBounds(150, 125, 80, 20);
		
		
		add(bt);
		add(accountpay);
		add(choice);
		add(account);
		add(number);
		add(passwd);
		add(number1);
		add(passwdT);
		
		bt.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PayByAccount();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			dispose();
		}
		
	}    
}

