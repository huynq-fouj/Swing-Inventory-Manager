package Controllers;

import Components.Dialog;
import Databases.ConnectionPool;
import Databases.ConnectionPoolImpl;
import Models.Objects.UserObject;
import Models.User.UserControl;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Views.Auth.SignInView;
import Views.Auth.SignUpView;
import Views.Pages.HomePage;
import Views.Pages.HomePageView;

public class AuthController {

	public void signInHandler(SignInView siView) {
		String username = siView.getTfUsername().getText();
		char[] ps = siView.getPfPassword().getPassword();
		String password = new String(ps);
		ConnectionPool cp = ConnectionContext.getCP();
		UserControl uc = new UserControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(uc.getCP());
		}
		UserObject user = uc.getUserObject(username, password);
		uc.releaseConnection();
		if (user != null) {
			AuthContext.setUser(user);
			HomePage home = new HomePage();
			home.setVisible(true);
			siView.dispose();
		} else {
			Dialog.error(siView, "Sai tên đăng nhập hoặc mật khẩu!");
		}
	}

	public void signUpHandler(SignUpView suView) {
		String userName = suView.getTfUsername().getText().trim();
		String pass1 = new String(suView.getPwdPassword().getPassword()).trim();
		String pass2 = new String(suView.getPwdConfirm().getPassword()).trim();
		if (userName != null && !userName.equalsIgnoreCase("")) {
			if (pass1 != null && !pass1.equalsIgnoreCase("") && pass1 != null && !pass2.equalsIgnoreCase("")
					&& pass1.equals(pass2)) {
				ConnectionPool cp = ConnectionContext.getCP();
				UserControl uc = new UserControl(cp);
				if(cp == null) {
					ConnectionContext.setCP(uc.getCP());
				}
				UserObject user = new UserObject();
				user.setUser_name(userName);
				user.setUser_password(pass1);
				boolean isExists = uc.existsByName(userName);
				if (!isExists) {
					boolean check = uc.addUser(user);
					if(check) {
						user = uc.getUserObject(userName, pass2);
						if(user != null) {
							AuthContext.setUser(user);
							HomePage home = new HomePage();
							home.setVisible(true);
							suView.dispose();
							Dialog.success(home, "Đăng ký thành công!");
						} else {
							SignInView view = new SignInView();
							view.setVisible(true);
							suView.dispose();
							Dialog.success(view, "Đăng ký thành công!");
						}
					} else {
						Dialog.error(suView, "Đăng ký không thành công!");
					}
				} else {
					Dialog.error(suView, "Tên đăng nhập đã tồn tại!");
				}
				uc.releaseConnection();
			} else {
				Dialog.error(suView, "Mật khẩu không hợp lệ!");
			}
		} else {
			Dialog.error(suView, "Tên đăng nhập không hợp lệ!");
		}
	}

}
