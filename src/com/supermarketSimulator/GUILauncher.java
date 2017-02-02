package com.supermarketSimulator;

import javax.swing.*;
import java.awt.*;

public class GUILauncher {
	
	private static final String FRAME_TITLE = "Supermarket Simulator";
	private static final int FRAME_WIDTH = 650;
	private static final int FRAME_HEIGHT = 500;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new MainGUI().mainPanel);
		
		//Centering on screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frame.setLocation(width / 4, height / 4);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(FRAME_TITLE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.pack();
		frame.setVisible(true);
	}
}
