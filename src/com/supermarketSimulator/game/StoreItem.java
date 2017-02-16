package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Item;

/**
 * Created by Justin Kur on 2/15/2017.
 */

public class StoreItem {
	Item item;
	double unitCost;
	
	StoreItem(Item i, double cost) {
		item = i;
		unitCost = cost;
	}
}
