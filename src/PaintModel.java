import java.awt.Color;
import java.awt.Shape;
import java.util.HashMap;
import java.util.Map;

public class PaintModel {

	private Map<Shape, Color> shapes;
	private Color color;

	public PaintModel(Map<Shape, Color> shapes, Color color) {
		this.shapes = shapes;
		this.color = color;

	}

	public PaintModel() {
		this.shapes = new HashMap<Shape, Color>();
		this.color = new Color(0);
	}

	public void addShape(Shape s, Color c) {
		shapes.put(s, c);
	}
	
	public void removeShape(Shape s, Color c) {
		shapes.remove(s);
	}

//	public void setShapes(Map<Shape, Color> shapes) {
//		this.shapes = shapes;
//	}

	public Map<Shape, Color> getShapes() {
		return shapes;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
