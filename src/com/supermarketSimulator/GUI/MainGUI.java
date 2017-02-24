package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.ShoppingCart;
import com.supermarketSimulator.items.Item;

import javax.swing.*;

public class MainGUI {
	
	public GameContext gameContext;
	
	//GUI components
	public JPanel mainPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	private JTabbedPane tabbedPane2;
	private JButton objectivesButton;
	private JButton button1;
	
	/**
	 * Startup method.
	 */
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		gameInit();
	}
	
	/**
	 * Tasks to setup the game.
	 * Separated from createUIComponents() for clarity.
	 */
	private void gameInit() {
		populateItemList();
		
		this.gameContext.mainGUI = this;
		this.gameContext.shoppingCart = new ShoppingCart();
	}
	
	private void populateItemList() {
		//TODO populate the list of items
	}
}
