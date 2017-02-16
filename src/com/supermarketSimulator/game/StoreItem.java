package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Item;

/**
 * Created by Justin Kur on 2/15/2017.
 */

public class StoreItem {
	private final Item item;
	private final double unitCost;
	
	StoreItem(Item i, double cost) { //Package private to enforce that only Store should call the constructor
		item = i;
		unitCost = cost;
	}
	
	public Item getItem() {
		return item;
	}
	
	public double getUnitCost() {
		return unitCost;
	}
}
