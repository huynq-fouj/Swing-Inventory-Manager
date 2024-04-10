package Models.Objects;

public class Employee {

	private int employee_id;
	private String employee_fullname;
	private String employee_phone;
	private String employee_address;
	private String employee_birthday;
	private String employee_email;
	private double employee_salary;
	private int auhtor_id;

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

	public int getAuhtor_id() {
		return auhtor_id;
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

	public void setAuhtor_id(int auhtor_id) {
		this.auhtor_id = auhtor_id;
	}

}
