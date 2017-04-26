package com.supermarketSimulator.GUI;

import com.supermarketSimulator.game.GameContext;
import com.supermarketSimulator.game.StoreItem;
import com.supermarketSimulator.items.IngredientStack;
import com.supermarketSimulator.items.ItemStack;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cjt on 4/24/2017.
 */
public class RecipeFrameDisplay {
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
	private JButton addAllButton;
	
	
	public RecipeFrameDisplay(Recipe r, GameContext gc) {
		recipeName.setText(r.getName());
		
		try {
			ing1.setText(r.ingredients[0].item.getShortName());      quan1.setText(Double.toString(r.ingredients[0].quantity));
			ing2.setText(r.ingredients[1].item.getShortName());      quan2.setText(Double.toString(r.ingredients[1].quantity));
			ing3.setText(r.ingredients[2].item.getShortName());      quan3.setText(Double.toString(r.ingredients[2].quantity));
			ing4.setText(r.ingredients[3].item.getShortName());      quan4.setText(Double.toString(r.ingredients[3].quantity));
			ing5.setText(r.ingredients[4].item.getShortName());      quan5.setText(Double.toString(r.ingredients[4].quantity));
			ing6.setText(r.ingredients[5].item.getShortName());      quan6.setText(Double.toString(r.ingredients[5].quantity));
			ing7.setText(r.ingredients[6].item.getShortName());      quan7.setText(Double.toString(r.ingredients[6].quantity));
			ing8.setText(r.ingredients[7].item.getShortName());      quan8.setText(Double.toString(r.ingredients[7].quantity));
		} catch(ArrayIndexOutOfBoundsException e) {
			//catch exception and do nothing
		}
		
		/*
		 * Try and add the items of this recipe to the cart. Make an error message box if not possible.
		 */
		addAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double wouldCost = 0;
				
				for (IngredientStack is : r.ingredients) {
					StoreItem storeItem = gc.store.getStoreItemFromItem(is.item);
					//Make sure the store has the item
					if (storeItem == null) {
						JOptionPane.showMessageDialog(null, "Sorry, the store doesn't have " + is.item.getShortName() + " in stock!", "Can't add those items", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//Add this item's cost to the counter so we can check if the recipe is too expensive
					wouldCost += storeItem.getUnitCost();
				}
				
				if (wouldCost <= gc.getFunds()) { //Can afford
					for (IngredientStack is : r.ingredients) { //Actually add each item, individually
						int quantity = (int) Math.ceil(is.quantity); //Round up
						gc.shoppingCart.add(gc.store.getStoreItemFromItem(is.item), quantity);
						gc.adjustFunds(-1 * quantity * gc.store.getStoreItemFromItem(is.item).getUnitCost());
						gc.mainGUI.refreshCart();
						gc.mainGUI.updateFunds();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Not enough funds for everything!", "Can't add those items", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}
}
