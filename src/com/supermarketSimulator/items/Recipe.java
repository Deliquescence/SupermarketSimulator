package com.supermarketSimulator.items;


import com.supermarketSimulator.database.Database;

import java.util.HashSet;

public class Recipe {
	
	public static HashSet<Recipe> recipes = new HashSet<>();
	
	public RecipeStack[] recipe;
	
	public Recipe(String recipeString) {
		//Using format "Item#,Quantity,Item#,Quantity...
		String[] split = recipeString.split(",");
		recipe = new RecipeStack[split.length / 2];
		for(int x = 0; x < split.length; x += 2) {
			recipe[x / 2] = new RecipeStack(Database.items[Integer.parseInt(split[x])], Integer.parseInt(split[x+1]));
		}
	}
	
}
