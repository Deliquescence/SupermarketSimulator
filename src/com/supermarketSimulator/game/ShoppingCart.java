package com.supermarketSimulator.game;

import com.supermarketSimulator.items.IngredientStack;
import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShoppingCart {
	
	private double happinessTotal = 0;
	private double healthTotal = 0;
	private double recipeBonusScore = 0;
	private ArrayList<ItemStack> itemStacks = new ArrayList<>();
	
	/**
	 * Add an ItemStack to the cart.
	 * If there already exists an ItemStack in this cart of the same item, combine their quantities.
	 *
	 * @param itemStack The ItemStack to add.
	 */
	public void add(ItemStack itemStack) {
		add(itemStack.getStoreItem(), itemStack.getQuantity());
	}
	
	/**
	 * Add some quantity of an item to this cart
	 * If there already exists an ItemStack in this cart of the same item, add to its quantity.
	 *
	 * @param storeItem     The StoreItem to be added
	 * @param quantity Quantity of that item to be added.
	 */
	public void add(StoreItem storeItem, int quantity) {
		for (ItemStack is : this.itemStacks) {
			Item item = storeItem.getItem();
			if (is.getItem().equals(item)) {
				is.setQuantity(is.getQuantity() + quantity);
				happinessTotal += (quantity * item.getBaseHappiness());
				healthTotal += (quantity * item.getBaseHealth());
				return;
			}
		}
		itemStacks.add(new ItemStack(storeItem, quantity));
		happinessTotal += (quantity * storeItem.getItem().getBaseHappiness());
		healthTotal += (quantity * storeItem.getItem().getBaseHealth());
	}
	
	/**
	 * Add a single Item to this cart.
	 * If there already exists an ItemStack in this cart of the same item, add to its quantity.
	 *
	 * @param storeItem The Item to be added.
	 */
	public void add(StoreItem storeItem) {
		add(storeItem, 1);
	}
	
	/**
	 * Remove an ItemStack from this cart.
	 * The existing ItemStack quantity will be subtracted by the given ItemStack's amount,
	 * unless this would result in a nonpositive quantity. In this case remove the entire ItemStack.
	 *
	 * @param itemStack The ItemStack to remove
	 * @return true if the cart changed as a result of the call
	 */
	public boolean remove(ItemStack itemStack) {
		return remove(itemStack.getItem(), itemStack.getQuantity());
	}
	
	/**
	 * Remove some quantity of an item from this cart.
	 * The existing ItemStack's quantity will be subtracted by the given amount,
	 * unless this would result in a nonpositive quantity. In this case remove the entire ItemStack.
	 *
	 * @param item     the Item to be removed
	 * @param quantity quantity of that item to be removed
	 * @return true if the cart changed as a result of the call
	 */
	public boolean remove(Item item, int quantity) {
		for (ItemStack is : this.itemStacks) {
			if (is.getItem().equals(item)) {
				if (is.getQuantity() - quantity > 0) {
					is.setQuantity(is.getQuantity() - quantity);
					happinessTotal -= (quantity * item.getBaseHappiness());
					healthTotal -= (quantity * item.getBaseHealth());
					return true;
				} else {
					this.itemStacks.remove(is);
					happinessTotal -= (is.getQuantity() * item.getBaseHappiness());
					healthTotal -= (is.getQuantity() * item.getBaseHealth());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Remove a single Item from this cart.
	 * The existing ItemStack's quantity will be subtracted by one,
	 * unless this would result in a nonpositive quantity. In this case remove the entire ItemStack.
	 *
	 * @param item the Item to be removed
	 * @return true if the cart changed as a result of the call
	 */
	public boolean remove(Item item) {
		return remove(item, 1);
	}
	
	/**
	 * Get a list of all the ItemStacks in this cart.
	 *
	 * @return A list of all the ItemStacks in this cart.
	 */
	public List<ItemStack> getItemStacks() {
		return this.itemStacks;
	}
	
	
	public double getHappinessTotal() {
		return happinessTotal;
	}
	
	public double getHealthTotal() {
		return healthTotal;
	}
	
	public double getRecipeBonusScore() {
		return recipeBonusScore;
	}
	
	public boolean containsItem(Item item) {
		for(ItemStack is : itemStacks) {
			if(is.getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}
}
