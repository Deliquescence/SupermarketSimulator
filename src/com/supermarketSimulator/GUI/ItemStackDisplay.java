package com.supermarketSimulator.GUI;

import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;

import javax.swing.*;

public class ItemStackDisplay {
	public JPanel panel;
	private JLabel labelItemName;
	private JLabel labelItemQuantity;
	
	public ItemStackDisplay(ItemStack itemStack) {
		this.labelItemName.setText(itemStack.getItem().getName());
		this.labelItemName.setIcon(itemStack.getItem().getIcon());
		this.labelItemQuantity.setText("x" + itemStack.getQuantity());
	}
}
