package Views.Pages.Employee;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
import Components.Borders.RoundedBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Databases.ConnectionPool;
import Models.Employee.EmployeeControl;
import Models.Objects.EmployeeObject;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;

public class EmployeeForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int employeeId;
	private String type;
	private JTextField employee_fullname;
	private JTextField employee_phone;
	private JTextField employee_address;
	private JTextField employee_birthday;
	private JTextField employee_email;
	private JTextField employee_salary;
	private JTextField employee_debt;
	private JTextField employee_position;
	private JTextArea employee_notes;
	
	public EmployeeForm() {
		this(0, "create");
	}
	
	public EmployeeForm(int employeeId, String type) {
		PageState.page = "employeeform";
		this.employeeId = employeeId;
		this.type = type;
		this.initUI();
	}
	
	private void initUI() {
		String title = "Nhân viên";
		if(this.isCreate()) title = "Thêm nhân viên";
		if(this.isUpdate()) title = "Cập nhật thông tin nhân viên";
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
		String fullname = "", phone = "", address = "",
				birthday = "", email = "", salary = "0",
				debt = "0", position = "", notes = "",
				created_date = "", modified_date = "";
		if(this.isUpdate()) {
			ConnectionPool cp = ConnectionContext.getCP();
			EmployeeControl ec = new EmployeeControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(ec.getCP());
			}
			EmployeeObject e = ec.getEmployee(this.employeeId);
			ec.releaseConnection();
			fullname = e.getEmployee_fullname();
			phone = e.getEmployee_phone();
			address = e.getEmployee_address();
			birthday = e.getEmployee_birthday();
			email = e.getEmployee_email();
			salary = e.getEmployee_formatSalary("###.##");
			debt = e.getEmployee_formatDebt("###.##");
			position = e.getEmployee_position();
			notes = e.getEmployee_notes();
			created_date = e.getEmployee_created_date();
			modified_date = e.getEmployee_modified_date();
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
			panel.add(this.EmployeeIdField(), gbc);
			gbc.gridx = 1;
		}
		panel.add(this.EmployeeFullnameField(fullname), gbc);
		gbc.gridx = this.isCreate() ? 1 : 2;
		gbc.gridwidth = this.isCreate() ? 2 : 1;
		panel.add(this.EmployeeAddressField(address), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(this.EmployeePhoneField(phone), gbc);
		gbc.gridx = 1;
		panel.add(this.EmployeeEmailField(email), gbc);
		gbc.gridx = 2;
		panel.add(this.EmployeeBirthdayField(birthday), gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		panel.add(this.EmployeeSalaryField(salary), gbc);
		gbc.gridx = 1;
		panel.add(this.EmployeeDebtField(debt), gbc);
		gbc.gridx = 2;
		panel.add(this.EmployeePositionField(position), gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = this.isCreate() ? 3 : 2;
		gbc.gridheight = 2;
		panel.add(this.NotesField(notes), gbc);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		if(this.isUpdate()) {
			gbc.gridx = 2;
			panel.add(this.ShowDateField("Ngày tạo:", created_date), gbc);
			gbc.gridy = 5;
			panel.add(this.ShowDateField("Sửa lần cuối:", modified_date), gbc);
		}
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(this.BottomButton(), gbc);
		return panel;
	}
	
	private JPanel EmployeeFullnameField(String fullname) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Họ tên:"), gbc);
		this.employee_fullname = this.createTextField(fullname);
		gbc.gridy = 1;
		panel.add(this.employee_fullname, gbc);
		return panel;
	}
	
	private JPanel EmployeeAddressField(String address) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Địa chỉ:"), gbc);
		this.employee_address = this.createTextField(address);
		gbc.gridy = 1;
		panel.add(this.employee_address, gbc);
		return panel;
	}
	
	private JPanel EmployeePhoneField(String phone) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Số điện thoại:"), gbc);
		this.employee_phone = this.createTextField(phone);
		gbc.gridy = 1;
		panel.add(this.employee_phone, gbc);
		return panel;
	}
	
	private JPanel EmployeeEmailField(String email) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Email:"), gbc);
		this.employee_email = this.createTextField(email);
		gbc.gridy = 1;
		panel.add(this.employee_email, gbc);
		return panel;
	}
	
	private JPanel EmployeeBirthdayField(String birthday) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Ngày sinh:"), gbc);
		this.employee_birthday = this.createTextField(birthday);
		gbc.gridy = 1;
		panel.add(this.employee_birthday, gbc);
		return panel;
	}
	
	private JPanel EmployeeSalaryField(String salary) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Lương cơ bản:"), gbc);
		this.employee_salary = this.createTextField(salary);
		gbc.gridy = 1;
		panel.add(this.employee_salary, gbc);
		return panel;
	}
	
	private JPanel EmployeeDebtField(String debt) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Nợ lương nhân viên:"), gbc);
		this.employee_debt = this.createTextField(debt);
		gbc.gridy = 1;
		panel.add(this.employee_debt, gbc);
		return panel;
	}
	
	private JPanel EmployeePositionField(String position) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Chức danh:"), gbc);
		this.employee_position = this.createTextField(position);
		gbc.gridy = 1;
		panel.add(this.employee_position, gbc);
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
		this.employee_notes = new JTextArea(5, 10);
		this.employee_notes.setBorder(new RoundedBorder(13));
		this.employee_notes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.employee_notes.setText(notes);
		this.employee_notes.setWrapStyleWord(true);
		this.employee_notes.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.employee_notes);
		scroll.setBorder(new RoundedBorder(13, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
	}
	
	private JPanel EmployeeIdField() {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("ID:"), gbc);
		JTextField field = this.createTextField(this.employeeId + "");
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
	
	private JPanel BottomButton() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton cancelBtn = new Button("Hủy", ButtonType.SECONDARY);
		cancelBtn.addActionListener(e -> {
			EmployeePage employeePage = new EmployeePage();
			employeePage.setVisible(true);
			this.dispose();
		});
		panel.add(cancelBtn);
		if(this.isCreate()) {
			JButton btn = new Button("Thêm mới", ButtonType.PRIMARY);
			btn.addActionListener(e -> {
				this.handleUpdateEmployee();
			});
			panel.add(btn);
		}
		if(this.isUpdate()) {
			JButton btn = new Button("Cập nhật", ButtonType.SUCCESS);
			btn.addActionListener(e -> {
				this.handleUpdateEmployee();
			});
			panel.add(btn);
		}
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
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String title = "Thông tin nhân viên";
		if(this.isCreate()) title = "Thêm nhân viên mới";
		JLabel label = new JLabel(title);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 15, 30, 0));
		panel.add(label);
		return panel;
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
	
	private JLabel createLabel(String content) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBorder(new EmptyBorder(0, 0, 5, 0));
		return label;
	}
	
	private boolean checkValid() {
		if(this.employee_fullname.getText() == null || this.employee_fullname.getText().trim().equals("")) {
			Dialog.error(this, "Trường họ tên không được để trống!");
			return false;
		}
		if(this.employee_phone.getText() == null || this.employee_phone.getText().trim().equals("")) {
			Dialog.error(this, "Trường số điện thoại không được để trống!");
			return false;
		}
		if(this.employee_email.getText() == null || this.employee_email.getText().trim().equals("")) {
			Dialog.error(this, "Trường email không được để trống!");
			return false;
		}
		if(this.employee_salary.getText() == null || this.employee_salary.getText().trim().equals("")) {
			Dialog.error(this, "Trường lương cơ bản không được để trống!");
			return false;
		} else {
			try {	
				Double salary = Double.parseDouble(this.employee_salary.getText().trim());
				if(salary < 0) {
					Dialog.error(this, "Trường lương cơ bản phải là số thực lớn hơn hoặc bằng 0!");
					return false;
				}
			} catch(Exception e) {
				Dialog.error(this, "Trường lương cơ bản phải là số thực!");
				return false;
			}
		}
		if(this.employee_debt.getText() == null || this.employee_debt.getText().trim().equals("")) {
			Dialog.error(this, "Trường nợ lương nhân viên không được để trống!");
		} else {
			try {	
				Double debt = Double.parseDouble(this.employee_debt.getText().trim());
				if(debt < 0) {
					Dialog.error(this, "Trường nợ lương nhân viên phải là số thực lớn hơn hoặc bằng 0!");
					return false;
				}
			} catch(Exception e) {
				Dialog.error(this, "Trường nợ lương nhân viên phải là số thực!");
				return false;
			}
		}
		return true;
	}
	
	private void handleUpdateEmployee() {
		if(this.checkValid()) {
			String fullname = this.employee_fullname.getText().trim();
			String address = this.employee_address.getText().trim();
			String notes = this.employee_notes.getText().trim();
			String position = this.employee_position.getText().trim();
			Double salary = Double.parseDouble(this.employee_salary.getText().trim());
			Double debt = Double.parseDouble(this.employee_debt.getText().trim());
			String phone = this.employee_phone.getText().trim();
			String birthday = this.employee_birthday.getText().trim();
			String email = this.employee_email.getText().trim();
			EmployeeObject employee = new EmployeeObject();
			employee.setAuthor_id(AuthContext.getUser().getUser_id());
			employee.setEmployee_address(address);
			employee.setEmployee_birthday(birthday);
			employee.setEmployee_debt(debt);
			employee.setEmployee_email(email);
			employee.setEmployee_fullname(fullname);
			employee.setEmployee_id(this.employeeId);
			employee.setEmployee_notes(notes);
			employee.setEmployee_salary(salary);
			employee.setEmployee_position(position);
			employee.setEmployee_phone(phone);
			ConnectionPool cp = ConnectionContext.getCP();
			EmployeeControl ec = new EmployeeControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(ec.getCP());
			}
			//Create
			if(this.isCreate()) {
				if(ec.addEmployee(employee)) {
					EmployeePage employeePage = new EmployeePage();
					employeePage.setVisible(true);
					Dialog.success(employeePage, "Thêm mới nhân viên thành công!");
					this.dispose();
				} else {
					Dialog.error(this, "Thêm mới nhân viên không thành công!");
				}
			}
			//Update
			if(this.isUpdate()) {
				if(ec.editEmployee(employee)) {
					EmployeePage employeePage = new EmployeePage();
					employeePage.setVisible(true);
					Dialog.success(employeePage, "Cập nhật nhân viên thành công!");
					this.dispose();
				} else {
					Dialog.error(this, "Cập nhật nhân viên không thành công!");
				}
			}
			ec.releaseConnection();
		}
	}
	
	private boolean isCreate() {
		return this.type.equals("create");
	}
	
	private boolean isUpdate() {
		return this.type.equals("update");
	}
	
}
