package edu.smith.cs.csc212.calc;

import java.util.Map;

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
}
