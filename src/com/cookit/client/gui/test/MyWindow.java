package com.cookit.client.gui.test;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MyWindow extends JFrame{

	private static final long serialVersionUID = -4939544011287453046L;
	String image = "src\\images\\background.png";
	JButton b1;
	JLabel l1;
	Dimension dimension;
	Dimension buttonDimension;
	 
	public MyWindow() throws IOException {
		super( "My first Swing application");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.dimension = new Dimension(900, 600);
		this.buttonDimension = new Dimension(dimension.width / 30, dimension.height/30);
		//set size avant position
		this.setSize(dimension);
		b1 = new JButton("Push me!");
		b1.setPreferredSize(buttonDimension);
				
		//null en param = centré par rapport au bureau
		this.setLocationRelativeTo(null);
		Image newImage = ImageIO.read(new File(image)).getScaledInstance(dimension.width, dimension.height, Image.SCALE_DEFAULT);
		
		//this.setLayout(new BorderLayout());
		this.setContentPane(new JLabel(new ImageIcon(newImage)));
		//this.setLayout(new FlowLayout());
		//l1=new JLabel("Here is a label");
		//b1=new JButton("Here is a button");
		//this.add(l1);
		//this.add(b1);
		
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(Box.createRigidArea(new Dimension((int) (dimension.getWidth() / 50), 0)));
		this.add( b1);
		this.add( new JButton("Click me!"));
		this.add( new JCheckBox("Check me"));
		
	}
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		// Apply a look'n feel
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		
		MyWindow myWindow;
		try {
			myWindow = new MyWindow();
			myWindow.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
