package com.cookit.client.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class ImageTextField extends JTextField {
	private Image image;
	
	public ImageTextField(String image, int width, int height) throws IOException {
		this(ImageIO.read(new File(image)).getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}
	public ImageTextField(String image) throws IOException {
		this(ImageIO.read(new File(image)));
	}
	public ImageTextField(Image image, int width, int height) {
		this(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));	
	}
	public ImageTextField(Image image) {
		this.image = image;
		//System.out.println("plop");
		Dimension size = new Dimension(image.getWidth(null),image.getHeight(null));
		System.out.println(image.getWidth(null));
		System.out.println(image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		this.setBorder(BorderFactory.createEmptyBorder());
	}
	
	 
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}