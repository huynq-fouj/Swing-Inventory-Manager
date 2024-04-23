package Models.User;

import java.sql.ResultSet;

import Databases.ShareControl;
import Models.Objects.UserObject;

public interface User extends ShareControl {

	public boolean addUser(UserObject item);
	public boolean isExisting(UserObject item);
	public boolean editUser(UserObject item);
	public boolean delUser(UserObject item);
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ResultSet getUsers(UserObject similar);
	public ResultSet countUser(UserObject similar);
	
}
