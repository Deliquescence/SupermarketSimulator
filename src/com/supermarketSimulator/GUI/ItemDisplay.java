package com.supermarketSimulator.GUI;


import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.StoreItem;
import com.supermarketSimulator.items.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDisplay {
	
	private final StoreItem storeItem;
	private final GameContext gameContext;
	
	//GUI components
	public JPanel panel;
	private JLabel labelItemName;
	private JButton buttonAdd;
	private JLabel labelCost;
	private JLabel labelHealth;
	private JLabel labelHappiness;
	
	/**
	 * Create a new ItemDisplay for an Item.
	 * Use the given GameContext.
	 *
	 * @param storeItem The storeItem to display
	 * @param gameContext the GameContext this is in.
	 */
	public ItemDisplay(StoreItem storeItem, GameContext gameContext) {
		this.storeItem = storeItem;
		Item item = storeItem.getItem();
		this.gameContext = gameContext;
		
		this.labelItemName.setText(item.getName());
		this.labelCost.setText("$" + String.format("%.2f", storeItem.getUnitCost()));
		this.labelHealth.setText("♥" + item.getBaseHealth());
		this.labelHappiness.setText("☺" + item.getBaseHappiness());
		this.labelItemName.setIcon(item.getIcon());
		
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemDisplay.this.gameContext.shoppingCart.add(ItemDisplay.this.getStoreItem());
				ItemDisplay.this.gameContext.adjustFunds(-1 * ItemDisplay.this.getItem().getBaseCost());
				ItemDisplay.this.gameContext.mainGUI.refreshCart();
			}
		});
	}
	
	public StoreItem getStoreItem() {
		return this.storeItem;
	}
	
	public Item getItem() {
		return this.storeItem.getItem();
	}
}
