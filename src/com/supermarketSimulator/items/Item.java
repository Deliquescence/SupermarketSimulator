package com.supermarketSimulator.items;

import javax.swing.*;

public class Item {
	
	private final int ID;
	private final String name;
	private final double baseHealth;
	private final double baseHappiness;
	private final Category category;
	private final double baseCost;
	private final ImageIcon icon;
	private final String shortName;
	
	public Item(int id, String name, double health, double happiness, Category category, double cost, ImageIcon icon) {
		this.ID = id;
		this.name = name;
		this.baseHealth = health;
		this.baseHappiness = happiness;
		this.category = category;
		this.baseCost = cost;
		this.icon = icon;
		
		String[] s = name.split(" - ");
		this.shortName = s[0];
	}
	
	/**
	 * Creates a new item from a string.
	 *
	 * @param itemString A comma-separated list of Category, id, Name, Cost, Health, Happiness
	 */
	public static Item itemFromString(String itemString) {
		String[] array = itemString.split(",");
		Category category;
		if (Category.categories.containsKey(array[0])) {
			category = Category.categories.get(array[0]);
		} else {
			category = Category.addCategory(array[0]);
		}
		return new Item(Integer.parseInt(array[1]), array[2], Double.parseDouble(array[4]), Double.parseDouble(array[5]), category, Double.parseDouble(array[3]), null); //TODO set icon
	}
	
	public int getID() {
		return ID;
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
	
	public String getShortName() { return shortName; }
	
	public double getBaseHealth() {
		return baseHealth;
	}
	
	public double getBaseHappiness() {
		return baseHappiness;
	}
	
	public Category getCategory() {
		return category;
	}
}
