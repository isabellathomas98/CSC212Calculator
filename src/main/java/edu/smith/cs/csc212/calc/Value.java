package edu.smith.cs.csc212.calc;

import java.util.Map;

/**
 * This class represents a "literal" integer value in our calculator.
 * @author jfoley
 *
 */
public class Value{
	/**
	 * What number is it?
	 */
	private int num;
	/**
	 * Must give a number to construct this.
	 * @param n
	 */
	public Value(int n) {
		this.num = n;
	}

	public int evaluate(Map<String, Integer> vars) {
		return num;
	}

	public String toString() {
		return ""+this.num;
	}
}