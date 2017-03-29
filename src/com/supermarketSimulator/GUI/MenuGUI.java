package com.supermarketSimulator.GUI;

import com.supermarketSimulator.GUILauncher;
import com.supermarketSimulator.game.Score;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuGUI {
	public JPanel mainPanel;
	private JButton buttonStart;
	private JLabel logo;
	private JButton buttonHighScores;
	
	/**
	 * Needed to disallow starting multiple instances
	 */
	private boolean starting = false;
	
	public MenuGUI() {
		
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (starting) {
					return;
				}
				starting = true;
				
				MainGUI mainGUI = new MainGUI();
				
				JFrame gameFrame = new JFrame(GUILauncher.FRAME_TITLE);
				gameFrame.add(mainGUI.mainPanel);
				
				GUILauncher.centerFrameOnScreen(gameFrame);
				
				gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				gameFrame.pack();
				gameFrame.setVisible(true);
				
				SwingUtilities.windowForComponent(mainPanel).setVisible(false);
				mainGUI.refreshCart(); //Fixes score and remaining funds labels
			}
		});
		
		
		buttonHighScores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File(System.getProperty("user.home")
						+  "/SupermarketSimulator/highscores.txt");
				
				if(!file.exists()) {
					System.err.println("No high scores found.");
				}
				else {
					Score.readHighScores(file);
					
					for (int i = 0; i < Score.highScores.length; i++) {
						System.out.println("High score " + i + ": " + Score.highScores[i]);
					}
				}
			}
		});
	}
}
