package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public class Usable extends UnicastRemoteObject implements UsableIF{

	private int nb_dispo;
	private String name;
	private int nb_utilise;
	
	protected Usable(int nb_dispo, String name) throws RemoteException {
		super();
		this.nb_dispo = nb_dispo;
		this.name = name;
		nb_utilise = 0;
	
	}
	
	public String getName() throws InterruptedException, RemoteException {
		return name;
	}
	
	public int getNbDispo() throws InterruptedException, RemoteException {
		return nb_dispo;
	}
	
	public int getNbUtilise() throws InterruptedException, RemoteException {
		return nb_utilise;
	}

	@Override
	public synchronized void takeUsable(ClientIF client) throws InterruptedException, RemoteException {
		if(nb_dispo > nb_utilise) {
			client.getPlayerUsables().add(this);
			nb_utilise ++;
		}
		else {
			System.out.println("Not available");
			wait();
		}
		
	}
	
	@Override
	public void releaseUsable(ClientIF client) throws InterruptedException, RemoteException {
		client.getPlayerUsables().remove(this);
		nb_utilise --;
	}

	

}
