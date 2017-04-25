package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.items.IngredientStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;

/**
 * Created by cjt on 4/25/2017.
 */
public class RecipeStackDisplay {
	
	private final Recipe recipe;
	private final GameContext gameContext;
	
	
	
	public JPanel recipePanel;
	private JButton addRecipeButton;
	private JLabel recipeLabel;
	private JLabel quantityLabel;
	
	
	
	RecipeStackDisplay(Recipe r, GameContext gc) {
		this.recipe = r;
		this.gameContext = gc;
		
		StringBuilder sb = new StringBuilder();
		sb.append(r.getName()).append(": ");
		
		for(IngredientStack is : r.ingredients) {
			sb.append(is.item.getShortName()).append(", ").append(is.quantity).append(", ");
		}
		
		this.recipeLabel.setText(sb.toString());
		this.quantityLabel.setText("0");
	
		
	}
	
	public Recipe getRecipe() { return this.recipe; }
	
}
