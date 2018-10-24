package marking.ui;

import java.awt.Color;

public class Element {

	// An element is a tool (pen, circle,...) or a color
	// A marking menu is composed of elements
	
	// Name is the name of the tool
	private String name;
	// uniq ID of the element
	private static int id = 0;
	// Uniq ID of element (corresponding to the index of the array of tools in )
	private int toolID;
	// the color of the element
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
