package Views.Pages.Category;

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

import Components.Borders.RoundedBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Databases.ConnectionPool;
import Models.Category.CategoryControl;
import Models.Objects.CategoryObject;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.Utilities_date;
import Components.Dialog;

public class CategoryForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int categoryId;
	private String type;
	private JTextField category_name;
	private JTextArea category_notes;
	
	public CategoryForm() {
		this(0, "create");
	}
	
	public CategoryForm(int CategoryId, String type) {
		PageState.page = "categoryform";
		this.categoryId = CategoryId;
		this.type = type;
		this.initUI();
	}
	
	private void initUI() {
		String title = "Thể loại";
		if(this.isCreate()) title = "Thêm thể loại sản phẩm";
		if(this.isUpdate()) title = "Cập nhật thông tin thể loại sản phẩm";
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
		String name = "", notes = "", created_date = Utilities_date.getDate(), modified_date = Utilities_date.getDate();
		if(this.isUpdate()) {
			ConnectionPool cp = ConnectionContext.getCP();
			CategoryControl cc = new CategoryControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(cc.getCP());
			}
			CategoryObject c = cc.getCategory(this.categoryId);
			name = c.getCategory_name();
			notes = c.getCategory_notes();
			created_date = c.getCategory_created_date();
			modified_date = c.getCategory_modified_date();
			cc.releaseConnection();
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
		panel.add(this.CategoryNameField(name), gbc);
		gbc.gridx = 1;
		panel.add(this.ShowDateField("Ngày tạo:", created_date), gbc);
		gbc.gridx = 2;
		panel.add(this.ShowDateField("Sửa lần cuối:", modified_date), gbc);
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(this.NotesField(notes), gbc);
		gbc.gridy = 4;
		gbc.gridheight = 1;
		panel.add(this.BottomButton(), gbc);
		return panel;
	}
	
	private JPanel CategoryNameField(String name) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên thể loại:"), gbc);
		this.category_name = this.createTextField(name);
		gbc.gridy = 1;
		panel.add(this.category_name, gbc);
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
		this.category_notes = new JTextArea(5, 10);
		this.category_notes.setBorder(new RoundedBorder(13));
		this.category_notes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.category_notes.setText(notes);
		this.category_notes.setWrapStyleWord(true);
		this.category_notes.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.category_notes);
		scroll.setBorder(new RoundedBorder(13, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
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
			CategoryPage categoryPage = new CategoryPage();
			categoryPage.setVisible(true);
			this.dispose();
		});
		panel.add(cancelBtn);
		if(this.isCreate()) {
			JButton btn = new Button("Thêm mới", ButtonType.PRIMARY);
			btn.addActionListener(e -> {
				this.handleUpdateCategory();
			});
			panel.add(btn);
		}
		if(this.isUpdate()) {
			JButton btn = new Button("Cập nhật", ButtonType.SUCCESS);
			btn.addActionListener(e -> {
				this.handleUpdateCategory();
			});
			panel.add(btn);
		}
		return panel;
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String title = "Thông tin thể loại";
		if(this.isCreate()) title = "Thêm thể loại mới";
		JLabel label = new JLabel(title);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 15, 20, 0));
		panel.add(label);
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
		if(this.category_name.getText() == null || this.category_name.getText().trim().equals("")) {
			Dialog.error(this, "Trường tên thể loại không được bỏ trống!");
			return false;
		}
		return true;
	}
	
	private void handleUpdateCategory() {
		if(this.checkValid()) {
			String name = this.category_name.getText().trim();
			String notes = this.category_notes.getText().trim();
			CategoryObject c = new CategoryObject();
			c.setCategory_name(name);
			c.setCategory_notes(notes);
			c.setAuthor_id(AuthContext.getUser().getUser_id());
			c.setCategory_id(this.categoryId);
			ConnectionPool cp = ConnectionContext.getCP();
			CategoryControl cc = new CategoryControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(cc.getCP());
			}
			boolean check = false;
			String successMessage = "";
			String errMessage = "Có lỗi trong quá trình xử lý!";
			//Create
			if(this.isCreate()) {
				check = cc.addCategory(c);
				successMessage = "Thêm thể loại thành công!";
				errMessage = "Thêm thể loại không thành công!";
			}
			//Update
			if(this.isUpdate()) {
				check = cc.editCategory(c);
				successMessage = "Cập nhật thể loại thành công!";
				errMessage = "Cập nhật thể loại không thành công!";
			}
			cc.releaseConnection();
			if(check) {
				CategoryPage categoryPage = new CategoryPage();
				Dialog.success(categoryPage, successMessage);
				this.dispose();
			} else {
				Dialog.error(this, errMessage);
			}
		}
	}
	
	private boolean isCreate() {
		return this.type.equals("create");
	}
	
	private boolean isUpdate() {
		return this.type.equals("update");
	}
	
}
