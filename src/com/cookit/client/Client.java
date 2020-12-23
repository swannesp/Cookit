package com.cookit.client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.cookit.client.gui.ConnectPage;
import com.cookit.client.gui.ConnectPage.RoomPanel;
import com.cookit.server.Game;
import com.cookit.server.GameIF;
import com.cookit.server.ServerIF;

public class Client extends UnicastRemoteObject implements ClientIF, Runnable {
	private static final long serialVersionUID = 1L;
	private ServerIF server;
	private GameIF game;
	private String name = null;
	private ConnectPage UI;

	protected Client( ServerIF server) throws RemoteException {
		//this.name = name;
		this.server = server;
		server.registerClient(this);
	}

	public String getName() {
		return this.name;
	}
	public void retrieveMessage(String message) throws RemoteException {
		System.out.println(message);
	}

	//temporraire, ligne de commande
	public void analyzeMessage(String message) throws RemoteException {
		if (message.matches("^use")) {
			if(game.tryUse()) {
				System.out.println("libre");
			}
			else System.out.println("occupé!");
		}
		else if (message.matches("^create")) {
			game = server.createRoom(this, this.name);
			System.out.println("Partie crée!");
		}
		else if (message.matches("^join")) {
			game = server.queue(this, this.name);
			
		}
	}
	
	
	/*
	 * Méthodes out
	 */
	public void createRoom() throws RemoteException {
		server.createRoom(this, this.name);

	}
	public void join(String s) throws RemoteException {
		server.join(this, this.name, s);
	}
	
	public void send(String s) throws RemoteException {
		server.display(s);
	}
	
	public boolean authenticate(String s) throws RemoteException {
		if (server.authenticate(s)) {
			this.name = s;
			return true;
		}
		else return false;		
	}
	
	public void queue() throws RemoteException {
		
	}
	
	/*
	 * Méthodes in
	 */
	//notifie joueur quand qqun rejoint la room, met a jour
	public void getRoomJoined(String hostname, String playername) throws RemoteException{
		if (UI.sidePanel instanceof  RoomPanel) {
			((RoomPanel) UI.sidePanel).refreshDisplay(hostname,playername);
		}
	}
	public void run() {
		try {
			UI = new ConnectPage(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Scanner scanner = new Scanner(System.in);
//		String message;
//		while (true) {
//			message = scanner.nextLine();
//			try {
//				this.analyzeMessage(message);
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

}
