package com.supermarketSimulator.items;

import javax.swing.*;

public class Item {
	
	private final int ID;
	private final String name;
	private final int baseHealth;
	private final int baseHappiness;
	private final Category category;
	private final double baseCost;
	private final ImageIcon icon;
	
	public Item(int id, String name, int health, int happiness, Category category, double cost, ImageIcon icon) {
		this.ID = id;
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
	 * @param itemString A comma-separated list of id, name, baseHealth, baseHappiness, category, cost
	 */
	public static Item itemFromString(String itemString) {
		String[] array = itemString.split(",");
		Category category;
		if (Category.categories.containsKey(array[4])) {
			category = Category.categories.get(array[4]);
		} else {
			category = Category.addCategory(array[4]);
		}
		return new Item(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), category, Double.parseDouble(array[5]), null); //TODO set icon
	}
	
	public int getID() { return ID; }
	
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
