package views.theater;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controllers.MDBMgr;
import models.TheaterBean;
import views.MFrame;
import views.MainMenu;

public class ManageTheater extends MFrame implements ActionListener {
	MDBMgr mgr = new MDBMgr();
	ArrayList<TheaterBean> list;
	TheaterBean bean;

	JPanel btnPanel;
	JButton btnCreate, btnUpdate, btnDelete, btnBack;

	public ManageTheater() {
		setTitle("극장관리");
		init();
	}

	private void init() {

		list = mgr.getTheaters();
		String col[] = { "극장 번호", "극장 이름", "주소", "전화번호", "수용인원", "설명" };
		String record[][] = new String[list.size()][col.length];
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			record[i][0] = Integer.toString(bean.getNo());
			record[i][1] = bean.getName();
			record[i][2] = bean.getAddress();
			record[i][3] = bean.getPhone();
			record[i][4] = Integer.toString(bean.getCapacity());
			record[i][5] = bean.getDescription();
		}

		JTable table = new JTable(record, col);
		table.setPreferredScrollableViewportSize(new Dimension(1350, 675));
		JScrollPane scpan = new JScrollPane(table);
		JPanel panline = new JPanel(new BorderLayout());
		panline.setBorder((Border) new TitledBorder(new EtchedBorder(), "극장のリスト"));
		panline.add(scpan, BorderLayout.CENTER);

		btnPanel = new JPanel();
		btnCreate = new JButton("극장추가");
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("삭제");
		btnBack = new JButton("戻る");

		btnCreate.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnBack.addActionListener(this);

		btnPanel.add(btnCreate);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnDelete);
		btnPanel.add(btnBack);

		add(btnPanel, BorderLayout.PAGE_START);
		add(panline, BorderLayout.CENTER);
		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCreate) {
			new CreateTheater();
		}
		if (e.getSource() == btnUpdate) {
			new UpdateTheater();
		}
		if (e.getSource() == btnDelete) {
			new DeleteTheater();
		}
		if (e.getSource() == btnBack) {
			new MainMenu();
			dispose();
		}
	}

	public static void main(String[] args) {
		new ManageTheater();
	}
}
