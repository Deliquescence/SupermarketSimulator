package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.Score;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
				for (RecipeStackDisplay rsd : recipeStackDisplays) {
					System.out.println(rsd.getQuantity());
				}
				
				
				double score = Score.scoreCart(gameContext.shoppingCart);
				System.out.format("Your total score is: " + ("%.3f%n%n"), score);
				JOptionPane.showMessageDialog(scoreDisplayPanel, String.format("Your final score is %.3f!", score), "Well done!", JOptionPane.INFORMATION_MESSAGE);
				
				
				Score.updateHighScore((int) score);
				Score.saveHighScores(gameContext.highScoresFile);
				
				
				String[] options = {"Get me outta here", "Hot dog yeah!"};
				int choice = JOptionPane.showOptionDialog(scoreDisplayPanel, "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				
				if(choice != JOptionPane.NO_OPTION) {
					System.exit(0);
				}
				else {
					gameContext.mainGUI.reload();
				}
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
		
		for(Recipe r: gameContext.store.possibleRecipes) {
			RecipeStackDisplay rsd = new RecipeStackDisplay(r, gameContext);
			
			recipePanel.add(rsd.recipePanel);
			recipeStackDisplays.add(rsd);
		}
	}
	
	
}
