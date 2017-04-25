package com.supermarketSimulator.GUI;

import com.supermarketSimulator.GUILauncher;
import com.supermarketSimulator.game.Score;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class MenuGUI {
	public JPanel mainPanel;
	private JButton buttonStart;
	private JLabel logo;
	private JButton buttonHighScores;
	private JButton aboutButton;
	private JButton howToPlaybutton;
	
	private static final URL MUSIC_RESOURCE = ItemDisplay.class.getResource("/resources/sounds/Supermarket_Sim_BGM 1.wav");
	
	/**
	 * Needed to disallow starting multiple instances
	 */
	private boolean starting = false;
	
	public MenuGUI() {
		
		/*
		 * Background music
		 */
		try {
			Clip soundClip = AudioSystem.getClip();
			soundClip.open(AudioSystem.getAudioInputStream(MUSIC_RESOURCE));
			soundClip.loop(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Start game, switch windows
		 */
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
				
				gameFrame.setPreferredSize(new Dimension(1250, 600));
				gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				gameFrame.pack();
				gameFrame.setVisible(true);
				
				SwingUtilities.windowForComponent(mainPanel).setVisible(false);
				mainGUI.updateFunds(); //Fixes score and remaining funds labels
			}
		});
		
		/*
		 * Show high score window
		 */
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
					
					JOptionPane.showMessageDialog(null, Score.highScores,
							"High Scores", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		/*
		 * Show credits window
		 */
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				String[] credits = {"Thank you for playing SuperMarket Simulator 2017! \n\n",
									"This game has been brought to you through the blood, sweat,",
									"and tears of the following brave souls in the honor of",
									"CSE280, Winter 2017.\n\n",
									
									"Josh Baird",
									"Justin Kur",
									"Josh Salar",
									"Isaac Springer",
									"Cody Trombley\n\n",
									
									"It is through their hard work, dedication, and sacrifice",
									"that we here today are able to enjoy this wonderful game.",
				};
				
				JOptionPane.showMessageDialog(null,credits, "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		/*
		 * Show how to play text
		 */
		howToPlaybutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] howToPlay = {
					"Click ‘Start!’ to show the main window and play the game.",
					"\t",
					"Your basic goal is to collect items on a shopping trip in an effort to make healthy choices on a budget.",
					"\t",
					"The ‘Objectives’ button displays the goals for your shopping trip. (They are randomly generated each trip.)",
					"The ‘View Recipes’ button displays recipes containing multiple items.  Collect each item in a recipe for a bonus!",
					"The seven tabs underneath contain the food groups.  Click on one to display the individual items in that category.",
					"\t",
					"Under each tab you can see the price, healthiness level, and happiness level for each of the items.",
					"Click an item’s plus button to add the item to your cart.  It appears in your shopping cart on the right.",
					"Click the minus button in the cart section to remove the item from your cart.  Click ‘Clear Cart’ to remove all items in the cart.",
					"\t",
					"Pay attention to your funds!  If they get low enough you will not be able to add any more items.",
					"Click the ‘Score Cart’ button to end your shopping trip and start choosing the recipes you want to make.",
					"\t",
					"After the recipes show in the main window, use the plus and minus buttons to choose which recipes you want to make.",
					"How much you will be able to make depends on what you bought, keep this in mind when shopping!",
				};
				
				JOptionPane.showMessageDialog(null, howToPlay, "How to play", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
}
