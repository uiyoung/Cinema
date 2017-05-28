package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MFrame extends JFrame {
	public MFrame() {
		setSize(1366, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set location in center of screen
		Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim2 = this.getSize();
		int x, y;
		x = (int) (dim1.getWidth() / 2 - dim2.getWidth() / 2);
		y = (int) (dim1.getHeight() / 2 - dim2.getHeight() / 2);
		setLocation(x, y);

		setResizable(false);
		setVisible(true);
	}
}