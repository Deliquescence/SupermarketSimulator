package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private ArrayList<ItemStack> itemStacks = new ArrayList<>();
	
	/**
	 * Add an ItemStack to the cart.
	 * If there already exists an ItemStack in this cart of the same item, combine their quantities.
	 *
	 * @param itemStack The ItemStack to add.
	 */
	public void add(ItemStack itemStack) {
		add(itemStack.getItem(), itemStack.getQuantity());
	}
	
	/**
	 * Add some quantity of an item to this cart
	 * If there already exists an ItemStack in this cart of the same item, add to its quantity.
	 *
	 * @param item     The Item to be added.
	 * @param quantity Quantity of that item to be added.
	 */
	public void add(Item item, int quantity) {
		for (ItemStack is : this.itemStacks) {
			if (is.getItem().equals(item)) {
				is.setQuantity(is.getQuantity() + quantity);
				return;
			}
		}
		itemStacks.add(new ItemStack(item, quantity));
	}
	
	/**
	 * Add a single Item to this cart.
	 * If there already exists an ItemStack in this cart of the same item, add to its quantity.
	 *
	 * @param item The Item to be added.
	 */
	public void add(Item item) {
		add(item, 1);
	}
	
	/**
	 * Get a list of all the ItemStacks in this cart.
	 *
	 * @return A list of all the ItemStacks in this cart.
	 */
	public List<ItemStack> getItemStacks() {
		return this.itemStacks;
	}
}
