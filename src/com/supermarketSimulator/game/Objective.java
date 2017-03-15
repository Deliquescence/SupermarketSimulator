package com.supermarketSimulator.game;


import com.supermarketSimulator.items.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by cjt on 3/15/2017.
 */

public class Objective {
	
	private int quantity;
	private Category category;
	
	private static final int OBJECTIVE_LIMIT = 4;
	
	
	public static ArrayList<Objective> objectivesList;
	
	public Objective(int quantity, Category category) {
		this.quantity = quantity;
		this.category = category;
	}
	
	/**
	 * Generate a new random list of Objectives.
	 *
	 */
	public static void generate() {
		
		objectivesList.clear(); // remove any objectives saved in the list
		
		//
		int[] catValues = new Random().ints(0, OBJECTIVE_LIMIT).distinct()
				.limit(Category.categories.size()).toArray();
		
		for (int i = 0; i < catValues.length; i++) {
			int counter = 0;
			int quan = new Random(System.currentTimeMillis()).nextInt(OBJECTIVE_LIMIT);
			for(Category cat: Category.categories.values()) {
				counter++;
				if (counter == catValues[i]) {
					objectivesList.add(new Objective(quan, cat));
				}
			}
		}
			
			
	}
	
	/**
	 * Print the contents of the ObjectiveList to the console window.
	 */
	public static void printObjectives() {
		for(Objective obj : objectivesList) {
			System.out.println("You must purchase " + obj.quantity + " " + obj.category.getName());
		}
	}

}



