package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemStackDisplay {
	
	private final ItemStack itemStack;
	private final GameContext gameContext;
	
	//GUI components
	public JPanel panel;
	private JLabel labelItemName;
	private JLabel labelItemQuantity;
	private JButton buttonRemove;
	
	public ItemStackDisplay(ItemStack itemStack, GameContext gameContext) {
		this.itemStack = itemStack;
		this.gameContext = gameContext;
		
		this.labelItemName.setText(itemStack.getItem().getName());
		this.labelItemName.setIcon(itemStack.getItem().getIcon());
		this.labelItemQuantity.setText("x" + itemStack.getQuantity());
		
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemStackDisplay.this.gameContext.shoppingCart.remove(ItemStackDisplay.this.getItemStack());
				ItemStackDisplay.this.gameContext.mainGUI.refreshCart();
			}
		});
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
}
