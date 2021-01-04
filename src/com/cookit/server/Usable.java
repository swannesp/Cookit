package com.cookit.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cookit.client.Client;

public interface Usable extends Remote {
	public void takeOven(Client client) throws InterruptedException, RemoteException;
	public void releaseOven(Client client) throws RemoteException;
	public String getName() throws InterruptedException, RemoteException;
	void takeKnife(Client client) throws InterruptedException, RemoteException;
	void releaseKnife(Client client) throws RemoteException;
	void takeBowl(Client client) throws InterruptedException, RemoteException;
	void releaseBowl(Client client) throws RemoteException;
	void takeFork(Client client) throws InterruptedException, RemoteException;
	void releaseFork(Client client) throws RemoteException;
	void releaseSpoon(Client client) throws RemoteException;
	void takeSpoon(Client client) throws InterruptedException, RemoteException;
	
}
