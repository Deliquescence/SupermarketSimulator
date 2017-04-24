package com.supermarketSimulator.GUI;

import com.supermarketSimulator.items.IngredientStack;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;

/**
 * Created by cjt on 4/24/2017.
 */
public class RecipeDisplay{
	public JPanel rPanel;
	private JLabel ing4;
	private JLabel ing1;
	private JLabel ing2;
	private JLabel ing3;
	private JLabel ing5;
	private JLabel ing6;
	private JLabel ing7;
	private JLabel ing8;
	private JTextField recipeName;
	
	
	
	public RecipeDisplay(Recipe r) {
		recipeName.setText(r.getName());
		
		for(IngredientStack is: r.ingredients) {
		
		}
		try {
			ing1.setText(r.ingredients[0].toString());
			ing2.setText(r.ingredients[1].toString());
			ing3.setText(r.ingredients[2].toString());
			ing4.setText(r.ingredients[3].toString());
			ing5.setText(r.ingredients[4].toString());
			ing6.setText(r.ingredients[5].toString());
			ing7.setText(r.ingredients[6].toString());
			ing8.setText(r.ingredients[7].toString());
		} catch(Exception e) {
			//catch exception and do nothing
		}
	}
}
