package com.cookit.client.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cookit.client.Client;

public class ConnectPage extends JFrame{
	String imgBackground;
	String imgButton;
	String imgLabel;
	String imgTextField;
	
	Dimension dimension;
	
	ImagePanel mainPanel;
	JPanel sidePanel;
	
	Client gameClient;
	
    
	public ConnectPage(Client client) throws IOException{
		this(client, 1200,600);
	}
	
	public ConnectPage(Client client, int width, int height) throws IOException {
		this.gameClient=client;

	   
        
		this.dimension = new Dimension(width,height);
		getImages();
		setBackground();
	    
        this.sidePanel = new ConnectPanel();
        

        mainPanel.add(this.sidePanel, new Integer(0), 0);
       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(this.dimension);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.mainPanel,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
      
	}
	
	private void getImages() {
		this.imgBackground = "C:\\Users\\swann\\M2\\Middleware\\Cookit\\src\\images\\background3.png";
		this.imgButton = "C:\\Users\\swann\\M2\\Middleware\\Cookit\\src\\images\\buttonlogin.png";
		this.imgLabel = "C:\\Users\\swann\\M2\\Middleware\\Cookit\\src\\images\\enteryournameResized.png";
		this.imgTextField = "C:\\Users\\swann\\M2\\Middleware\\Cookit\\src\\images\\textbox1.png";
	}
	
	private void setBackground() throws IOException {
		this.mainPanel = new ImagePanel(imgBackground);
	}

	private void replacePanel(JPanel panel){
		   Container parent = this.sidePanel.getParent();
		   int index = parent.getComponentZOrder(this.sidePanel);
		   // remove the old edition of the panel
		   parent.remove(this.sidePanel);
		   // generate the replacement panel
		   this.sidePanel = panel;
		   // place the replacement panel in the same relative location as the one it is replacing
		   parent.add(this.sidePanel, index);

		   // must call both of these, in the correct order
		   parent.validate();
		   parent.repaint();
		}


	public class ConnectPanel extends JPanel{
		private ImageButton loginButton;
		private JTextField textField;
		private ImageLabel textFieldLabel;
		
		protected ConnectPanel() throws IOException {
			setButton();
			setTextField();	
			this.setLayout(new BorderLayout());
			this.setBounds(100, 250, 250, 300);
			this.setOpaque(false);
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(new ImageLabel(imgLabel));
			this.add(textFieldLabel,BorderLayout.NORTH );
			this.add(Box.createRigidArea(new Dimension(0,60)));
			this.add(this.loginButton);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		private void setButton() throws IOException {
			this.loginButton = null;
			this.loginButton = new ImageButton(imgButton);
			this.loginButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                connect(textField.getText());
		            }
		        });
		}
		
		private void connect(String s) {
			try {
            	gameClient.send(textField.getText());
            	if(gameClient.authenticate(textField.getText())) {}
            		//card.show(cards, "One");
            		System.out.println(textField.getText());
            		replacePanel(new MenuPanel());
            } catch (RemoteException e1){} 
		}
		
		
		private void setTextField() throws IOException {
			this.textField = new JTextField();
			this.textField.setOpaque(false);
			this.textField.setBorder(BorderFactory.createEmptyBorder());
			this.textField.setMaximumSize(new Dimension(230,38));
			this.textField.setHorizontalAlignment(JTextField.CENTER);
			
			this.textFieldLabel = new ImageLabel(imgTextField);
			this.textFieldLabel.setLayout(new BorderLayout());
			this.textFieldLabel.add(textField);
			this.textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
	}

	
	public class MenuPanel extends JPanel{
		//private ImageButton createButton;
		//private ImageButton joinButton;
		//private ImageButton queueButton;
		private JButton createButton;
		private JButton joinButton;
		private JButton queueButton;
		
		protected MenuPanel() {
			setButtons();
			this.setLayout(new BorderLayout());
			this.setBounds(100, 250, 250, 300);
			this.setOpaque(false);
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(this.createButton);
			this.add(this.joinButton);
			this.add(this.queueButton);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		private void setButtons() {
			this.createButton = new JButton("create game (NI)");
			this.createButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               
	            }
	        });
			this.joinButton = new JButton("join game (NI)");
			this.joinButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               
	            }
	        });
			this.queueButton = new JButton("queue");
			this.queueButton.addActionListener(new ActionListener() {
				
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	queue();
	            }
	        });
		}
		
		public void queue() {
			
		}
		
	}
		
}
