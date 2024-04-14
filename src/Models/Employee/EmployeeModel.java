package Models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Objects.EmployeeObject;

public class EmployeeModel {

private Employee e;
	
	public EmployeeModel(ConnectionPool cp) {
		this.e = new EmployeeImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.e.getCP();
	}
	
	public void releaseConnection() {
		this.e.releaseConnection();
	}
	
	public boolean addEmployee(EmployeeObject item) {
		return this.e.addEmployee(item);
	}
	
	public boolean editEmployee(EmployeeObject item) {
		return this.e.editEmployee(item);
	}
	
	public boolean delEmployee(EmployeeObject item) {
		return this.e.delEmployee(item);
	}
	
	public EmployeeObject getEmployee(int id) {
		ResultSet rs = this.e.getEmployee(id);
		EmployeeObject item = null;
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new EmployeeObject();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public ArrayList<EmployeeObject> getEmployees(EmployeeObject similar) {
		ArrayList<EmployeeObject> items = new ArrayList<>();
		EmployeeObject item = null;
		ResultSet rs = this.e.getEmployees(similar);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new EmployeeObject();
					
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
	
	public int countEmployee(EmployeeObject similar) {
		ResultSet rs = this.e.countEmployee(similar);
		if(rs != null) {
			try {
				if(rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
