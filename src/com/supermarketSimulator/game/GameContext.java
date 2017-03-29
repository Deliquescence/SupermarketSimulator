package com.supermarketSimulator.game;

import com.supermarketSimulator.GUI.MainGUI;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * An instance of a game.
 * Contains variables and such things that each game has.
 *
 * For example, shopping cart, current store, score, remaining funds, etc.
 */
public class GameContext {
	
	public static final double STARTING_FUNDS = 100;
	/**
	 * Keep a reference to the main GUI because that might be useful
	 */
	public MainGUI mainGUI;
	
	public ShoppingCart shoppingCart;
	public Store store;
	public File highScoresFile;
	
	private double funds;
	
	public GameContext() {
		store = new Store();
		
		try {
			highScoresFile = new File(this.getClass().getResource("/resources/save/highscores.txt").toURI());
			
			if(!highScoresFile.exists()){
				highScoresFile.createNewFile();
			}
			Score.readHighScores(highScoresFile);
		} catch (URISyntaxException e) {
			System.err.println("Cannot open High Scores file.");
		} catch (IOException e) {
			System.err.println("Cannot create HighScores File.");
		}
		Objective.generate();
	}
	
	public double getFunds() {
		return funds;
	}
	
	public void setFunds(double funds) {
		this.funds = funds;
	}
	
	/**
	 * Increment or decrement the current funds
	 *
	 * @param amount the amount to adjust by
	 */
	public void adjustFunds(double amount) {
		this.funds = funds + amount;
	}
	
}
