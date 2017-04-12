package com.supermarketSimulator;

import com.supermarketSimulator.GUI.MenuGUI;
import com.supermarketSimulator.database.Database;
import com.supermarketSimulator.items.Recipe;

import javax.swing.*;
import java.awt.*;

public class GUILauncher {
	
	public static final String FRAME_TITLE = "Supermarket Simulator";
	
	public static void main(String[] args) {
		//Database
		Database testDb = new Database("database.csv", "recipes.csv");
		testDb.ReadItems();
		testDb.ReadRecipes();
		
		//GUI
		JFrame frame = new JFrame();
		frame.add(new MenuGUI().mainPanel);
		
		GUILauncher.centerFrameOnScreen(frame);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension (425, 375));
		frame.setTitle(FRAME_TITLE);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Utility method to set the location of a frame to the center of the screen.
	 *
	 * @param frame the JFrame to move to the center
	 */
	public static void centerFrameOnScreen(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frame.setLocation(width / 4, height / 4);
	}
}
