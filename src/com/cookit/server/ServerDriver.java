package com.cookit.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServerDriver {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		
		// this instance of Server is gonna be bound to the name RMIServer.
		Naming.rebind("RMIServer", new Server());
	}

}
