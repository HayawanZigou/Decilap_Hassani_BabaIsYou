package fr.umlv.baba;

import java.util.ArrayList;

/**
 * @author Decilap
 * @author Hassani
 */
public interface Property {
	
	/**
	 * Clear the content of the property.
	 */
	void clear();
	
	/**
	 * Add an array of "Element" objects in a property.
	 * @param arrayName Represent an array of "Element" objects.
	 */
	void addAll(ArrayList<Element> arrayName);
	
	/**
	 * Add an "Element" in property.
	 * @param element Represent an "Element" object.
	 */
	void add(Element element);
	
	/**
	 * Remove an "Element" in property.
	 * @param element Represent an "Element" object.
	 */
	void remove(Element element);
	
	/**
	 * Return the ArrayList of the property.
	 *  @return the ArrayList of the property.
	 */
	ArrayList<Element> elements();
	
	/**
	 * Return if the property is empty or not.
	 * @return {@code true} if property is empty.
	 */
	boolean isEmpty();
	
}
