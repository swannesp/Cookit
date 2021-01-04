package com.cookit.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.cookit.client.Client;

public class Bowl extends UnicastRemoteObject implements Usable{

	private boolean available = true;
	private Game game;
	
	public Bowl() throws RemoteException {
	}

	public String getName() throws InterruptedException, RemoteException {
		return "Bowl";
	}
	
	@Override
	public void takeBowl(Client client) throws InterruptedException, RemoteException {
		if(available) {
			game.retrieveUsables().remove(this);
			client.getPlayerUsables().add(new Bowl());
			available = false;
		}
		else {
			wait();
		}
	}

	@Override
	public void releaseBowl(Client client) throws RemoteException {
		for(Usable usable : client.getPlayerUsables()) {
			if(usable instanceof Bowl) {
				client.getPlayerUsables().remove(usable);
			}
		}
		available = true;
		game.retrieveUsables().add(this);
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
	public void takeKnife(Client client) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseKnife(Client client) {
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
