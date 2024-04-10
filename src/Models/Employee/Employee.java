package Models.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ShareControl;
import Models.Objects.EmployeeObject;
import Models.Objects.UserObject;

public interface Employee extends ShareControl {
	
	public boolean addEmployee(EmployeeObject item);
	public boolean editEmployee(EmployeeObject item);
	public boolean delEmployee(EmployeeObject item);
	
	public ResultSet getEmployee(Short id);
	public ArrayList<ResultSet> getCategories(UserObject user);	
	
}
