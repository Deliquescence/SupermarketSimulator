package com.supermarketSimulator.game;

import com.supermarketSimulator.GUI.ItemStackDisplay;
import com.supermarketSimulator.items.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ShoppingCart {
	
	public HashSet<Recipe> potentialRecipes = new HashSet<>();
	public HashMap<Recipe, Integer> recipesMade = new HashMap<>();
	private double happinessTotal = 0;
	private double healthTotal = 0;
	private double recipeBonusScore = 0;
	private ArrayList<ItemStack> itemStacks = new ArrayList<>();
	private ArrayList<ItemStackDisplay> itemStackDisplays = new ArrayList<>();
	private HashMap<Item, Double> unpairedItems = new HashMap<>();
	private GameContext gameContext;
	
	public ShoppingCart(GameContext gc) {
		gameContext = gc;
	}
	
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
	 * @param storeItem The StoreItem to be added
	 * @param quantity  Quantity of that item to be added.
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
	
	public void printPotentialRecipes() {
		System.out.println("Recipes: ");
		potentialRecipes.forEach(r -> System.out.println(r.getName()));
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
	 * Empties the cart of all item stacks and resets health and happiness totals
	 * to 0.
	 */
	public void clearCart() {
		itemStacks.clear();
		this.happinessTotal = 0;
		this.healthTotal = 0;
		this.gameContext.setFunds(GameContext.STARTING_FUNDS);
	}
	
	/**
	 * Called when items are added to cart. Updates eligible recipes, which are recipes the player has the ingredients
	 * to make.
	 *
	 * @param item Item to be checked
	 */
	private void checkRecipes(Item item) {
		//Surrender now or prepare to fight!
		HashSet<Recipe> eligibleRecipes = new HashSet<>();
		if (Recipe.recipesByItem.containsKey(item)) {
			RecipeLoop:
			for (Recipe r : Recipe.recipesByItem.get(item)) { //All recipes that can be made with this item
				for (IngredientStack stack : r.ingredients) {
					if (unpairedItems.containsKey(stack.item)) {
						if (unpairedItems.get(stack.item) < stack.quantity) { //Insufficient quantity
							if(potentialRecipes.contains(r)) {
								potentialRecipes.remove(r);
							}
							break RecipeLoop;
						}
					} else { //Wrong item types
						if(potentialRecipes.contains(r)) {
							potentialRecipes.remove(r);
						}
						break RecipeLoop;
					}
				}
				eligibleRecipes.add(r);
			}
			
		}
		potentialRecipes.addAll(eligibleRecipes); //Adding everything from this local HashSet to the global one.
	}
	
	
	/**
	 * Fulfills a recipe from items existing in the cart.
	 *
	 * @param r Recipe
	 * @return True if successful, false if not successful
	 */
	public boolean fulfillRecipe(Recipe r) {
		if (potentialRecipes.contains(r)) {
			for (IngredientStack stack : r.ingredients) {
				updateUnpaired(stack.item, false, stack.quantity); //Update unpaired items
			}
			//Track recipes that have been made
			if (recipesMade.containsKey(r)) {
				int quantity = recipesMade.get(r);
				recipesMade.put(r, quantity + 1);
			} else {
				recipesMade.put(r, 1);
			}
			recipeBonusScore += r.getScore();
			return true; //Succeeded at making recipe.
		}
		return false; //Failed to fulfill recipe
	}
	
	/**
	 * Unbinds items from a recipe
	 * @param r Recipe
	 * @return True if the items were unbound, false if nothing was done
	 */
	public boolean unFulfillRecipe(Recipe r) {
		int quantity = recipesMade.getOrDefault(r, -1);
		if (quantity > 0) { //Should always be true, but just in case
			for(IngredientStack stack : r.ingredients) {
				updateUnpaired(stack.item, true, stack.quantity);
			}
			if(quantity - 1 > 0) {
				recipesMade.put(r, quantity - 1);
			} else {
				recipesMade.remove(r);
			}
			recipeBonusScore -= r.getScore();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Updates the list of unpaired items, i.e. items which aren't being used in any recipe.
	 *
	 * @param i        Item
	 * @param adding   True if adding, false if subtracting the given quantity
	 * @param quantity The quantity of that item
	 */
	private void updateUnpaired(Item i, boolean adding, double quantity) {
		if (adding) {
			if (!unpairedItems.containsKey(i)) {
				unpairedItems.put(i, quantity);
			} else {
				double x = unpairedItems.get(i);
				unpairedItems.put(i, x + quantity);
			}
		} else { //By deduction, removing
			double unpaired = unpairedItems.get(i);
			if (unpaired - quantity == 0) {
				unpairedItems.remove(i);
			} else {
				unpairedItems.put(i, unpaired - quantity);
			}
		}
		checkRecipes(i);
		printPotentialRecipes();
		printUnpairedItems();
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
	 * Determine whether a specific item is in the cart
	 *
	 * @param item Item to be searched for
	 * @return True if cart contains item, false otherwise
	 */
	public boolean containsItem(Item item) {
		for (ItemStack is : itemStacks) {
			if (is.getItem().equals(item)) {
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
	public int numberOfItemsInCategory(Category category) {
		int count = 0;
		for (ItemStack is : this.itemStacks) {
			if (is.getItem().getCategory() == category) {
				count += is.getQuantity();
			}
		}
		return count;
	}
	
	/**
	 * More verbose printing of unpaired items
	 */
	public void printUnpairedItems() {
		System.out.println("Unpaired Items: ");
		unpairedItems.forEach((i, d) -> System.out.println(i.getName() + " " + d));
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
	
	public List<ItemStackDisplay> getItemStackDisplays() { return this.itemStackDisplays; }
}
