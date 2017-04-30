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

	ImageIcon poster;
	JLabel posterLabel = new JLabel();
	JList jList1;

	JButton btnCancel = new JButton("Cancel");
	JButton btnSeat = new JButton("Select Seat");

	public Reservation() {
		setTitle("Reservation");
		setLayout(null);

		MovieBean movieBean;
		list = movieMgr.getMovie();
		String[] col = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			col[i] = list.get(i).getTitle();
		}

		jList1 = new JList<String>(col);
		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.addMouseListener(this);

		JScrollPane sp = new JScrollPane(jList1);
		sp.setBounds(40, 95, 300, 200);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		btnCancel.setBounds(30, 650, 130, 65);
		btnSeat.setBounds(1200, 650, 130, 65);
		btnCancel.addActionListener(this);
		btnSeat.addActionListener(this);

		add(sp);
		add(posterLabel);
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

		posterLabel.setIcon(poster);
		posterLabel.setBounds(1000, 30, 320, 452);
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
