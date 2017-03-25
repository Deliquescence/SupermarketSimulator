package com.supermarketSimulator.items;

import com.supermarketSimulator.database.Database;

import java.util.HashMap;
import java.util.Map;

public class Category {
	
	public static HashMap<String, Category> categories = new HashMap<>();
	private final String name;
	private boolean healthy; //In general
	
	public Category(String name) {
		this.name = name;
	}
	
	/**
	 * Use this static method when creating categories
	 * @param name Name of the category to be created
	 * @return The created category object
	 */
	public static Category addCategory(String name) {
		Category c = new Category(name);
		categories.put(name, c);
		return c;
	}
	
	/**
	 * Procedurally determine if a category is healthy in general
	 */
	public static void determineHealthiness() {
		HashMap<Category, Integer> outcomes = new HashMap<>();
		for(Category c : categories.values()) {
			outcomes.put(c, 0);
		}
		
		for(Item i : Database.items) {
			Category c = i.getCategory();
			int x = outcomes.get(c);
			if(i.getBaseHealth() - i.getBaseHappiness() > 0.5) {
				outcomes.replace(c, x + 1);
			}
			else if(i.getBaseHealth() / i.getBaseCost() > 1.5) {
				outcomes.replace(c, x + 1);
			}
			else {
				outcomes.replace(c, x - 1);
			}
		}
		
		for(Map.Entry<Category, Integer> entry : outcomes.entrySet()) {
			entry.getKey().healthy = (entry.getValue() > 0);
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isHealthy() {
		return healthy;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
