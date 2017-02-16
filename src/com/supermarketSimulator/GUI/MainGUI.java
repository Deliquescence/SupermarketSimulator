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
		//TODO populate the list of items
	}
	
	private void createUIComponents() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		populateItemList();
	}
}
