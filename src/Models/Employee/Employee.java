package Models.Employee;

import java.sql.ResultSet;

import Databases.ShareControl;
import Models.Objects.EmployeeObject;

public interface Employee extends ShareControl {
	
	public boolean addEmployee(EmployeeObject item);
	public boolean editEmployee(EmployeeObject item);
	public boolean delEmployee(EmployeeObject item);
	
	public ResultSet getEmployee(int id);
	public ResultSet getEmployees(EmployeeObject similar);	
	public ResultSet countEmployee(EmployeeObject similar);
}
