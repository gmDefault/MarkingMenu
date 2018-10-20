package marking.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class JMarkingMenu extends JComponent implements MouseInputListener {

	MarkingMenuModel model;
	private int portion;
	private final int width = 200;
	private final int height = 200;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean in;
	private JLayeredPane lp;

	public JMarkingMenu(JLayeredPane lp, ArrayList<Element> arrayList) {
		model = new MarkingMenuModel();

		model.setElements(arrayList); 
		model.setX(100);
		model.setY(100);
		in = false;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.lp = lp;
	}

	@Override

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(200, 0, 0, 150));

		g2.fillOval(0, 0, 200, 200);

		g2.setColor(new Color(220, 0, 0, 200));

		double pas = 2 * Math.PI / model.getSize();

		g2.fillArc(0, 0, 200, 200, (int) Math.toDegrees(this.portion*pas), (int) Math.toDegrees(pas));

		g2.setColor(Color.BLACK);


		double angle=0;
		for(int i=0;i<model.getSize();i++) {
			g2.drawLine(100,100, (int) (100+100*Math.cos(angle)), (int)(100+100*Math.sin(angle)));
			angle+=pas;
		}

		g2.setColor(new Color(255, 0, 0, 255));
		g2.fillOval(85, 85, 30, 30);

		g2.setStroke(new BasicStroke(3));


		g2.drawLine(this.getWidth()/2, this.getHeight()/2, model.getX(), model.getY());
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
		model.setX(this.getWidth() / 2);
		model.setY(this.getHeight() / 2);
		lp.remove(this);
		lp.repaint();


	}

	@Override
	public void mouseDragged(MouseEvent e) {

		System.out.println(model.getX() + " " + model.getY() + " " + in + " " + e.getX() + " " + e.getY());

		this.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (in) {
			model.setX(e.getX());
			model.setY(e.getY());
		}
		Point p1 = new Point(100, 0);
		Point p2 = new Point(e.getX() - 100, e.getY() - 100);
		double np1 = Math.sqrt(p1.getX() * p1.getX() + p1.getY() * p1.getY());
		double np2 = Math.sqrt(p2.getX() * p2.getX() + p2.getY() * p2.getY());
		double prod_scal = p1.getX() * p2.getX() + p2.getY() * p1.getY();
		double angle = Math.acos(prod_scal / (np1 * np2));
		
		if (e.getY() > this.height/2) {
			angle = Math.PI + (Math.PI - angle);
		}
		// the var "angle" is the angle between the right side of the marking menu, the center of the marking menu and the position of the mouse
		// 0<angle<2*PI
		double pas = 2 * Math.PI / model.getSize();

		this.portion=(int)(angle/pas);
		this.repaint();
	}
}
