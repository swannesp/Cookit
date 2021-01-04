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
	private ArrayList<String> steps;
	private boolean used;
	private String id;
	
	
	protected Game(ClientIF host, String id) throws RemoteException {
		this.id = id;
		this.host = host;
		this.hostname = hostname;
		this.usables = new ArrayList<Usable>();
		this.clients = new ArrayList<ClientIF>();
		this.steps = new ArrayList<String>();
		this.clients.add(host);
		this.playersIn = 1;
		this.used=false;
	}

	public ArrayList<Usable> retrieveUsables() throws RemoteException{
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
			client.getClients(clients);
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
	
	public synchronized ArrayList<String> initSteps() {
		System.out.println("Recette de la tarte aux tomates du soleil");
		this.steps.add("Découper la pâte feuilletée en rectangle à l'aide du couteau");
		this.steps.add("Piquer la pâte avec une fourchette");
		this.steps.add("Couper la ciboulette avec le couteau");
		this.steps.add("Mélanger la crème, le parmesan et la ciboulette dans un saladier, à l'aide d'une cuillère");
		this.steps.add("Etaler à la cuillère la crème obtenue sur la pâte");
		this.steps.add("Couper les tomates avec le couteau");
		this.steps.add("Enfourner le tout");
		return this.steps;
	}
	
	public synchronized ArrayList<Usable> initUsables() throws RemoteException {
		this.usables.add(new Oven());
		this.usables.add(new Knife());
		this.usables.add(new Fork());
		this.usables.add(new Spoon());
		this.usables.add(new Bowl());
		for(Usable usable : this.usables) {
			System.out.println(usable.getClass());
		}
		return this.usables;
	}
	
	public void run() {
		
	}
}
