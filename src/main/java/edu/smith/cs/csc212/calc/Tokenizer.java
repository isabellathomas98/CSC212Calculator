package edu.smith.cs.csc212.calc;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
	public RuntimeException error(String msg) {
		return new RuntimeException(msg+": "+this.toString());
	}
	private char[] data;
	int position;
	
	public Tokenizer(String input) {
		this.data = input.toCharArray();
		this.position = 0;
	}
	
	private int peek() {
		if (position < data.length) {
			return data[position];
		}
		return -1;
	}
	
	public int remaining() {
		return data.length - position;
	}
	
	// rest="abcd" 
	// (then .getc()=> "a") 
	// then rest="bcd"
	public String rest() {
		if (position >= data.length) {
			return "";
		}
		return new String(data, position, this.remaining());
	}
	
	
	public String toString() {
		return "Tokenizer(@"+position+", ..."+rest()+")";
	}
	
	// "abcd".consume(2) => "ab", rest="cd"
	public String consume(int amt) {
		String out = new String(data, position, amt);
		position += amt;
		return out;
	}
	
	public void skipWhitespace() {
		while(true) {
			int next = peek();
			if (next == -1) {
				return;
			}
			char ch = (char) next;
			if (Character.isWhitespace(ch)) {
				position++;
				continue;
			}
			break;
		}
	}
	
	public String nextToken() {
		skipWhitespace();
		int next = peek();
		if (next == -1) {
			return null;
		}
		
		// and = &, or = |, xor = #  not = ~, implies = >
		char ch = (char) next;
		if (ch == 'a' || ch == 'x' || ch == 'n') {
			return consume(3);
		} else if (ch == 'o') {
			return consume(2);
		} else if (ch == 'i') {
			return consume(7);
		}
		
		// Assume it's part of a number or variable:
		StringBuilder id = new StringBuilder();
		while (Character.isLetterOrDigit(ch)) {
			id.append(ch);
			position++;
			next = peek();
			if (next == -1) {
				break;
			}
			ch = (char) next;
		}
		if (id.length() > 0) {
			return id.toString();
		}
		throw error("Unknown token.");
	}
	
	public static List<String> tokenize(String input) {
		// creates a blank output arraylist
		List<String> output = new ArrayList<>();
		// creates a new tokenizer w the input string -> contains an array of the characters in the input
		Tokenizer tok = new Tokenizer(input);
		while(true) {
			String token = tok.nextToken();
			if (token == null) break;
			output.add(token);
		}
		return output;
	}
	
}