package Components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Components.Buttons.SideBarButton;
import Shared.PageState;
import Themes.Colors;
import Views.Auth.AuthView;
import Views.Pages.HomePage;
import Views.Pages.UserPage;

public class SideBar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame currentFrame;
	
	public SideBar(JFrame currentFrame) {
		this.currentFrame = currentFrame;
		this.initUI();
	}
	
	public void initUI() {
		this.setForeground(Colors.Black);
		this.setBackground(Colors.White);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		this.add(this.BtnHome(), gbc);
		gbc.gridy = 1;
		this.add(this.BtnProduct(), gbc);
		gbc.gridy = 2;
		this.add(this.BtnCategory(), gbc);
		gbc.gridy = 3;
		this.add(this.BtnEmployee(), gbc);
		gbc.gridy = 4;
		this.add(this.BtnUser(), gbc);
		gbc.gridy = 5;
		this.add(this.BtnLogout(), gbc);
	}
	
	public JButton BtnHome() {
		JButton btn = this.createSideBarButton("Trang chủ");
		btn.addActionListener(e -> {
			if(PageState.page != "home") {
				HomePage view = new HomePage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnProduct() {
		JButton btn = this.createSideBarButton("Sản phẩm");
		btn.addActionListener(e -> {
			if(PageState.page != "product") {
				PageState.page = "product";
				
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnCategory() {
		JButton btn = this.createSideBarButton("Danh mục");
		btn.addActionListener(e -> {
			if(PageState.page != "category") {
				PageState.page = "category";
				
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnEmployee() {
		JButton btn = this.createSideBarButton("Nhân viên");
		btn.addActionListener(e -> {
			if(PageState.page != "employee") {
				PageState.page = "employee";
				
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnUser() {
		JButton btn = this.createSideBarButton("Người dùng");
		btn.addActionListener(e -> {
			if(PageState.page != "user") {
				UserPage view = new UserPage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnLogout() {
		JButton btn = this.createSideBarButton("Đăng xuất");
		btn.addActionListener(e -> {
			AuthView view = new AuthView();
			view.setVisible(true);
			this.currentFrame.dispose();
		});
		return btn;
	}
	
	private JButton createSideBarButton(String label) {
		JButton btn = new SideBarButton(label);
		return btn;
	}
	
}
