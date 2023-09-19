package fr.umlv.baba;

import fr.umlv.zen5.KeyboardKey;

/**
 * @author Decilap
 * @author Hassani
 */
public class You extends AbstractProperty {

	/**
	 * @param width  Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public You(float width, float height) {
		super(width, height);
	}

	/**
	 * Return if a movement is possible.
	 * @param stop Represent the elements that cannot be crossed.
	 * @param key  Represent a key pressed.
	 * @return {@code true} If a movement is possible.
	 */
	private boolean canMove(Stop stop, KeyboardKey key) {
		for (var name : elements) {
			if (!onScreen(name) || stop.isStop(name, key))
				return false;
		}
		return true;
	}

	/**
	 * Permit to move the contained elements in "You".
	 * 
	 * @param key  Represent a key pressed.
	 * @param stop Represent the elements that cannot be crossed.
	 */
	public void move(KeyboardKey key, Stop stop) {
		dx = dy = 0;
		keyPressed(key);
		if (canMove(stop, key)) {
			for (var element : elements) {
				element.positionx += dx;
				element.positiony += dy;
			}
		}
	}
}
