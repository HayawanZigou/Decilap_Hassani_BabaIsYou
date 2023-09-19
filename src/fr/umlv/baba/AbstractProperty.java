package fr.umlv.baba;

import java.util.ArrayList;
import java.util.Objects;

import fr.umlv.zen5.KeyboardKey;

/**
 * @author Decilap
 * @author Hassani
 */
abstract class AbstractProperty implements Property {
	float width;
	float height;
	int dx;
	int dy;
	final ArrayList<Element> elements = new ArrayList<>();

	/**
	 * @param width Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public AbstractProperty(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Change the direction according the the key pressed.
	 * dx and dy represent the direction which respectively are the direction on the x axis and the direction on the y axis.
	 * @param key Represent a key pressed. 
	 */
	void keyPressed(KeyboardKey key) {
		if (key == KeyboardKey.UP )
			dy = -40;
		if (key == KeyboardKey.DOWN)
			dy = 40;
		if (key == KeyboardKey.LEFT)
			dx = -40;
		if (key == KeyboardKey.RIGHT)
			dx = 40;
	}
	
	/**
	 * Return if the specific element is on screen.
	 * 
	 * @param element  Represent an "Element" object.
	 * @return {@code true} If the specific element is on screen.
	 */
	boolean onScreen(Element element) {
		return (element.positionx + dx < width && element.positionx + dx >= 0 && element.positiony + dy < height
				&& element.positiony + dy >= 0);
	}

	/**
	 * Add an "Element" in property.
	 * @param element Represent an "Element" object.
	 */
	public void add(Element element) {
		Objects.requireNonNull(element);
		elements.add(element);
	}
	
	/**
	 * Add an array of "Element" objects in a property.
	 * @param array Represent an array of "Element" objects.
	 */
	public void addAll(ArrayList<Element> array) {
		Objects.requireNonNull(array);
		elements.addAll(array);
	}
	
	/**
	 * Remove an "Element" in property.
	 * @param element Represent an "Element" object.
	 */
	public void remove(Element element) {
		Objects.requireNonNull(element);
		elements.remove(element);
	}
	
	/**
	 * Clear the content of the property.
	 */
	public void clear() {
		elements.clear();
	}
	
	/**
	 * Return if the property is empty or not.
	 * @return {@code true} if property is empty.
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	/**
	 * Return the ArrayList of the property.
	 */
	public ArrayList<Element> elements(){
		return elements;
	}
}
