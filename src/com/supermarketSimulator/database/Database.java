package com.supermarketSimulator.database;

import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.Recipe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Database {
    
    public static Item[] items;
    public static Map<Category, Set<Item>> itemsByCategory = new HashMap<>();
	
	private final String itemFileString;
	private final String recipeFileString;
	/**
	 * Creates a database from a csv
	 *
	 * @param itemFileString The name of the csv file as a String
	 */
	public Database(String itemFileString, String recipeFileString) {
		this.itemFileString = itemFileString;
		this.recipeFileString = recipeFileString;
	}
	
	/**
	 * Reads in lines from a csv to populate the items in the game
	 */
	public void readItems() {
		try {
			String line;
			FileInputStream file = new FileInputStream(itemFileString);
			InputStreamReader reader = new InputStreamReader(file, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(reader);
			items = new Item[Integer.parseInt(br.readLine())];
			System.out.println("Length of the array is " + items.length);
			while ((line = br.readLine()) != null) {
				Item i = Item.itemFromString(line);
				items[i.getID()] = i;
				itemsByCategory.computeIfAbsent(i.getCategory(), k -> new HashSet<>()); //Init the HashSet for the category if null
				itemsByCategory.get(i.getCategory()).add(i);
			}
			Category.determineHealthiness();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	/**
		Read recipes in from recipeFileString.
	 */
	public void readRecipes() {
		try {
			String line;
			FileInputStream file = new FileInputStream(recipeFileString);
			InputStreamReader reader = new InputStreamReader(file, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(reader);
			
			while ((line = br.readLine()) != null) {
				Recipe.recipeFromString(line);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
