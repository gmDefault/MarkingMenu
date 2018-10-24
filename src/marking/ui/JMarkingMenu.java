package marking.ui;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.event.MouseInputListener;

public class JMarkingMenu extends JComponent implements MouseInputListener {

	// the model of JMarking Menu
	MarkingMenuModel model;
	// portion is the position of the mouse in the marking menu (if there are 6 items, 0<=portion<=5, and zero correspond to the first item (0 rad and turnung clockwise))
	private int portion;
	//the width in pixels of the MarkingMenu
	private final int width = 200;
	//the height in pixels of the MarkingMenu
	private final int height = 200;
	//the radius in pixels of the MarkingMenu
	private final int radius = 100;
	// state indicates if we are in the first (0) or the second marking menu (1)
	private int state = 0;
	//choice1 indicates if we selected colors (0) or tools (1) in the first menu
	private int choice1;
	//lightRed is the background color of the marking Menu
	private Color lightRed = new Color(200, 0, 0, 150);
	//deepRed is the color of selected item in the marking Menu
	private Color deepRed = new Color(200, 0, 0, 200);
	//fontFirstMenu is the font of the first MarkingMenu (to select between Colors and Tools)
	private Font fontFirstMenu = new Font("TimesRoman", Font.BOLD, 15);
	// fontTools is the font used in the tools menu
	private Font fontTools= new Font("TimesRoman", Font.BOLD, 12);

	private Boolean changeTool;
	private Boolean changeColor = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean in;
	private JLayeredPane lp;

	// Filling the JMarkingMenu with elements (tools and colors)
	public JMarkingMenu(JLayeredPane lp, ArrayList<Element> arrayColor, ArrayList<Element> arrayTool) {
		model = new MarkingMenuModel();
		changeTool = false;
		model.setElements(arrayColor, arrayTool);
		model.setX(100);
		model.setY(100);
		in = false;
		this.lp = lp;
	}

	// To draw the component
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		double pas;
		// Draw the first menu 
		if (state == 0) {
			pas = Math.PI;
			g2.setColor(lightRed);
			//draw the background circle
			g2.fillOval(0, 0, width, height);

			g2.setColor(deepRed);
			//draw the selected portion
			g2.fillArc(0, 0, width, height, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));

			g2.setColor(Color.BLACK);

			g2.setFont(fontFirstMenu);
			g2.drawString("COLORS", (int)(0.65*radius), (int)(0.60*radius));

			g2.drawString("TOOLS",(int)( 0.70*radius), (int) (radius+0.45*radius));


			g2.setColor(Color.BLACK);
			pas = Math.PI;

			// draw the black lines to separate portions
			double angle = 0;
			for (int i = 0; i < 2; i++) {
				g2.drawLine(width/2, height/2, (int) (radius+ radius * Math.cos(angle)), (int) (radius + radius * Math.sin(angle)));
				angle += pas;
			}
		}

		else {
			// draw the second menu
			if (this.choice1 == 0) {
				//  Colors
				int size = model.getSizeColor();
				// we have a limitation to 7 items in our marking menu
				if (size > 7) {
					size = 7;
				}
				pas = 2 * Math.PI / size;

				g2.setColor(this.model.getArrayColor().get(this.portion).getColor());

				g2.fillArc(0, 0, width, height, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));

				for (int i = 0; i < size; i++) {
					Color c = this.model.getArrayColor().get(i).getColor();
					g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 175));
					g2.fillArc(0, 0, width, height, (int) Math.toDegrees(i * pas), (int) Math.toDegrees(pas));
				}

				g2.setColor(Color.BLACK);

				double angle = 0;
				for (int i = 0; i < size; i++) {
					g2.drawLine(width/2, height/2, (int) (radius + radius * Math.cos(angle)), (int) (radius + radius * Math.sin(angle)));
					angle += pas;
				}
			}

			else {
				// Tools
				int size = model.getSizeTool();
				if (size > 7) {
					size = 7;
				}
				pas = 2 * Math.PI / size;
				double angle = 0;
				g2.setColor(lightRed);

				g2.fillOval(0, 0, width, height);

				g2.setColor(deepRed);
				g2.fillArc(0, 0, width, height, (int) Math.toDegrees(this.portion * pas), (int) Math.toDegrees(pas));

				g2.setColor(Color.BLACK);
				g2.setFont(fontTools);
				AffineTransform orig = g2.getTransform();

				for (int i = 0; i < size; i++) {
					g2.drawLine(width/2, height/2, (int) (radius + radius * Math.cos(angle)), (int) (radius + radius * Math.sin(angle)));
					angle += pas;
				}

				g2.translate(radius, radius);

				g2.rotate(-pas / 2 + pas);

				angle = 0;

				for (int i = 0; i < size; i++) {
					g2.rotate(-pas);
					// Avoid upside down labels
					if (angle >= Math.PI / 2 && angle < 3 * Math.PI / 2) {
						g2.rotate(-Math.PI);
						g2.drawString(model.getArrayTool().get(i).getName(), (int)(-0.75*radius), 0);
						g2.rotate(Math.PI);
					} else {
						g2.drawString(model.getArrayTool().get(i).getName(), (int)(radius/2), 0);

					}
					angle += pas;


				}
				g2.setTransform(orig);
			}
		}

		g2.setColor(Color.RED);
		g2.fillOval((int)(0.85*(width/2)), (int)(0.85*(height/2)), (int)(0.30*radius), (int)(0.30*radius));

		g2.setStroke(new BasicStroke(3));

		g2.setColor(Color.RED);


		g2.drawLine(this.getWidth() / 2, this.getHeight() / 2, model.getX(), model.getY());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click");

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
		if (state == 0) {
			choice1 = this.portion;
		}
		if (state == 1 && choice1 == 0) {
			this.setChangeColor(true);
		} else if (state == 1 && choice1 == 1) {

			this.setChangeTool(true);
		}
		state = state == 0 ? ++state : 0;
		System.out.println(state);
		if (state == 1) {
			this.setBounds((int) this.getLocation().getX() + e.getX() - radius,
					(int) this.getLocation().getY() + e.getY() - radius, width, height);

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
		Point p1 = new Point(radius, 0);
		Point p2 = new Point(e.getX() - radius, e.getY() - radius);
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
		if (state == 0) {
			pas = Math.PI;
		} else {
			if (choice1 == 0) {
				// colors
				if (model.getSizeColor() <= 7)
					pas = 2 * Math.PI / model.getSizeColor();
				else
					pas = 2 * Math.PI / 7;

			} else {
				// tools
				if (model.getSizeTool() <= 7)
					pas = 2 * Math.PI / model.getSizeTool();
				else
					pas = 2 * Math.PI / 7;

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

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
