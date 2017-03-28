package com.supermarketSimulator.items;


import com.supermarketSimulator.database.Database;

import java.util.HashMap;
import java.util.HashSet;

public class Recipe {
	
	/**
	 * Using an Item as key, store the set of all Recipes that use that Item
	 */
	public static HashMap<Item, HashSet<Recipe>> recipesByItem = new HashMap<>();
	
	public IngredientStack[] ingredients;
	private double scoreValue = 0;
	
	
	/**
	 * Using a string with the format "Item#,Quantity,Item#,Quantity..." create a new Recipe.
	 * Do not add it to the set of recipes.
	 *
	 * @param recipeString the String of data
	 */
	public Recipe(String recipeString) {
		String[] split = recipeString.split(",");
		this.ingredients = new IngredientStack[split.length / 2];
		for (int x = 0; x < split.length; x += 2) {
			this.ingredients[x / 2] = new IngredientStack(Database.items[Integer.parseInt(split[x])], Integer.parseInt(split[x + 1]));
		}
		//Let's try this for now...
		double score = 0;
		for (IngredientStack stack : ingredients) {
			Item i = stack.item;
			score += 2 * (i.getBaseHappiness() + i.getBaseHealth());
		}
		scoreValue = score;
		System.out.println("DEBUG this is worth: " + score);
	}
	
	/**
	 * Using a string with the format "Item#,Quantity,Item#,Quantity..." create a new Recipe.
	 * Add it to the set of recipes.
	 *
	 * @param recipeString the String of data
	 * @return a Recipe with the given ingredients
	 */
	public static Recipe recipeFromString(String recipeString) {
		Recipe r = new Recipe(recipeString);
		for (IngredientStack stack : r.ingredients) {
			Item i = stack.item;
			if (recipesByItem.containsKey(i)) {
				recipesByItem.get(i).add(r);
			} else {
				recipesByItem.put(i, new HashSet<>());
				recipesByItem.get(i).add(r);
			}
		}
		return r;
	}
}
