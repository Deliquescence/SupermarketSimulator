package com.supermarketSimulator.game;

import com.supermarketSimulator.database.Database;
import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Justin Kur on 2/15/2017.
 */
public class Store {
	
	/**
	 * How many items each store will have.
	 */
	private final int ITEM_COUNT = 30;
	
	public ArrayList<StoreItem> storeItems = new ArrayList<>();
	
	private Random rand;
	
	/**
	 * Creates a store with a random seed
	 */
	public Store() {
		generateItems(-1L);
	}
	
	/**
	 * Creates a store with a given seed
	 *
	 * @param seed The Long seed for the random number generator
	 */
	public Store(Long seed) {
		generateItems(seed);
	}
	
	private void generateItems(Long seed) {
		//Categories that do not have an item added to them and need to be populated
		//Initially, all categories.
		Collection<Category> neededCategories = new HashSet<>(Category.categories.values());
		
		if (seed == -1L) { //Because Java has no default parameters
			rand = new Random();
		} else {
			rand = new Random(seed);
		}
		
		//A set of integers is created and these integers are used as indices of the items array.
		int[] itemsToGrab = rand.ints(0, Database.items.length - 1).distinct().limit(ITEM_COUNT).toArray();
		
		for (int number : itemsToGrab) {
			Item item = Database.items[number];
			storeItems.add(new StoreItem(item, getMultiplier() * item.getBaseCost()));
			neededCategories.remove(item.getCategory()); //Since the category now has at least one item
		}
		
		//Check if there are any categories without an Item and add an Item if so
		for (Category c : neededCategories) {
			//Get an Item out of the set of items in the needed category.
			//Documentation for HashSet.iterator() says "The elements are returned in no particular order" so should be good on randomness.
			Item item = Database.itemsByCategory.get(c).iterator().next();
			
			storeItems.add(new StoreItem(item, getMultiplier() * item.getBaseCost()));
		}
	}
	
	/**
	 * This is a function to make interesting pseudorandom prices based off of a given item's base price. It will
	 * need be reviewed later after testing.
	 */
	private double getMultiplier() {
		double gaussRandom = rand.nextGaussian();
		double multiplier;
		
		if (gaussRandom > 3) {
			multiplier = 2;
		} else if (gaussRandom > 1) {
			multiplier = 1 + (gaussRandom - Math.floor(gaussRandom)); //Ranges from 1 to 2
		} else if (gaussRandom > 0) {
			multiplier = 1 + (0.5 * gaussRandom); //Ranges from 1 to 1.5
		} else if (gaussRandom > -1) {
			multiplier = 1 + (0.75 * gaussRandom); //Ranges from 0.75 to 1
		} else {
			multiplier = 0.75;
		}
		
		return multiplier;
	}
}
