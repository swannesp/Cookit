package com.cookit.client.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {
private Image image;
	
	public ImageLabel(String image, int width, int height) throws IOException {
		this(ImageIO.read(new File(image)).getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	public ImageLabel(String image) throws IOException {
		this(ImageIO.read(new File(image)));
	}
	public ImageLabel(Image image, int width, int height) {
		this(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));	
	}
	public ImageLabel(Image image) {
		this.image = image;
		//System.out.println("plop");
		Dimension size = new Dimension(image.getWidth(null),image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	 
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

}
