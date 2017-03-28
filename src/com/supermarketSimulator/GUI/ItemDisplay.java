package com.supermarketSimulator.GUI;


import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.StoreItem;
import com.supermarketSimulator.items.Item;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class ItemDisplay {
	
	private final StoreItem storeItem;
	private final GameContext gameContext;
	
	private Clip soundClip;
	private static final URL SOUND_RESOURCE = ItemDisplay.class.getResource("/resources/sounds/Money,Coins,Hand,Count,x1.wav");
	
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
	 * @param storeItem   The storeItem to display
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
		
		try {
			soundClip = AudioSystem.getClip();
			soundClip.open(AudioSystem.getAudioInputStream(SOUND_RESOURCE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ItemDisplay.this.gameContext.getFunds() - ItemDisplay.this.getItem().getBaseCost() > 0) { //Funds are available
					ItemDisplay.this.gameContext.shoppingCart.add(ItemDisplay.this.getStoreItem());
					ItemDisplay.this.gameContext.adjustFunds(-1 * ItemDisplay.this.getItem().getBaseCost());
					ItemDisplay.this.gameContext.mainGUI.refreshCart();
					if (soundClip != null && !soundClip.isActive()) {
						soundClip.start();
						soundClip.setFramePosition(0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Out of funds!", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
				}
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
