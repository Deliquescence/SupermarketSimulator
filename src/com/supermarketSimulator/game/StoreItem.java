package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Item;

/**
 * Created by Justin Kur on 2/15/2017.
 */

public class StoreItem {
	private final Item item;
	private final double unitCost;
	
	public StoreItem(Item i, double cost) {
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
