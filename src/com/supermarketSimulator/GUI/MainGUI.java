package com.supermarketSimulator.GUI;

import javax.swing.*;

public class MainGUI {
	public JPanel mainPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	private JTabbedPane tabbedPane2;
	private JButton objectivesButton;
	private JButton button1;
	
	private void populateItemList() {
		//for testing add some hardcoded item display panels
		leftPanel.add(new ItemDisplay("testItem", null, 1.0, 2, 32).panel);
		leftPanel.add(new ItemDisplay("another testItem", null, 5, 50, 2).panel);
		leftPanel.add(new ItemDisplay("test Item three", null, .44, 100, 99).panel);
	}
	
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		populateItemList();
	}
}
