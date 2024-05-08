package Models.Objects;

import java.util.ArrayList;

public class UserRole {

	private int role;
	private String name;

	public UserRole(int role, String name) {
		this.role = role;
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	private static UserRole[] toArray(ArrayList<UserRole> items) {
		UserRole[] arr = new UserRole[items.size()];
		items.forEach(item -> arr[items.indexOf(item)] = item);
		return arr;
	}

	public static UserRole[] getRoleList() {
		ArrayList<UserRole> items = new ArrayList<>();
		// Thêm role tại đây;
		items.add(new UserRole(0, "Người dùng"));
		items.add(new UserRole(3, "Quản trị viên"));

		return UserRole.toArray(items);
	}

}
