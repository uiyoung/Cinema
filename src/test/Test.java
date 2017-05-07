package test;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.DBMgr;
import controllers.MovieMgr;
import models.MovieBean;
import views.CinemaFrame;

public class Test extends CinemaFrame {
	DBMgr dbMgr = new DBMgr();
	MovieMgr movieMgr = new MovieMgr();
	String title;

	ArrayList<MovieBean> movies;
	ArrayList<String> schedules;
	JList<String> listMovie, listSchedule;
	DefaultListModel<String> schedulesListModel;

	public Test() {
		setLayout(new FlowLayout());

		// first JList
		movies = movieMgr.getMovie();
		DefaultListModel<String> moviesListModel = new DefaultListModel<>();
		for (int i = 0; i < movies.size(); i++) {
			moviesListModel.addElement(movies.get(i).getTitle());
		}
		listMovie = new JList<>(moviesListModel);
		listMovie.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					title = listMovie.getSelectedValue();

					schedulesListModel.removeAllElements();
					schedules = dbMgr.getSchedule(title, "170502");
					for (int i = 0; i < schedules.size(); i++) {
						schedulesListModel.addElement(schedules.get(i));
					}
				}
			}
		});
		JScrollPane sp1 = new JScrollPane(listMovie);
		add(sp1);

		// second JList
		schedulesListModel = new DefaultListModel<>();
		listSchedule = new JList<>(schedulesListModel);
		listSchedule.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && listSchedule.hasFocus()) {
					System.out.println(listSchedule.getSelectedValue());
				}
			}
		});
		JScrollPane sp2 = new JScrollPane(listSchedule);
		add(sp2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Test();
	}

}
