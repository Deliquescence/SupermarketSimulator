package com.supermarketSimulator.game;

import com.supermarketSimulator.items.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	
	private List<ItemStack> itemStacks = new ArrayList<>();
	
	/**
	 * Add an ItemStack to the cart.
	 * If there already exists an ItemStack in this cart of the same item, combine their quantities.
	 *
	 * @param itemStack The ItemStack to add.
	 */
	public void add(ItemStack itemStack) {
		
		for (ItemStack is : this.itemStacks) {
			if (is.getItem().equals(itemStack.getItem())) {
				is.setQuantity(is.getQuantity() + itemStack.getQuantity());
			}
		}
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
