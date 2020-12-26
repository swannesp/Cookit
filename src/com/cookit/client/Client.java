package com.cookit.client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cookit.client.gui.ConnectPage;
import com.cookit.client.gui.ConnectPage.MenuPanel;
import com.cookit.client.gui.ConnectPage.RoomPanel;
import com.cookit.server.Game;
import com.cookit.server.GameIF;
import com.cookit.server.ServerIF;
import com.cookit.server.Usable;

public class Client extends UnicastRemoteObject implements ClientIF, Runnable {
	private static final long serialVersionUID = 1L;
	private ServerIF server;
	public GameIF game;
	private String name = null;
	private ConnectPage UI;
	private List<Usable> usables = new ArrayList<>();

	protected Client( ServerIF server) throws RemoteException {
		//this.name = name;
		this.server = server;
		server.registerClient(this);
	}

	public String getName() throws RemoteException {
		return this.name;
	}
	
	public List<Usable> getUsables(){
		return this.usables;
	}
	
	public GameIF getGame() {
		return this.game;
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
			game = server.createRoom(this);
			System.out.println("Partie crée!");
		}
		else if (message.matches("^join")) {
			game = server.queue(this);
			
		}
	}
	
	
	/*
	 * Méthodes out
	 */
	public void createRoom() throws RemoteException {
		this.game = server.createRoom(this);

	}
	public void join(String s) throws RemoteException {
		this.game = server.join(this, s);
	}
	
	public void send(String s) throws RemoteException {
		server.display(s);
	}
	
	public void test(String s) throws RemoteException {
		server.test(this, s);
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
	public void getClients(ArrayList<ClientIF> clients) throws RemoteException{
		if (UI.sidePanel instanceof  RoomPanel) {
			((RoomPanel) UI.sidePanel).refreshInfos(clients);
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
