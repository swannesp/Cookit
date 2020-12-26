package com.cookit.server;

import com.cookit.client.Client;

public class Bowl implements Usable{

	private boolean available = true;
	private Game game;

	public Bowl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeBowl(Client client) throws InterruptedException {
		if(available) {
			game.getUsables().remove(this);
			client.getUsables().add(new Bowl());
			available = false;
		}
		else {
			wait();
		}
	}

	@Override
	public void releaseBowl(Client client) {
		for(Usable usable : client.getUsables()) {
			if(usable instanceof Bowl) {
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