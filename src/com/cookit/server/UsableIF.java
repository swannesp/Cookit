package com.cookit.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public interface UsableIF extends Remote {
	String getName() throws InterruptedException, RemoteException;
	void takeUsable(ClientIF client) throws InterruptedException, RemoteException;
	void releaseUsable(ClientIF client) throws InterruptedException, RemoteException;
}
