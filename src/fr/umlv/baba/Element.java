package fr.umlv.baba;

import java.util.Objects;

import fr.umlv.baba.enumerate.EnumWords;

/**
 * @author Decilap
 * @author Hassani
 */
public class Element {
	int positionx;
	int positiony;
	EnumWords type;

	/**
	 * @param positionx represent the coordinates x of a "Name" element.
	 * @param positiony represent the coordinates y of a "Name" element.
	 * @param type      represent the type of the element (EnumNouns,
	 *                  EnumOperators,EnumProperties).
	 */
	public Element(int positionx, int positiony, EnumWords type) {
		Objects.requireNonNull(positionx);
		Objects.requireNonNull(positiony);
		this.positionx = positionx;
		this.positiony = positiony;
		this.type = type;
	}

	/**
	 * Return if the first text block is next to the second text block.
	 * 
	 * @param element1 Represent a text block.
	 * @param element2 Represent a text block.
	 * @return {@code true} if the first text block is next to the second text
	 *         block.
	 */
	public static boolean nextTo(Element element1, Element element2) {
		Objects.requireNonNull(element1);
		Objects.requireNonNull(element2);
		return ((element1.positionx + 40) / 40 == element2.positionx / 40 && element1.positiony / 40 == element2.positiony / 40);

	}

	/**
	 * Return if the first text bloc is above of the second text block.
	 * 
	 * @param element1 Represent a text block.
	 * @param element2 Represent a text block.
	 * @return {@code true} If the first text bloc is above of the second text
	 *         block.
	 */
	public static boolean aboveOf(Element element1, Element element2) {
		Objects.requireNonNull(element1);
		Objects.requireNonNull(element2);
		return ((element1.positionx) / 40 == element2.positionx / 40 && (element1.positiony + 40) / 40 == element2.positiony / 40);

	}

	/**
	 * Return if two elements are in the same position.
	 * 
	 * @param element1 Represent an element.
	 * @param element2 Represent an element.
	 * @return {@code true} If the two elements are on the same position.
	 */
	public static boolean samePosition(Element element1, Element element2) {
		Objects.requireNonNull(element1);
		Objects.requireNonNull(element2);
		return (element1.positionx / 40 == (element2.positionx / 40)
				&& element1.positiony / 40 == (element2.positiony / 40));
	}

}
