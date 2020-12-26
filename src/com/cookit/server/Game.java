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
	private ArrayList<Usable> usables;
	private ArrayList<ClientIF> clients;
	private ArrayList<String> steps;
	private boolean used;
	private String id;
	
	
	protected Game(ClientIF host, String id) throws RemoteException {
		this.id = id;
		this.host = host;
		this.usables = new ArrayList<Usable>();
		this.clients = new ArrayList<ClientIF>();
		this.steps = new ArrayList<String>();
		this.clients.add(host);
		this.playersIn = 1;
		this.used=false;
	}

	public ArrayList<Usable> getUsables(){
		return this.usables;
	}
	
	public ArrayList<String> getSteps(){
		return this.steps;
	}
	
	public ClientIF getHost(){
		return this.host;
	}
	
	public ArrayList<ClientIF> getClients(){
		return this.clients;
	}
	
	public void refreshRoom(ClientIF client, String host, String player) throws RemoteException {
		client.getRoomJoined(host,player);
	}
	
	public synchronized boolean tryJoin(ClientIF client) throws RemoteException {
		if(playersIn < 2) {
			playersIn += 1;
			this.clients.add(client);
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
	
	public synchronized ArrayList<String> initSteps() {
		System.out.println("Recette de la tarte au tomates du soleil");
		this.steps.add("D�couper la p�te feuillet�e en rectangle � l'aide du couteau");
		this.steps.add("Piquer la p�te avec une fourchette");
		this.steps.add("Couper la ciboulette avec le couteau");
		this.steps.add("M�langer la cr�me, le parmesan et la ciboulette dans un saladier, � l'aide d'une cuill�re");
		this.steps.add("Etaler � la cuill�re la cr�me obtenue sur la p�te");
		this.steps.add("Couper les tomates avec le couteau");
		this.steps.add("Enfourner le tout");
		return this.steps;
	}
	
	public synchronized ArrayList<Usable> initUsables(){
		this.usables.add(new Oven());
		this.usables.add(new Knife());
		this.usables.add(new Fork());
		this.usables.add(new Spoon());
		this.usables.add(new Bowl());
		return this.usables;
	}
	
	public void run() {
		
	}
}
