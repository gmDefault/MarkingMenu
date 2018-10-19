package marking.ui;

import java.awt.Color;

public class Element {

	private String name;
	private Color color;
	
	public Element(String name) {
		this.name = name;
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
