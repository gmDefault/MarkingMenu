package marking.ui;

import java.awt.Color;

public class Element {

	private String name;
	private static int id = 0;
	private int toolID;
	private Color color = Color.BLACK;
	
	public int getToolID() {
		return toolID;
	}

	public void setToolID(int toolID) {
		this.toolID = toolID;
	}
	
	public Element(String name) {
		this.name = name;
		this.toolID = Element.id;
		Element.id++;
	}
	
	public Element(Color color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
