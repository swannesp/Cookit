package com.cookit.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public class PlayerPanel extends JPanel {
	private JLabel name_label;
	public PlayerPanel(Color color, Client client, int x, int y) throws RemoteException {
		name_label = new JLabel();
		
		if (client == null)
			name_label.setText("empty");
		else
			name_label.setText(client.getName());
		
		Dimension size = new Dimension(300, 100);
		setLayout(new BorderLayout());
		setLocation(x,y);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setBackground(color);
		add(name_label);
		
	}
	
	public void setPlayer(String client){
		if (client == null)
			name_label.setText("empty");
		else
			name_label.setText(client);
		name_label.revalidate();
		name_label.repaint();
		this.repaint();
	}
}
