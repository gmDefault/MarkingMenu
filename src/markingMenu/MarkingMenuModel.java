package markingMenu;

import java.util.ArrayList;

public class MarkingMenuModel {

	// x is the x position of the Marking Menu
	private int x = 0;
	// y is the y position of the Marking Menu
	private int y = 0;
	// sizeColor is the number of colors in the marking menu
	private int sizeColor;
	// sizeTool is the number of tools in the marking menu
	private int sizeTool;
	//arrayColor is the array of Colors
	private ArrayList<Element> arrayColor;
	//arrayTool is the array of Tools
	private ArrayList<Element> arrayTool;


	public MarkingMenuModel() {

	}


	public int getSizeColor() {
		return sizeColor;
	}


	public void setSizeColor(int sizeColor) {
		this.sizeColor = sizeColor;
	}


	public int getSizeTool() {
		return sizeTool;
	}


	public void setSizeTool(int sizeTool) {
		this.sizeTool = sizeTool;
	}


	public ArrayList<Element> getArrayColor() {
		return arrayColor;
	}


	public void setArrayColor(ArrayList<Element> arrayColor) {
		this.arrayColor = arrayColor;
	}


	public ArrayList<Element> getArrayTool() {
		return arrayTool;
	}


	public void setArrayTool(ArrayList<Element> arrayTool) {
		this.arrayTool = arrayTool;
	}


	public ArrayList<Element> getColors() {
		return this.arrayColor;
	}
	
	public ArrayList<Element> getTools() {
		return this.arrayTool;
	}


	/**
	 * Add elements in the marking Menu
	 * @param arrayColor the array of colors that we want to add in the model
	 * @param arrayTool the array of tools that we want to add in the model
	 */
	public void setElements(ArrayList<Element> arrayColor, ArrayList<Element> arrayTool) {
		if(arrayColor != null && arrayTool !=null) {
			this.sizeColor=arrayColor.size();
			this.sizeTool=arrayTool.size();
			
			this.arrayColor = arrayColor;
			this.arrayTool = arrayTool;

		}


	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
