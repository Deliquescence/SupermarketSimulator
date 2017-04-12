package com.supermarketSimulator.items;


import com.supermarketSimulator.database.Database;
import com.supermarketSimulator.game.Store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Recipe implements Comparable<Recipe> {
	
	/**
	 * Using an Item as key, store the set of all Recipes that use that Item
	 */
	public static HashMap<Item, HashSet<Recipe>> recipesByItem = new HashMap<>();
	public static TreeSet<Recipe> sortedRecipes = new TreeSet<>();
	
	public IngredientStack[] ingredients;
	
	private final String recipeName;
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
		recipeName = split[0];
		for (int x = 1; x < split.length; x += 2) {
			this.ingredients[x / 2] = new IngredientStack(Database.items[Integer.parseInt(split[x])], Integer.parseInt(split[x + 1]));
		}
		//Let's try this for now...
		double score = 0;
		for (IngredientStack stack : ingredients) {
			Item i = stack.item;
			score += 2 * (i.getBaseHappiness() + i.getBaseHealth());
		}
		scoreValue = score;
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
		sortedRecipes.add(r);
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
	
	/**
	 * Verbose debug method that prints all the recipes out item-by-item
	 */
	public static void debugPrintRecipes() {
		//Warning, very verbose
		recipesByItem.forEach((item, hashSet) -> {
			System.out.println("Recipes containing " + item.getName());
			for(Recipe r: hashSet) {
				System.out.println("Recipe: " + r.getName());
				for(IngredientStack stack : r.ingredients ) {
					System.out.println("Item " + stack.item.getName() + " quantity " + stack.quantity);
				}
				System.out.println("---------");
			}
		});
	}
	
	public static boolean storeCheck(Store store, Recipe recipe) {
		for(IngredientStack stack : recipe.ingredients) {
			if(!store.containsItem(stack.item)) {
				return true; //We want this to return true because we want to removeIf the recipe cannot be made
			}
		}
		return false;
	}
	
	
	public String getName() {
		return recipeName;
	}
	
	
	public double getScore() {
		return scoreValue;
	}
	
	@Override
	public int compareTo(Recipe o) {
		return this.getName().compareTo(o.getName());
	}
}
