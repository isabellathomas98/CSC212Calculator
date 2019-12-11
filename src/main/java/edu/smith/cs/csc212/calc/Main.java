package edu.smith.cs.csc212.calc;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
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
		int barLength=inputVars.size();
		String bar="~~~~~~~~~~~~~~~~~~".repeat(barLength);
		System.out.println(bar);
		System.out.format("%20s","TRUTH TABLE   by Isabel and Bella"+"\n");
		System.out.println(bar);
		for(String var : inputVars) {
			System.out.format("%8s ",var+" ");}
		System.out.format("%6s %13s","*",input);
		System.out.println(" ");
		System.out.println(bar);
		for (ArrayList<Boolean> truthValueCombo : truthValues) {
			Map<String, Boolean> truthAssignments = new HashMap<>();
			int tvCounter = 0;
			for (String var : inputVars) {
				truthAssignments.put(var, truthValueCombo.get(tvCounter));
				
				System.out.format("%10s",truthValueCombo.get(tvCounter).toString().toUpperCase()+"    ");
				tvCounter++;
			}
			System.out.format("%3s %10s","*",ExprParser.parse(input).evaluate(truthAssignments).toString().toUpperCase()+"\n");
			
		}
		System.out.println(bar);
		
			}
		
		
		
	
	
	public static void main(String[] args) {
		tablePrinter("a | b | c ");
		
		

  }

}
