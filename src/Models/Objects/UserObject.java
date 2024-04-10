package Models.Objects;

public class UserObject {

	private int user_id;
	private String user_name;
	private String user_password;
	private String user_fullname;
	private String user_email;
	private String user_phone;
	private int user_logined;
	private int user_role;

	public int getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public int getUser_role() {
		return user_role;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_logined() {
		return user_logined;
	}

	public void setUser_logined(int user_logined) {
		this.user_logined = user_logined;
	}

}
