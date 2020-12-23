package com.cookit.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.cookit.server.ServerIF;

public class ClientDriver {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String serverURL = "rmi://localhost/RMIServer";
		ServerIF server = (ServerIF) Naming.lookup(serverURL);
		new Thread(new Client(server)).start();
	}
}
