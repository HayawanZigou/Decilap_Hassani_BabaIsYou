package fr.umlv.baba;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.baba.enumerate.EnumWords;

/**
 * @author Decilap
 * @author Hassani
 */

public class Melt extends AbstractProperty {
	
	/**
	 * @param width Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public Melt(float width, float height) {
		super(width, height);
	}
	
	/**
	 * Remove the elements which have the "Melt" property that are on the same
	 * position as the elements which have the "Hot" property.
	 * 
	 * @param elementsWorld  Represent a HashMap which contains all the elements in the game.
	 * @param hot Represent the elements that have the "Hot" property.
	 */
	public void removeMeltElement(HashMap<EnumWords, ArrayList<Element>> elementsWorld, Hot hot) {
		for (var hotElement : hot.elements) {
			for (var i = 0 ; i < elements.size(); i++) {
				if (Element.samePosition(hotElement, elements.get(i))) {
					var meltElement = elements.get(i);
					elementsWorld.get(meltElement.type).remove(meltElement);
				}
			}
		}
	}
}
