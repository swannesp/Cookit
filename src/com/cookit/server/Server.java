package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public class Server extends UnicastRemoteObject implements ServerIF{
	private static final long serialVersionUID = 1L;
	private ArrayList<ClientIF>	clients;
	private ArrayList<ClientIF>	queuingClients;
	private ArrayList<Game> gameRooms;
	private Authenticator  aut;

	protected Server() throws RemoteException {
		clients = new ArrayList<ClientIF>();
		queuingClients = new ArrayList<ClientIF>();
		gameRooms = new ArrayList<Game>();
		this.aut = new Authenticator();
	}

	/** test */
	public synchronized void display(String s) throws RemoteException{
		System.out.println(s);
	}
	
	public synchronized void registerClient(ClientIF client) throws RemoteException {
		// TODO Auto-generated method stub
		this.clients.add(client);
		
	}

	public synchronized void broadcastMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		int i = 0;
		while (i < clients.size()) {
			clients.get(i++).retrieveMessage(message);
		}
		
	}
	
	public synchronized boolean authenticate(String clientName) throws RemoteException {
		boolean b = aut.authenticate(clientName);
		if (b)
			display("authenticated: " + clientName);
		else
			display("already in use: " + clientName);
		return b;
	}

	
	public GameIF join(ClientIF client) throws RemoteException {
		return null;
	}

	/**
	 * Test if there is a room waiting for players, if not creates one,
	 * if yes tries to join it.
	 */
	public GameIF queue(ClientIF client) throws RemoteException {
		// TODO Auto-generated method stub
		if (gameRooms.isEmpty()){
			client.retrieveMessage("room created");
			return this.createRoom(client);
		}
		else {
			for (Game plop :gameRooms) {
				if(plop.tryJoin(client)) {
					plop.getHost().retrieveMessage(client.getName() + "joined");
					client.retrieveMessage(client.getName() + "joined");
					return plop;
				}
				//
			}
			return null;
		}
	}
	
	public GameIF createRoom(ClientIF client) throws RemoteException {
		System.out.println("in createroom");
		//new Thread(new Game(client)).start();
		Game game = new Game(client);
		this.gameRooms.add(game);
		return game;
	}

}
