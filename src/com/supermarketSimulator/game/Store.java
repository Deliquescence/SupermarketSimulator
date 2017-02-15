package com.supermarketSimulator.game;

import com.supermarketSimulator.database.Database;
import com.supermarketSimulator.items.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Justin Kur on 2/15/2017.
 */
public class Store {
	
	public ArrayList<StoreItem> storeItems = new ArrayList<>();
	private final int ITEM_COUNT = 10;
	
	
	public Store() {
		generateItems(-1L);
	}
	
	private void generateItems(Long seed) {
		Random rand;
		if(seed == -1L) {
			rand = new Random();
		}
		else {
			rand = new Random(seed);
		}
		HashSet<Integer> itemsToGrab = new HashSet<>();
		int counter = 0;
		while(counter < ITEM_COUNT) {
			int randomInt = rand.nextInt(50);
			if(!itemsToGrab.contains(randomInt)) {
				counter++;
				itemsToGrab.add(randomInt);
			}
		}
		for(int number : itemsToGrab) {
			double gaussRandom = rand.nextGaussian();
			double multiplier;
			if(gaussRandom > 3) {
				multiplier = 2;
			}
			else if (gaussRandom > 1) {
				multiplier = 1 + (gaussRandom - Math.floor(gaussRandom)); //Ranges from 1 to 2
			}
			else if(gaussRandom > 0) {
				multiplier = 1 + (0.5 * gaussRandom); //Ranges from 1 to 1.5
			}
			else if(gaussRandom > -1) {
				multiplier = 1 + (0.75 * gaussRandom); //Ranges from 0.75 to 1
			}
			else {
				multiplier = 0.75;
			}
			Item item = Database.items[number];
			storeItems.add(new StoreItem(item, multiplier * item.getBaseCost()));
		}
		
	}
}
