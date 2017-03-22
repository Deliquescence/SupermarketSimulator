package com.supermarketSimulator.GUI;

import com.supermarketSimulator.GUILauncher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
	private JButton buttonStart;
	public JPanel mainPanel;
	
	public MenuGUI() {
		
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame gameFrame = new JFrame(GUILauncher.FRAME_TITLE);
				gameFrame.add(new MainGUI().mainPanel);
				
				gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				gameFrame.pack();
				gameFrame.setVisible(true);
				
				SwingUtilities.windowForComponent(mainPanel).setVisible(false);
			}
		});
	}
}
