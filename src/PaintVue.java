
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

	private PaintModel pm;
	private JLayeredPane lp = new JLayeredPane();
	final JMarkingMenu f;
	
	public PaintVue(String title,  final PaintModel pm) {
		super(title);
		this.pm=pm;
	
		Element elem = new Element("ss");
		Element eleme = new Element("dd");
		Element elemed = new Element("ddd");
		Element elemedd = new Element("ddd");
		Element elemeddd = new Element("ddd");
		
		Element elem1 = new Element(Color.BLACK);
		Element eleme1 = new Element(Color.BLUE);
		Element elemed1 = new Element(Color.GREEN);
		Element elemedd1 = new Element(Color.GRAY);
		Element elemeddd1 = new Element(Color.RED);


		ArrayList<Element> arrayColor = new ArrayList<Element> () {};
		arrayColor.add(elem1);
		arrayColor.add(eleme1);
		arrayColor.add(elemed1);
		arrayColor.add(elemedd1);
		
		ArrayList<Element> arrayTool = new ArrayList<Element> () {};
		arrayTool.add(elem);
		arrayTool.add(eleme);
		arrayTool.add(elemed);
		arrayTool.add(elemedd);


		f = new JMarkingMenu(lp, arrayColor, arrayTool );
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
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
			}
		},JLayeredPane.DEFAULT_LAYER);
		panel.setBounds(0,40,800,560);
		tb.setBounds(0,0,800,40);
	
		lp.add(tb,JLayeredPane.FRAME_CONTENT_LAYER);


		add(lp);
		
		panel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					f.setBounds(e.getX() - 100,e.getY() - 57 ,200,200);
					lp.add(f,JLayeredPane.PALETTE_LAYER);
				}
			}

			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
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
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
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
	}, };

	Tool tool;

	JPanel panel;
	

}
