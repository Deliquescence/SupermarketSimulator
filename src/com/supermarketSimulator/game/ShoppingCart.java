package com.supermarketSimulator.game;

import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Item;

import java.util.ArrayList;
import java.util.HashSet;

public class ShoppingCart {
	
	private HashSet<ItemStack> itemStacks = new HashSet<>();
	
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
				return;
			}
		}
		itemStacks.add(itemStack);
	}

	public void add(Item item, int quantity) {
		for(ItemStack is : this.itemStacks) {
			if(is.getItem().equals(item)) {
				is.setQuantity(is.getQuantity() + quantity);
				return;
			}
		}
		itemStacks.add(new ItemStack(item, quantity));
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
