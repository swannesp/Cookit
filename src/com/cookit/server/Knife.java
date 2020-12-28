package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.cookit.client.Client;

public class Knife extends UnicastRemoteObject implements Usable{

	private boolean available = true;
	private Game game;

	public Knife() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeKnife(Client client) throws InterruptedException, RemoteException {
		if(available) {
			game.getUsables().remove(this);
			client.getUsables().add(new Knife());
			available = false;
		}
		else {
			wait();
		}
	}

	@Override
	public void releaseKnife(Client client) {
		for(Usable usable : client.getUsables()) {
			if(usable instanceof Knife) {
				client.getUsables().remove(usable);
			}
		}
		available = true;
		game.getUsables().add(this);
	}

	@Override
	public void takeOven(Client client) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseOven(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeBowl(Client client) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseBowl(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeFork(Client client) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseFork(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseSpoon(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeSpoon(Client client) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}
}
