package fr.umlv.baba;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;

import fr.umlv.baba.enumerate.EnumNouns;
import fr.umlv.baba.enumerate.EnumOperators;
import fr.umlv.baba.enumerate.EnumProperties;
import fr.umlv.baba.enumerate.EnumWords;

/**
 * @author Decilap
 * @author Hassani
 */

abstract class AbstractLevel {
	float width, height;
	
	/**
	 * elements contains all the elements in the game excepting the text blocks
	 * which are in texts.
	 */
	public final HashMap<EnumWords, ArrayList<Element>> elements, texts;
	
	/**
	 * contains all the properties
	 */
	public final HashMap<EnumProperties, Property> properties;
	private final LinkedHashMap<Image, ArrayList<Element>> elementsImage;

	/**
	 * @param width  Represent the width of the window.
	 * @param height Represent the height of the window.
	 */
	public AbstractLevel(float width, float height) {
		this.width = width;
		this.height = height;
		properties = new HashMap<>();
		elements = new HashMap<>();
		elementsImage = new LinkedHashMap<>();
		texts = new HashMap<>();
		createProperties();

	}

	/**
	 * Read a file which contain a level informations and generate the level.
	 * 
	 * @param path Represents the path to the file to be generated.
	 */
	void generateLevel(String path) {
		Path saveFilePath1 = Path.of(path);
		try (var reader = Files.newBufferedReader(saveFilePath1, StandardCharsets.UTF_8)) {
			var buffer = reader.readLine();
			while (buffer != null) {
				if (!buffer.isBlank()) {
					parsedLine(buffer);
				}
				buffer = reader.readLine();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Add "element" to the hashmap "elements".
	 * 
	 * @param elements Contains all the elements in the game.
	 * @param element  Represents an element.
	 */
	private void putIn(HashMap<EnumWords, ArrayList<Element>> elements, Element element) {
		if (elements.get(element.type) == null) {
			var List = new ArrayList<Element>();
			List.add(element);
			elements.put(element.type, List);
			return;
		}
		elements.get(element.type).add(element);
	}

	/**
	 * Read "line" and generate the element corresponding to the information
	 * contained in it.
	 * 
	 * @param line Represent a line.
	 */
	private void parsedLine(String line) {
		var tokens = line.split("#");
		var type = tokens[0];
		EnumWords typeElement = null;
		if (type.equals("N")) {// noun
			typeElement = EnumNouns.valueOf(tokens[3]);
			var element = new Element(Integer.parseInt(tokens[1]) * 40, Integer.parseInt(tokens[2]) * 40, typeElement);
			var pathImage = "images/" + typeElement.toString() + "/" + typeElement.toString() + "_0.gif";
			putIn(elements, element);
			elementsImage.put(new ImageIcon(pathImage).getImage(), elements.get(typeElement));
		} else {
			if (type.equals("TN"))// text noun
				typeElement = EnumNouns.valueOf(tokens[3]);
			if (type.equals("TO"))// text operator
				typeElement = EnumOperators.valueOf(tokens[3]);
			if (type.equals("TP"))// text operator
				typeElement = EnumProperties.valueOf(tokens[3]);
			var element = new Element(Integer.parseInt(tokens[1]) * 40, Integer.parseInt(tokens[2]) * 40, typeElement);
			var pathImage = "images/" + typeElement.toString() + "/Text_" + typeElement.toString() + "_0.gif";
			putIn(texts, element);
			elementsImage.put(new ImageIcon(pathImage).getImage(), texts.get(typeElement));
		}
	}

	/**
	 * Create and put all the properties in the Hashmap properties.
	 */
	private void createProperties() {
		properties.put(EnumProperties.PUSH, new Push(width, height));
		properties.put(EnumProperties.WIN, new Win(width, height));
		properties.put(EnumProperties.STOP, new Stop(width, height));
		properties.put(EnumProperties.YOU, new You(width, height));
		properties.put(EnumProperties.MELT, new Melt(width, height));
		properties.put(EnumProperties.DEFEAT, new Defeat(width, height));
		properties.put(EnumProperties.SINK, new Sink(width, height));
		properties.put(EnumProperties.HOT, new Hot(width, height));
		properties.put(EnumProperties.BREAKER, new Breaker(width, height));
	}

	/**
	 * Draw all the elements on the window.
	 * 
	 * @param graphic represent the window.
	 */
	public void drawLevel(Graphics graphic) {
		for (var entry : elementsImage.entrySet()) {
			for (var element : entry.getValue()) {
				graphic.drawImage(entry.getKey(), element.positionx, element.positiony, 40, 40, null);
			}
		}
	}

	/**
	 * Add to "property" all the corresponding elements.
	 * 
	 * @param text     Represent a text block.
	 * @param property Represent the property to associate.
	 */
	void associatePropertyToNoun(Element text, Property property) {
		for (var entry : texts.entrySet()) {
			if (entry.getKey().getClass() == EnumOperators.class) {
				for (var text2 : entry.getValue()) {
					for (var entry1 : texts.entrySet()) {
						if (entry1.getKey().getClass() == EnumNouns.class) {
							for (var text3 : entry1.getValue()) {
								if ((Element.nextTo(text3, text2) && Element.nextTo(text2, text))
										|| (Element.aboveOf(text3, text2) && Element.aboveOf(text2, text))) {
									var nounArrayList = elements.get(text3.type);
									property.addAll(nounArrayList);

								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Add to "nouns" all the corresponding elements.
	 * 
	 * @param text  Represent a text block.
	 * @param nouns Represent the noun to associate.
	 */
	void associateNounToNoun(Element text, ArrayList<Element> nouns) {
		for (var entry : texts.entrySet()) {
			if (entry.getKey().getClass() == EnumOperators.class) {
				for (var text2 : entry.getValue()) {
					for (var entry1 : texts.entrySet()) {
						if (entry1.getKey().getClass() == EnumNouns.class) {
							for (var text3 : entry1.getValue()) {
								if ((Element.nextTo(text3, text2) && Element.nextTo(text2, text))
										|| (Element.aboveOf(text3, text2) && Element.aboveOf(text2, text))) {
									var nounArrayList = elements.get(text3.type);
									nouns.addAll(nounArrayList);
									nounArrayList.clear();
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Generate all the properties and elements.
	 */
	public void generatePropertiesAndElements() {
		for (var entry : properties.entrySet())
			entry.getValue().clear();

		for (var entry : texts.entrySet()) {
			for (var text : entry.getValue()) {
				properties.get(EnumProperties.PUSH).add(text);
				if (entry.getKey().getClass() == EnumProperties.class)
					associatePropertyToNoun(text, properties.get(text.type));
				if (entry.getKey().getClass() == EnumNouns.class)
					associateNounToNoun(text, elements.get(text.type));

			}
		}
	}

}
