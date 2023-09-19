package fr.umlv.baba;

/**
 * @author Decilap
 * @author Hassani
 */
public class Win extends AbstractProperty {

	/**
	 * @param width  Represent the with of the window.
	 * @param height Represent the height of the window.
	 */
	public Win(float width, float height) {
		super(width, height);
	}

	/**
	 * Return if an "You" element is on the same position as an element which have the property "Win".
	 * 
	 * @param you Contains all the element that have the "You" property.
	 * @return {@code true} If an element in "you" have the same coordinates as an element which have the "Win" Property.
	 */
	public boolean isWin(You you) {
		for (var element : elements) {
			for (var player : you.elements) {
				if (Element.samePosition(player, element)) {
					return true;
				}
			}
		}
		return false;
	}
}
