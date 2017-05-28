package views.seat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.MDBMgr;
import views.MFrame;
import views.MainMenu;

public class InsertSeat extends MFrame implements ActionListener {
	MDBMgr mgr = new MDBMgr();
	String theater, seatNo, date, time;
	int numOfSeats;
	ArrayList<String> theaterList;
	ArrayList<String> dateList, timeList;
	JComboBox<String> cbTheaterList, cbDateList, cbTimeList;
	JLabel lbl1, lbl2, lbl3, lbl4;
	JButton btnInsert, btnBack;

	public InsertSeat() {
		JPanel btnPanel = new JPanel();
		btnBack = new JButton("戻る");
		btnBack.addActionListener(this);
		btnPanel.add(btnBack);

		JPanel centerPanel = new JPanel();
		/* --------------------theater combobox --------------------- */
		theaterList = mgr.getTheaterNames();
		cbTheaterList = new JComboBox<>();
		for (int i = 0; i < theaterList.size(); i++) {
			cbTheaterList.addItem(theaterList.get(i));
		}
		cbTheaterList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theater = Integer.toString(cbTheaterList.getSelectedIndex() + 1);
				System.out.println("theater : " + theater);

				cbDateList.removeAllItems();
				updateDateListItems();
			}
		});

		theater = Integer.toString(cbTheaterList.getSelectedIndex() + 1);
		System.out.println("theater : " + theater);
		/*--------------- end of theater combobox -----------------------*/

		/* ---------------------date combobox --------------------------- */
		cbDateList = new JComboBox<>();
		updateDateListItems();

		cbDateList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				date = cbDateList.getItemAt(cbDateList.getSelectedIndex());
				System.out.println("date : " + date);

				cbTimeList.removeAllItems();
				updateTimeListItmes();
			}
		});
		date = cbDateList.getItemAt(cbDateList.getSelectedIndex());
		System.out.println("date : " + date);
		/* ------------------- end of date combobox ---------------------- */

		/* ---------------------time combobox --------------------------- */
		cbTimeList = new JComboBox<>();
		updateTimeListItmes();

		cbTimeList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time = cbTimeList.getItemAt(cbTimeList.getSelectedIndex());
				System.out.println("time : " + time);
			}
		});
		time = cbTimeList.getItemAt(cbTimeList.getSelectedIndex());
		System.out.println("time : " + time);
		/* ------------------- end of date combobox ---------------------- */

		/*-------------------- num of seats combobox ----------------------*/
		JComboBox<Integer> cbNumOfSeats = new JComboBox<>();
		cbNumOfSeats.addItem(100);
		cbNumOfSeats.addItem(80);
		cbNumOfSeats.addItem(60);

		cbNumOfSeats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numOfSeats = cbNumOfSeats.getItemAt(cbNumOfSeats.getSelectedIndex());
				System.out.println("numOfSeats : " + numOfSeats);
			}
		});
		numOfSeats = cbNumOfSeats.getItemAt(cbNumOfSeats.getSelectedIndex());
		System.out.println("numOfSeats : " + numOfSeats);
		/*----------------end of num of seats combobox ----------------------*/

		centerPanel.add(cbTheaterList);
		centerPanel.add(new JLabel("극장"));
		centerPanel.add(cbDateList);
		centerPanel.add(new JLabel("일 "));
		centerPanel.add(cbTimeList);
		centerPanel.add(new JLabel("시에 "));
		centerPanel.add(cbNumOfSeats);
		centerPanel.add(new JLabel("개의 좌석을 생성합니다."));

		btnInsert = new JButton("insert");
		btnInsert.addActionListener(this);
		centerPanel.add(btnInsert);


		add(centerPanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.PAGE_START);
		setVisible(true);
	}

	private void updateDateListItems() {
		dateList = mgr.getDates(theater);
		for (int i = 0; i < dateList.size(); i++) {
			cbDateList.addItem(dateList.get(i));
		}
	}

	private void updateTimeListItmes() {
		timeList = mgr.getTimes(theater, date);
		for (int i = 0; i < timeList.size(); i++) {
			cbTimeList.addItem(timeList.get(i));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			new MainMenu();
			dispose();
		}
		if (e.getSource() == btnInsert) {
			if (mgr.isSeatInserted(theater, date, time)) {
				JOptionPane.showMessageDialog(null, theater + "극장, " + date + "일, " + time + "시에 이미 좌석이 생성되어 있습니다.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String[] row = new String[numOfSeats / 10];
			for (int i = 0; i < row.length; i++) {
				row[i] = Character.toString((char) (65 + i)); // A~I
			}

			for (int i = 0; i < numOfSeats; i++) {
				if (i % 10 <= 8)
					seatNo = row[i / 10] + "0" + (i % 10 + 1); // A01~A09, B01~B09, ...
				else
					seatNo = row[i / 10] + "10"; // A10,B10, ...

				mgr.insertSeats(theater, seatNo, date, time);
			}
			JOptionPane.showMessageDialog(null,
					theater + "극장, " + date + "일, " + time + "시에 " + numOfSeats + "개의 좌석이 생성되었습니다.", "Insert Seat Info",
					JOptionPane.DEFAULT_OPTION);
		}
	}

	public static void main(String[] args) {
		new InsertSeat();
	}
}
