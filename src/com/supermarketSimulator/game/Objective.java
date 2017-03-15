package com.supermarketSimulator.game;


import com.supermarketSimulator.items.Category;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cjt on 3/15/2017.
 */

public class Objective {
	
	private int quantity;
	private Category category;
	
	private static final int MAX_OBJECTIVE_QUANTITY = 4;
	private static final int MAX_NUM_OBJECTIVES = 4;
	
	public static ArrayList<Objective> objectivesList;
	
	public Objective(int quantity, Category category) {
		this.quantity = quantity;
		this.category = category;
	}
	
	/**
	 * Generate a new random list of Objectives.
	 *
	 * If the ArrayList is null, create a new Array. Otherwise,
	 * clear any remaining elements left in the ArrayList.
	 * 
	 *
	 */
	public static void generate() {
		
		if(objectivesList == null) {
			objectivesList = new ArrayList<Objective>();
		}
		else {
			objectivesList.clear(); // remove any objectives saved in the list
		}
		
		//
		
		
		Random rand = new Random(System.currentTimeMillis());
		int numObjectives = 1+ rand.nextInt(MAX_OBJECTIVE_QUANTITY-1);
		
		int[] randQuantity =rand.ints(1, MAX_OBJECTIVE_QUANTITY).limit(MAX_NUM_OBJECTIVES).toArray();
		int[] randIndexes = rand.ints(0, Category.categories.size()).distinct()
				.limit(Category.categories.size()).toArray();
		Category[] categories = Category.categories.values().toArray(new Category[0]);
			
		for(int i = 0; i < numObjectives; i++) {
			objectivesList.add(new Objective(randQuantity[i], categories[randIndexes[i]]));
		}
	}
	
	/**
	 * Print the contents of the ObjectiveList to the console window.
	 */
	public static void printObjectives() {
		System.out.println("\nObjective List");
		System.out.println("----------------");
		for(Objective obj : objectivesList) {
			System.out.print(obj.toString() + "\n");
		}
	}
	
	/**
	 * toString() representation of an Objective
	 *
	 * @return A String with an Objective's quantity and category.
	 */
	public String toString() {
		return this.quantity + " " + this.category.getName();
	}
	
	public int getQuantity() { return this.quantity; }
	
	public Category getCategory() { return this.category; }

}



