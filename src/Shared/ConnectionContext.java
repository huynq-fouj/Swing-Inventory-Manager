package Shared;

import Databases.ConnectionPool;

public class ConnectionContext {

	private static ConnectionPool cp;
	
	public static void setCP(ConnectionPool cp) {
		ConnectionContext.cp = cp;
	}
	
	public static ConnectionPool getCP() {
		return ConnectionContext.cp;
	}
	
}
