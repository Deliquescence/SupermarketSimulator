package com.supermarketSimulator.GUI;


import com.supermarketSimulator.items.Item;

import javax.swing.*;

public class ItemDisplay {
	public JPanel panel;
	private JLabel labelItemName;
	private JButton buttonAdd;
	private JLabel labelCost;
	private JLabel labelHealth;
	private JLabel labelHappiness;
	
	public ItemDisplay(Item item) {
		this.labelItemName.setText(item.getName());
		this.labelCost.setText("$" + String.format("%.2f", item.getBaseCost()));
		this.labelHealth.setText("♥" + item.getBaseHealth());
		this.labelHappiness.setText("☺" + item.getBaseHappiness());
		this.labelItemName.setIcon(item.getIcon());
	}
}
