package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.*;
import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MainGUI {
	
	public GameContext gameContext;
	
	//GUI components
	public JPanel mainPanel;
	public JLabel labelFunds;
	private JPanel rightPanel;
	public JPanel leftPanel;
	private JPanel topPanel;
	private JTabbedPane tabbedPane2;
	private JButton objectivesButton;
	private JButton printScoreButton;
	private JPanel shoppingCartPanel;
	private JLabel labelScore;
	private JButton recipeButton;
	private JButton scoreCartButton;
	private JButton clearCartButton;
	private JLabel labelMenuLogo;
	private JButton highScoresButton;
	private JScrollPane cartScroll;
	private JButton helpButton;
	
	/**
	 * Each category tab has a JPanel in it
	 */
	private Map<Category, JPanel> panelsInCategoryTabs;
	
	public MainGUI() {
		this.gameContext = new GameContext();
		this.panelsInCategoryTabs = new HashMap<>();
		
		displayGUIItems();
		
		/*
		 * Show objective list
		 */
		objectivesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon ic = new ImageIcon(this.getClass().getResource("/resources/images/list.png"));
				String list = Objective.objectivesList.toString();
				JOptionPane.showMessageDialog(null, list.substring(1, list.length() - 1)
						.replaceAll(", ", ",\n"), "Objective List", JOptionPane.INFORMATION_MESSAGE, ic);
			}
		});
		
		/*
		 * Show recipe list
		 */
		recipeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame recipeFrame = new JFrame();
				JPanel contentPane = new JPanel(new GridLayout(2, 5, 10, 10));
				for (Recipe r : gameContext.store.possibleRecipes) {
					RecipeFrameDisplay rd = new RecipeFrameDisplay(r, gameContext);
					contentPane.add(rd.rPanel);
				}
				
				recipeFrame.add(contentPane);
				recipeFrame.setTitle("Recipes");
				recipeFrame.setPreferredSize(new Dimension(1000, 400));
				Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
				recipeFrame.setLocation((int) d.getWidth() / 4, (int) d.getHeight() / 4);
				
				recipeFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
				recipeFrame.pack();
				recipeFrame.setVisible(true);
				//Recipe.debugPrintRecipes();
			}
		});
		
		/*
		 * Score cart, show recipe assignment
		 */
		scoreCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] options = {"Hold on...", "Score my cart!"};
				
				int choice = JOptionPane.showOptionDialog(mainPanel,
						"Are you ready to score your cart?",
						"Confirm",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, options , null);
						
				
				if (choice != JOptionPane.YES_OPTION) {
				
					for(ItemStackDisplay ids: gameContext.shoppingCart.getItemStackDisplays()){
						ids.setButtonEnabled(false);
					}
					scoreCartButton.setVisible(false);
					clearCartButton.setVisible(false);
					leftPanel.removeAll();
					
					ScoreDisplay scoreDisplay = new ScoreDisplay(gameContext);
					leftPanel.add(new ScoreDisplay(gameContext).scoreDisplayPanel);
					leftPanel.revalidate();
					leftPanel.repaint();
					
					JOptionPane.showMessageDialog(leftPanel, "Select which recipes you wish to build from your cart items", "Select Recipes", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		/*
		 * Clear cart
		 */
		clearCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameContext.shoppingCart.clearCart();
				gameContext.setFunds(GameContext.STARTING_FUNDS);
				gameContext.mainGUI.refreshCart();
				gameContext.mainGUI.updateFunds();
			}
		});
		
		/*
		 * Show high scores display
		 */
		highScoresButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File(System.getProperty("user.home") + "/SupermarketSimulator/highscores.txt");
				
				if (!file.exists()) {
					System.err.println("No high scores found.");
				} else {
					Score.readHighScores(file);
					
					JOptionPane.showMessageDialog(null, Score.highScores, "High Scores", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		/*
		 * Show help image
		 */
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO different image for recipe help
				ImageIcon helpImage = new ImageIcon(this.getClass().getResource("/resources/images/help-main.png"));
				JOptionPane.showMessageDialog(null, null, "Help", JOptionPane.INFORMATION_MESSAGE, helpImage);
			}
		});
		
		//Do last to prevent leaking this before completely constructed
		this.gameContext.mainGUI = this;
	}
	
	/**
	 * Runs by magic when the GUI frame is rendered.
	 *
	 * ONLY GUI related things; put other init tasks in constructor.
	 */
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		shoppingCartPanel = new JPanel();
		shoppingCartPanel.setLayout(new BoxLayout(shoppingCartPanel, BoxLayout.PAGE_AXIS));
		tabbedPane2 = new JTabbedPane();
	}
	
	/**
	 * Populate the category tabs with item displays
	 */
	public void displayGUIItems() {
		//For each category, make a panel. Add the panel to a new tab and to the map of panels.
		for (Category c : Category.categories.values()) {
			JPanel toAdd = new JPanel();
			toAdd.setLayout(new BoxLayout(toAdd, BoxLayout.PAGE_AXIS));
			tabbedPane2.addTab(c.getName(), toAdd);
			panelsInCategoryTabs.put(c, toAdd);
		}
		
		//Add every item in the store to the tabs, after sorting
		gameContext.store.storeItems.sort(Comparator.comparing(o -> o.getItem().getName())); //You can thank IntelliJ for Comparator.comparing
		for (StoreItem item : gameContext.store.storeItems) {
			panelsInCategoryTabs.get(item.getCategory()).add(new ItemDisplay(item, gameContext).panel);
		}
	}
	
	/**
	 * Reload the GUI, making a new store, and starting a new game.
	 */
	public void reload() {
		this.gameContext = new GameContext();
		this.gameContext.mainGUI = this;
		
		tabbedPane2.removeAll();
		panelsInCategoryTabs.clear();
		displayGUIItems();
		
		clearCartButton.setVisible(true);
		scoreCartButton.setVisible(true);
		leftPanel.removeAll();
		leftPanel.add(tabbedPane2);
		leftPanel.revalidate();
		leftPanel.repaint();
		
		refreshCart();
		updateFunds();
	}
	
	/**
	 * Update the shopping cart display in the GUI with the Items in the current {@link ShoppingCart}.
	 * The current shopping cart is found in {@link GameContext}.
	 */
	public void refreshCart() {
		//Remove every ItemStackDisplay and make new ones.
		//Might be inefficient, but not on a large enough scale to warrant concern.
		this.shoppingCartPanel.removeAll();
		for (ItemStack is : this.gameContext.shoppingCart.getItemStacks()) {
			ItemStackDisplay ids = new ItemStackDisplay(is, gameContext);
			this.shoppingCartPanel.add(ids.panel);
			this.gameContext.shoppingCart.getItemStackDisplays().add(ids);
		}
		this.shoppingCartPanel.repaint();
		this.shoppingCartPanel.revalidate();
	}
	
	/**
	 * Visually update the funds and score display.
	 */
	public void updateFunds() {
		labelFunds.setText("Remaining Funds: " + String.format("%.2f", gameContext.getFunds()));
		labelScore.setText("Score: " + String.format("%.0f", Score.scoreCart(gameContext.shoppingCart)));
		
		if (Score.scoreCart(gameContext.shoppingCart) == 0) {
			labelScore.setText(labelScore.getText() + "  (Objectives not met)");
			labelScore.setToolTipText("Meet objectives first!");
		} else {
			labelScore.setToolTipText("");
		}
	}
}
