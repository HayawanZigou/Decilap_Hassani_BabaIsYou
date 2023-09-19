package fr.umlv.baba;

import java.util.Objects;

import fr.umlv.baba.enumerate.EnumNouns;
import fr.umlv.baba.enumerate.EnumOperators;
import fr.umlv.baba.enumerate.EnumProperties;

/**
 * @author Decilap
 * @author Hassani
 */
public class Level extends AbstractLevel {

	/**
	 * @param width  the width of the window.
	 * @param height the height of the window.
	 * @param path represent a path to a file.
	 */
	public Level(float width, float height, String path) {
		super(width, height);
		generateLevel(path);
	}
	
	
	/**
	 * Return if "level" is in "levels".
	 * @param level  Represent a level.
	 * @param levels  Contains all the levels.
	 * @return {@code true} If "level" is in "levels".
	 */
	public static boolean validLevel(String level, String[] levels) {
		Objects.requireNonNull(level);
		Objects.requireNonNull(levels);
		for (var l : levels) {
			if (level.equals(l))
				return true;
		}
		return false;
	}
	
	/**
	 * Permit to change the properties of a level and change the noun elements to other noun elements.
	 * @param args contains the supplied command-line arguments.
	 */
	public void executeCommand(String[] args) {
		Objects.requireNonNull(args);
		for (var i = 0; i < args.length; i++) {
			if (args[i].equals("--execute") && i + 3 < args.length) {
				if (EnumNouns.isNoun(args[i + 1]) && EnumOperators.isOperator(args[i + 2]) && EnumProperties.isProperty(args[i + 3])) {
					properties.get(EnumProperties.valueOf(args[i + 3]))
							.addAll(elements.get((EnumNouns.valueOf(args[i + 1]))));
				} else if (EnumNouns.isNoun(args[i + 1]) && EnumOperators.isOperator(args[i + 2])
						&& EnumNouns.isNoun(args[i + 3])) {
					elements.get(EnumNouns.valueOf(args[i + 3]))
							.addAll(elements.get((EnumNouns.valueOf(args[i + 1]))));
					elements.get((EnumNouns.valueOf(args[i + 1]))).clear();
				}
			}
		}
		generateDefaultProperties();
	}
	
	/**
	 * Check if the commands entered are correct.
	 * @param args contains the supplied command-line arguments.
	 * @param levels Contains all the levels.
	 */
	public static void checkCommands(String[] args, String[] levels) {
		Objects.requireNonNull(args);
		Objects.requireNonNull(levels);
		if ((!args[0].equals("--level") && args.length > 2)
				|| (args[0].equals("--level") && args.length > 2 && !args[2].equals("--execute")))
			throw new IllegalArgumentException("wrong command. command: --level name --execute word1 word2 word3");
		if (args.length > 0 && !args[0].equals("--level") && !args[0].equals("--levels"))
			throw new IllegalArgumentException("The command does not exist.");
		if (args.length > 1 && args[0].equals("--levels") && !args[1].equals("levels"))
			throw new IllegalArgumentException("The folder entered is not correct. folder name : 'levels'.");
		if (args.length > 1 && args[0].equals("--level")) {
			if (validLevel(args[1], levels))
				levels[0] = args[1];
			else
				throw new IllegalArgumentException("The level doesnt exist.");
		}
	}
	
	/**
	 * Generate the default properties (used if the "execute" command is used).
	 */
	private void generateDefaultProperties() {
		for (var entry : texts.entrySet()) {
			for (var text : entry.getValue()) {
				properties.get(EnumProperties.PUSH).add(text);
				if (properties.get(EnumProperties.YOU).isEmpty()) {
					if (entry.getKey().equals(EnumProperties.YOU))
						associatePropertyToNoun(text, properties.get(EnumProperties.YOU));
				}
			}
		}
	}
}
