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
	private static final int MAX_NUM_OBJECTIVES = 3;
	
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
	 * Generate random quantities and categories, and add
	 * the corresponding objectives to the list.
	 *
	 */
	public static void generate() {
		
		if(objectivesList == null) {
			objectivesList = new ArrayList();
		}
		else {
			objectivesList.clear(); // remove any objectives saved in the list
		}
		
		//
		
		int numObjectives = new Random(System.currentTimeMillis()).nextInt(MAX_OBJECTIVE_QUANTITY);
		
		Random rand = new Random(System.currentTimeMillis());
		while (numObjectives != 0) {
			
			int randQuantity = rand.nextInt(MAX_OBJECTIVE_QUANTITY);
			
			int counter = 0;
			int randCat = rand.nextInt(Category.categories.size());
			for(Category randCategory : Category.categories.values()) {
				if(counter == randCat) {
					objectivesList.add(new Objective(randQuantity, randCategory));
					break;
				}
				counter++;
				
			}
			
			numObjectives--;
		}
	}
	
	/**
	 * Print the contents of the ObjectiveList to the console window.
	 */
	public static void printObjectives() {
		for(Objective obj : objectivesList){
			System.out.print(obj.toString() +"\n");
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

}



