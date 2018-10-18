package marking.ui;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class JMarkingMenu extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {
		g.drawRect(10, 10, 50, 50);
		g.fillRect(0, 0, 100, 50);
		System.out.println("eee");
	}
}
