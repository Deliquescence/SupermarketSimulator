package com.supermarketSimulator.items;

import java.util.HashMap;

public class Category {
	
	public static HashMap<String, Category> categories = new HashMap<>();
	private final String name;
	
	public Category(String name) {
		this.name = name;
	}
	
	public static Category addCategory(String name) {
		Category c = new Category(name);
		categories.put(name, c);
		return c;
	}
	
	public String getName() {
		return name;
	}
}
