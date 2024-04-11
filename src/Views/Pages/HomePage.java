package Views.Pages;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Components.Dialog;
import Components.SideBar;
import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
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
	private JTextField address;
	
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
		JLabel loginname = this.createSubtitle("Người dùng: " + user.getUser_name());
		panel.add(loginname, gbc);
		JLabel logincount = this.createSubtitle("Số lần đăng nhập: " + user.getUser_logined());
		gbc.gridx = 1;
		panel.add(logincount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(this.FullnameField(user.getUser_fullname()), gbc);
		gbc.gridx = 1;
		panel.add(this.EmailField(user.getUser_email()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(this.PhoneField(user.getUser_phone()), gbc);
		gbc.gridx = 1;
		panel.add(this.AddressField(""), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(this.BtnField(), gbc);
		return panel;
	}
	
	public JLabel createSubtitle(String content) {
		JLabel label = this.createLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBorder(new EmptyBorder(0, 50, 30, 0));
		return label;
	}
	
	public JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(0, 50, 20, 0));
		return panel;
	}
	
	public JTextField createTextField(String value) {
		JTextField field = new JTextField();
		field.setBorder(new RoundedBorder(13));
		field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		field.setPreferredSize(new Dimension(275, 38));
		field.setText(value);
		return field;
	}
	
	public JPanel FullnameField(String fullname) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên người dùng:"), gbc);
		this.fullname = this.createTextField(fullname);
		gbc.gridy = 1;
		panel.add(this.fullname, gbc);
		return panel;
	}
	
	public JPanel EmailField(String email) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Email:"), gbc);
		this.email = this.createTextField(email);
		gbc.gridy = 1;
		panel.add(this.email, gbc);
		return panel;
	}
	
	public JPanel PhoneField(String phone) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Số điện thoại:"), gbc);
		this.phone = this.createTextField(phone);
		gbc.gridy = 1;
		panel.add(this.phone, gbc);
		return panel;
	}
	
	public JPanel AddressField(String address) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Địa chỉ:"), gbc);
		this.address = this.createTextField(address);
		gbc.gridy = 1;
		panel.add(this.address, gbc);
		return panel;
	}
	
	public JPanel BtnField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton btnUpd = new Button("Cập nhật", ButtonType.SUCCESS);
		btnUpd.addActionListener(e -> this.onSubmit());
		panel.add(btnUpd);
		//panel.add(this.createPanel());
		JButton btnCancel = new Button("Hủy", ButtonType.SECONDARY);
		btnCancel.addActionListener(e -> {
			this.handleCancel();
		});
		panel.add(btnCancel);
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
		String txtFullname = this.fullname.getText().trim();
		String txtEmail = this.email.getText().trim();
		String txtPhone = this.phone.getText().trim();
		String txtAddress = this.address.getText().trim();
		UserObject user = AuthContext.getUser();
		if(this.isChange(user)) {
			if(txtFullname.equals("")) {
				Dialog.error(this, "Họ và tên không được để trống!");
			} else {
				if(txtEmail.equals("")) {
					Dialog.error(this, "Email không được để trống!");
				} else {
					if(txtPhone.equals("")) {
						Dialog.error(this, "Số điện thoại không được để trống!");
					} else {
						user.setUser_fullname(txtFullname);
						user.setUser_email(txtEmail);
						user.setUser_phone(txtPhone);
						//user.setUser_address(txtAddress);
						ConnectionPool cp = ConnectionContext.getCP();
						UserControl uc = new UserControl(cp);
						if(cp == null) {
							ConnectionContext.setCP(uc.getCP());
						}
						boolean result = uc.editUser(user);
						if(result) {
							AuthContext.setUser(user);
							Dialog.success(this, "Cập nhật thông tin thành công!");
						} else {
							Dialog.success(this, "Cập nhật thông tin không thành công!");
						}
						uc.releaseConnection();
					}
				}
			}
		}
	}
	
	public boolean isChange(UserObject user) {
		if(!this.fullname.getText().trim().equals(user.getUser_fullname())) return true;
		if(!this.email.getText().trim().equals(user.getUser_email())) return true;
		if(!this.phone.getText().trim().equals(user.getUser_phone())) return true;
		//if(!this.address.getText().trim().equals(user.getUser_address())) return true;
		return false;
	}
	
	public void handleCancel() {
		UserObject u = AuthContext.getUser();
		this.fullname.setText(u.getUser_fullname());
		this.email.setText(u.getUser_email());
		this.phone.setText(u.getUser_phone());
		this.address.setText("");
	}

}
