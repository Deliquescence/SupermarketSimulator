package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.items.ItemStack;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;

public class ItemStackDisplay {
	
	private final ItemStack itemStack;
	private final GameContext gameContext;
	
	private Clip soundClip;
	private static final URL SOUND_RESOURCE = ItemStackDisplay.class.getResource("/resources/sounds/Money,Coins,Handle.wav");
	
	//GUI components
	public JPanel panel;
	private JLabel labelItemName;
	private JLabel labelItemQuantity;
	private JButton buttonRemove;
	private JLabel labelSubtotal;
	
	public ItemStackDisplay(ItemStack itemStack, GameContext gameContext) {
		this.itemStack = itemStack;
		this.gameContext = gameContext;
		
		this.labelItemName.setText(itemStack.getItem().getName());
		this.labelItemName.setIcon(itemStack.getItem().getIcon());
		this.labelItemQuantity.setText("x" + itemStack.getQuantity());
		
		double subTotal = itemStack.getQuantity() * itemStack.getStoreItem().getUnitCost();
		DecimalFormat df = new DecimalFormat("0.00");
		this.labelSubtotal.setText("$"+ df.format(subTotal));
		
		try {
			soundClip = AudioSystem.getClip();
			soundClip.open(AudioSystem.getAudioInputStream(SOUND_RESOURCE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemStackDisplay.this.gameContext.shoppingCart.remove(ItemStackDisplay.this.getItemStack());
				ItemStackDisplay.this.gameContext.adjustFunds(ItemStackDisplay.this.getItemStack().getQuantity() * ItemStackDisplay.this.getItemStack().getStoreItem().getUnitCost());
				ItemStackDisplay.this.gameContext.mainGUI.refreshCart();
				ItemStackDisplay.this.gameContext.mainGUI.updateFunds();
				if (soundClip != null && !soundClip.isActive()) {
					soundClip.start();
					soundClip.setFramePosition(0);
				}
			}
		});
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public void setButtonEnabled(boolean choice) {
		buttonRemove.setEnabled(choice);
	}
}
