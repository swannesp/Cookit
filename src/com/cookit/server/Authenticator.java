package com.cookit.server;

import java.util.ArrayList;

public class Authenticator {
	//private String[] loggedList;
	private ArrayList<String> loggedList;
	
	protected Authenticator() {
		//this.loggedList = new String[]{};
		this.loggedList = new ArrayList<String>();
	}
	
	public synchronized boolean authenticate(String s) {
		if (this.loggedList.contains(s.toLowerCase())) {
			return false;
		}
		else{
			loggedList.add(s);
			return true;
		}
	}
}
