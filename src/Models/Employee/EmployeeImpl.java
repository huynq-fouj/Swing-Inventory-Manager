package Models.Employee;

import java.sql.ResultSet;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.EmployeeObject;
import Utilities.Utilities;

public class EmployeeImpl extends BasicImpl implements Employee {

	public EmployeeImpl(ConnectionPool cp) {
		super(cp, "Employee");
	}

	@Override
	public ConnectionPool getCP() {
		return super.getCP();
	}

	@Override
	public void releaseConnection() {
		super.releaseConnection();
	}

	@Override
	public boolean addEmployee(EmployeeObject item) {
		return false;
	}

	@Override
	public boolean editEmployee(EmployeeObject item) {
		return false;
	}

	@Override
	public boolean delEmployee(EmployeeObject item) {
		return false;
	}

	@Override
	public ResultSet getEmployee(int id) {
		String sql = "SELECT * FROM tblemployee WHERE employee_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getEmployees(EmployeeObject similar) {
		String sql = "SELECT * FROM tblemployee ";
		sql += this.createConditions(similar);
		sql += " ORDER BY employee_id DESC";
		return this.gets(sql);
	}
	
	@Override
	public ResultSet countEmployee(EmployeeObject similar) {
		String sql = "SELECT COUNT(*) AS total FROM tblemployee ";
		sql += this.createConditions(similar);
		return this.gets(sql);
	}

	private String createConditions(EmployeeObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar != null) {
			if(similar.getAuthor_id() > 0) {
				int id = similar.getAuthor_id();
				conds.append(" employee_author_id=").append(id);
			}
			
			String name = similar.getEmployee_fullname();
			if(name != null && !name.equalsIgnoreCase("")) {
				if(!conds.toString().equalsIgnoreCase("")) {
					conds.append(" AND ");
				}
				String key = Utilities.encode(name);
				conds.append(" ((employee_address LIKE '%"+key+"%') OR ");
				conds.append(" (employee_fullname LIKE '%"+key+"%') OR ");
				conds.append(" (employee_email LIKE '%"+key+"%')) ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		return conds.toString();
	}

}
