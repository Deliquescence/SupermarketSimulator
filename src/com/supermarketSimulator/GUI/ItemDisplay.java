package com.supermarketSimulator.GUI;


import javax.swing.*;

public class ItemDisplay {
	public JPanel panel;
	private JLabel labelItemName;
	private JButton buttonAdd;
	private JLabel labelCost;
	private JLabel labelHealth;
	private JLabel labelHappiness;
	
	/*public void setItemName(String name){
		
		this.labelItemName.setText(name);
	}*/
	
	public ItemDisplay(String name, Icon icon, double cost, int health, int happiness) {
		this.labelItemName.setText(name);
		this.labelCost.setText("$" + String.format("%.2f", cost));
		this.labelHealth.setText("♥" + health);
		this.labelHappiness.setText("☺" + happiness);
	}
}
