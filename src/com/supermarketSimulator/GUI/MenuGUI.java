package com.supermarketSimulator.GUI;

import com.supermarketSimulator.GUILauncher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
	public JPanel mainPanel;
	private JButton buttonStart;
	private JLabel logo;
	
	public MenuGUI() {
		
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
	}
}
