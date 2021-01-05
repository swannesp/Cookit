package com.cookit.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;
import com.cookit.server.Game;
import com.cookit.server.GameIF;
import com.cookit.server.UsableIF;

public class ConnectPage extends JFrame{
	String imgBackground;
	String imgButton;
	String imgLabel;
	String imgTextField;
	
	Dimension dimension;
	
	ImagePanel mainPanel;
	 public JPanel sidePanel;
	
	Client gameClient;
	Game game;

	
    
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
		this.imgBackground = "src\\images\\background3.png";
		this.imgButton = "src\\images\\buttonlogin.png";
		this.imgLabel = "src\\images\\enteryournameResized.png";
		this.imgTextField = "src\\images\\textbox1.png";
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

	/*
	 * Panel de connexion
	 */
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
            		try {
						replacePanel(new MenuPanel());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
	/*
	 * Panel du menu
	 */
	public class MenuPanel extends JPanel{
		//private ImageButton createButton;
		//private ImageButton joinButton;
		//private ImageButton queueButton;
		private JTextField textField;
		private JButton createButton;
		private JButton joinButton;
		private JButton queueButton;
		
		protected MenuPanel() throws IOException {
			setButtons();
			setTextField();
			this.setLayout(new BorderLayout());
			this.setBounds(100, 250, 250, 300);
			this.setOpaque(false);
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(this.createButton);
			this.add(this.joinButton);
			this.add(this.queueButton);
			this.add(this.textField);
			this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		private void setTextField() throws IOException {
			this.textField = new JTextField();
			this.textField.setOpaque(false);
			this.textField.setBorder(BorderFactory.createEmptyBorder());
			this.textField.setMaximumSize(new Dimension(230,38));
			this.textField.setHorizontalAlignment(JTextField.CENTER);
		}
		
		private void setButtons() {
			this.createButton = new JButton("create game (NI)");
			this.createButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               create();
	            }
	        });
			this.joinButton = new JButton("join game (NI)");
			this.joinButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	join(textField.getText());
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
		public void join(String s) {
			try {
				gameClient.join(s);
				replacePanel(new RoomPanel());
			} catch (RemoteException e) {}
		}
		
		public void create() {
			try {
				gameClient.createRoom();
				replacePanel(new RoomPanel());
			} 	catch (RemoteException e) {}
		}
		
		public void queue() {
			
		}
	}

	/*
	 * Panel de l'écran de room
	 */
	public class RoomPanel extends JPanel{
		ArrayList<ClientIF> clients;
		PlayerPanel panel_p1 = new PlayerPanel(Color.blue, null, 0, 0);
		PlayerPanel panel_p2 = new PlayerPanel(Color.red, null, 0, 100);
		JPanel panel_start = new JPanel();
		JPanel panel_game = new JPanel();
		JPanel panel_recette = new JPanel(); 
		JPanel panel_usables = new JPanel();

		Dimension size = new Dimension(250,100);
		JButton startButton = new JButton("Start");
		
		

		protected RoomPanel() throws RemoteException{
			this.setLayout(null);
			this.setBounds(50, 50, 1100, 500);
			this.setOpaque(true);
			panel_game.setLayout( null);
			panel_game.setBounds(300, 0, 500, 500);
			panel_game.setOpaque(true);
			panel_game.setBackground(Color.black);
			panel_start.setBackground(Color.GREEN);
			panel_start.setBounds(0, 200, 60, 40);
			JLabel label_recette = new JLabel("Recette de la tarte aux tomates du soleil");
			panel_recette.setBackground(Color.WHITE);
			panel_recette.setBounds(0, 240, 500, 500);
			panel_recette.setVisible(false);
			panel_usables.setBackground(Color.WHITE);
			panel_usables.setBounds(300, 0, 500, 500);
			panel_usables.setVisible(false);

			retrieveInfos(gameClient.getGame());
			
			this.setLayout(null);
			this.setBackground(Color.green);
			
			this.startButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
						start();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
			
			this.add(panel_p1);
			this.add(panel_p2);
			panel_recette.add(label_recette);
			this.add(panel_recette);
			this.add(panel_usables);
			
			if(clients.size() < 2) {
				panel_start.add(this.startButton);
				this.add(panel_start);
			}
			
			//this.add(this.createButton);
			//this.add(this.joinButton);
			//this.add(this.queueButton);
			//this.add(this.textField);
			//this.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		public void start() throws InterruptedException {
			try {
				if(clients.size() >= 2) {
					gameClient.getGame().initSteps();
					this.startButton.setEnabled(false);
					displaySteps();
					displayUsables();
				}
				else {
					System.out.println("Vous ne pouvez pas jouer seul");
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		public void refreshDisplay(String hostname, String playername) {
			panel_p1.setPlayer(hostname);
			panel_p2.setPlayer(playername);
			this.add(panel_game);
		}
		public void retrieveInfos(GameIF game) throws RemoteException {
			this.clients = game.retrieveClients();
			update();
		}
		
		public void refreshInfos(ArrayList<ClientIF> clients) throws RemoteException{
			this.clients = clients;
			update();
		}
		
		public void update() throws RemoteException {
			if (clients.size() > 0) 
				panel_p1.setPlayer(clients.get(0).getName());
			if (clients.size() > 1) 
				panel_p2.setPlayer(clients.get(1).getName());
		}
		
		public void displaySteps() throws RemoteException {
			panel_recette.setVisible(true);
			for(String step : gameClient.getGame().getSteps()) {
				JButton JButton = new JButton(step);
				JButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
						switch(step) {
							case "test1" : System.out.println("take knife");  
							break;
							case "test2" : System.out.println("take bowl");
							break;
							case "test3" : System.out.println("take fork");
							break;
							case "test4" : System.out.println("take spoon");
							break;
							case "test5" : System.out.println("take oven");
							break;
						}	
		            }
		        });
				panel_recette.add(JButton);
			}
		}
		
		public void displayUsables() throws RemoteException, InterruptedException {
			panel_usables.setVisible(true);
			for(UsableIF usable : gameClient.getGame().retrieveUsables()) {
				JButton JButton = new JButton(usable.getName());
				JButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	try {
							usable.takeUsable(gameClient);
							} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
		            }
		        });
				panel_usables.add(JButton);
			}
		}
	}
	
	
}
