package Components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Components.Buttons.SideBarButton;
import Shared.AuthContext;
import Shared.PageState;
import Themes.Colors;
import Themes.HoverEvent;
import Utilities.ResourceUtil;
import Views.Auth.AuthView;
import Views.Pages.HomePage;
import Views.Pages.ProfilePage;
import Views.Pages.Category.CategoryPage;
import Views.Pages.Employee.EmployeePage;
import Views.Pages.Product.ProductPage;
import Views.Pages.User.UserPage;

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
		this.add(this.createEmptyPanel(), gbc);
		gbc.gridy = 2;
		this.add(this.BtnProduct(), gbc);
		gbc.gridy = 3;
		this.add(this.createEmptyPanel(), gbc);
		gbc.gridy = 4;
		this.add(this.BtnCategory(), gbc);
		gbc.gridy = 5;
		this.add(this.createEmptyPanel(), gbc);
		gbc.gridy = 6;
		this.add(this.BtnEmployee(), gbc);
		gbc.gridy = 7;
		if(AuthContext.getUser().getUser_role() >= 5) {
			this.add(this.createEmptyPanel(), gbc);
			gbc.gridy = 8;
			this.add(this.BtnUser(), gbc);
			gbc.gridy = 9;
		}
		this.add(this.createEmptyPanel(), gbc);
		gbc.gridy = 10;
		this.add(this.BtnProfile(), gbc);
		gbc.gridy = 11;
		this.add(this.BtnLogout(), gbc);
	}
	
	public JPanel createEmptyPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Colors.White);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		return panel;
	}
	
	public JButton BtnHome() {
		JButton btn = this.createSideBarButton("Trang chủ");
		String path = ResourceUtil.loadStaticPath("images/icons/home.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "home") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
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
		String path = ResourceUtil.loadStaticPath("images/icons/product.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "product") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
		btn.addActionListener(e -> {
			if(PageState.page != "product") {
				ProductPage view = new ProductPage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnCategory() {
		JButton btn = this.createSideBarButton("Danh mục");
		String path = ResourceUtil.loadStaticPath("images/icons/category.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "category") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
		btn.addActionListener(e -> {
			if(PageState.page != "category") {
				CategoryPage view = new CategoryPage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnEmployee() {
		JButton btn = this.createSideBarButton("Nhân viên");
		String path = ResourceUtil.loadStaticPath("images/icons/employee.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "employee") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
		btn.addActionListener(e -> {
			if(PageState.page != "employee") {
				EmployeePage view = new EmployeePage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnUser() {
		JButton btn = this.createSideBarButton("Người dùng");
		String path = ResourceUtil.loadStaticPath("images/icons/users.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "user") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
		btn.addActionListener(e -> {
			if(PageState.page != "user") {
				UserPage view = new UserPage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnProfile() {
		JButton btn = this.createSideBarButton("Tài khoản");
		String path = ResourceUtil.loadStaticPath("images/icons/profile.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		if(PageState.page == "profile") {
			btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.WhiteHover, Colors.WhiteHover));
			btn.setBackground(Colors.WhiteHover);
		}
		btn.addActionListener(e -> {
			if(PageState.page != "profile") {
				ProfilePage view = new ProfilePage();
				view.setVisible(true);
				this.currentFrame.dispose();
			}
		});
		return btn;
	}
	
	public JButton BtnLogout() {
		JButton btn = this.createSideBarButton("Đăng xuất");
		String path = ResourceUtil.loadStaticPath("images/icons/logout.png");
		ImageIcon icon = new ImageIcon(path);
		btn.setIcon(icon);
		btn.addActionListener(e -> {
			AuthView view = new AuthView();
			view.setVisible(true);
			this.currentFrame.dispose();
		});
		return btn;
	}
	
	private JButton createSideBarButton(String label) {
		SideBarButton btn = new SideBarButton(label);
		btn.setSize(170, 40);
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		return btn;
	}
	
}
