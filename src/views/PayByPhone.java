package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PayByPhone extends JFrame implements ActionListener{
	Choice choice;
	JButton bt;
	JLabel phonepay, phone, number, passwd;
	JTextField number1, number2, number3, number4, passwdT;
	public PayByPhone(){
		this.setTitle("핸드폰결제");
		this.setLayout(null);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		choice = new Choice();
        choice.add("SKT");
        choice.add("KT");
        choice.add("LGU+");
		
		bt = new JButton("입력완료");
		phonepay = new JLabel("핸드폰결제");
		phone = new JLabel("통신사");
		number = new JLabel("핸드폰번호");
		passwd = new JLabel("결제비밀번호");
		number1 = new JTextField(10);
		number2 = new JTextField(10);
		number3 = new JTextField(10);
		passwdT = new JTextField(20);
		
		phonepay.setFont(new Font("굴림", Font.BOLD, 20));
		
		bt.setBounds(120,180,100,30);
		phonepay.setBounds(50, 10, 150, 50);
		phone.setBounds(50, 50, 100, 50);
		number.setBounds(50, 80, 100, 50);
		passwd.setBounds(50, 110, 100, 50);
		choice.setBounds(150, 65, 120, 15);
		number1.setBounds(150, 95, 40, 20);
		number2.setBounds(200, 95, 40, 20);
		number3.setBounds(250, 95, 40, 20);
		passwdT.setBounds(150, 125, 80, 20);
		
		
		add(bt);
		add(phonepay);
		add(choice);
		add(phone);
		add(number);
		add(passwd);
		add(number1);
		add(number2);
		add(number3);
		add(passwdT);
		
		bt.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PayByPhone();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			dispose();
		}
		
	}    
	   
	
	
	
}

