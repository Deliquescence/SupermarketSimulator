package com.supermarketSimulator;

import com.supermarketSimulator.GUI.MainGUI;
import com.supermarketSimulator.database.Database;

import javax.swing.*;
import java.awt.*;

public class GUILauncher {
	
	private static final String FRAME_TITLE = "Supermarket Simulator";
	
	public static void main(String[] args) {
		//Database
		Database testDb = new Database("database.csv", "recipes.csv");
		testDb.ReadItems();
		
		//GUI
		JFrame frame = new JFrame();
		frame.add(new MainGUI().mainPanel);
		
		//Centering on screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frame.setLocation(width / 4, height / 4);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(FRAME_TITLE);
		frame.pack();
		frame.setVisible(true);
	}
}
