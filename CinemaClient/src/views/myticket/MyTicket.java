package views.myticket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.DBMgr;
import models.MemberInfo;
import models.TicketBean;
import views.CinemaFrame;
import views.MainMenu;

public class MyTicket extends CinemaFrame implements ActionListener {
	DBMgr mgr = new DBMgr();
	ArrayList<TicketBean> list;
	TicketBean bean;

	JTable table;
	DefaultTableModel tableModel;
	JScrollPane sp;
	JButton btnBack, btnCancelReserve;

	public MyTicket() {
		setTitle("MyTicket");
		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		JPanel tablePanel = new JPanel(new BorderLayout());
		list = mgr.getTicketInfo(MemberInfo.ID);
		String[] columnNames = { "예매번호", "영화제목", "극장", "상영날짜", "상영시간", "좌석", "가격" };
		String[][] records = new String[list.size()][columnNames.length];
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			records[i][0] = Integer.toString(bean.getNo());
			records[i][1] = bean.getTitle();
			records[i][2] = bean.getTheater_name();
			records[i][3] = bean.getDate();
			records[i][4] = bean.getTime();
			records[i][5] = bean.getSeat_no();
			records[i][6] = bean.getPrice() + "円";
		}

		// table 수정 못하게 DefaultTableModel 사용
		tableModel = new DefaultTableModel(records, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		jTableSet();
		sp = new JScrollPane(table);
		tablePanel.setBorder(new TitledBorder(new EtchedBorder(), "예매 목록"));
		tablePanel.add(sp, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();
		btnBack = new JButton("back");
		btnBack.addActionListener(this);
		btnCancelReserve = new JButton("예매 취소");
		btnCancelReserve.addActionListener(this);
		btnPanel.add(btnBack);
		btnPanel.add(btnCancelReserve);

		add(tablePanel, BorderLayout.CENTER);
		add(btnPanel, BorderLayout.PAGE_END);
		setVisible(true);
	}

	private void jTableSet() {
		// 이동과 길이조절 여러개 선택되는 것을 방지한다.
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// column 정렬에 필요한 메서드
		DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
		cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
		cellAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer cellAlignLeft = new DefaultTableCellRenderer();
		cellAlignLeft.setHorizontalAlignment(JLabel.LEFT);

		// column별 size 조절&정렬
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(4).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(5).setPreferredWidth(5);
		table.getColumnModel().getColumn(5).setCellRenderer(cellAlignCenter);
		table.getColumnModel().getColumn(6).setPreferredWidth(5);
		table.getColumnModel().getColumn(6).setCellRenderer(cellAlignCenter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack) {
			new MainMenu();
			dispose();
		}
		if (e.getSource() == btnCancelReserve) {
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.isRowSelected(i)) {
					// 선택된 row의 ticket_no
					int ticketNo = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					String theater = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
					String date = table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
					String time = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
					String seat = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();

					System.out.println(ticketNo);
					System.out.println(theater + "," + date + "," + time + "," + seat);

					int result = JOptionPane.showConfirmDialog(null, "예매를 취소하시겠습니까?", "예매 취소",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
					} else if (result == JOptionPane.YES_OPTION) {
						// 티켓 취소
						mgr.cancelTicket(ticketNo);
						// 좌석 state='n'으로 바꾸는 코드
						mgr.cancelSeat(theater, date, time, seat);
						// TODO : 적립포인트 회수하는 코드
						dispose();
						new MyTicket();
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "취소할 예매목록을 선택해 주세요 ", "Error", JOptionPane.DEFAULT_OPTION);
		}
	}
}
