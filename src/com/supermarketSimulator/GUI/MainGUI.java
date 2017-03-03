package com.supermarketSimulator.GUI;

import com.supermarketSimulator.database.Database;
import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.ShoppingCart;
import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.game.Score;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainGUI {
	
	public GameContext gameContext;
	
	//GUI components
	public JPanel mainPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	private JTabbedPane tabbedPane2;
	private JButton objectivesButton;
	private JButton printScoreButton;
	private JPanel shoppingCartPanel;
	
	/**
	 * Each category tab has a JPanel in it
	 */
	private Map<Category, JPanel> panelsInCategoryTabs;
	
	public MainGUI() {
		printScoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.format("Total health: " + ("%.3f%n") + "Total happiness: " +
						("%.3f%n"), gameContext.shoppingCart.getHealthTotal(),
						gameContext.shoppingCart.getHappinessTotal());
				System.out.format("Your total score is: " + ("%.3f%n%n"), Score.scoreCart(gameContext.shoppingCart));
			}
		});
	}
	
	/**
	 * Startup method.
	 */
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		shoppingCartPanel = new JPanel();
		shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.PAGE_AXIS));
		tabbedPane2 = new JTabbedPane();
		panelsInCategoryTabs = new HashMap<>();
		
		gameInit();
	}
	
	/**
	 * Tasks to setup the game.
	 * Separated from createUIComponents() for clarity.
	 */
	private void gameInit() {
		this.gameContext = new GameContext();
		this.gameContext.mainGUI = this;
		this.gameContext.shoppingCart = new ShoppingCart();
		
		displayGUIItems();
	}
	
	/**
	 * Populate the category tabs with item displays
	 */
	private void displayGUIItems() {
		//For each category, make a panel. Add the panel to a new tab and to the map of panels.
		for (Category c : Category.categories.values()) {
			JPanel toAdd = new JPanel();
			toAdd.setLayout(new BoxLayout(toAdd, BoxLayout.PAGE_AXIS));
			tabbedPane2.addTab(c.getName(), toAdd);
			
			panelsInCategoryTabs.put(c, toAdd);
		}
		
		//Add every item in the database to the tabs
		for (Item item : Database.items) {
			panelsInCategoryTabs.get(item.getCategory()).add(new ItemDisplay(item, this.gameContext).panel);
		}
	}
	
	/**
	 * Update the shopping cart display in the GUI with the Items in the current {@Link ShoppingCart}.
	 * The current shopping cart is found in {@Link GameContext}.
	 */
	public void refreshCart() {
		//Remove every ItemStackDisplay and make new ones.
		//Might be inefficient, but not on a large enough scale to warrant concern.
		this.shoppingCartPanel.removeAll();
		for (ItemStack is : this.gameContext.shoppingCart.getItemStacks()) {
			this.shoppingCartPanel.add(new ItemStackDisplay(is, gameContext).panel);
		}
		this.shoppingCartPanel.repaint();
		this.shoppingCartPanel.revalidate();
	}
}
