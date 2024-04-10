package Views;

import java.awt.EventQueue;

import Databases.DbEngine;
import Models.TaiKhoan.QuanlyTaiKhoanModel;
import Models.TaiKhoan.TaiKhoan;
import Views.Auth.AuthView;
import Views.Pages.HomePage;
import Views.Pages.HomePageView;

public class MainView {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AuthView frame = new AuthView();
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
