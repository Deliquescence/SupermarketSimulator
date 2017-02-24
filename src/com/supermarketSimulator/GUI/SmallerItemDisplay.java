package com.supermarketSimulator.GUI;

import com.supermarketSimulator.items.Item;

import javax.swing.*;

public class SmallerItemDisplay {
	public JPanel panel;
	private JLabel labelItemName;
	
	public SmallerItemDisplay(Item item) {
		this.labelItemName.setText(item.getName());
		this.labelItemName.setIcon(item.getIcon());
	}
}
