package Models.Employee;

import java.util.ArrayList;

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
	
	public ArrayList<EmployeeObject> getEmployees(EmployeeObject similar) {
		return this.em.getEmployees(similar);
	}
	
	public int countEmployee(EmployeeObject similar) {
		return this.em.countEmployee(similar);
	}
	
}
