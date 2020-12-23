package com.cookit.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
	public void getRoomJoined(String hostname, String playername) throws RemoteException;
	void retrieveMessage(String message) throws RemoteException;
	String getName() throws RemoteException;
}
