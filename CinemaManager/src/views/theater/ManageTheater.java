package views.theater;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

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
		setTitle("劇場管理");
		init();
	}

	private void init() {
		list = mgr.getTheaters();
		String col[] = { "劇場番号", "劇場の名前", "住所", "電話番号", "立ち見客数", "劇場情報" };
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
		
		JTableHeader th = table.getTableHeader();
	      th.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.BOLD, 18));
	      th.setBackground(Color.white);
	      th.setForeground(Color.DARK_GRAY);
		JPanel panline = new JPanel(new BorderLayout());
		//panline.setBorder((Border) new TitledBorder(new EtchedBorder(), "劇場のリスト"));
		panline.setBackground(Color.WHITE);
		panline.add(scpan, BorderLayout.CENTER);

		
		btnPanel = new JPanel();
		btnCreate = new JButton("登録");
		btnUpdate = new JButton("修正");
		btnDelete = new JButton("削除");
		btnBack = new JButton("戻る");
		// 하위버튼의 색상과 폰트지정
		btnPanel.setBackground(Color.WHITE);
		btnCreate.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnCreate.setBackground(Color.DARK_GRAY);
		btnCreate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setForeground(Color.WHITE);
		btnBack.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setForeground(Color.WHITE);
		
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
