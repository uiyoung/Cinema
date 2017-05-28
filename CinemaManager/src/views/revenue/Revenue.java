package views.revenue;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import controllers.MDBMgr;
import models.RevenuBean;
import views.MFrame;
import views.MainMenu;;

public class Revenue extends MFrame implements ActionListener {

	// 메인 list버튼부분
	ButtonGroup mgroup = new ButtonGroup();
	JButton exit = new JButton("戻る");
	JPanel pan1 = new JPanel();
	JPanel pan2 = new JPanel();

	// calculate관련 부분
	JTable mctable;
	JScrollPane mcpan;
	String mc[] = { "順番", "映画のタイトル", "販売額" };
	MDBMgr mgr = new MDBMgr();
	ArrayList<RevenuBean> list;
	RevenuBean mcbean;

	// 총정산부분
	JLabel cal1 = new JLabel("総精算額 : ");

	JPanel calpan = new JPanel();

	public Revenue() {
		this.setTitle("映画精算");
		list = mgr.allcal();
		this.setSize(1400, 850);
		init();
	}

	private void init() {
		Container mccon = this.getContentPane();
		// 상위 버튼에 색상과 폰트지정
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));

		pan1.add(pan2);
		pan1.add(exit);

		calpan.add(cal1);

		exit.addActionListener(this);

		// table 관련
		JLabel cal2 = new JLabel();
		int sum = 0;
		String mcrecord[][] = new String[list.size()][mc.length];
		for (int i = 0; i < list.size(); i++) {
			mcbean = list.get(i);
			mcrecord[i][0] = i + 1 + "";
			mcrecord[i][1] = mcbean.getTitle();
			mcrecord[i][2] = Integer.parseInt(mcbean.getSold()) * Integer.parseInt(mcbean.getPrice()) + "";

			sum += Integer.parseInt(mcrecord[i][2]);
		}
		cal2.setText("" + sum);

		mctable = new JTable(mcrecord, mc);
		mctable.setPreferredScrollableViewportSize(new Dimension(1350, 675));
		mcpan = new JScrollPane(mctable);
		calpan.add(cal2);
		JPanel mcpanline = new JPanel(new BorderLayout());
		mcpanline.setBorder(new TitledBorder(new EtchedBorder()));
		mcpanline.add(mcpan, BorderLayout.CENTER);

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = mctable.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		// 총정산

		mccon.add("North", pan1);
		mccon.add("Center", mcpanline);
		mccon.add("South", calpan);

		setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (button.equals(exit.getText())) {
			new MainMenu();
			dispose();
		}
	}

	public static void main(String[] args) {
		new Revenue();
	}
}
