package views.reservations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import controllers.MDBMgr;
import models.TicketBean;
import views.MFrame;
import views.MainMenu;

public class Reservations extends MFrame implements ActionListener {
	// 상위(메인) 버튼
	ButtonGroup mgroup = new ButtonGroup();
	JButton exit = new JButton("戻る");

	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel mrpanel = new JPanel();

	// reserve관련 부분
	JTable mrtable;
	JScrollPane mrpan;
	String mr[] = { "番号", "映画のタイトル", "映画館の名前", "日付", "時間", "座席番号", "価格", "アイディー" };
	MDBMgr mgr = new MDBMgr();
	ArrayList<TicketBean> list;
	TicketBean mrbean;

	public Reservations() {
		this.setTitle("予約情報");
		list = mgr.allreserve();
		init();
	}

	private void init() {
		Container mrcon = this.getContentPane();
		pan1.setBackground(Color.WHITE);
		// 상위 버튼에 색상과 폰트지정
		exit.setBackground(Color.DARK_GRAY);
		exit.setForeground(Color.white);
		exit.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		pan1.add(pan2);
		pan1.add(exit);

		// 모든 버튼에 ActionListener적용
		exit.addActionListener(this);

		// table관련

		String mrrecord[][] = new String[list.size()][mr.length];
		for (int i = 0; i < list.size(); i++) {
			mrbean = list.get(i);
			mrrecord[i][0] = mrbean.getNo();
			mrrecord[i][1] = mrbean.getTitle();
			mrrecord[i][2] = mrbean.getTheater_name();
			mrrecord[i][3] = mrbean.getDate();
			mrrecord[i][4] = mrbean.getTime();
			mrrecord[i][5] = mrbean.getSeat_no();
			mrrecord[i][6] = mrbean.getPrice();
			mrrecord[i][7] = mrbean.getUser_id();
		}
		mrtable = new JTable(mrrecord, mr);
		mrtable.setPreferredScrollableViewportSize(new Dimension(1350, 675));
		mrpan = new JScrollPane(mrtable);
		JTableHeader th = mrtable.getTableHeader();
	      th.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.BOLD, 18));
	      th.setBackground(Color.white);
	      th.setForeground(Color.DARK_GRAY);
	
		JPanel mrpanline = new JPanel(new BorderLayout());
		mrpanline.setBorder(new TitledBorder(new EtchedBorder()));
		mrpanline.add(mrpan, BorderLayout.CENTER);

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = mrtable.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		mrcon.add("North", pan1);

		mrcon.add("Center", mrpanline);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		if (button.equals(exit.getText())) {
			new MainMenu();
			dispose();
		}
	}
}