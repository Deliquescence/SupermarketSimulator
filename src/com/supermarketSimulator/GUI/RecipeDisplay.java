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
	private JLabel quan1;
	private JLabel quan2;
	private JLabel quan3;
	private JLabel quan4;
	private JLabel quan6;
	private JLabel quan5;
	private JLabel quan7;
	private JLabel quan8;
	private JLabel quantityLabel;
	
	
	public RecipeDisplay(Recipe r) {
		recipeName.setText(r.getName());
		
		for(IngredientStack is: r.ingredients) {
		
		}
		try {
			ing1.setText(r.ingredients[0].item.getShortName());      quan1.setText(Double.toString(r.ingredients[0].quantity));
			ing2.setText(r.ingredients[1].item.getShortName());      quan2.setText(Double.toString(r.ingredients[1].quantity));
			ing3.setText(r.ingredients[2].item.getShortName());      quan3.setText(Double.toString(r.ingredients[2].quantity));
			ing4.setText(r.ingredients[3].item.getShortName());      quan4.setText(Double.toString(r.ingredients[3].quantity));
			ing5.setText(r.ingredients[4].item.getShortName());      quan5.setText(Double.toString(r.ingredients[4].quantity));
			ing6.setText(r.ingredients[5].item.getShortName());      quan6.setText(Double.toString(r.ingredients[5].quantity));
			ing7.setText(r.ingredients[6].item.getShortName());      quan7.setText(Double.toString(r.ingredients[6].quantity));
			ing8.setText(r.ingredients[7].item.getShortName());      quan8.setText(Double.toString(r.ingredients[7].quantity));
		} catch(Exception e) {
			//catch exception and do nothing
		}
	}
	
}
