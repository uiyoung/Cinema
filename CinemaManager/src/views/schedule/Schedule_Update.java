package views.schedule;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import controllers.MDBMgr;
import models.ScheduleBean;
import views.MFrame;

public class Schedule_Update extends MFrame implements ActionListener {
	JLabel lab = new JLabel("上映情報の修正");
	// JTextField txtf = new JTextField(10);
	JButton btnSearch = new JButton("照会");
	JPanel pan = new JPanel();

	JLabel lab1 = new JLabel("番号");
	JLabel lab2 = new JLabel("上映日");
	JLabel lab3 = new JLabel("上映時間");
	JLabel lab4 = new JLabel("映画番号");
	JLabel lab5 = new JLabel("劇場番号");

	JTextField txtf1 = new JTextField(10);
	JTextField txtf2 = new JTextField(10);
	JTextField txtf3 = new JTextField(10);
	JTextField txtf4 = new JTextField(10);
	JTextField txtf5 = new JTextField(10);
	JPanel pan1 = new JPanel();

	JButton bt1 = new JButton("修整");
	JButton bt2 = new JButton("取り消す");

	JTable mftable;
	JScrollPane Scpan;
	String col[] = { "番号", "上映日", "映画番号", "映画番号", "劇場番号" };
	MDBMgr mgr = new MDBMgr();// DAO
	ArrayList<ScheduleBean> list;
	ScheduleBean fbean;
	private int scheduleNo, theaterNo;
	private String date, time;

	public Schedule_Update() {
		this.setTitle("上映情報の修正");
		mftable = new JTable();
		JTableHeader th = mftable.getTableHeader();
		th.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		th.setBackground(Color.darkGray);
		th.setForeground(Color.white);
		this.setSize(550, 550);
		init();
	}

	public void init() {
		Container con = this.getContentPane();
		con.setLayout(null);
		pan.setBackground(Color.DARK_GRAY);
		lab.setForeground(Color.WHITE);
		pan.add(lab);

		// 버튼의 색상과 폰트지정
		btnSearch.setBackground(Color.DARK_GRAY);
		bt1.setBackground(Color.DARK_GRAY);
		bt2.setBackground(Color.DARK_GRAY);
		btnSearch.setForeground(Color.WHITE);
		bt1.setForeground(Color.WHITE);
		bt2.setForeground(Color.WHITE);

		// 라벨과 버튼에 대한 폰트지정
		lab.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		lab5.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnSearch.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		pan.setBounds(0, 0, 550, 35);

		// txtf.setBounds(100, 75, 200, 25);
		/* combobox for select theater_no */
		ArrayList<Integer> scheduleNoList = mgr.getScheduleNo();
		JComboBox<Integer> cbScheduleNo = new JComboBox<>();
		for (int i = 0; i < scheduleNoList.size(); i++) {
			cbScheduleNo.addItem(scheduleNoList.get(i));
		}
		scheduleNo = (int) cbScheduleNo.getSelectedItem(); // default value
		cbScheduleNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scheduleNo = (int) cbScheduleNo.getSelectedItem();
				System.out.println(scheduleNo);
			}
		});
		cbScheduleNo.setBounds(100, 75, 200, 25);
		btnSearch.setBounds(340, 70, 80, 35);

		lab1.setBounds(50, 125, 150, 50);
		txtf1.setBounds(200, 140, 250, 25);
		txtf1.setEditable(false);

		lab2.setBounds(50, 170, 150, 50);
		txtf2.setBounds(200, 185, 250, 25);

		lab3.setBounds(50, 215, 150, 50);
		txtf3.setBounds(200, 230, 250, 25);

		lab4.setBounds(50, 260, 150, 50);
		txtf4.setBounds(200, 275, 250, 25);

		lab5.setBounds(50, 305, 150, 50);
		txtf5.setBounds(200, 320, 250, 25);

		bt1.setBounds(100, 400, 120, 40);
		bt2.setBounds(300, 400, 120, 40);

		con.add(pan);

		// con.add(txtf);
		con.add(cbScheduleNo);
		con.add(btnSearch);
		con.add(lab1);
		con.add(txtf1);
		con.add(lab2);
		con.add(txtf2);
		con.add(lab3);
		con.add(txtf3);
		con.add(lab4);
		con.add(txtf4);
		con.add(lab5);
		con.add(txtf5);
		con.add(bt1);
		con.add(bt2);

		btnSearch.addActionListener(this);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			list = mgr.getScheduleInfo(scheduleNo);

			if (list.size() == 0) {
				JOptionPane.showMessageDialog(null, scheduleNo + "番上映番号が存在しません。", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			theaterNo = list.get(0).getTheater_no();
			date = list.get(0).getDate();
			time = list.get(0).getTime();

			txtf1.setText(Integer.toString(list.get(0).getNo()));
			txtf2.setText(date);
			txtf3.setText(time);
			txtf4.setText(Integer.toString(list.get(0).getMovie_no()));
			System.out.println(list.get(0).getMovie_no());
			txtf5.setText(Integer.toString(list.get(0).getTheater_no()));

		} else if (e.getSource() == bt1) {
			int no = Integer.parseInt(txtf1.getText());
			String newDate = txtf2.getText();
			String newTime = txtf3.getText();
			String newMovieNo = txtf4.getText();
			String newTheaterNo = txtf5.getText();

			mgr.updateSchedule(newDate, newTime, newMovieNo, newTheaterNo, no);
			mgr.updateSeats(Integer.parseInt(newTheaterNo), newDate, newTime, theaterNo, date, time);

			JOptionPane.showMessageDialog(null, no + "番上映情報が修正されました。", "メッセージ", JOptionPane.DEFAULT_OPTION);
			/* 열려있는 모든 창 닫기 */
			java.awt.Window win[] = java.awt.Window.getWindows();
			for (int i = 0; i < win.length; i++) {
				win[i].dispose();
				win[i] = null;
			}
			new ManageSchedule();

		} else if (e.getSource() == bt2) {
			dispose();
		}
	}
}
