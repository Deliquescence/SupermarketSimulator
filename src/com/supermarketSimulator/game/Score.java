package com.supermarketSimulator.game;

/**
 * Created by Gaming on 3/3/2017.
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
		double happinessScore = 0;
		double healthScore = 0;
		
		if(cart.getHappinessTotal() * HAPPINESS_CONSTANT > 1) { //Avoiding massively negative answers
			happinessScore = 100 * Math.log(HAPPINESS_CONSTANT * cart.getHappinessTotal());
		}
		
		if(cart.getHealthTotal() * HEALTH_CONSTANT > 1) { //Avoiding massively negative answers
			healthScore = 100 * Math.log(HEALTH_CONSTANT * cart.getHealthTotal());
		}
		return healthScore + happinessScore;
	}
}
