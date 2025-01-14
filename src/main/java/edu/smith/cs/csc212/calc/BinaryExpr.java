package edu.smith.cs.csc212.calc;

import java.util.Map;
import java.util.Set;

public class BinaryExpr implements Expr {
	private String op;
	Expr left;
	Expr right;
	
	public BinaryExpr(String op) {
		this(op, null, null);
	}
	public BinaryExpr(String op, Expr left, Expr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	@Override
	//ours should take in only strings (variables like p,q,r and operators like and or etc.)
	public Boolean evaluate(Map<String, Boolean> vars) {
		switch(op) {
			// and
			case "&":
				return left.evaluate(vars) && right.evaluate(vars);
			// or
			case "|":
				return left.evaluate(vars) || right.evaluate(vars);
			// xor
			case "#":
				return (left.evaluate(vars) || right.evaluate(vars)) && !(left.evaluate(vars) && right.evaluate(vars));
			// not
			case "~":
				return !right.evaluate(vars);
			// if/then
			case ">":
				return !left.evaluate(vars) || right.evaluate(vars);
			default:
				throw new UnsupportedOperationException(op);
		}
	}
	@Override
	public String toString() {
		return "("+op+" "+left+" "+right+")";
	}
	@Override
	public void findVars(Set<String> output) {
		left.findVars(output);
		right.findVars(output);		
	}
}