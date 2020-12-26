package com.cookit.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.SecureRandom;

import com.cookit.client.Client;
import com.cookit.client.ClientIF;

public interface ServerIF extends Remote{

	void registerClient(ClientIF client) throws RemoteException;
	void broadcastMessage(String message) throws RemoteException;
	GameIF queue(ClientIF client) throws RemoteException;
	GameIF createRoom(ClientIF client) throws RemoteException; 
	GameIF join(ClientIF client, String id) throws RemoteException;
	GameIF initGame(ClientIF client) throws RemoteException;
	void display(String s) throws RemoteException;
	public boolean authenticate(String clientName) throws RemoteException;
}
