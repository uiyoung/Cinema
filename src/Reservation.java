import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Reservation extends CinemaFrame implements ActionListener, MouseListener {
	DBMgr dbMgr = new DBMgr();
	MovieMgr movieMgr = new MovieMgr();
	ArrayList<MovieBean> list;
	CalendarPanel calendarPanel = new CalendarPanel();

	JList jList1;
	ImageIcon poster;
	JLabel lblPoster = new JLabel();
	JLabel lblTitle = new JLabel();
	JLabel lblDate = new JLabel();
	// TODO:예매 정보 : 영화제목, 일자, 시간, 인원, 금액 나오는 라벨 만들기
	JButton btnCancel = new JButton("Cancel");
	JButton btnSeat = new JButton("Select Seat");

	public Reservation() {
		setTitle("Reservation");
		setLayout(null);
		// TODO : gridlayout(1,3)으로 할까?

		MovieBean movieBean;
		list = movieMgr.getMovie();

		// JList for select movie
		String[] col = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			col[i] = list.get(i).getTitle();
		}
		jList1 = new JList<String>(col);
		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.addMouseListener(this);

		JScrollPane sp = new JScrollPane(jList1);
		sp.setBounds(40, 95, 300, 200);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		btnCancel.setBounds(30, 650, 130, 65);
		btnSeat.setBounds(1200, 650, 130, 65);
		btnCancel.addActionListener(this);
		btnSeat.addActionListener(this);

		// Calendar for select date
		calendarPanel.setBounds(500, 30, 350, 400);
		add(calendarPanel);

		add(sp);
		add(lblPoster);
		add(lblTitle);
		add(btnCancel);
		add(btnSeat);
		repaint();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSeat) {
			// new Seats();
		}

		if (e.getSource() == btnCancel) {
			new MainMenu();
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		poster = new ImageIcon("images/" + jList1.getSelectedValue() + ".jpg");

		lblPoster.setIcon(poster);
		lblPoster.setBounds(1000, 30, 320, 452);

		lblTitle.setText(list.get(jList1.getSelectedIndex()).getTitle());
		lblTitle.setBounds(1000, 450, 300, 100);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) {
		new Reservation();
	}
}
