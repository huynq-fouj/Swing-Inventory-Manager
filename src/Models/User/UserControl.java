package Models.User;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
	
	public boolean existsByName(String name) {
		return this.um.isExistsByName(name);
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

	public int countUser(UserObject similar) {
		return this.um.countUser(similar);
	}
	
	public DefaultTableModel getTableModel(UserObject similar) {
		String columnHeaders[] = {"ID", "Tên đăng nhập", "Họ tên", "Email", "Số điện thoại", "Số lần đăng nhập"};
		ArrayList<UserObject> items = this.getUserObjects(similar);
		Object data[][] = new Object[items.size()][columnHeaders.length];
		items.forEach(item -> {
			int i = items.indexOf(item);
			data[i][0] = item.getUser_id();
			data[i][1] = item.getUser_name();
			data[i][2] = item.getUser_fullname();
			data[i][3] = item.getUser_email();
			data[i][4] = item.getUser_phone();
			data[i][5] = item.getUser_logined();
		});
		return new DefaultTableModel(
				data,
				columnHeaders
		);
	}
}
