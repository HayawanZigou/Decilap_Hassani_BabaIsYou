package fr.umlv.baba;

import fr.umlv.zen5.KeyboardKey;

/**
 * @author Decilap
 * @author Hassani
 */
public class Stop extends AbstractProperty {

	/**
	 * @param width  Represent the with of the window.
	 * @param height Represent the height of the window.
	 */
	public Stop(float width, float height) {
		super(width, height);
	}

	/**
	 * Return if the "pusher" will be on the same position as a "Stop" element.
	 * 
	 * @param pusher Represent an element in the game.
	 * @param key    Represent a key pressed.
	 * @return {@code true} If the "pusher" will be on the same position as a "Stop"
	 *         element.
	 */
	public boolean isStop(Element pusher, KeyboardKey key) {
		dx = dy = 0;
		keyPressed(key);
		for (var stop : elements) {
			if ((pusher.positionx + dx) / 40 == (stop.positionx / 40)
					&& (pusher.positiony + dy) / 40 == (stop.positiony / 40)) {
				return true;
			}

		}
		return false;
	}
}