package Configuration;

import Databases.ConnectionPool;
import Databases.ConnectionPoolImpl;
import Models.Category.CategoryControl;
import Models.Employee.EmployeeControl;
import Models.Objects.CategoryObject;
import Models.Objects.EmployeeObject;
import Models.Objects.ProductObject;
import Models.Objects.UserObject;
import Models.Product.ProductControl;
import Models.User.UserControl;
import Shared.ConnectionContext;

public class CreateData {
	private ConnectionPool cp;
	private UserControl uc;
	private CategoryControl cc;
	private ProductControl pc;
	private EmployeeControl ec;
	
	public CreateData() {
		this.cp = new ConnectionPoolImpl();
		ConnectionContext.setCP(cp);
	}
	
	private String randomDate() {
		return null;
	}
	
	private String randomPosition() {
		return null;
	}
	
	private String randomLastName() {
		String[] lastNames = {
				"Hoài Thanh", "Thanh Hoài", "Thị Thanh Hoài", "Quang Huy", "Khánh Linh", "Thùy Dung", "Thùy Dương", "Thị Ly",
				"Anh", "Minh Anh", "Kim Anh", "Phương Anh", "Vân Anh", "Tuấn Anh", "Văn Huy", "Việt Huy", "Quốc Huy",
				"Quang Phúc", "Thái Phúc", "Hữu Phúc", "Đức Dũng", "Văn Dũng", "Việt Hùng", "Văn Hùng",
				"Thái Hưng", "Ngọc Hưng", "Mai Hương", "Thu Hương", "Gia Ân", "Bảo Ân", "Minh Hiếu", "Trung Hiếu",
				"Ngọc Quang", "Văn Quang", "Văn Đại", "Quang Đạo", "Minh Đăng", "Việt Hoàng", "Văn Hoàng",
				"Quang Minh", "Thành Thuận", "Đức Thông", "Thị Thu Hường", "Thanh Quỳnh", "Như Quỳnh", "Ngọc Thúy"
		};
		return lastNames[(int) (Math.random() * lastNames.length)];
	}
	
	private String randomFirstName() {
		String[] firstNames = {
				"Nguyễn", "Trần", "Hoàng", "Lương", "Vũ", "Cao", "Khổng", "Lại", "Mai", "Ma", "Triệu",
				"Đỗ", "Võ", "Phạm", "Trịnh", "Trương"
		};
		return firstNames[(int) (Math.random() * firstNames.length)];
	}
	
	private String randomAddress() {
		String[] address = {
				"Vĩnh Phúc", "Hà Nội", "Tp. Hồ Chí Minh", "Thái Nguyên", "Phú Thọ", "Nghệ An", "Hải Phòng",
				"Quảng Ninh", "Hà Nam", "Bình Thuận", "Bình Dương", "Bắc Ninh", "Ninh Thuận", "Lạng Sơn"
		};
		return address[(int) (Math.random() * address.length)];
	}
	
	private String randomUsername() {
		String[] username = {
				"sa", "ku", "ra", "ma", "ka", "ta", "mu", "yu", "an", "to", "ni", "ri", "rj",
				"yo", "ny", "li", "hi", "hu", "ko", "ne", "no", "le", "la", "ek", "ya", "to"
		};
		return username[(int) (Math.random() * username.length)] + username[(int) (Math.random() * username.length)] + username[(int) (Math.random() * username.length)] + (int)(Math.random()*1000);
	}
	
	private String randomPhoneNumber() {
		return "0" + ((int) (Math.random() * 9) + 1) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10));
	}
	
	private String randomEmail() {
		return this.randomUsername() + "@gmail.com";
	}
	
	private String randomFullname() {
		return this.randomFirstName() + " " + this.randomLastName();
	}
	
	private UserObject createUser() {
		String fullname = this.randomFullname();
		String address = this.randomAddress();
		String username = this.randomUsername();
		String email = this.randomUsername() + "@gmail.com";
		String phone = this.randomPhoneNumber();
		String password = "123456";
		UserObject item = new UserObject();
		item.setUser_address(address);
		item.setUser_email(email);
		item.setUser_password(password);
		item.setUser_logined((int)(Math.random() * 100));
		item.setUser_role(0);
		item.setUser_fullname(fullname);
		item.setUser_phone(phone);
		item.setUser_name(username);
		return item;
	}
	
	public void createUsers(int n) {
		this.uc = new UserControl(this.cp);
		for(int i = 0; i < n; i++) {
			UserObject item = this.createUser();
			String result = this.uc.addUser(item) ? "Success" : "Error";
			System.out.println(result + " - User: " + item.getUser_name());
		}
		uc.releaseConnection();
	}
	
	private EmployeeObject createEmployee(int author_id) {
		EmployeeObject item = new EmployeeObject();
		item.setEmployee_address(this.randomAddress());
		item.setEmployee_email(this.randomEmail());
		item.setEmployee_fullname(this.randomFullname());
		item.setEmployee_phone(this.randomPhoneNumber());
		item.setEmployee_birthday(this.randomDate());
		item.setEmployee_salary(0);
		item.setEmployee_debt(0);
		item.setEmployee_position(this.randomPosition());
		item.setAuthor_id(author_id);
		return item;
	}
	
	public void createEmployees(int n, int author_id) {
		this.ec = new EmployeeControl(this.cp);
		for(int i = 0; i < n; i++) {
			EmployeeObject item = this.createEmployee(author_id);
			String result = this.ec.addEmployee(item) ? "Success" : "Error";
			System.out.println(result + " - User: " + item.getEmployee_fullname());
		}
		this.ec.releaseConnection();
	}
	
	private ProductObject createProduct(int author_id) {
		ProductObject item = new ProductObject();
		item.setAuthor_id(author_id);
		return item;
	}
	
	public void createProducts(int n, int author_id) {
		this.pc = new ProductControl(this.cp);
		for(int i = 0; i < n; i++) {
			ProductObject item = this.createProduct(author_id);
			String result = this.pc.addProduct(item) ? "Success" : "Error";
			System.out.println(result + " - User: " + item.getProduct_name());
		}
		this.pc.releaseConnection();
	}
	
	private CategoryObject createCategory(int author_id) {
		CategoryObject item = new CategoryObject();
		item.setAuthor_id(author_id);
		return item;
	}
	
	public void createCategories(int n, int author_id) {
		this.cc = new CategoryControl(this.cp);
		for(int i = 0; i < n; i++) {
			CategoryObject item = this.createCategory(author_id);
			String result = this.cc.addCategory(item) ? "Success" : "Error";
			System.out.println(result + " - User: " + item.getCategory_name());
		}
		this.cc.releaseConnection();
	}
	
}
