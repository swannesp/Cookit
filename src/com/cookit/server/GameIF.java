package com.cookit.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.cookit.client.ClientIF;

public interface GameIF extends Remote {
	public boolean tryJoin(ClientIF client) throws RemoteException;
	public boolean tryUse() throws RemoteException;
	public ArrayList<ClientIF> retrieveClients() throws RemoteException; 
	public ArrayList<String> getSteps() throws RemoteException;
	public ArrayList<UsableIF> retrieveUsables() throws RemoteException;
	public ArrayList<String> initSteps() throws RemoteException;
	//public ArrayList<Usable> initUsables() throws RemoteException;
	
}
