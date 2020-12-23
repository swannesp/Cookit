package com.cookit.client.gui.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	//Panel inside of frames
	//Panelss coe with paintcomponents that let us draw graphics on em
	//java game = jfram with 1 or more jpanels inside where things are drawn
	
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	
	public GamePanel() {
		super(); // good practice
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.BLACK); //Black for testing, white for live
		
	}

}
