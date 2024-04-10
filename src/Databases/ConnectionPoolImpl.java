package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {

	private String driver;
	private String url;
	private String username;
	private String userpass;
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost:3306/btl_java?allowMultiQueries=true";
		this.username = "root";
		this.userpass = "123456";
		this.loadDriver();
		this.pool = new Stack<>();
	}
	
	private void loadDriver() {
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		if(this.pool.isEmpty()) {
			System.out.println(objectName + " have created a new Connection");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			System.out.println(objectName + " have popped the Connection");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		System.out.println(objectName + " have pushed the Connection");
		this.pool.push(con);
	}
	
	protected void finalized()throws Throwable{
		this.pool.clear();
		this.pool = null;
		System.gc();
		System.out.println("ConnectionPool is closed");
	}

}
