package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PayByCredit extends JFrame implements ActionListener{
	Choice choice;
	JButton bt;
	JLabel creditpay, card, number, passwd;
	JTextField number1, number2, number3, number4, passwdT;
	public PayByCredit(){
		this.setTitle("카드결제");
		this.setLayout(null);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		choice = new Choice();
        choice.add("Samsung");
        choice.add("Shinhan");
        choice.add("BC");
        choice.add("NH");
		
		
		bt = new JButton("입력완료");
		creditpay = new JLabel("카드결제");
		card = new JLabel("카드선택");
		number = new JLabel("카드번호 입력");
		passwd = new JLabel("비밀번호 입력");
		number1 = new JTextField(10);
		number2 = new JTextField(10);
		number3 = new JTextField(10);
		number4 = new JTextField(10);
		passwdT = new JTextField(20);
		
		creditpay.setFont(new Font("굴림", Font.BOLD, 20));
		
		bt.setBounds(120,180,100,30);
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
		
		
		add(bt);
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
		
		bt.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PayByCredit();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			dispose();
		}
		
	}

	    
	   
	
	
	
}
