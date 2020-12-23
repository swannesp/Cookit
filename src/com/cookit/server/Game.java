package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public class Game extends UnicastRemoteObject implements GameIF, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playersIn;
	
	private ClientIF host;
	private String hostname;
	private String playername;
	private ArrayList<Usable> usables;
	private ArrayList<ClientIF> clients;
	private boolean used;
	private String id;
	
	
	protected Game(ClientIF host, String hostname, String id) throws RemoteException {
		this.id = id;
		this.host = host;
		this.hostname = hostname;
		this.usables = new ArrayList<Usable>();
		this.clients = new ArrayList<ClientIF>();
		this.clients.add(host);
		this.playersIn = 1;
		this.used=false;
	}

	public ArrayList<Usable> getUsables(){
		return this.usables;
	}
	
	public ClientIF getHost(){
		return this.host;
	}
	
	public void refreshRoom() throws RemoteException {
		for (ClientIF client : this.clients)
			client.getRoomJoined(hostname,playername);
	}
	
	public synchronized boolean tryJoin(ClientIF client, String playername) throws RemoteException {
		if(playersIn < 2) {
			playersIn += 1;
			this.clients.add(client);
			this.playername = playername;
			refreshRoom();
			return true;
		}
		else return false;
	}
	
	/** */
	public synchronized boolean tryUse() throws RemoteException {
		if (!this.used) {
			this.used = true;
			return true;
		}
		else return false;
	}
	
	public void run() {
		
	}
}
