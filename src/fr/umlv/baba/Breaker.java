package fr.umlv.baba;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.baba.enumerate.EnumWords;
import fr.umlv.zen5.KeyboardKey;

/**
 * @author Decilap
 * @author Hassani
 */
public class Breaker extends AbstractProperty {
	
	/**
	 * @param width Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public Breaker(float width, float height) {
		super(width, height);
	}
	
	/**
	 * Return if "you" have the "Breaker" Property.
	 * @param you Represent a "You" object.
	 * @return {@code true} If "you" have the "Breaker" Property.
	 */
	public boolean isBreaker(You you) {
		for (var element : you.elements) {
			if (elements.contains(element))
				return true;
		}
		return false;
	}
	
	/**
	 * Remove the elements which are on the same position as the elements in "you".
	 * @param key Represent the key pressed.
	 * @param you Represent an ArrayList of "Element" objects.
	 * @param elements Represent a HashMap which contains all the elements in the game.
	 */
	public void destroy(KeyboardKey key, ArrayList<Element> you, HashMap<EnumWords, ArrayList<Element>> elements) {
		dx = dy = 0;
		keyPressed(key);
		for (var entry : elements.entrySet()) {
			for (var i = 0 ; i < entry.getValue().size() ; i++) {
				for (var youElement : you) {
					var element = entry.getValue().get(i);
					if ((element.positionx - dx)/40 == youElement.positionx/40 && (element.positiony - dy)/40 == youElement.positiony/40 && element.type != youElement.type) {
						entry.getValue().remove(element);
					}
				}
			}
		}
	}
}
