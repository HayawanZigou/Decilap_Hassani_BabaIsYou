package fr.umlv.baba;

import java.util.ArrayList;
import java.util.HashMap;
import fr.umlv.baba.enumerate.EnumWords;

/**
 * @author Decilap
 * @author Hassani
 */
public class Sink extends AbstractProperty {
	/**
	 * @param width  Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public Sink(float width, float height) {
		super(width, height);
	}

	/**
	 * Remove the elements that are on the same position as a "Sink" element.
	 * @param elementsWorld  Represent a HashMap which contains all the elements in the game.
	 */
	public void removeSinkElements(HashMap<EnumWords, ArrayList<Element>> elementsWorld) {
		for (var i = 0; i < elements.size(); i++) {
			for (var elementEntry : elementsWorld.entrySet()) {
				for (var j = 0; j < elementEntry.getValue().size(); j++) {
					var element = elementEntry.getValue().get(j);
					if (Element.samePosition(element, elements.get(i)) && element.type != elements.get(i).type) {
						var arrayList = elementsWorld.get(elements.get(i).type);
						elementEntry.getValue().remove(j);
						arrayList.remove(elements.get(i));
					}
				}
			}
		}
	}

}
