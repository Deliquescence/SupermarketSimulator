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
		
		try {
			Clip soundClip = AudioSystem.getClip();
			soundClip.open(AudioSystem.getAudioInputStream(MUSIC_RESOURCE));
			soundClip.loop(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
				
				gameFrame.setPreferredSize(new Dimension(1200, 600));
				gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				gameFrame.pack();
				gameFrame.setVisible(true);
				
				SwingUtilities.windowForComponent(mainPanel).setVisible(false);
				mainGUI.updateFunds(); //Fixes score and remaining funds labels
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
					
					JOptionPane.showMessageDialog(null, Score.highScores,
							"High Scores", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		
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
									"Issac Springer",
									"Cody Trombley\n\n",
									
									"It is through their hard work, dedication, and sacrifice",
									"that we here today are able to enjoy this wonderful game.",
									"God bless them, and God bless America. Thank you."
					
				};
				
				JOptionPane.showMessageDialog(null,credits, "About", JOptionPane.PLAIN_MESSAGE);
				
			}
		});
	}
}
