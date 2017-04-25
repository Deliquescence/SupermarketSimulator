package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.Store;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjt on 4/24/2017.
 */
public class ScoreDisplay {
	private final GameContext gameContext;
	
	public JPanel scoreDisplayPanel;
	private JPanel recipePanel;
	private JLabel scoreLabel;
	private JSeparator jSeparator;
	private JButton submitScoreButton;
	private JButton returnButton;
	private JLabel recipeLabel;
	private JLabel ingredLabel;
	private JLabel quantityLabel;
	
	private ArrayList<RecipeStackDisplay> recipeStackDisplays;
	
	public ScoreDisplay(GameContext gc) {
		this.gameContext = gc;
		this.recipeStackDisplays = new ArrayList<RecipeStackDisplay>();
		
		displayScoreGUI();
	
		
		submitScoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(RecipeStackDisplay rsd : recipeStackDisplays) {
					System.out.println(rsd.getQuantity());
				}
			
				
				gameContext.shoppingCart.clearCart();
				gameContext.mainGUI.refreshCart();
				gameContext.mainGUI.refreshStore();
			}
			
			
		});
	}
	
	
	
	private void createUIComponents() {
		recipePanel = new JPanel();
		recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.PAGE_AXIS));
		scoreLabel = new JLabel();
		scoreLabel.setText("This is the score label.");
		quantityLabel = new JLabel();
	}
	
	private void displayScoreGUI() {
		
		for(Recipe r: Recipe.sortedRecipes) {
			RecipeStackDisplay rsd = new RecipeStackDisplay(r, gameContext);
			
			recipePanel.add(rsd.recipePanel);
			recipeStackDisplays.add(rsd);
		}
	}
	
	
}
