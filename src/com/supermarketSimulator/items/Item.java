package com.supermarketSimulator.items;

import java.util.HashMap;

public class Item {
	
	public static HashMap<Integer, Item> items = new HashMap<>();
	private static int lastId = 0;
	private final String name;
	private final int id;
	private int baseHealth;
	private int baseHappiness;
	private Category category;
	private double baseCost;
	
	public Item(String name, int health, int happiness, Category category) {
		lastId++;
		this.name = name;
		baseHealth = health;
		baseHappiness = happiness;
		this.category = category;
		id = lastId;
	}
	
	/**
	 * Creates a new item from a string and adds it to the HashMap of all existing items
	 *
	 * @param itemString A comma-separated list of name, baseHealth, baseHappiness, and category
	 */
	public static void itemFromString(String itemString) {
		String[] array = itemString.split(",");
		Category category;
		if (Category.categories.containsKey(array[3])) {
			category = new Category(array[3]);
		} else {
			category = Category.addCategory(array[3]);
		}
		items.put(lastId + 1, new Item(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), category));
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getBaseHealth() {
		return baseHealth;
	}
	
	public int getBaseHappiness() {
		return baseHappiness;
	}
	
	public Category getCategory() {
		return category;
	}
}
