package com.supermarketSimulator.items;

public class RecipeStack {
	
	//This class was needed because "ItemStack" now uses StoreItems
	public int quantity;
	public Item item;
	
	public RecipeStack(Item item, int quantity) {
		this.quantity = quantity;
		this.item = item;
	}
}
