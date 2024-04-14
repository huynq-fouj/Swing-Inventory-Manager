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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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

public class ProfilePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fullname;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	private JTextArea notes;

	public ProfilePage() {
		PageState.page = "profile";
		this.initUI();
	}

	private void initUI() {
		this.setTitle("Thông tin cá nhân");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 920, 600);
		this.contentPane = this.createContentPane();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private JPanel createContentPane() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(this.createSidebar());
		panel.add(new VerticalBorder(2, 500));
		panel.add(this.mainView());
		return panel;
	}

	private JPanel mainView() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		panel.add(this.createTitle(), gbc);
		UserObject user = AuthContext.getUser();
		JLabel loginname = this.createSubtitle("Tên đăng nhập: " + user.getUser_name());
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		panel.add(loginname, gbc);
		JLabel logincount = this.createSubtitle("Số lần đăng nhập: " + user.getUser_logined());
		gbc.gridx = 1;
		panel.add(logincount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(this.FullnameField(user.getUser_fullname()), gbc);
		gbc.gridx = 1;
		panel.add(this.EmailField(user.getUser_email()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(this.PhoneField(user.getUser_phone()), gbc);
		gbc.gridx = 1;
		panel.add(this.AddressField(user.getUser_address()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(this.NotesField(user.getUser_notes()), gbc);
		gbc.gridx = 1;
		panel.add(this.InfoField(user), gbc);
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridy = 5;
		panel.add(this.BtnField(), gbc);
		gbc.gridwidth = 1;
		return panel;
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = this.createLabel("Thông tin cá nhân");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 20, 0));
		panel.add(label);
		return panel;
	}

	private JLabel createSubtitle(String content) {
		JLabel label = this.createLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBorder(new EmptyBorder(0, 50, 30, 0));
		return label;
	}
	
	private JPanel InfoField(UserObject user) {
		JPanel panel = this.createPanelField();
		JLabel created = new JLabel("Ngày tạo: " + user.getUser_created_at());
		created.setFont(new Font("Tahoma", Font.PLAIN, 14));
		created.setBorder(new EmptyBorder(0, 0, 10, 0));
		JLabel modified = new JLabel("Sửa lần cuối: " + user.getUser_modified_at());
		modified.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modified.setBorder(new EmptyBorder(0, 0, 10, 0));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.gridy = 0;
		panel.add(created, gbc);
		gbc.gridy = 1;
		panel.add(modified, gbc);
		return panel;
	}

	private JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(0, 50, 20, 0));
		return panel;
	}

	private JTextField createTextField(String value) {
		JTextField field = new JTextField();
		field.setBorder(new RoundedBorder(13));
		field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		field.setPreferredSize(new Dimension(275, 38));
		field.setText(value);
		return field;
	}

	private JPanel FullnameField(String fullname) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Họ và tên:"), gbc);
		this.fullname = this.createTextField(fullname);
		gbc.gridy = 1;
		panel.add(this.fullname, gbc);
		return panel;
	}

	private JPanel EmailField(String email) {
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

	private JPanel PhoneField(String phone) {
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

	private JPanel AddressField(String address) {
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
	
	private JPanel NotesField(String notes) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Ghi chú:"), gbc);
		this.notes = new JTextArea(5, 10);
		this.notes.setBorder(new RoundedBorder(13));
		this.notes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.notes.setText(notes);
		this.notes.setWrapStyleWord(true);
		this.notes.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.notes);
		scroll.setBorder(new RoundedBorder(13, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
	}

	private JPanel BtnField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton btnUpd = new Button("Cập nhật", ButtonType.SUCCESS);
		btnUpd.addActionListener(e -> this.onSubmit());
		panel.add(btnUpd);
		JButton btnCancel = new Button("Hủy", ButtonType.SECONDARY);
		btnCancel.addActionListener(e -> this.handleCancel());
		panel.add(btnCancel);
		return panel;
	}

	private JPanel createSidebar() {
		SideBar sidebar = new SideBar(this);
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		String path = ResourceUtil.loadStaticPath("images\\profile.png");
		ImageIcon imageIcon = new ImageIcon(path);
		JLabel label = new JLabel(imageIcon);
		panel.add(label, gbc);
		gbc.gridy = 1;
		panel.add(sidebar, gbc);
		return panel;
	}

	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}

	private JLabel createLabel(String content) {
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
		String txtNotes = this.notes.getText().trim();
		UserObject user = AuthContext.getUser();
		if (this.isChange(user)) {
			if (Dialog.confirm(this, "Bạn có chắc chắn muốn sửa thông tin?")) {
				if (txtFullname.equals("")) {
					Dialog.error(this, "Họ và tên không được để trống!");
				} else {
					if (txtEmail.equals("")) {
						Dialog.error(this, "Email không được để trống!");
					} else {
						if (txtPhone.equals("")) {
							Dialog.error(this, "Số điện thoại không được để trống!");
						} else {
							UserObject newUser = new UserObject(user);
							newUser.setUser_fullname(txtFullname);
							newUser.setUser_email(txtEmail);
							newUser.setUser_phone(txtPhone);
							newUser.setUser_address(txtAddress);
							newUser.setUser_notes(txtNotes);
							ConnectionPool cp = ConnectionContext.getCP();
							UserControl uc = new UserControl(cp);
							if (cp == null) {
								ConnectionContext.setCP(uc.getCP());
							}
							boolean result = uc.editUser(newUser);
							if (result) {
								newUser = uc.getUserObject(user.getUser_id());
								AuthContext.setUser(newUser);
								ProfilePage view = new ProfilePage();
								view.setVisible(true);
								Dialog.success(view, "Cập nhật thông tin thành công!");
								this.dispose();
							} else {
								Dialog.success(this, "Cập nhật thông tin không thành công!");
							}
							uc.releaseConnection();
						} //Check phone
					} //Check email
				} //Check full name
			} //Check confirm
		} //Check change
	}

	private boolean isChange(UserObject user) {
		if (!this.fullname.getText().trim().equals(user.getUser_fullname()))
			return true;
		if (!this.email.getText().trim().equals(user.getUser_email()))
			return true;
		if (!this.phone.getText().trim().equals(user.getUser_phone()))
			return true;
		if(!this.address.getText().trim().equals(user.getUser_address())) 
			return true;
		if(!this.notes.getText().trim().equals(user.getUser_notes())) 
			return true;
		return false;
	}

	private void handleCancel() {
		UserObject u = AuthContext.getUser();
		this.fullname.setText(u.getUser_fullname());
		this.email.setText(u.getUser_email());
		this.phone.setText(u.getUser_phone());
		this.address.setText(u.getUser_address());
		this.notes.setText(u.getUser_notes());
	}

}
