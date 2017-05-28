package views.movie;

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
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import controllers.MDBMgr;
import models.MovieBean;
import views.MFrame;
import views.MainMenu;

public class ManageMovie extends MFrame implements ActionListener {
	JPanel pan1_1 = new JPanel();
	JPanel pan2 = new JPanel();
	JPanel moviepan1 = new JPanel();

	MDBMgr li_mgr = new MDBMgr();

	ButtonGroup mschgroup = new ButtonGroup();
	JButton bt = new JButton("登録");
	JButton bt1 = new JButton("修正");
	JButton bt2 = new JButton("削除");
	JButton bt3 = new JButton("戻る");

	JTable table;
	JScrollPane scpan;
	String col[] = { "映画のタイトル", "映画のジャンル", "タイプ", "監督", "出演", "公開日", "上映時間", "ストーリー" };
	MDBMgr mgr = new MDBMgr();
	ArrayList<MovieBean> list;
	MovieBean bean;

	public ManageMovie() {
		this.setTitle("映画情報");
		list = mgr.allmovie();
		init();
	}

	public ManageMovie(ArrayList<MovieBean> list) {
		this.setTitle("映画情報");
		this.list = list;
		init();
	}

	private void init() {
		Container con = this.getContentPane();

		JPanel moviepan2 = new JPanel();
		// 하위버튼의 색상과 폰트지정
		bt.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt.setBackground(Color.DARK_GRAY);
		bt.setForeground(Color.WHITE);
		bt1.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt1.setBackground(Color.DARK_GRAY);
		bt1.setForeground(Color.WHITE);
		bt2.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt2.setBackground(Color.DARK_GRAY);
		bt2.setForeground(Color.WHITE);
		bt3.setFont(new Font("EPSON 太丸ゴシック体Ｂ", Font.PLAIN, 18));
		bt3.setBackground(Color.DARK_GRAY);
		bt3.setForeground(Color.WHITE);

		moviepan1.add(bt);
		moviepan1.add(bt1);
		moviepan1.add(bt2);
		moviepan1.add(bt3);

		// 모든 버튼에 ActionListener적용
		bt.addActionListener(this);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		// table
		String record[][] = new String[list.size()][col.length];
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			record[i][0] = bean.getTitle();
			record[i][1] = bean.getGenre();
			record[i][2] = bean.getType();
			record[i][3] = bean.getDirector();
			record[i][4] = bean.getCast();
			record[i][5] = bean.getReleaseDate();
			record[i][6] = bean.getRunningTime();
			record[i][7] = bean.getDescription();
		}
		table = new JTable(record, col);
		table.setPreferredScrollableViewportSize(new Dimension(1350, 675));
		scpan = new JScrollPane(table);
		JPanel panline = new JPanel(new BorderLayout());
		panline.setBorder((Border) new TitledBorder(new EtchedBorder(), "映画のリスト"));
		panline.add(scpan, BorderLayout.CENTER);

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		moviepan2.add("Center", panline);

		con.add("South", moviepan2);
		con.add("Center", moviepan1);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (e.getActionCommand().equals("登録")) {
			new Movie_Input();
		} else if (e.getActionCommand().equals("修正")) {
			new Movie_Update();
		} else if (e.getActionCommand().equals("削除")) {
			new Movie_Delete();
		} else if (e.getActionCommand().equals("戻る")) {
			new MainMenu();
			dispose();
		}
	}
}