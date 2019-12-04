package edu.smith.cs.csc212.calc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ExprParserTest {

	
	
	@Test
	public void parenPrecedence() {
		Map<String, Boolean> vars = new HashMap<>();
		vars.put("a", true);
		vars.put("b", true);
		vars.put("c", false);
		//change these?
		Assert.assertEquals(false, ExprParser.parse("a&(b&c)").evaluate(vars));
		Assert.assertEquals(true, ExprParser.parse("a&(b|c)").evaluate(vars));
		Assert.assertEquals(true, ExprParser.parse("~(a&c)").evaluate(vars));
		Assert.assertEquals(false, ExprParser.parse("~a&c").evaluate(vars));
		//maybe do more?????
	}
	
	@Test
	public void longExpr() {
		Map<String, Boolean> vars = new HashMap<>();
		vars.put("a", true);
		vars.put("b", true);
		vars.put("c", true);
		vars.put("d", true);
		vars.put("e", true);
		vars.put("f", false);
		Assert.assertEquals(false, ExprParser.parse("a&b&c&d&e&f").evaluate(vars));
		Assert.assertEquals(true, ExprParser.parse("a|b|c|d|e|f").evaluate(vars));
	}
	
//	@Test(expected=Variable.BadNameError.class)
//	public void missingVariable() {
//		Expr missing = ExprParser.parse("1+missing");
//		missing.evaluate();
//	}
	
	@Test
	public void checkOr() {
		Map<String, Boolean> vars = new HashMap<>();
		//a true, b true
		vars.put("a", true);
		vars.put("b", true);
		Expr missing = ExprParser.parse("a | b");
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b true
		vars.put("a", false);
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b false
		vars.put("b", false);
		Assert.assertEquals(false, missing.evaluate(vars));
		//a true, b false
		vars.put("a", true);
		Assert.assertEquals(true, missing.evaluate(vars));
	}
	
	@Test
	public void checkXor() {
		Map<String, Boolean> vars = new HashMap<>();
		//a true, b true
		vars.put("a", true);
		vars.put("b", true);
		Expr missing = ExprParser.parse("a # b");
		Assert.assertEquals(false, missing.evaluate(vars));
		//a false, b true
		vars.put("a", false);
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b false
		vars.put("b", false);
		Assert.assertEquals(false, missing.evaluate(vars));
		//a true, b false
		vars.put("a", true);
		Assert.assertEquals(true, missing.evaluate(vars));
		
	}
	
	@Test
	public void checkAnd() {
		Map<String, Boolean> vars = new HashMap<>();
		//a true, b true
		vars.put("a", true);
		vars.put("b", true);
		Expr missing = ExprParser.parse("a & b");
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b true
		vars.put("a", false);
		Assert.assertEquals(false, missing.evaluate(vars));
		//a false, b false
		vars.put("b", false);
		Assert.assertEquals(false, missing.evaluate(vars));
		//a true, b false
		vars.put("a", true);
		Assert.assertEquals(false, missing.evaluate(vars));
		
	}
	
	@Test
	public void testNot() {
		Map<String, Boolean> vars = new HashMap<>();
		vars.put("a", true);
		Assert.assertEquals(false, ExprParser.parse("~a").evaluate(vars));
		Assert.assertEquals(true, ExprParser.parse("~~a").evaluate(vars));
		vars.put("a", false);
		Assert.assertEquals(true, ExprParser.parse("~a").evaluate(vars));
		Assert.assertEquals(false, ExprParser.parse("~~a").evaluate(vars));
	}
	
	@Test
	public void testImplies() {
		Map<String, Boolean> vars = new HashMap<>();
		//a true, b true
		vars.put("a", true);
		vars.put("b", true);
		Expr missing = ExprParser.parse("a > b");
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b true
		vars.put("a", false);
		Assert.assertEquals(true, missing.evaluate(vars));
		//a false, b false
		vars.put("b", false);
		Assert.assertEquals(true, missing.evaluate(vars));
		//a true, b false
		vars.put("a", true);
		Assert.assertEquals(false, missing.evaluate(vars));
		
	}

}
