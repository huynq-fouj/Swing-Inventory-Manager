package Views.Pages;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Components.Dialog;
import Components.SideBar;
import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Databases.ConnectionPool;
import Models.Objects.UserObject;
import Models.User.UserControl;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;

public class HomePage extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fullname;
	private JTextField email;
	private JTextField phone;
	
	public HomePage() {
		PageState.page = "home";
		this.initUI();
	}
	
	public void initUI() {
		this.setTitle("Trang chủ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 920, 600);
		this.contentPane = this.createContentPane();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel createContentPane() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(this.createSidebar());
		panel.add(new VerticalBorder(2, 500));
		panel.add(this.mainView());
		return panel;
	}
	
	public JPanel mainView() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		UserObject user = AuthContext.getUser();
		JLabel label = this.createLabel("Người dùng: " + user.getUser_name());
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBorder(new EmptyBorder(0, 0, 10, 0));
		panel.add(label, gbc);
		gbc.gridy = 1;
		panel.add(this.FullnameField(user.getUser_fullname()), gbc);
		gbc.gridx = 1;
		panel.add(this.EmailField(user.getUser_email()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(this.PhoneField(user.getUser_phone()), gbc);
		return panel;
	}
	
	public JPanel FullnameField(String fullname) {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên đăng nhập:"), gbc);
		this.fullname = new JTextField();
		this.fullname.setBorder(new RoundedBorder(12));
		this.fullname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.fullname.setPreferredSize(new Dimension(150, 40));
		this.fullname.setText(fullname);
		gbc.gridy = 1;
		panel.add(this.fullname, gbc);
		return panel;
	}
	
	public JPanel EmailField(String email) {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Email:"), gbc);
		this.email = new JTextField();
		this.email.setBorder(new RoundedBorder(12));
		this.email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.email.setPreferredSize(new Dimension(150, 40));
		this.email.setText(email);
		gbc.gridy = 1;
		panel.add(this.email, gbc);
		return panel;
	}
	
	public JPanel PhoneField(String phone) {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Số điện thoại:"), gbc);
		this.phone = new JTextField();
		this.phone.setBorder(new RoundedBorder(12));
		this.phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.phone.setPreferredSize(new Dimension(150, 40));
		this.phone.setText(phone);
		gbc.gridy = 1;
		panel.add(this.phone, gbc);
		return panel;
	}
	
	public JPanel createSidebar() {
		SideBar sidebar = new SideBar(this);
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		String path = ResourceUtil.loadStaticPath("images\\home.png");
		ImageIcon imageIcon = new ImageIcon(path);
		JLabel label = new JLabel(imageIcon);
		panel.add(label, gbc);
		gbc.gridy = 1;
		panel.add(sidebar, gbc);
		return panel;
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}
	
	public JLabel createLabel(String content) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBorder(new EmptyBorder(0, 0, 5, 0));
		return label;
	}
	
	private void onSubmit() {
		String txtFullname = this.fullname.getText();
		String txtEmail = this.email.getText();
		String txtPhone = this.phone.getText();
		//Check valid
		UserObject userContext = AuthContext.getUser();
		UserObject user = new UserObject();
		user.setUser_id(userContext.getUser_id());
		user.setUser_fullname(txtFullname);
		user.setUser_email(txtEmail);
		user.setUser_phone(txtPhone);
		ConnectionPool cp = ConnectionContext.getCP();
		UserControl uc = new UserControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(uc.getCP());
		}
		boolean result = uc.editUser(user);
		if(result) {
			Dialog.success(this, "Cập nhật thông tin thành công!");
		} else {
			Dialog.success(this, "Cập nhật thông tin không thành công!");
		}
		uc.releaseConnection();
		
	}

}
