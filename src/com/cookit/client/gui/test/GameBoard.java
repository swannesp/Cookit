package com.cookit.client.gui.test;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameBoard extends JFrame {
		//Jframe -> used for organisation
		//JPanel -> for content
	/** draws the shapes */
	private GamePanel myGamePanel;
	
	public GameBoard() {
		super("plop");
		myGamePanel = new GamePanel();
		start();
	}

	public void start() {
		setVisible(true);
		buildGUI();
	}
	
	private void buildGUI() {
		/**this shoukd set up and display
		* the board data
		* */
		//JPanel masterPanel = new JPanel();
		
		/** good practice master panel =
		 * JPanel to hold all other panels
		 * ->no need to modify the Jframe
		 */
		//masterPanel.add(myGamePanel);
		
		//add(masterPanel);
		add(myGamePanel);
		
		pack(); // pack 'resizes' the panel so things fit in
		
	}
	
	public static void main(String[] args) {
		GameBoard myGameBoard = new GameBoard();
		myGameBoard.start();
		
		
	}
}
