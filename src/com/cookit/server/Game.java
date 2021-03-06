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
	private ArrayList<UsableIF> usables;
	private ArrayList<ClientIF> clients;
	private ArrayList<String> steps;
	private boolean used;
	private String id;
	
	
	protected Game(ClientIF host, String id) throws RemoteException {
		this.id = id;
		this.host = host;
		this.hostname = hostname;
		this.usables = new ArrayList<UsableIF>();
		this.clients = new ArrayList<ClientIF>();
		this.steps = new ArrayList<String>();
		this.clients.add(host);
		this.playersIn = 1;
		this.used=false;
	}

	public ArrayList<UsableIF> retrieveUsables() throws RemoteException{
		return usables;
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
	

	public void refreshRoom() throws RemoteException {
		for (ClientIF client : this.clients)
			client.refreshRoom(clients);
	}
	public void quit(ClientIF client) throws RemoteException {
		clients.remove(client);
		playersIn--;
		refreshRoom();
	}
	public String getID() throws RemoteException {
		return this.id;
	}
	
	public synchronized boolean tryJoin(ClientIF client) throws RemoteException {
		if(playersIn < 2) {
			playersIn += 1;
			this.clients.add(client);
			refreshRoom();
			return true;
		}
		else return false;
	}
	public ArrayList<ClientIF> retrieveClients() throws RemoteException{
		return clients;
	}
	
	/** */
	public synchronized boolean tryUse() throws RemoteException {
		if (!this.used) {
			this.used = true;
			return true;
		}
		else return false;
	}
	
	public void start() throws RemoteException, InterruptedException {
		initSteps();
		for (ClientIF client : this.clients)
			client.start();
	}
	public synchronized ArrayList<String> initSteps() {
		System.out.println("Recette de la tarte aux tomates du soleil");
		this.steps.add("D�couper la p�te feuillet�e en rectangle � l'aide du couteau");
		this.steps.add("Piquer la p�te avec une fourchette");
		this.steps.add("Couper la ciboulette avec le couteau");
		this.steps.add("M�langer la cr�me, le parmesan et la ciboulette dans un saladier, � l'aide d'une cuill�re");
		this.steps.add("Etaler � la cuill�re la cr�me obtenue sur la p�te");
		this.steps.add("Couper les tomates avec le couteau");
		this.steps.add("Enfourner le tout");
		return this.steps;
	}
	
	public synchronized ArrayList<UsableIF> initUsables() throws RemoteException {
		this.usables.add(new Usable(1, "Oven"));
		this.usables.add(new Usable(1, "Fork"));
		this.usables.add(new Usable(1, "Spoon"));
		this.usables.add(new Usable(1, "Bowl"));
		this.usables.add(new Usable(1, "Knife"));
		return this.usables;
	}
	
	public void run() {
		
	}
}
