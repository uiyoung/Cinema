package views.schedule;

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
import javax.swing.JOptionPane;
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
import models.ScheduleBean;
import views.MFrame;
import views.MainMenu;

public class ManageSchedule extends MFrame implements ActionListener {
	JPanel pan1_1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel mschpan1 = new JPanel();

	MDBMgr li_mgr = new MDBMgr();

	ButtonGroup mschgroup = new ButtonGroup();
	JButton mschbt1 = new JButton("登録");// 등록
	JButton mschbt2 = new JButton("修整");// 수정
	JButton mschbt3 = new JButton("削除");// 삭제
	JButton mschbt4 = new JButton("戻る");// 돌아가기

	JTable table;
	JScrollPane mschfpan;
	String col[] = { "番号", "上映日", "上映時間", "映画番号", "劇場番号" };
	MDBMgr mgr = new MDBMgr();
	ArrayList<ScheduleBean> list;
	ScheduleBean mschbean;

	public ManageSchedule() {
		this.setTitle("上映情報");
		list = mgr.allmschedule();
		init();
	}

	private void init() {
		Container mcon = this.getContentPane();
		mcon.setBackground(Color.WHITE);
		JPanel mschpan2 = new JPanel();
		// 하위버튼의 색상과 폰트지정
		mschbt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		mschbt1.setBackground(Color.DARK_GRAY);
		mschbt1.setForeground(Color.WHITE);
		mschbt2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		mschbt2.setBackground(Color.DARK_GRAY);
		mschbt2.setForeground(Color.WHITE);
		mschbt3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		mschbt3.setBackground(Color.DARK_GRAY);
		mschbt3.setForeground(Color.WHITE);
		mschbt4.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		mschbt4.setBackground(Color.DARK_GRAY);
		mschbt4.setForeground(Color.WHITE);
		mschpan1.setBackground(Color.WHITE);
		mschpan1.add(mschbt1);
		mschpan1.add(mschbt2);
		mschpan1.add(mschbt3);
		mschpan1.add(mschbt4);

		// 모든 버튼에 ActionListener적용
		mschbt1.addActionListener(this);
		mschbt2.addActionListener(this);
		mschbt3.addActionListener(this);
		mschbt4.addActionListener(this);
		// table
		String mschrecord[][] = new String[list.size()][col.length];
		for (int i = 0; i < list.size(); i++) {
			mschbean = list.get(i);
			mschrecord[i][0] = Integer.toString(mschbean.getNo());
			mschrecord[i][1] = mschbean.getDate();
			mschrecord[i][2] = mschbean.getTime();
			mschrecord[i][3] = Integer.toString(mschbean.getMovie_no());
			mschrecord[i][4] = Integer.toString(mschbean.getTheater_no());
		}
		table = new JTable(mschrecord, col);
		table.setPreferredScrollableViewportSize(new Dimension(1350, 675));
		mschfpan = new JScrollPane(table);
		JTableHeader th = table.getTableHeader();
	      th.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.BOLD, 18));
	      th.setBackground(Color.white);
	      th.setForeground(Color.DARK_GRAY);
	
		JPanel panline = new JPanel(new BorderLayout());
		panline.setBorder(new TitledBorder(new EtchedBorder(), "上映のリスト"));
		panline.add(mschfpan, BorderLayout.CENTER);

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		mschpan2.add("Center", panline);

		mcon.add("South", mschpan2);
		mcon.add("Center", mschpan1);
		this.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (e.getActionCommand().equals("登録")) {
			new Schedule_Input();
		}
		if (e.getActionCommand().equals("修整")) {
			new Schedule_Update();
		}
		if (e.getActionCommand().equals("削除")) {
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.isRowSelected(i)) {
					// 선택된 row의 ticket_no
					int scheduleNo = Integer
							.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					String date = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					String time = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
					int theaterNo = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());

					System.out.println(scheduleNo);
					System.out.println(theaterNo + "," + date + "," + time);

					int result = JOptionPane.showConfirmDialog(null, scheduleNo + "番上映日程を削除します。", "上映日程の削除",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
					} else if (result == JOptionPane.YES_OPTION) {
						mgr.deleteSchedule(scheduleNo); // 상영정보 삭제
						mgr.deleteSeats(theaterNo, date, time); // 좌석정보 삭제

						JOptionPane.showMessageDialog(null, "上映日程と座席が削除されました。", "完了", JOptionPane.DEFAULT_OPTION);

						new ManageSchedule();
						dispose();
					}
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "削除する上映日程を選択してください。 ", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (e.getActionCommand().equals("戻る")) {
			new MainMenu();
			dispose();
		}
	}

	public static void main(String[] args) {
		new ManageSchedule();
	}
}