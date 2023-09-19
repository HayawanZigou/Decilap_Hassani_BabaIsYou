package fr.umlv.baba.enumerate;

import java.util.Objects;

/**
 * @author Decilap
 * @author Hassani
 */
public enum EnumOperators implements EnumWords {
	/**
	  * Is operator
	  */
	IS;
	
	/**
	 * Return if "string" correspond to an operator.
	 * @param string Represent a string.
	 * @return {@code true} If "string" correspond to an operator.
	 */
	public static boolean isOperator(String string) {
		Objects.requireNonNull(string);
		return string.equals(IS.toString());
	}
}
