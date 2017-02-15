package com.supermarketSimulator.items;

import javax.swing.*;

public class Item {
	
	private final String name;
	private final int baseHealth;
	private final int baseHappiness;
	private final Category category;
	private final double baseCost;
	private final ImageIcon icon;
	
	public Item(String name, int health, int happiness, Category category, double cost, ImageIcon icon) {
		this.name = name;
		this.baseHealth = health;
		this.baseHappiness = happiness;
		this.category = category;
		this.baseCost = cost;
		this.icon = icon;
	}
	
	/**
	 * Creates a new item from a string and adds it to the HashMap of all existing items
	 *
	 * @param itemString A comma-separated list of name, baseHealth, baseHappiness, category, cost
	 */
	public static Item itemFromString(String itemString) {
		String[] array = itemString.split(",");
		Category category;
		if (Category.categories.containsKey(array[3])) {
			category = new Category(array[3]);
		} else {
			category = Category.addCategory(array[3]);
		}
		return new Item(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), category, Double.parseDouble(array[3]), null); //TODO set icon
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public double getBaseCost() {
		return baseCost;
	}
	
	public String getName() {
		return name;
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
