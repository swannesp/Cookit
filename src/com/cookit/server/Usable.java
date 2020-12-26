package com.cookit.server;

import com.cookit.client.Client;

public interface Usable {
	public void takeOven(Client client) throws InterruptedException;
	public void releaseOven(Client client);
	void takeKnife(Client client) throws InterruptedException;
	void releaseKnife(Client client);
	void takeBowl(Client client) throws InterruptedException;
	void releaseBowl(Client client);
	void takeFork(Client client) throws InterruptedException;
	void releaseFork(Client client);
	void releaseSpoon(Client client);
	void takeSpoon(Client client) throws InterruptedException;
}
