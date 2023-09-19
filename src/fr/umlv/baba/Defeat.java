package fr.umlv.baba;

import java.util.ArrayList;
import java.util.HashMap;
import fr.umlv.baba.enumerate.EnumWords;

/**
 * @author Decilap
 * @author Hassani
 */
public class Defeat extends AbstractProperty {

	/**
	 * @param width  Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public Defeat(float width, float height) {
		super(width, height);
	}

	/**
	 * Remove the "You" elements that are on the same position as an "Defeat"
	 * element.
	 * @param elementsWorld  Represent a HashMap which contains all the elements in the game.
	 * @param you Represent the "You" elements.
	 */
	public void removeDefeatElements(HashMap<EnumWords, ArrayList<Element>> elementsWorld, You you) {
		for (var i = 0; i < elements.size(); i++) {
			for (var elementEntry : elementsWorld.entrySet()) {
				for (var j = 0; j < elementEntry.getValue().size(); j++) {
					var element = elementEntry.getValue().get(j);
					if (Element.samePosition(element, elements.get(i)) && element.type != elements.get(i).type) {
						if (you.elements.contains(element))
							elementEntry.getValue().remove(j);
					}
				}
			}
		}
	}
}
