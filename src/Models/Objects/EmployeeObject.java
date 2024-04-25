package Models.Objects;

import Utilities.Utilities;

public class EmployeeObject {

	private int employee_id;
	private String employee_fullname;
	private String employee_phone;
	private String employee_address;
	private String employee_birthday;
	private String employee_email;
	private double employee_salary;
	private int author_id;
	private String employee_created_date;
	private String employee_modified_date;
	private double employee_debt;
	private String employee_position;
	private String employee_notes;

	public int getEmployee_id() {
		return employee_id;
	}

	public String getEmployee_fullname() {
		return employee_fullname;
	}

	public String getEmployee_phone() {
		return employee_phone;
	}

	public String getEmployee_address() {
		return employee_address;
	}

	public String getEmployee_birthday() {
		return employee_birthday;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public double getEmployee_salary() {
		return employee_salary;
	}
	
	public String getEmployee_formatSalary() {
		return Utilities.doubleFormat(employee_salary);
	}
	
	public String getEmployee_formatSalary(String format) {
		return Utilities.doubleFormat(employee_salary, format);
	}

	public int getAuthor_id() {
		return author_id;
	}

	public String getEmployee_created_date() {
		return employee_created_date;
	}

	public String getEmployee_modified_date() {
		return employee_modified_date;
	}

	public double getEmployee_debt() {
		return employee_debt;
	}
	
	public String getEmployee_formatDebt() {
		return Utilities.doubleFormat(employee_debt);
	}
	
	public String getEmployee_formatDebt(String format) {
		return Utilities.doubleFormat(employee_debt, format);
	}

	public String getEmployee_position() {
		return employee_position;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public void setEmployee_fullname(String employee_fullname) {
		this.employee_fullname = employee_fullname;
	}

	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}

	public void setEmployee_address(String employee_address) {
		this.employee_address = employee_address;
	}

	public void setEmployee_birthday(String employee_birthday) {
		this.employee_birthday = employee_birthday;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public void setEmployee_salary(double employee_salary) {
		this.employee_salary = employee_salary;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public void setEmployee_created_date(String employee_created_date) {
		this.employee_created_date = employee_created_date;
	}

	public void setEmployee_modified_date(String employee_modified_date) {
		this.employee_modified_date = employee_modified_date;
	}

	public void setEmployee_debt(double employee_debt) {
		this.employee_debt = employee_debt;
	}

	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}

	public String getEmployee_notes() {
		return employee_notes;
	}

	public void setEmployee_notes(String employee_notes) {
		this.employee_notes = employee_notes;
	}

}
