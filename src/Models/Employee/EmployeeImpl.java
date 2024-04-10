package Models.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.EmployeeObject;
import Models.Objects.UserObject;

public class EmployeeImpl extends BasicImpl implements Employee {

	public EmployeeImpl(ConnectionPool cp) {
		super(cp, "Employee");
	}

	@Override
	public ConnectionPool getCP() {
		return this.getCP();
	}

	@Override
	public void releaseConnection() {
		this.releaseConnection();
	}

	@Override
	public boolean addEmployee(EmployeeObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editEmployee(EmployeeObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delEmployee(EmployeeObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getEmployee(Short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getCategories(UserObject user) {
		// TODO Auto-generated method stub
		return null;
	}

}
