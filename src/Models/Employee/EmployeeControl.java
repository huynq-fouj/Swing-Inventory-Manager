package Models.Employee;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Databases.ConnectionPool;
import Models.Objects.EmployeeObject;

public class EmployeeControl {

private EmployeeModel em;
	
	public EmployeeControl(ConnectionPool cp) {
		this.em = new EmployeeModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.em.getCP();
	}
	
	public void releaseConnection() {
		this.em.releaseConnection();
	}
	
	public boolean addEmployee(EmployeeObject item) {
		return this.em.addEmployee(item);
	}
	
	public boolean editEmployee(EmployeeObject item) {
		return this.em.editEmployee(item);
	}
	
	public boolean delEmployee(EmployeeObject item) {
		return this.em.delEmployee(item);
	}
	
	public EmployeeObject getEmployee(int id) {
		return this.em.getEmployee(id);
	}
	
	public ArrayList<EmployeeObject> getEmployees(EmployeeObject similar, EmployeeSortType type) {
		return this.em.getEmployees(similar, type);
	}
	
	public int countEmployee(EmployeeObject similar) {
		return this.em.countEmployee(similar);
	}
	
	public DefaultTableModel getTableModel(EmployeeObject similar, EmployeeSortType type) {
		String columnHeaders[] = {"ID", "Họ tên", "Lương", "Email", "Số điện thoại", "Địa chỉ"};
		ArrayList<EmployeeObject> items = this.getEmployees(similar, type);
		Object data[][] = new Object[items.size()][columnHeaders.length];
		items.forEach(item -> {
			int i = items.indexOf(item);
			data[i][0] = item.getEmployee_id();
			data[i][1] = item.getEmployee_fullname();
			data[i][2] = item.getEmployee_formatSalary();
			data[i][3] = item.getEmployee_email();
			data[i][4] = item.getEmployee_phone();
			data[i][5] = item.getEmployee_address();
		});
		return new DefaultTableModel(
				data,
				columnHeaders
		);
	}
	
}
