package fr.umlv.baba.enumerate;

import java.util.Objects;

/**
 * @author Decilap
 * @author Hassani
 */
public enum EnumProperties implements EnumWords {
	/**
	  * You Property
	  */
	YOU,
	/**
	  * Win Property
	  */
	WIN, 
	/**
	  * Stop Property
	  */
	STOP, 
	/**
	  * Push Property
	  */
	PUSH, 
	/**
	  * Melt Property
	  */
	MELT, 
	/**
	  * Hot Property
	  */
	HOT, 
	/**
	  * Defeat Property
	  */
	DEFEAT, 
	/**
	  * Sink Property
	  */
	SINK, 
	/**
	  * Breaker Property
	  */
	BREAKER;
	
	/**
	 * Return if "string" correspond to a property.
	 * @param string Represent a string.
	 * @return {@code true} If "string" correspond to a property.
	 */
	public static boolean isProperty(String string) {
		Objects.requireNonNull(string);
		return string.equals(YOU.toString()) || string.equals(WIN.toString()) || string.equals(STOP.toString())
				|| string.equals(PUSH.toString()) || string.equals(MELT.toString()) || string.equals(HOT.toString())
				|| string.equals(DEFEAT.toString()) || string.equals(SINK.toString()) || string.equals(BREAKER.toString());
	}
}
