package com.cookit.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.cookit.server.GameIF;
import com.cookit.server.Usable;

public interface ClientIF extends Remote {
	public void getClients(ArrayList<ClientIF> clients) throws RemoteException;
	void retrieveMessage(String message) throws RemoteException;
	public String getName() throws RemoteException;
	public ArrayList<Usable> getUsables() throws RemoteException;
	public GameIF getGame() throws RemoteException;
}
