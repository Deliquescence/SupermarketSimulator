package com.supermarketSimulator.items;


import com.supermarketSimulator.database.Database;

import java.util.HashSet;

public class Recipe {
	
	public static HashSet<Recipe> recipes = new HashSet<>();
	
	public RecipeStack[] ingredients;
	
	/**
	 * Using a string with the format "Item#,Quantity,Item#,Quantity..." create a new Recipe.
	 * Do not add it to the set of recipes.
	 *
	 * @param recipeString the String of data
	 */
	public Recipe(String recipeString) {
		String[] split = recipeString.split(",");
		this.ingredients = new RecipeStack[split.length / 2];
		for (int x = 0; x < split.length; x += 2) {
			this.ingredients[x / 2] = new RecipeStack(Database.items[Integer.parseInt(split[x])], Integer.parseInt(split[x + 1]));
		}
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
		recipes.add(r);
		return r;
	}
}
