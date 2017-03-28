package com.supermarketSimulator.items;


import com.supermarketSimulator.database.Database;

import java.util.HashSet;
import java.util.Set;

public class Recipe {
	
	public static Set<Recipe> recipes;
	
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
		recipes.add(r);
		return r;
	}
	
	/**
	 * Given an Item, find all the Recipes with that Item.
	 *
	 * @param item the Item
	 * @return Set of all Recipes that use that item
	 */
	public static Set<Recipe> getRecipesWithItem(Item item) {
		HashSet<Recipe> recipesWithItem = new HashSet<>();
		for (Recipe r : recipes) {
			for (IngredientStack is : r.ingredients) {
				if (is.item == item) {
					recipesWithItem.add(r);
					break;
				}
			}
		}
		return recipesWithItem;
	}
}
