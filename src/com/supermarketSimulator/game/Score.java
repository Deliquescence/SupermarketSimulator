package com.supermarketSimulator.game;

/**
 * Hosts static methods for scoring a shopping cart.
 */
public class Score {
	
	/*
	 * Group of static functions for scoring shopping carts.
	 */
	private static final double HEALTH_CONSTANT = 0.1;
	private static final double HAPPINESS_CONSTANT = 0.1;
	
	/**
	 * Scores a cart
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
	 * @param cart Shopping cart to score
	 * @return Happiness score
	 */
	public static double scoreHappiness(ShoppingCart cart) {
		if(cart.getHappinessTotal() * HAPPINESS_CONSTANT > 1) { //Avoiding massively negative answers
			return 100 * Math.log(HAPPINESS_CONSTANT * cart.getHappinessTotal());
		}
		return 0;
	}
	
	/**
	 * Returns only health score of a cart
	 * @param cart Shopping cart to score
	 * @return Health score
	 */
	public static double scoreHealth(ShoppingCart cart) {
		if(cart.getHealthTotal() * HEALTH_CONSTANT > 1) { //Avoiding massively negative answers
			return 100 * Math.log(HEALTH_CONSTANT * cart.getHealthTotal());
		}
		return 0;
	}
}
