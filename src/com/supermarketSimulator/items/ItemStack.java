package com.supermarketSimulator.items;


public class ItemStack {
	
	private final Item item;
	private int quantity;
	
	/**
	 * Create a new ItemStack, which allows an Item to have quantity stored alongside it.
	 *
	 * @param item The Item in this ItemStack
	 * @param quantity The quantity of items in the ItemStack
	 */
	public ItemStack(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
