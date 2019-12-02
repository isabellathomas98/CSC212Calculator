package edu.smith.cs.csc212.calc;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

// Tokenization means to break up the input into pieces called tokens 

public class TokenizerTest {

	@Test
	public void testTokenizer() {
		String input = "(1+2-3*4/5)";
		Assert.assertEquals(Arrays.asList("(", "1", "+", "2", "-", "3", "*", "4", "/", "5", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerWS() {
		String input = " ( 1 + 2 - 3 * 4 / 5 ) ";
		Assert.assertEquals(Arrays.asList("(", "1", "+", "2", "-", "3", "*", "4", "/", "5", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerNothing() {
		String input = "  ";
		Assert.assertEquals(Collections.emptyList(), Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerJustNumber() {
		String input = "1234";
		Assert.assertEquals(Arrays.asList("1234"), Tokenizer.tokenize(input));
		//print input
		System.out.println("Input:"+(input));
		//print out output
		System.out.println("Tokenized:"+Tokenizer.tokenize(input));
	}
	
	@Test(expected=RuntimeException.class)
	public void testTokenizerError() {
		String input = " 1 !";
		Tokenizer.tokenize(input);
	}
	
	@Test
	public void testVars() {
		String input = "(a+b-c*d/e)";
		Assert.assertEquals(Arrays.asList("(", "a", "+", "b", "-", "c", "*", "d", "/", "e", ")"),
				Tokenizer.tokenize(input));
		//print input
		System.out.println("Input:"+(input));
		//print out output
		System.out.println("Tokenized:"+Tokenizer.tokenize(input));
	}
	@Test
	public void testVarsWS() {
		String input = "( a +b -c * d / e)";
		Assert.assertEquals(Arrays.asList("(", "a", "+", "b", "-", "c", "*", "d", "/", "e", ")"),
				Tokenizer.tokenize(input));
		//print input
		System.out.println("Input:"+(input));
		//print out output
		System.out.println("Tokenized:"+Tokenizer.tokenize(input));
		System.out.println("BELLABELLABELLA");
	}
}
