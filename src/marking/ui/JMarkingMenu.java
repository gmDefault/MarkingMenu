package marking.ui;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.event.MouseInputListener;

public class JMarkingMenu extends JComponent implements MouseInputListener {

	MarkingMenuModel model;
	private int portion;
	private final int width = 200;
	private final int height = 200;
	private int state = 0;
	private int choice1;
	private Boolean changeTool;
	private Boolean changeColor = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean in;
	private JLayeredPane lp;

	public JMarkingMenu(JLayeredPane lp, ArrayList<Element> arrayColor, ArrayList<Element> arrayTool) {
		model = new MarkingMenuModel();
		changeTool = false;
		model.setElements(arrayColor, arrayTool);
		model.setX(100);
		model.setY(100);
		in = false;
		this.lp = lp;
	}

	@Override

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;


		double pas;
		if (state == 0) {
			pas=Math.PI;
			g2.setColor(new Color(200, 0, 0, 150));

			g2.fillOval(0, 0, 200, 200);

			g2.setColor(new Color(200, 0, 0, 200));
			g2.fillArc(0, 0, 200, 200, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));

			g2.setColor(Color.BLACK);

			g2.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			g2.drawString("COLORS", 65 , 60);
			
			g2.drawString("TOOLS", 70 , 145);

			g2.setColor(Color.BLACK);
			pas = Math.PI;

			double angle = 0;
			for (int i = 0; i <2 ; i++) {
				g2.drawLine(100, 100, (int) (100 + 100 * Math.cos(angle)), (int) (100 + 100 * Math.sin(angle)));
				angle += pas;
			}
		}
		
		else {
			
			if(this.choice1 ==0) {
				//partie haute du premier Marking Menu -> Colors
				pas = 2 * Math.PI / model.getSizeColor();
				
				g2.setColor(this.model.getArrayColor().get(this.portion).getColor());
				
				g2.fillArc(0, 0, 200, 200, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));
				
				for (int i = 0; i < model.getSizeColor(); i++) {
					Color c = this.model.getArrayColor().get(i).getColor();
					g2.setColor(new Color(c.getRed(),c.getGreen(),c.getBlue(),175));
					g2.fillArc(0, 0, 200, 200, (int) Math.toDegrees(i * pas), (int) Math.toDegrees(pas));
				}
				
				g2.setColor(Color.BLACK);

				double angle = 0;
				for (int i = 0; i < model.getSizeColor(); i++) {
					g2.drawLine(100, 100, (int) (100 + 100 * Math.cos(angle)), (int) (100 + 100 * Math.sin(angle)));
					angle += pas;
				}
			}

			else {
				//partie basse du premier Marking Menu -> Tools
				pas = 2 * Math.PI / model.getSizeTool();
				
				g2.setColor(new Color(200, 0, 0, 150));

				g2.fillOval(0, 0, 200, 200);

				g2.setColor(new Color(200, 0, 0, 200));
				g2.fillArc(0, 0, 200, 200, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));
				
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
				AffineTransform orig = g2.getTransform();
				
				double angle = 0;
				for (int i = 0; i < model.getSizeTool(); i++) {
					g2.drawLine(100, 100, (int) (100 + 100 * Math.cos(angle)), (int) (100 + 100 * Math.sin(angle)));
					angle += pas;
				}
				
				g2.translate(100.0, 100.0);
				g2.rotate(-pas/2+pas);

				for (int i = 0; i < model.getSizeTool(); i++) {
					g2.rotate(-pas);
					g2.drawString(model.getArrayTool().get(i).getName(),50 ,0 );
				}
				g2.setTransform(orig);
			}
		}

		g2.setColor(new Color(255, 0, 0, 255));
		g2.fillOval(85, 85, 30, 30);

		g2.setStroke(new BasicStroke(3));

		g2.setColor(new Color(255, 0, 0, 255));

		g2.drawLine(this.getWidth() / 2, this.getHeight() / 2, model.getX(), model.getY());

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
		model.setX(this.getWidth() / 2);
		model.setY(this.getHeight() / 2);
		if(state == 0) {
			choice1 = this.portion;
		}
		if(state==1 && choice1 == 0) {
			this.setChangeColor(true);
		}
		else if(state==1 && choice1 == 1) {
			this.setChangeTool(true);
		}
		state = state == 0 ? ++state : 0;
		System.out.println(state);
		if (state == 1) {
			this.setBounds((int) this.getLocation().getX() + e.getX() - 100,
					(int) this.getLocation().getY() + e.getY() - 100, 200, 200);
			lp.repaint();
		} else {
			lp.remove(this);
		}
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

		if (e.getY() > this.height / 2) {
			angle = Math.PI + (Math.PI - angle);
		}
		// the var "angle" is the angle between the right side of the marking menu, the
		// center of the marking menu and the position of the mouse
		// 0<angle<2*PI
		double pas;
		if(state==0) {
			pas = Math.PI;
		}else {
			if(choice1==0) {
				// colors
				pas = 2*Math.PI / model.getSizeColor();
			}
			else {
				pas = 2*Math.PI / model.getSizeTool();

			}
		}
		this.portion = (int) (angle / pas);
		this.repaint();
	}
	
	public Element getElement() {
		Element e ;
		if(this.choice1==0) {
			e = model.getColors().get(portion);
		}
		else {
			e = model.getTools().get(portion);
		}
		return e;
	}
	

	public Boolean getChangeColor() {
		return changeColor;
	}

	public void setChangeColor(Boolean changeColor) {
		this.changeColor = changeColor;
	}

	public Boolean getChangeTool() {
		return changeTool;
	}

	public void setChangeTool(Boolean changeTool) {
		this.changeTool = changeTool;
	}


}
