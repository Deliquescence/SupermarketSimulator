package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.io.*;

/**
 * Hosts static methods for scoring a shopping cart.
 */
public class Score {
	
	/*
	 * Group of static functions for scoring shopping carts.
	 */
	private static final double HEALTH_CONSTANT = 0.075;
	private static final double HAPPINESS_CONSTANT = 0.075;
	
	public static String[] highScores = new String[5];
	
	
	/**
	 * Reads values from file and store them
	 * into the static String array highScores.
	 *
	 * @param file High Score file
	 */
	public static void readHighScores(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			int counter = 0;
			
			while (true) {
				highScores[counter] = line;
				counter++;
				if (counter == highScores.length) {
					break;
				}
				line = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file");
		} catch (IOException e) {
			System.err.println("Could not read file.");
		}
	}
	
	
	/**
	 * Writes the values of highScores[] to the text file
	 * passed in as an argument
	 *
	 * @param file File attached to HighScores text file
	 */
	public static void saveHighScores(File file) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < highScores.length; i++) {
				if (highScores[i] != null) {
					bw.write(highScores[i]);
					bw.newLine();
					bw.flush();
				}
			}
			
			bw.close();
		} catch (IOException e) {
			System.err.println("Error printing to file.");
		}
	}
	
	
	/**
	 * Iterate through the high score list and insert the new
	 * high score if high enough.
	 *
	 * @param newScore Score to be evaluated - this type can change to whatever we need it to
	 */
	public static void updateHighScore(int newScore) {
		String[] temp = new String[highScores.length];
		
		for (int i = 0; i < highScores.length; i++) {
			int score = -1;
			
			if (highScores[i] != null) {
				String[] lines = highScores[i].split(" ");
				score = Integer.parseInt(lines[1].trim());
			}
			
			if (newScore < score) {
				temp[i] = highScores[i];
			} else {
				
				ImageIcon ic = new ImageIcon(Score.class.getResource("/resources/images/thumbsup.png"));
				String name;
				try {
					name = JOptionPane.showInputDialog(null, "Input new name (no spaces plz): ", "New High Score!", JOptionPane.PLAIN_MESSAGE, ic, null, null).toString();
				} catch (NullPointerException e) {
					name = "GaryOak";
				}
				temp[i] = name + " " + newScore;
				
				for (int j = i; j < highScores.length; j++) {
					try {
						temp[j + 1] = highScores[j];
					} catch (IndexOutOfBoundsException e) {
						//temp is full, exit loop
						break;
					}
					
					
				}
				
				highScores = temp;
				break;
			}
		}
	}
	
	
	/**
	 * Scores a cart
	 *
	 * @param cart The shopping cart to score
	 * @return The sum of the health and happiness scores
	 */
	public static double scoreCart(ShoppingCart cart) {
		/*
		This will be broken down later
		 */
		if (objectivesAreMet(cart)) {
			double happinessScore = scoreHappiness(cart);
			double healthScore = scoreHealth(cart);
			double recipeScore = cart.getRecipeBonusScore();
			return healthScore + happinessScore + recipeScore;
		}
		
		return 0;
	}
	
	/*
	The below functions are public because I think we will want to display them to the user in the future
	 */
	
	/**
	 * Returns only happiness score of a cart
	 *
	 * @param cart Shopping cart to score
	 * @return Happiness score
	 */
	public static double scoreHappiness(ShoppingCart cart) {
		if (cart.getHappinessTotal() * HAPPINESS_CONSTANT > 1) { //Avoiding massively negative answers
			return 100 * Math.log(HAPPINESS_CONSTANT * cart.getHappinessTotal());
		}
		return 0;
	}
	
	/**
	 * Returns only health score of a cart
	 *
	 * @param cart Shopping cart to score
	 * @return Health score
	 */
	public static double scoreHealth(ShoppingCart cart) {
		if (cart.getHealthTotal() * HEALTH_CONSTANT > 1) { //Avoiding massively negative answers
			return 100 * Math.log(HEALTH_CONSTANT * cart.getHealthTotal());
		}
		return 0;
	}
	
	/**
	 * Determine if a given objective is met by the given cart.
	 *
	 * @param objective the Objective
	 * @param cart      the ShoppingCart
	 * @return true if the contents of this cart fulfil the objective
	 */
	public static boolean objectiveIsMet(Objective objective, ShoppingCart cart) {
		return cart.numberOfItemsInCategory(objective.getCategory()) >= objective.getQuantity();
	}
	
	private static boolean objectivesAreMet(ShoppingCart cart) {
		for (Objective o : Objective.objectivesList) {
			if (!objectiveIsMet(o, cart)) {
				return false;
			}
		}
		return true;
	}
}
