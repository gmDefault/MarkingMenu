
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputListener;

import marking.ui.Element;
import marking.ui.JMarkingMenu;

public class PaintVue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int TOOLBAR_HEIGHT = 40;
	private PaintModel pm;
	private JLayeredPane lp = new JLayeredPane();
	final JMarkingMenu f;
	public PaintVue(String title,  final PaintModel pm) {
		super(title);
		this.pm=pm;
		lp.setBounds(0, 80, WIDTH, HEIGHT - TOOLBAR_HEIGHT);
	
		Element pen = new Element("pen");
		Element line = new Element("line");
		Element rect = new Element("rect");
		Element ellipse = new Element("ellipse");
		
		Element elem1 = new Element(Color.BLACK);
		Element eleme1 = new Element(Color.BLUE);
		Element elemed1 = new Element(Color.GREEN);
		Element elemedd1 = new Element(Color.YELLOW);
		Element elemeddd1 = new Element(Color.RED);
		Element elemedddd1 = new Element(Color.WHITE);


		ArrayList<Element> arrayColor = new ArrayList<Element> ();
		arrayColor.add(elem1);
		arrayColor.add(eleme1);
		arrayColor.add(elemed1);
		arrayColor.add(elemedd1);
		arrayColor.add(elemeddd1);
		arrayColor.add(elemedddd1);

		
		ArrayList<Element> arrayTool = new ArrayList<Element> ();
		arrayTool.add(pen);
		arrayTool.add(line);
		arrayTool.add(rect);
		arrayTool.add(ellipse);


		f = new JMarkingMenu(lp, arrayColor, arrayTool );
		
		f.addMouseListener(f);
		f.addMouseMotionListener(f);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		JToolBar tb = new JToolBar() {{
			for(AbstractAction tool: tools) {
				tool.setEnabled(true);
				add(tool);
			}
			JButton blue = new JButton();
			blue.setBackground(Color.BLUE);
			blue.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					pm.setColor(Color.BLUE); 
				} 
			});
			add(blue);
			JButton red = new JButton();
			red.setBackground(Color.RED);
			red.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					pm.setColor(Color.RED); 
				} 
			});
			add(red);
			JButton green = new JButton();
			green.setBackground(Color.GREEN);
			green.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					pm.setColor(Color.GREEN); 
				} 
			});
			add(green);
			JButton yellow = new JButton();
			yellow.setBackground(Color.yellow);
			yellow.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					pm.setColor(Color.YELLOW); 
				} 
			});
			add(yellow);
			
			JButton black = new JButton();
			black.setBackground(Color.black);
			black.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					pm.setColor(Color.BLACK); 
				} 
			});			
			add(black);
		}};
		
		lp.add(panel = new JPanel() {	
			public void paintComponent(Graphics g) {
				super.paintComponent(g);	
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				                    RenderingHints.VALUE_ANTIALIAS_ON);
		
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());
				
				g2.setColor(pm.getColor());
				
				Set<Entry<Shape, Color>> setHm = pm.getShapes().entrySet();
				Iterator<Entry<Shape, Color>> it = setHm.iterator();
				while(it.hasNext()) {
					Entry<Shape, Color> e = it.next();
					g2.setColor(e.getValue());
					g2.draw(e.getKey());
				}
				if(f.getChangeColor() && f.getElement().getColor() != null && pm.getColor() != f.getElement().getColor() ) {
					pm.setColor(f.getElement().getColor());
					f.setChangeColor(false);
				}
			}
		},JLayeredPane.DEFAULT_LAYER);
		panel.setBounds(0,TOOLBAR_HEIGHT,WIDTH,HEIGHT - TOOLBAR_HEIGHT);
		tb.setBounds(0,0,WIDTH,TOOLBAR_HEIGHT);
	
		lp.add(tb,JLayeredPane.DEFAULT_LAYER);


		add(lp);
		
		lp.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				System.out.println("TAP");
			}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					f.setBounds(e.getX() - 100,e.getY() - 100,200,200);
					lp.add(f,JLayeredPane.PALETTE_LAYER);
					
				}
			}

			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
				if(f.getChangeTool()) {	
					lp.removeMouseListener(tool);
					lp.removeMouseMotionListener(tool);
					tool = tools[f.getElement().getToolID()];
					lp.addMouseListener(tool);
					lp.addMouseMotionListener(tool);
					f.setChangeTool(false);
				}
			}
			public void mouseExited(MouseEvent e) {

			}});
		
		pack();
		setVisible(true);
	}
		
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			lp.removeMouseListener(tool);
			lp.removeMouseMotionListener(tool);
			tool = this;
			lp.addMouseListener(tool);
			lp.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
			
		}
	}

	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				pm.addShape(shape = path, pm.getColor());
			}
			path.lineTo(e.getX(), e.getY());
			panel.repaint();
		}
	}, new Tool("line") {
		public void mouseDragged(MouseEvent e) {
			Line2D.Double line = (Line2D.Double) shape;
			if(line == null) {
				line = new Line2D.Double(e.getX(), e.getY(), 0, 0);
				pm.addShape(shape = line, pm.getColor());
			}
			line.setLine(e.getX(), e.getY(),o.getX(), o.getY());
			panel.repaint();
		}
	}, new Tool("rect") {
		public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				pm.addShape(shape = rect, pm.getColor());
			}
			rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
			if (ellipse == null) {
				ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				pm.addShape(shape = ellipse, pm.getColor());
			}
			ellipse.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}
	};

	Tool tool;

	JPanel panel;
	
}
