package fr.umlv.baba.enumerate;

import java.util.Objects;

/**
 * @author Decilap
 * @author Hassani
 */
public enum EnumNouns implements EnumWords {
	/**
	 * Baba noun
	 */
	BABA,
	/**
	 * Wall noun
	 */
	WALL,
	/**
	 * Flag noun
	 */
	FLAG,
	/**
	 * Water noun
	 */
	WATER,
	/**
	 * Skull noun
	 */
	SKULL,
	/**
	 * Lava noun
	 */
	LAVA,
	/**
	 * Rock noun
	 */
	ROCK,
	/**
	 * Grass noun
	 */
	GRASS,
	/**
	 * Tile noun
	 */
	TILE,
	/**
	 * Flower noun
	 */
	FLOWER,
	/**
	 * Brick noun
	 */
	BRICK,
	/**
	 * Jojo noun
	 */
	JOJO;

	/**
	 * Return if "string" correspond to a noun.
	 * 
	 * @param string Represent a string.
	 * @return {@code true} If "string" correspond to a noun.
	 */
	public static boolean isNoun(String string) {
		Objects.requireNonNull(string);
		return string.equals(BABA.toString()) || string.equals(WALL.toString()) || string.equals(FLAG.toString())
				|| string.equals(WATER.toString()) || string.equals(SKULL.toString()) || string.equals(LAVA.toString())
				|| string.equals(ROCK.toString()) || string.equals(GRASS.toString()) || string.equals(TILE.toString())
				|| string.equals(FLOWER.toString()) || string.equals(BRICK.toString())
				|| string.equals(JOJO.toString());
	}
}
