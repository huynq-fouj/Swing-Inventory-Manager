package Models.User;

import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Objects.UserObject;

public class UserControl {

	private UserModel um;
	
	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	protected void finallize() throws Throwable {
		this.um = null;
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}

	public boolean editUser(UserObject item) {
		return this.um.editUser(item);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String password) {
		return this.um.getUserObject(username, password);
	}
	
	public ArrayList<UserObject> getUserObjects(UserObject similar) {
		return this.um.getUserObjects(similar);
	}
	
}
