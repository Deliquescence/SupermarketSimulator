package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.items.IngredientStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cjt on 4/25/2017.
 */
public class RecipeStackDisplay {
	
	private final Recipe recipe;
	private final GameContext gameContext;
	
	
	
	public JPanel recipePanel;
	private JButton addRecipeButton;
	private JLabel recipeNameLabel;
	private JLabel quantityLabel;
	private JLabel ingredientLabel;
	private JButton removeRecipeButton;
	
	
	RecipeStackDisplay(Recipe r, GameContext gc) {
		this.recipe = r;
		this.gameContext = gc;
		
		
		recipeNameLabel.setText(r.getName());
		
		StringBuilder sb = new StringBuilder();
		for(IngredientStack is : r.ingredients) {
			sb.append(is.item.getShortName()).append(" - ").append(is.quantity).append(", ");
		}
		
		this.ingredientLabel.setText(sb.toString());
		this.quantityLabel.setText("0");
		
		
		
		addRecipeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gc.shoppingCart.fulfillRecipe(r)) {
					int inc = Integer.parseInt(quantityLabel.getText()) + 1;
					quantityLabel.setText(Integer.toString(inc));
					recipePanel.repaint();
					System.out.println("Recipe made: " + r.getName());
				}
			}
		});
		
		removeRecipeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gc.shoppingCart.unFulfillRecipe(r)) {
					int dec = Integer.parseInt(quantityLabel.getText());
					quantityLabel.setText(Integer.toString(dec - 1));
					recipePanel.repaint();
					System.out.println("Recipe unmade: " + r.getName());
				}
			}
			
		});
	}
	
	public Recipe getRecipe() { return this.recipe; }
	
	public int getQuantity() { return Integer.parseInt(quantityLabel.getText()); }
	
	
}
