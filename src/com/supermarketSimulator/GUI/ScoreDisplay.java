package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;

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
	
	
	public ScoreDisplay(GameContext gc) {
		this.gameContext = gc;
		
		
		displayScoreGUI();
	
	
	}
	
	private void createUIComponents() {
		recipePanel = new JPanel();
		recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.PAGE_AXIS));
		scoreLabel = new JLabel();
		scoreLabel.setText("This is the score label.");
		
	}
	
	private void displayScoreGUI() {
		
		for(Recipe r: Recipe.sortedRecipes) {
			RecipeStackDisplay rsd = new RecipeStackDisplay(r, gameContext);
			
			recipePanel.add(rsd.recipePanel);
		}
	}
}
