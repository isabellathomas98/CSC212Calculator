package edu.smith.cs.csc212.calc;

import java.util.Map;
import java.util.Set;

public class Variable implements Expr {
	private String name;

	public Variable(String name) {
		
		this.name = name;
	}

	/*
	 * 
	 */
	@Override
	public Boolean evaluate(Map<String, Boolean> vars) {
		Boolean truthValue = vars.get(name);
		if (truthValue == null) {
			throw new BadNameError(name);
		}
		return truthValue;
	}

	@SuppressWarnings("serial")
	public static class BadNameError extends RuntimeException {
		public BadNameError(String what) {
			super(what);
		}
	}

	@Override
	public void findVars(Set<String> output) {
		//to avoid null column when using negation
		if (name==null) {
			//do nothing
		}
		else
		output.add(name);
		
	}
}
