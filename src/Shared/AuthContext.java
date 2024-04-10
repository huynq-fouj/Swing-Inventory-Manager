package Shared;

import Models.Objects.UserObject;

public class AuthContext {

	private static UserObject user;
	
	public static void setUser(UserObject user) {
		AuthContext.user = user;
	}
	
	public static UserObject getUser() {
		return AuthContext.user;
	}
	
}
