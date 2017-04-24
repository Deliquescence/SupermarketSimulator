package com.supermarketSimulator.items;

public class IngredientStack {
	
	//This class was needed because "ItemStack" now uses StoreItems
	public double quantity;
	public Item item;
	
	public IngredientStack(Item item, double quantity) {
		this.quantity = quantity;
		this.item = item;
	}
	
	public String toString() {
		return this.item.getName().split(" - ")[0] + ", " + this.quantity + "    ";
	}
}
