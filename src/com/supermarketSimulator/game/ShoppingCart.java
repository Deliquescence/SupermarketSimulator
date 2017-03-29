package com.supermarketSimulator.game;

import com.supermarketSimulator.items.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ShoppingCart {
	
	private double happinessTotal = 0;
	private double healthTotal = 0;
	private double recipeBonusScore = 0;
	public HashSet<Recipe> potentialRecipes = new HashSet<>();
	private ArrayList<ItemStack> itemStacks = new ArrayList<>();
	private HashMap<Item, Integer> unpairedItems = new HashMap<>();
	
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
		//TODO add recipe logic in
		for (ItemStack is : this.itemStacks) {
			Item item = storeItem.getItem();
			if (is.getItem().equals(item)) {
				is.setQuantity(is.getQuantity() + quantity);
				happinessTotal += (quantity * item.getBaseHappiness());
				healthTotal += (quantity * item.getBaseHealth());
				updateUnpaired(is.getItem(), true, quantity);
				return;
			}
		}
		itemStacks.add(new ItemStack(storeItem, quantity));
		happinessTotal += (quantity * storeItem.getItem().getBaseHappiness());
		healthTotal += (quantity * storeItem.getItem().getBaseHealth());
		updateUnpaired(storeItem.getItem(), true, quantity);
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
	 * Called when items are added to cart. Updates eligible recipes, which are recipes the player has the ingredients
	 * to make.
	 * @param item Item to be checked
	 */
	private void checkRecipes(Item item) {
		//Surrender now or prepare to fight!
		HashSet<Recipe> eligibleRecipes = new HashSet<>();
		if(Recipe.recipesByItem.containsKey(item)) {
			RecipeLoop:
			for(Recipe r : Recipe.recipesByItem.get(item)) { //All recipes that can be made with this item
				for(IngredientStack stack : r.ingredients) {
					if(unpairedItems.containsKey(stack.item)) {
						if(unpairedItems.get(stack.item) < stack.quantity) { //Insufficient quantity
							break RecipeLoop;
						}
					} else { //Wrong item types
						break RecipeLoop;
					}
				}
				eligibleRecipes.add(r);
			}
			
		}
		potentialRecipes.addAll(eligibleRecipes); //Adding everything from this local HashSet to the global one.
	}
	
	/**
	 * Updates the list of unpaired items, i.e. items which aren't being used in any recipe.
	 * @param i Item
	 * @param adding True if adding, false if subtracting the given quantity
	 * @param quantity The quantity of that item
	 */
	private void updateUnpaired(Item i, boolean adding, int quantity) {
		if(adding) {
			if(!unpairedItems.containsKey(i)){
				unpairedItems.put(i, quantity);
			}
			else {
				int x = unpairedItems.get(i);
				unpairedItems.put(i, x + quantity);
			}
		}
		else { //By deduction, removing
			//TODO Logic for undoing recipes when removing items from the cart.
		}
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
	
	/**
	 * Given a Category, count how many total items in this cart are in that category.
	 *
	 * @param category the Category
	 * @return the number of items
	 */
	public int numberOfItemsInCategory(Category category){
		int count = 0;
		for (ItemStack is : this.itemStacks){
			if (is.getItem().getCategory() == category) {
				count += is.getQuantity();
			}
		}
		return count;
	}
}
