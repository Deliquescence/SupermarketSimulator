package com.supermarketSimulator.game;

import com.supermarketSimulator.items.Recipe;

import java.io.*;
import java.util.Map;

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
		if(objectivesAreMet(cart)) {
			double happinessScore = scoreHappiness(cart);
			double healthScore = scoreHealth(cart);
			double recipeScore = 0;
			for(Map.Entry<Recipe, Integer> entry : cart.recipesMade.entrySet()) {
				recipeScore += entry.getKey().getScore() * entry.getValue();
			}
			
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
	 * @return true if this contents of the cart fulfil the objective
	 */
	public static boolean objectiveIsMet(Objective objective, ShoppingCart cart) {
		return cart.numberOfItemsInCategory(objective.getCategory()) >= objective.getQuantity();
	}
	
	private static boolean objectivesAreMet(ShoppingCart cart) {
		for(Objective o : Objective.objectivesList) {
			if(!objectiveIsMet(o, cart)) {
				return false;
			}
		}
		return true;
	}
}
