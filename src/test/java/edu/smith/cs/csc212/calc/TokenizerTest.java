package edu.smith.cs.csc212.calc;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

// Tokenization means to break up the input into pieces called tokens 

public class TokenizerTest {

	@Test
	public void testTokenizer() {
		String input = "((a|b)&c)";
		Assert.assertEquals(Arrays.asList("(", "(", "a", "|", "b", ")", "&", "c", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerWS() {
		String input = "( ( a | b ) & ( c > ( d # f ) )";
		Assert.assertEquals(Arrays.asList("(", "(", "a", "|", "b", ")", "&", "(", "c", ">", "(", "d",
				"#", "f", ")", ")"),
				Tokenizer.tokenize(input));
	}
	
	@Test
	public void testTokenizerNothing() {
		String input = "  ";
		Assert.assertEquals(Collections.emptyList(), Tokenizer.tokenize(input));
	}
	
	@Test(expected=RuntimeException.class)
	public void testTokenizerError() {
		String input = " 1 !";
		Tokenizer.tokenize(input);
	}
}
