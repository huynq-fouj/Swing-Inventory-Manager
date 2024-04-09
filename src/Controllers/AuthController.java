package Controllers;

import Components.Dialog;
import Models.KhoDb;
import Models.TaiKhoan.TaiKhoan;
import Views.Auth.SignInView;
import Views.Auth.SignUpView;
import Views.Pages.HomePageView;

public class AuthController {

	public void signInHandler(SignInView siView) {
		String username = siView.getTfUsername().getText();
		char[] ps = siView.getPfPassword().getPassword();
		String password = new String(ps);
		KhoDb db = new KhoDb();
		boolean check = db.getLiTaiKhoan().checkSignIn(username, password);
		if (check) {
			new HomePageView();
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

				KhoDb db = new KhoDb();
				TaiKhoan item = new TaiKhoan();
				item.setMatKhau(pass2);
				item.setTenDangNhap(userName);
				if (db.getLiTaiKhoan().addTaikhoan(item)) {
					HomePageView view = new HomePageView();
					Dialog.success(view, "Đăng ký thành công!");
					suView.dispose();
				} else {
					Dialog.error(suView, "Tên đăng nhập đã tồn tại!");
				}
			} else {
				Dialog.error(suView, "Mật khẩu không hợp lệ!");
			}
		} else {
			Dialog.error(suView, "Tên đăng nhập không hợp lệ!");
		}
	}

}
