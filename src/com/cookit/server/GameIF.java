package com.cookit.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cookit.client.ClientIF;

public interface GameIF extends Remote {
	public boolean tryJoin(ClientIF client, String playername) throws RemoteException;
	public boolean tryUse() throws RemoteException; 
}
