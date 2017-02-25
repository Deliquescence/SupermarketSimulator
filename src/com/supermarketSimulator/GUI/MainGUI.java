package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.ShoppingCart;
import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.Item;
import com.supermarketSimulator.items.ItemStack;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private JButton button1;
	private JPanel shoppingCartPanel;
	
	/**
	 * Each "asile" tab has a JPanel in it
	 */
	private List<JPanel> panelsInTabs;
	
	/**
	 * Startup method.
	 */
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		shoppingCartPanel = new JPanel();
		shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.PAGE_AXIS));
		tabbedPane2 = new JTabbedPane();
		panelsInTabs = new ArrayList<>();
		
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
		
		populateItems();
		displayGUIItems();
	}
	
	/**
	 * Populate all the items in the database and make objects for them.
	 */
	private void populateItems() {
		//TODO populate the list of items
	}
	
	/**
	 * Populate the "aisle" tabs with item displays
	 */
	private void displayGUIItems() {
		//Some of this should be done somewhere else
		
		Category testCategory = Category.addCategory("test category");
		Category testCategory2 = Category.addCategory("testing category two");
		Item testItem = new Item(1, "test item", 99, 99, testCategory, 9.99, null);
		Item testItem2 = new Item(2, "testing item2", 22, 22, testCategory2, 2.22, null);
		
		//Keep track of what category is in what panel
		Map<Category, Integer> CategoryToPanelIndex = new HashMap<>();
		
		//For each category, make a panel and add it to the list of tab panels (and the actual tabs)
		for (Category c : Category.categories.values()) {
			JPanel toAdd = new JPanel();
			toAdd.setLayout(new BoxLayout(toAdd, BoxLayout.PAGE_AXIS));
			tabbedPane2.addTab(c.getName(), toAdd);
			
			CategoryToPanelIndex.put( c, this.panelsInTabs.size());
			this.panelsInTabs.add(toAdd);
		}
		
		List<Item> everyItemWouldBeHereButThisIsATest = new ArrayList<>();
		everyItemWouldBeHereButThisIsATest.add(testItem);
		everyItemWouldBeHereButThisIsATest.add(testItem2);
		
		for (Item item : everyItemWouldBeHereButThisIsATest) {
			panelsInTabs.get(CategoryToPanelIndex.get(item.getCategory())).add(new ItemDisplay(item, this.gameContext).panel);
		}
	}
	
	public void refreshCart() {
		//TODO maybe could look into some event driven updating
		
		this.shoppingCartPanel.removeAll(); //TODO this is obviously super inefficient
		
		for (ItemStack is : this.gameContext.shoppingCart.getItemStacks()) {
			this.shoppingCartPanel.add(new ItemStackDisplay(is).panel);
		}
		this.shoppingCartPanel.repaint();
		this.shoppingCartPanel.revalidate();
	}
}
