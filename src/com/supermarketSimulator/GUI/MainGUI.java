package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.*;
import com.supermarketSimulator.items.Category;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainGUI {
	
	public GameContext gameContext;
	
	//GUI components
	public JPanel mainPanel;
	public JLabel labelFunds;
	private JPanel rightPanel;
	private JPanel leftPanel;
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
		this.gameContext.shoppingCart = new ShoppingCart();
		this.gameContext.setFunds(GameContext.STARTING_FUNDS);
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
				for (Recipe r : Recipe.sortedRecipes) {
					RecipeFrameDisplay rd = new RecipeFrameDisplay(r);
					
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
		 * Score cart, show dialogue for high score
		 */
		scoreCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				leftPanel.removeAll();
				
				ScoreDisplay scoreDisplay = new ScoreDisplay(gameContext);
				
				leftPanel.add(new ScoreDisplay(gameContext).scoreDisplayPanel);
				
				leftPanel.revalidate();
				leftPanel.repaint();
				
				System.out.format("Total health: " + ("%.3f%n") + "Total happiness: " + ("%.3f%n"), gameContext.shoppingCart.getHealthTotal(), gameContext.shoppingCart.getHappinessTotal());
				
				double score = Score.scoreCart(gameContext.shoppingCart);
				System.out.format("Your total score is: " + ("%.3f%n%n"), score);
				
				Score.updateHighScore((int) score);
				Score.saveHighScores(gameContext.highScoresFile);
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
	private void displayGUIItems() {
		//For each category, make a panel. Add the panel to a new tab and to the map of panels.
		for (Category c : Category.categories.values()) {
			JPanel toAdd = new JPanel();
			toAdd.setLayout(new BoxLayout(toAdd, BoxLayout.PAGE_AXIS));
			tabbedPane2.addTab(c.getName(), toAdd);
			panelsInCategoryTabs.put(c, toAdd);
		}
		
		//Add every item in the store to the tabs
		for (StoreItem item : gameContext.store.storeItems) {
			panelsInCategoryTabs.get(item.getCategory()).add(new ItemDisplay(item, gameContext).panel);
		}
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
			this.shoppingCartPanel.add(new ItemStackDisplay(is, gameContext).panel);
		}
		this.shoppingCartPanel.repaint();
		this.shoppingCartPanel.revalidate();
	}
	
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
