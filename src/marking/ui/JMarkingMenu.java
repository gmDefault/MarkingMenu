package marking.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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
	private JLayeredPane lp;
	
	public JMarkingMenu(JLayeredPane lp) {
		x = 50;
		y = 50;
		in = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.lp = lp;
	}
	@Override

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.drawRect(0, 0, 100, 100);
		g2.fillRect(0, 0, 100, 100);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(this.getWidth()/2, this.getHeight()/2, x, y);
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
			System.out.println("Right CLick Released");
			this.x = this.getWidth()/2;
			this.y = this.getHeight()/2;
			lp.remove(this);
			lp.repaint();
		
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
