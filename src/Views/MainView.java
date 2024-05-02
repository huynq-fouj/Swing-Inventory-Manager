package Views;

import java.awt.EventQueue;

import Models.Objects.UserObject;
import Models.User.UserControl;
import Views.Auth.AuthView;

public class MainView {

	public static void main(String[] args) {

		//Create admin account
		UserObject admin = new UserObject();
		admin.setUser_email("admin@test.com");
		admin.setUser_password("admin");
		admin.setUser_name("admin");
		admin.setUser_fullname("Admin");
		admin.setUser_role(5);
		UserControl uc = new UserControl(null);
		if(uc.addUser(admin)) System.out.println("Create admin account:\nUsername: admin\nPassword: admin");
		else System.out.println("Admin account has been already.");
		uc.releaseConnection();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthView frame = new AuthView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
