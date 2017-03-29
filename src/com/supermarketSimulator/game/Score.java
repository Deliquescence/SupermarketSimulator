package com.supermarketSimulator.game;

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
	
	private static final String path = "/resources/save/highscores.txt";
	private static String[] highScores = new String[5];
	
	
	
	public static void readHighScores(File file) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = br.readLine();
			int counter = 0;
			
			while(br != null) {
				highScores[counter] = line;
				counter++;
				if (counter == highScores.length) {
					break;
				}
				line = br.readLine();
			}
			
		} catch(FileNotFoundException e) {
			System.err.println("Could not find file");
		} catch (IOException e) {
			System.err.println("Could not read file.");
		}
	}
	
	
	public static void saveHighScores(File file) {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < highScores.length; i++) {
				if(highScores[i] != null) {
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
	 * Scores a cart
	 *
	 * @param cart The shopping cart to score
	 * @return The sum of the health and happiness scores
	 */
	public static double scoreCart(ShoppingCart cart) {
		/*
		This will be broken down later
		 */
		double happinessScore = scoreHappiness(cart);
		double healthScore = scoreHealth(cart);
		
		return healthScore + happinessScore;
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
	 * @return true if this contents of the cart fulfil the objective
	 */
	public static boolean objectiveIsMet(Objective objective, ShoppingCart cart) {
		return cart.numberOfItemsInCategory(objective.getCategory()) >= objective.getQuantity();
	}
}
