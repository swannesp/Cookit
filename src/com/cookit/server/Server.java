package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public class Server extends UnicastRemoteObject implements ServerIF{
	private static final long serialVersionUID = 1L;
	private ArrayList<ClientIF>	clients;
	private ArrayList<ClientIF>	queuingClients;
	private ArrayList<Game> gameRooms;
	private Authenticator  aut;
	private Map<String, Game> map;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public String getGameCode(){
	   StringBuilder sb = new StringBuilder(8);
	   for(int i = 0; i < 8; i++)
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   if (map.containsKey(sb.toString()))
	   		return getGameCode();
	   else
	   		return sb.toString();
	}
	
	
	protected Server() throws RemoteException {
		clients = new ArrayList<ClientIF>();
		queuingClients = new ArrayList<ClientIF>();
		gameRooms = new ArrayList<Game>();
		aut = new Authenticator();
		map = new HashMap<String, Game>();
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

	
	public Game join(ClientIF client, String id) throws RemoteException {
		if (map.containsKey(id)) {
			if (map.get(id).tryJoin(client)) {
				System.out.println(client.getName() + "joined room" + id);
				return map.get(id);
			}
			else {
				System.out.println("Room is full");	
				return null;	
			}
		}
		else {
			System.out.println("Room not found");
			return null;	
		}
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
	
	public Game createRoom(ClientIF client) throws RemoteException {
		System.out.println("in createroom");
		//new Thread(new Game(client)).start();
		String id = getGameCode();
		Game game = new Game(client, id);
		map.put(id, game);
		this.gameRooms.add(game);
		System.out.println("Game " + id + " created by" + client.getName());
		return game;
	}
}
