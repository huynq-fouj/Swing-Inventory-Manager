package Views.Pages.User;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Components.Dialog;
import Components.Borders.RoundedBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Databases.ConnectionPool;
import Models.Objects.UserObject;
import Models.User.UserControl;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;

public class UserForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int userId;
	private String type;
	private JTextField user_name;
	private JPasswordField user_password;
	private JTextField user_fullname;
	private JTextField user_email;
	private JTextField user_phone;
	private JTextField user_address;
	private JTextArea user_notes;
	
	public UserForm() {
		this(0, "create");
	}
	
	public UserForm(int userId, String type) {
		PageState.page = "userform";
		this.userId = userId;
		this.type = type;
		this.initUI();
	}
	
	private void initUI() {
		String title = "Người dùng";
		if(this.isCreate()) title = "Thêm người dùng";
		if(this.isUpdate()) title = "Cập nhật thông tin người dùng";
		this.setTitle(title);
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
		panel.add(this.mainView());
		return panel;
	}
	
	private JPanel mainView() {
		UserObject user = null;
		String name = "", fullname = "",
				notes = "", email = "",
				address = "", phone = "",
				created_at = "", modified_at = "";
		if(this.isUpdate()) {
			ConnectionPool cp = ConnectionContext.getCP();
			UserControl uc = new UserControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(uc.getCP());
			}
			user = uc.getUserObject(this.userId);
			uc.releaseConnection();
			name = user.getUser_name();
			fullname = user.getUser_fullname();
			notes = user.getUser_notes();
			email = user.getUser_email();
			address = user.getUser_address();
			phone = user.getUser_phone();
			created_at = user.getUser_created_at();
			modified_at = user.getUser_modified_at();
		}
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 3;
		panel.add(this.createTitle(), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		if(this.isUpdate()) {
			panel.add(this.UserIdField(), gbc);
			gbc.gridx = 1;
		}
		panel.add(this.UserNameField(name), gbc);
		if(this.isCreate()) {
			gbc.gridx = 1;
			panel.add(this.UserPasswordField(), gbc);
		}
		gbc.gridx = 2;
		panel.add(this.UserFullnameField(fullname), gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(this.UserEmailField(email), gbc);
		gbc.gridx = 1;
		panel.add(this.UserPhoneField(phone), gbc);
		gbc.gridx = 2;
		panel.add(this.UserAddressField(address), gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = this.isCreate() ? 3 : 2;
		gbc.gridheight = 2;
		panel.add(this.NotesField(notes), gbc);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		if(this.isUpdate()) {	
			gbc.gridx = 2;
			panel.add(this.ShowDateField("Ngày tạo:", created_at), gbc);
			gbc.gridy = 4;
			panel.add(this.ShowDateField("Sửa lần cuối:", modified_at), gbc);
		}
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(this.BottomButton(), gbc);
		return panel;
	}
	
	private JPanel BottomButton() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton cancelBtn = new Button("Hủy", ButtonType.SECONDARY);
		cancelBtn.addActionListener(e -> {
			UserPage userPage = new UserPage();
			userPage.setVisible(true);
			this.dispose();
		});
		panel.add(cancelBtn);
		if(this.isCreate()) {
			JButton btn = new Button("Thêm mới", ButtonType.PRIMARY);
			btn.addActionListener(e -> {
				this.handleUpdateUser();
			});
			panel.add(btn);
		}
		if(this.isUpdate()) {
			JButton btn = new Button("Cập nhật", ButtonType.SUCCESS);
			btn.addActionListener(e -> {
				this.handleUpdateUser();
			});
			panel.add(btn);
		}
		return panel;
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String title = "Thông tin người dùng";
		if(this.isCreate()) title = "Thêm người dùng mới";
		JLabel label = new JLabel(title);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 15, 30, 0));
		panel.add(label);
		return panel;
	}
	
	private JPanel UserNameField(String name) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên đăng nhập:"), gbc);
		this.user_name = this.createTextField(name);
		if(this.isUpdate()) {
			this.user_name.setEnabled(false);
		}
		gbc.gridy = 1;
		panel.add(this.user_name, gbc);
		return panel;
	}
	
	private JPanel UserPasswordField() {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Mật khẩu:"), gbc);
		this.user_password = this.createPasswordField();
		gbc.gridy = 1;
		panel.add(this.user_password, gbc);
		return panel;
	}
	
	private JPanel UserFullnameField(String fullname) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Họ và tên:"), gbc);
		this.user_fullname = this.createTextField(fullname);
		gbc.gridy = 1;
		panel.add(this.user_fullname, gbc);
		return panel;
	}
	
	private JPanel UserEmailField(String email) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Email:"), gbc);
		this.user_email = this.createTextField(email);
		gbc.gridy = 1;
		panel.add(this.user_email, gbc);
		return panel;
	}
	
	private JPanel UserPhoneField(String phone) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Số điện thoại:"), gbc);
		this.user_phone = this.createTextField(phone);
		gbc.gridy = 1;
		panel.add(this.user_phone, gbc);
		return panel;
	}
	
	private JPanel UserAddressField(String address) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Địa chỉ:"), gbc);
		this.user_address = this.createTextField(address);
		gbc.gridy = 1;
		panel.add(this.user_address, gbc);
		return panel;
	}
	
	private JPanel UserIdField() {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Mã người dùng:"), gbc);
		JTextField field = this.createTextField(this.userId + "");
		field.setEnabled(false);
		gbc.gridy = 1;
		panel.add(field, gbc);
		return panel;
	}
	
	private JPanel ShowDateField(String label, String content) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel(label), gbc);
		JTextField field = this.createTextField(content);
		field.setEnabled(false);
		gbc.gridy = 1;
		panel.add(field, gbc);
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
		this.user_notes = new JTextArea(5, 10);
		this.user_notes.setBorder(new RoundedBorder(13));
		this.user_notes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.user_notes.setText(notes);
		this.user_notes.setWrapStyleWord(true);
		this.user_notes.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.user_notes);
		scroll.setBorder(new RoundedBorder(13, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
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
	
	private JPasswordField createPasswordField() {
		JPasswordField field = new JPasswordField();
		field.setBorder(new RoundedBorder(13));
		field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		field.setPreferredSize(new Dimension(275, 38));
		return field;
	}
	
	private JLabel createLabel(String content) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBorder(new EmptyBorder(0, 0, 5, 0));
		return label;
	}
	
	private JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(0, 18, 20, 0));
		return panel;
	}
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}
	
	private void handleUpdateUser() {
		if(this.checkValid()) {
			String name = this.user_name.getText().trim();
			String fullname = this.user_fullname.getText().trim();
			String email = this.user_email.getText().trim();
			String phone = this.user_phone.getText().trim();
			String address = this.user_address.getText().trim();
			String notes = this.user_notes.getText().trim();
			UserObject user = new UserObject();
			user.setUser_id(this.userId);
			user.setUser_name(name);
			user.setUser_fullname(fullname);
			user.setUser_email(email);
			user.setUser_phone(phone);
			user.setUser_address(address);
			user.setUser_notes(notes);
			ConnectionPool cp = ConnectionContext.getCP();
			UserControl uc = new UserControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(uc.getCP());
			}
			boolean check = false;
			String successMessage = "";
			String errMessage = "Có lỗi trong quá trình xử lý!";
			//Create
			if(this.isCreate()) {
				check = uc.addUser(user);
				successMessage = "Thêm người dùng thành công!";
				errMessage = "Thêm người dùng không thành công!";
			}
			//Update
			if(this.isUpdate()) {
				check = uc.editUser(user);
				successMessage = "Cập nhật người dùng thành công!";
				errMessage = "Cập nhật người dùng không thành công!";
			}
			uc.releaseConnection();
			if(check) {
				UserPage userPage = new UserPage();
				userPage.setVisible(true);
				Dialog.success(userPage, successMessage);
				this.dispose();
			} else {
				Dialog.error(this, errMessage);
			}
		}
	}
	
	private boolean checkValid() {
		if(this.isCreate()) {
			if(this.user_name.getText() == null || this.user_name.getText().trim().equals("")) {
				Dialog.error(this, "Trường tên đăng nhập không được để trống!");
				return false;
			}
			if(this.user_password.getPassword() == null || new String(this.user_password.getPassword()).trim().equals("")) {
				Dialog.error(this, "Trường mật khẩu không được để trống!");
				return false;
			}
		}
		if(this.user_fullname.getText() == null || this.user_fullname.getText().trim().equals("")) {
			Dialog.error(this, "Trường họ và tên không được để trống!");
			return false;
		}
		if(this.user_email.getText() == null || this.user_email.getText().trim().equals("")) {
			Dialog.error(this, "Trường email không được để trống!");
			return false;
		}
		if(this.user_phone.getText() == null || this.user_phone.getText().trim().equals("")) {
			Dialog.error(this, "Trường số điện thoại không được để trống!");
			return false;
		}
		return true;
	}
	
	private boolean isCreate() {
		return this.type.equals("create");
	}
	
	private boolean isUpdate() {
		return this.type.equals("update");
	}

	
}
