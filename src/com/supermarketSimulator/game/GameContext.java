package com.supermarketSimulator.game;

import com.supermarketSimulator.GUI.MainGUI;

/**
 * An instance of a game.
 * Contains variables and such things that each game has.
 *
 * For example, shopping cart, current store, score, remaining funds, etc.
 */
public class GameContext {
	
	/**
	 * Keep a reference to the main GUI because that might be useful
	 */
	public MainGUI mainGUI;
	
	public ShoppingCart shoppingCart;
	public Store store;
	
}
