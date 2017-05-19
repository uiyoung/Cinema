package views;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PayByKaKao extends JFrame implements ActionListener{
	Choice choice;
	JButton bt;
	JLabel kakaoPay, kakao, passwd;
	JTextField passwdT;
	public PayByKaKao(){
		this.setTitle("카카오결제");
		this.setLayout(null);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		choice = new Choice();
        choice.add("Samsung");
        choice.add("Shinhan");
        choice.add("BC");
        choice.add("NH");
		
		
		bt = new JButton("입력완료");
		kakaoPay = new JLabel("카카오결제");
		kakao = new JLabel("카드선택");
		passwd = new JLabel("비밀번호");
		passwdT = new JTextField(20);
		
		kakaoPay.setFont(new Font("굴림", Font.BOLD, 20));
		
		bt.setBounds(120,180,100,30);
		kakaoPay.setBounds(50, 10, 150, 50);
		kakao.setBounds(50, 60, 100, 50);
		passwd.setBounds(50, 100, 100, 50);
		choice.setBounds(150, 75, 120, 15);
		passwdT.setBounds(150, 115, 80, 20);
		
		
		add(bt);
		add(kakaoPay);
		add(choice);
		add(kakao);
		add(passwd);
		add(passwdT);
		
		bt.addActionListener(this);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new PayByKaKao();

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt){
			dispose();
		}
		
	}
	   
	
	
	
}

