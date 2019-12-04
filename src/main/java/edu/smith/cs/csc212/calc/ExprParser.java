package edu.smith.cs.csc212.calc;

import java.util.List;

public class ExprParser {
	/**
	 * The list of tokens so far.
	 */
	List<String> tokens;
	/**
	 * The position in this token stream.
	 */
	int position;

	/**
	 * Construct a parse from a list of tokens.
	 * 
	 * @param tokens the list of important strings.
	 */
	public ExprParser(List<String> tokens) {
		this.tokens = tokens;
		this.position = 0;
	}

	/**
	 * Advance past a given token; or crash if it is not what we think.
	 * 
	 * @param what the token to skip over.
	 */
	public void expectExact(String what) {
		String value = tokens.get(position++);
		if (!value.equals(what)) {
			throw new RuntimeException("Expected: " + what);
		}
	}

	/**
	 * @return Variable.
	 */
	public Expr readVar() {
		String value = tokens.get(position++);
		return new Variable(value);
	}

	/**
	 * Which token is next?
	 * 
	 * @return get the current token or null.
	 */
	public String peek() {
		if (position < tokens.size()) {
			return tokens.get(position);
		}
		return null;
	}

	/**
	 * No operators have precedence over each other, but all cede to parentheses. 
	 * Every time we want to "recurse" here, we call the one that
	 * knows about parentheses: readExpr.
	 * 
	 * @return a tree of all the operator expressions we can find.
	 */
	public Expr logExpr() {
		Expr left = readExpr();

		while (position < tokens.size()) {
			String tok = peek();

			if (tok.equals("&") || tok.equals("|") || tok.equals("#") || tok.equals(">")) {
				position++;
				Expr right = readExpr();
				left = new BinaryExpr(tok, left, right);
			} else {
				break;
			}
		}
		return left;
	}

	/**
	 * This rule reads parentheses, or negation in front, or a variable.
	 * 
	 * The BNF for this looks like:
	 * <pre>
	 * expr := '(' + logExpr + ')' 
	 *       | '-' expr
	 *       | variable

	 * logExpr := expr '&' expr
	 *             | expr '|' expr
	 *             | expr '#' expr
	 *             | '~' expr
	 *             | expr '>' expr
	 *             | expr
	 * </pre>
	 * 
	 * @return the expression subtree starting from here.
	 */
	public Expr readExpr() {
		String tok = tokens.get(position);
		if (tok.equals("(")) {
			expectExact("(");
			Expr e = logExpr();
			expectExact(")");
			return e;
		} else if (tok.equals("~")) {
			expectExact("~");
			return new BinaryExpr("~", new Variable(null), readExpr());
		} else {
			return readVar();
		}
	}

	public static Expr parse(String input) {
		ExprParser p = new ExprParser(Tokenizer.tokenize(input));
		return p.logExpr();
	}
}