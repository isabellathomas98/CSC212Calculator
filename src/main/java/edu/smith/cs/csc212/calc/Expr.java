package edu.smith.cs.csc212.calc;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * This is an expression tree.
 * @author jfoley
 *
 */
public interface Expr {
	/**
	 * Evaluate this expression, with the given mapping of variables.
	 * @param variables - the values of variables in this expression.
	 * @return the value of the expression.
	 */
	public Boolean evaluate(Map<String, Boolean> variables);
	
	/**
	 * For testing, we often don't set variables.
	 * @return the value of this expression.
	 */
	public default Boolean evaluate() {
		return this.evaluate(Collections.emptyMap());
	}
	
	public void findVars(Set<String> output);
}