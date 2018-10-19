package marking.ui;

import java.util.ArrayList;

public class MarkingMenuModel {

	private int x;
	private int y;
	private int size;
	private ArrayList<Element> elements;

	public MarkingMenuModel() {

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MarkingMenuModel(Element[] elements) {

		for (int i = 0; i < elements.length; i++) {
			this.elements.add(elements[i]);
		}
		this.size=elements.length;

	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> arrayList) {
		this.size=arrayList.size();
		this.elements = arrayList;
		System.out.println(this.size);

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
