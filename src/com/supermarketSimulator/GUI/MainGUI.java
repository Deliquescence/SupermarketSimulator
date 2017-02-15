package com.supermarketSimulator.GUI;

import javax.swing.*;

public class MainGUI {
	public JPanel mainPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	
	private void populateItemList() {
		//TODO populate the list of items
	}
	
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		populateItemList();
	}
}
