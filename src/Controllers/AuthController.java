package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Models.KhoDb;
import Models.TaiKhoan.TaiKhoan;
import Views.Auth.SignInView;
import Views.Auth.SignUpView;
import Views.Pages.HomePageView;

public class AuthController implements ActionListener {
	private Object view;

	public AuthController(Object temp) {
		this.view = temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String url = e.getActionCommand();
		// Đăng nhập
		if (url.equals("Sign In"))
			this.signInHandler((SignInView) this.view);
		// Đăng ký
		if (url.equals("Sign up"))
			this.signUpHandler((SignUpView) this.view);
	}

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
			JOptionPane.showConfirmDialog(siView, "Tên đăng nhập hoặc mật khẩu không đúng!", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showConfirmDialog(view, "Đăng ký tài khoản thành công!", "Thông báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					suView.dispose();
				} else {
					JOptionPane.showConfirmDialog(suView, "Tên đăng nhập đã tồn tại!", "Cảnh báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showConfirmDialog(suView, "Mật khẩu không hợp lệ!", "Cảnh báo", JOptionPane.CLOSED_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showConfirmDialog(suView, "Tên đăng nhập không hợp lệ!", "Cảnh báo", JOptionPane.CLOSED_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
