package marking.ui;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class JMarkingMenu extends JComponent implements MouseInputListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Boolean in;
	
	public JMarkingMenu() {
		x = 0;
		y = 0;
		in = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	@Override

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.drawRect(10, 10, 50, 50);
		g2.fillRect(0, 0, 100, 50);
		System.out.println("eee");
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(0, 0, x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
			System.out.println("Click");		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.in = true;

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.in = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	
		System.out.println(x + " " + y + " " + in + " " + e.getX() + " " + e.getY());	

		this.repaint();		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(in) {
			this.x = e.getX();
			this.y = e.getY();
		}
		this.repaint();
	}
}
