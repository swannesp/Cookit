package com.cookit.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.cookit.server.GameIF;
import com.cookit.server.UsableIF;

public interface ClientIF extends Remote {
	public void getClients(ArrayList<ClientIF> clients) throws RemoteException;
	void retrieveMessage(String message) throws RemoteException;
	public String getName() throws RemoteException;
	public ArrayList<UsableIF> getPlayerUsables() throws RemoteException;
	public GameIF getGame() throws RemoteException;
}
