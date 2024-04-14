package Views;

import java.awt.EventQueue;

import Views.Auth.AuthView;

public class MainView {

	public static void main(String[] args) {
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
