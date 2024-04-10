package Shared;

import Models.Objects.User;

public class AuthContext {

	private static User user;
	
	public static void setUser(User user) {
		AuthContext.user = user;
	}
	
	public static User getUser() {
		return AuthContext.user;
	}
	
}
