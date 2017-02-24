package com.supermarketSimulator.GUI;


import com.supermarketSimulator.items.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDisplay {
	public JPanel panel;
	private JLabel labelItemName;
	private JButton buttonAdd;
	private JLabel labelCost;
	private JLabel labelHealth;
	private JLabel labelHappiness;
	
	/**
	 * Create a new ItemDisplay for an Item.
	 * Use the given ActionListener for the add button.
	 *
	 * @param item              the Item to display.
	 * @param addButtonListener Action listener for the add button.
	 */
	public ItemDisplay(Item item, ActionListener addButtonListener) {
		this.labelItemName.setText(item.getName());
		this.labelCost.setText("$" + String.format("%.2f", item.getBaseCost()));
		this.labelHealth.setText("♥" + item.getBaseHealth());
		this.labelHappiness.setText("☺" + item.getBaseHappiness());
		this.labelItemName.setIcon(item.getIcon());
		
		buttonAdd.addActionListener(addButtonListener);
	}
}
