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
	private String user_created_at;
	private String user_modified_at;
	private String user_notes;
	private String user_address;

	public UserObject() {
		this(0, "", "", "", "", "", 0, 0, "", "", "", "");
	}
	
	public UserObject(int user_id, String user_name, String user_password, String user_fullname, String user_email,
			String user_phone, int user_logined, int user_role, String user_created_at, String user_modified_at,
			String user_notes, String user_address) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_fullname = user_fullname;
		this.user_email = user_email;
		this.user_phone = user_phone;
		this.user_logined = user_logined;
		this.user_role = user_role;
		this.user_created_at = user_created_at;
		this.user_modified_at = user_modified_at;
		this.user_notes = user_notes;
		this.user_address = user_address;
	}

	public UserObject(UserObject user) {
		this(
				user.getUser_id(),
				user.getUser_name(),
				user.getUser_password(),
				user.getUser_fullname(),
				user.getUser_email(),
				user.getUser_phone(),
				user.getUser_logined(),
				user.getUser_role(),
				user.getUser_created_at(),
				user.getUser_modified_at(),
				user.getUser_notes(),
				user.getUser_address()
			);
	}
	
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

	public String getUser_created_at() {
		return user_created_at;
	}

	public void setUser_created_at(String user_created_at) {
		this.user_created_at = user_created_at;
	}

	public String getUser_modified_at() {
		return user_modified_at;
	}

	public void setUser_modified_at(String user_modified_at) {
		this.user_modified_at = user_modified_at;
	}

	public String getUser_notes() {
		return user_notes;
	}

	public void setUser_notes(String user_notes) {
		this.user_notes = user_notes;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

}
