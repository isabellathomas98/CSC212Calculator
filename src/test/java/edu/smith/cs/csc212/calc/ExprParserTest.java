package edu.smith.cs.csc212.calc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ExprParserTest {

	@Test
	public void testAdd() {
		Assert.assertEquals(3, ExprParser.parse("1+2").evaluate());
	}
	@Test
	public void testSub() {
		Assert.assertEquals(-1, ExprParser.parse("1-2").evaluate());
		//print "input expression"
		System.out.println("Calculate 1-2");
		//print out output
		System.out.println("Result: "+ExprParser.parse("1-2").evaluate());
		Assert.assertEquals(1, ExprParser.parse("3-2").evaluate());
	}
	@Test
	public void testMul() {
		Assert.assertEquals(2, ExprParser.parse("1*2").evaluate());
		
		//print "input expression"
		System.out.println("Calculate 1*2");
		//print out output
		System.out.println("Result: "+ExprParser.parse("1*2").evaluate());
		
		Assert.assertEquals(6, ExprParser.parse("3*2").evaluate());
	}
	@Test
	public void testDiv() {
		Assert.assertEquals(0, ExprParser.parse("1/2").evaluate());
		Assert.assertEquals(5, ExprParser.parse("10/2").evaluate());
	}
	@Test
	public void testNegation() {
		Assert.assertEquals(-2, ExprParser.parse("-2").evaluate());
		Assert.assertEquals(2, ExprParser.parse("--2").evaluate());
		// Maybe this should be an error...
		Assert.assertEquals(2, ExprParser.parse("--------2").evaluate());
	}
	
	@Test
	public void operatorPrecedence() {
		Assert.assertEquals(7, ExprParser.parse("1+2*3").evaluate());
		Assert.assertEquals(-5, ExprParser.parse("1-2*3").evaluate());
		Assert.assertEquals(1, ExprParser.parse("1+2/3").evaluate());
		Assert.assertEquals(1, ExprParser.parse("1-2/3").evaluate());
	}
	
	@Test
	public void parenPrecedence() {
		Assert.assertEquals(9, ExprParser.parse("(1+2)*3").evaluate());
		Assert.assertEquals(-3, ExprParser.parse("(1-2)*3").evaluate());
		Assert.assertEquals(1, ExprParser.parse("(1+2)/3").evaluate());
		Assert.assertEquals(0, ExprParser.parse("(1-2)/3").evaluate());
	}
	
	@Test
	public void longExpr() {
		Assert.assertEquals(55, ExprParser.parse("1+2+3+4+5+6+7+8+9+10").evaluate());
		Assert.assertEquals(120, ExprParser.parse("1*2*3*4*5").evaluate());
	}
	
	@Test(expected=Variable.BadNameError.class)
	public void missingVariable() {
		Expr missing = ExprParser.parse("1+missing");
		missing.evaluate();
	}
	
	@Test
	public void checkOr() {
		Map<String, Boolean> vars = new HashMap<>();
		vars.put("a", true);
		vars.put("b", true);
		Expr missing = ExprParser.parse("a | b");
		Assert.assertEquals(true, missing.evaluate(vars));
		vars.put("a", false);
		Assert.assertEquals(true, missing.evaluate(vars));
		vars.put("b", false);
		Assert.assertEquals(false, missing.evaluate(vars));
		vars.put("a", true);
		Assert.assertEquals(true, missing.evaluate(vars));
	}
	
	@Test
	public void checkAnd() {
		
	}

}
