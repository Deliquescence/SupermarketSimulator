package com.supermarketSimulator.items;


import com.supermarketSimulator.game.StoreItem;

public class ItemStack {
	
	private final StoreItem item;
	private int quantity;
	
	/**
	 * Create a new ItemStack, which allows an StoreItem to have quantity stored alongside it.
	 *
	 * @param item     The StoreItem in this ItemStack
	 * @param quantity The quantity of the item in the ItemStack
	 */
	public ItemStack(StoreItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public StoreItem getStoreItem() {
		return item;
	}
	
	public Item getItem() {
		return item.getItem();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getCost() {
		return item.getUnitCost();
	}
}
