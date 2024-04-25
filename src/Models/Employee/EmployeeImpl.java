package Models.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.EmployeeObject;
import Utilities.Utilities;
import Utilities.Utilities_date;

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
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblemployee(")
		.append("employee_fullname, employee_phone, employee_address,")
		.append("employee_birthday, employee_email, employee_salary,")
		.append("employee_author_id, employee_created_date, employee_modified_date,")
		.append("employee_debt, employee_position, employee_notes")
		.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?,?);");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getEmployee_fullname()));
			pre.setString(2, item.getEmployee_phone());
			pre.setString(3, Utilities.encode(item.getEmployee_address()));
			pre.setString(4, item.getEmployee_birthday());
			pre.setString(5, item.getEmployee_email());
			pre.setDouble(6, item.getEmployee_salary());
			pre.setInt(7, item.getAuthor_id());
			pre.setString(8, Utilities_date.getDate());
			pre.setString(9, Utilities_date.getDate());
			pre.setDouble(10, item.getEmployee_debt());
			pre.setString(11, Utilities.encode(item.getEmployee_position()));
			pre.setString(12, Utilities.encode(item.getEmployee_notes()));
			return this.add(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editEmployee(EmployeeObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblemployee SET ")
		.append("employee_fullname=?, employee_phone=?, employee_address=?,")
		.append("employee_birthday=?, employee_email=?, employee_salary=?,")
		.append("employee_modified_date=?,")
		.append("employee_debt=?, employee_position=?, employee_notes=? ")
		.append("WHERE employee_id=?;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getEmployee_fullname()));
			pre.setString(2, item.getEmployee_phone());
			pre.setString(3, Utilities.encode(item.getEmployee_address()));
			pre.setString(4, item.getEmployee_birthday());
			pre.setString(5, item.getEmployee_email());
			pre.setDouble(6, item.getEmployee_salary());
			pre.setString(7, Utilities_date.getDate());
			pre.setDouble(8, item.getEmployee_debt());
			pre.setString(9, Utilities.encode(item.getEmployee_position()));
			pre.setString(10, Utilities.encode(item.getEmployee_notes()));
			pre.setInt(11, item.getEmployee_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delEmployee(EmployeeObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tblemployee WHERE employee_id=?;");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getEmployee_id());
			return this.del(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getEmployee(int id) {
		String sql = "SELECT * FROM tblemployee WHERE employee_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getEmployees(EmployeeObject similar, EmployeeSortType type) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblemployee ")
		.append(this.createConditions(similar));
		switch(type) {
		case ID_ASC:
			sql.append("ORDER BY employee_id ASC");
			break;
		case FULLNAME_ASC:
			sql.append("ORDER BY employee_fullname ASC");
			break;
		case EMAIL_ASC:
			sql.append("ORDER BY employee_email ASC");
			break;
		case POSITION_ASC:
			sql.append("ORDER BY employee_position ASC");
			break;
		case PHONE_ASC:
			sql.append("ORDER BY employee_phone ASC");
			break;
		case ADDRESS_ASC:
			sql.append("ORDER BY employee_address ASC");
			break;
		case FULLNAME_DESC:
			sql.append("ORDER BY employee_fullname DESC");
			break;
		case EMAIL_DESC:
			sql.append("ORDER BY employee_email DESC");
			break;
		case POSITION_DESC:
			sql.append("ORDER BY employee_position DESC");
			break;
		case PHONE_DESC:
			sql.append("ORDER BY employee_phone DESC");
			break;
		case ADDRESS_DESC:
			sql.append("ORDER BY employee_address DESC");
			break;
		case SALARY_DESC:
			sql.append("ORDER BY employee_salary DESC");
			break;
		case SALARY_ASC:
			sql.append("ORDER BY employee_salary ASC");
			break;
		default:
			sql.append("ORDER BY employee_id DESC");
		}
		sql.append(";");
		return this.gets(sql.toString());
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
		
		conds.append(" ");
		
		return conds.toString();
	}

}
