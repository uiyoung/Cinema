package views;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CinemaFrame extends JFrame {
	public CinemaFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setSize(1366, 768);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set location in center of screen
		// Dimension dim1 = Toolkit.getDefaultToolkit().getScreenSize();
		// Dimension dim2 = this.getSize();
		// int x, y;
		// x = (int) (dim1.getWidth() / 2 - dim2.getWidth() / 2);
		// y = (int) (dim1.getHeight() / 2 - dim2.getHeight() / 2);
		// setLocation(x, y);

		setResizable(false);
		setVisible(true);
	}
}
