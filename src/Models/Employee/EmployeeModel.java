package Models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Objects.EmployeeObject;
import Utilities.Utilities;

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
					item.setEmployee_id(rs.getInt("employee_id"));
					item.setEmployee_fullname(Utilities.decode(rs.getString("employee_fullname")));
					item.setEmployee_phone(rs.getString("employee_phone"));
					item.setEmployee_address(Utilities.decode(rs.getString("employee_address")));
					item.setEmployee_birthday(rs.getString("employee_birthday"));
					item.setEmployee_email(rs.getString("employee_email"));
					item.setEmployee_salary(rs.getDouble("employee_salary"));
					item.setAuthor_id(rs.getInt("employee_author_id"));
					item.setEmployee_created_date(rs.getString("employee_created_date"));
					item.setEmployee_modified_date(rs.getString("employee_modified_date"));
					item.setEmployee_debt(rs.getDouble("employee_debt"));
					item.setEmployee_position(Utilities.decode(rs.getString("employee_position")));
					item.setEmployee_notes(Utilities.decode(rs.getString("employee_notes")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public ArrayList<EmployeeObject> getEmployees(EmployeeObject similar, EmployeeSortType type) {
		ArrayList<EmployeeObject> items = new ArrayList<>();
		EmployeeObject item = null;
		ResultSet rs = this.e.getEmployees(similar, type);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new EmployeeObject();
					item.setEmployee_id(rs.getInt("employee_id"));
					item.setEmployee_fullname(Utilities.decode(rs.getString("employee_fullname")));
					item.setEmployee_phone(rs.getString("employee_phone"));
					item.setEmployee_address(Utilities.decode(rs.getString("employee_address")));
					item.setEmployee_birthday(rs.getString("employee_birthday"));
					item.setEmployee_email(rs.getString("employee_email"));
					item.setEmployee_salary(rs.getDouble("employee_salary"));
					item.setAuthor_id(rs.getInt("employee_author_id"));
					item.setEmployee_created_date(rs.getString("employee_created_date"));
					item.setEmployee_modified_date(rs.getString("employee_modified_date"));
					item.setEmployee_debt(rs.getDouble("employee_debt"));
					item.setEmployee_position(Utilities.decode(rs.getString("employee_position")));
					item.setEmployee_notes(Utilities.decode(rs.getString("employee_notes")));
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
