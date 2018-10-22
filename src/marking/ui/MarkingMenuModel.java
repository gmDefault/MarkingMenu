package marking.ui;

import java.util.ArrayList;

public class MarkingMenuModel {

	private int x = 0;
	private int y = 0;
	private int sizeColor;
	private int sizeTool;
	private ArrayList<Element> arrayColor;
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
