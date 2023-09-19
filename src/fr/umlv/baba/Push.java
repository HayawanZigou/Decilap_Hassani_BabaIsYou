package fr.umlv.baba;

import java.util.Objects;

import fr.umlv.zen5.KeyboardKey;

/**
 * @author Decilap
 * @author Hassani
 */
public class Push extends AbstractProperty {

	/**
	 * @param width  Represent the with of the window.
	 * @param height Represent the height of the window.
	 */
	public Push(float width, float height) {
		super(width, height);
	}

	/**
	 * Return if "pushable" can be push.
	 * 
	 * @param pushable Represent an element to push.
	 * @param key      Represent a key pressed.
	 * @param stop     Represent the elements that cannot be crossed.
	 * @return {@code true} If the "pushable" can be push.
	 */
	private boolean isPushable(Element pushable, KeyboardKey key, Stop stop) {
		return onScreen(pushable) && !stop.isStop(pushable, key);
	}

	/**
	 * Return if there is two different elements in the same position.
	 * 
	 * @param element Represent an element in the game.
	 * @return {@code true} If there is two different elements in the same position.
	 */
	private boolean noDouble(Element element) {
		for (var e : elements) {
			if (Element.samePosition(element, e) && element != e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Push all the elements that need to be pushed.
	 * 
	 * @param Key    Represent a key pressed.
	 * @param pusher Represent the "You" elements.
	 * @param stop   Represent the elements that cannot be crossed.
	 */
	public void pushable(KeyboardKey Key, You pusher, Stop stop) {
		Objects.requireNonNull(Key);
		Objects.requireNonNull(pusher);
		Objects.requireNonNull(stop);
		for (var pushable : elements) {
			for (var push : pusher.elements) {
				keyPressed(Key);
				if (Element.samePosition(push, pushable) && arePushable(pushable, Key, stop)) {
					pushAll(pushable);
				} else if (Element.samePosition(push, pushable)) {
					push.positionx -= dx;
					push.positiony -= dy;
				}
			}
		}
		dx = dy = 0;
	}

	/**
	 * Push all the elements so that there is not 2 elements in the same position.
	 * 
	 * @param pushable Represent an element to push.
	 */
	private void pushAll(Element pushable) {
		Element lastPushed = pushable;
		lastPushed.positionx += dx;
		lastPushed.positiony += dy;
		while (!noDouble(lastPushed)) {
			for (var element : elements) {
				if (Element.samePosition(lastPushed, element) && lastPushed != element) {
					element.positionx += dx;
					element.positiony += dy;
					lastPushed = element;
				}
			}
		}

	}

	/**
	 * Return if it is possible to push all the elements.
	 * 
	 * @param pushable Represent an element to push.
	 * @param Key      Represent a key pressed.
	 * @param stop     Represent the elements that cannot be crossed.
	 */
	private boolean arePushable(Element pushable, KeyboardKey key, Stop stop) {
		Element lastPushable = pushable;
		while (isPushable(lastPushable, key, stop)) {
			Element save = lastPushable;
			for (var element : elements) {
				if ((lastPushable.positionx + dx) / 40 == element.positionx / 40
						&& (lastPushable.positiony + dy) / 40 == element.positiony / 40) {
					lastPushable = element;
				}
			}
			if (lastPushable.equals(save)) // if nothing is pushed and we are still in the loop, so all the elements can
											// be pushed
				return true;
		}
		return false;
	}
}