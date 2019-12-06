package edu.smith.cs.csc212.calc;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
	
	/**
	* truth tables
	*/
	
	public static ArrayList<ArrayList<Boolean>> truthComboMaker
	(ArrayList<ArrayList<Boolean>> values) {
		
		ArrayList<ArrayList<Boolean>> more = new ArrayList<>();
		for (ArrayList<Boolean> value : values) {
			ArrayList<Boolean> newValue = (ArrayList<Boolean>) value.clone();
			newValue.add(true);
			more.add(newValue);
		}
		for (ArrayList<Boolean> value : values) {
			ArrayList<Boolean> newValue = (ArrayList<Boolean>) value.clone();
			newValue.add(false);
			more.add(newValue);
		}
		
		return more;
	}
	
	public static void tablePrinter (String input){
		Expr inputTree = ExprParser.parse(input);
		
		Set<String> inputVars = new HashSet<>();
		inputTree.findVars(inputVars);
		
		// making the set of the empty set
		ArrayList<Boolean> empty = new ArrayList<>();
		ArrayList<ArrayList<Boolean>> truthValues = new ArrayList<>();
		truthValues.add(empty);
		
		// making the truth values we want out of the empty set
		for (int i = 0; i < inputVars.size(); i++) {
			truthValues = truthComboMaker(truthValues);
		}
		
		for (ArrayList<Boolean> truthValueCombo : truthValues) {
			Map<String, Boolean> truthAssignments = new HashMap<>();
			int tvCounter = 0;
			for (String var : inputVars) {
				truthAssignments.put(var, truthValueCombo.get(tvCounter));
				System.out.println(var+ ": " + truthValueCombo.get(tvCounter));
				tvCounter++;
			}
		}
		
		//prints out who;e expression
		System.out.println("Expression: "+input);
		//prints out variables
		System.out.println("Variables: " +inputVars);
		System.out.println("Truth values: " +truthValues);
		System.out.println("**********_____________________");

	}
	
	public static void main(String[] args) {
		tablePrinter("a | b");

  }

}
