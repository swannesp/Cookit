package com.cookit.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
	void retrieveMessage(String message) throws RemoteException;
	String getName() throws RemoteException;
}
