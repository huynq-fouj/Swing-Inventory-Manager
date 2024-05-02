package Views.Pages.Product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import Models.Category.CategoryComboBoxModel;
import Models.Category.CategoryControl;
import Models.Category.CategorySortType;
import Models.Objects.CategoryObject;
import Models.Objects.ProductObject;
import Models.Product.ProductControl;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.Utilities_date;

public class ProductForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int productId;
	private String type;
	private JTextField product_name;
	private JTextField product_quantity;
	private JTextField product_price;
	private JComboBox<CategoryObject> category;
	private JTextField product_size;
	private JTextField product_unit;
	private JTextField product_promotion;
	private JTextArea product_details;
	private ArrayList<CategoryObject> categories;
	
	public ProductForm() {
		this(0, "create");
	}
	
	public ProductForm(int productId, String type) {
		PageState.page = "productform";
		this.productId = productId;
		ConnectionPool cp = ConnectionContext.getCP();
		CategoryControl cc = new CategoryControl(cp);
		if(cp == null) ConnectionContext.setCP(cc.getCP());
		CategoryObject similar = new CategoryObject();
		similar.setAuthor_id(AuthContext.getUser().getUser_id());
		this.categories = cc.getCategories(similar, CategorySortType.NAME_ASC);
		cc.releaseConnection();
		if(this.categories.size() <= 0) {
			ProductPage productPage = new ProductPage();
			productPage.setVisible(true);
			Dialog.warning(productPage, "Vui lòng thêm ít nhất một thể loại sản phẩm!");
			this.dispose();
		}
		this.type = type;
		this.initUI();
	}
	
	private void initUI() {
		String title = "Sản phẩm";
		if(this.isCreate()) title = "Thêm sản phẩm";
		if(this.isUpdate()) title = "Cập nhật thông tin sản phẩm";
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
		String name = "", quantity = "0", price = "0.0", size = "", unit = "",
				promotion = "0", details = "", created_date = Utilities_date.getDate(), modified_date = Utilities_date.getDate();
		int category_id = this.categories.get(0).getCategory_id();
		if(this.isUpdate()) {
			ConnectionPool cp = ConnectionContext.getCP();
			ProductControl pc = new ProductControl(cp);
			if(cp == null) {
				ConnectionContext.setCP(pc.getCP());
			}
			ProductObject product = pc.getProduct(this.productId);
			pc.releaseConnection();
			name = product.getProduct_name();
			category_id = product.getProduct_category_id();
			quantity = product.getProduct_quantity() + "";
			price = product.getProduct_formatPrice("###.##");
			size = product.getProduct_size();
			unit = product.getProduct_unit();
			promotion = product.getProduct_promotion() + "";
			details = product.getProduct_details();
			created_date = product.getProduct_created_date();
			modified_date = product.getProduct_modified_date();
		}
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(this.createTitle(), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		panel.add(this.ProductNameField(name), gbc);
		gbc.gridx = 1;
		panel.add(this.ProductUnitField(unit), gbc);
		gbc.gridx = 2;
		panel.add(this.ProductSizeField(size), gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(this.ProductQuantityField(quantity), gbc);
		gbc.gridx = 1;
		panel.add(this.ProductPriceField(price), gbc);
		gbc.gridx = 2;
		panel.add(this.ProductPromotionField(promotion), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(this.ProductCategoryField(category_id), gbc);
		gbc.gridx = 1;
		panel.add(this.ShowDateField("Ngày tạo:", created_date), gbc);
		gbc.gridx = 2;
		panel.add(this.ShowDateField("Sửa lần cuối:", modified_date), gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 2;
		panel.add(this.NotesField(details), gbc);
		gbc.gridheight = 1;
		gbc.gridy = 6;
		panel.add(this.BottomButton(), gbc);
		return panel;
	}
	
	private JPanel ProductNameField(String name) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên sản phẩm:"), gbc);
		this.product_name = this.createTextField(name);
		gbc.gridy = 1;
		panel.add(this.product_name, gbc);
		return panel;
	}
	
	private JPanel ProductQuantityField(String quantity) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Số lượng sản phẩm:"), gbc);
		this.product_quantity = this.createTextField(quantity);
		gbc.gridy = 1;
		panel.add(this.product_quantity, gbc);
		return panel;
	}
	
	private JPanel ProductPriceField(String price) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Đơn giá:"), gbc);
		this.product_price = this.createTextField(price);
		gbc.gridy = 1;
		panel.add(this.product_price, gbc);
		return panel;
	}
	
	private JPanel ProductSizeField(String size) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Kích thước:"), gbc);
		this.product_size = this.createTextField(size);
		gbc.gridy = 1;
		panel.add(this.product_size, gbc);
		return panel;
	}
	
	private JPanel ProductPromotionField(String promotion) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Phần trăm khuyến mại:"), gbc);
		this.product_promotion = this.createTextField(promotion);
		gbc.gridy = 1;
		panel.add(this.product_promotion, gbc);
		return panel;
	}
	
	private JPanel ProductCategoryField(int category_id) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Thể loại:"), gbc);
		CategoryObject[] items = new CategoryObject[this.categories.size()];
		this.categories.forEach(item -> items[this.categories.indexOf(item)] = item);
		this.category = new JComboBox<>(new CategoryComboBoxModel(items));
		this.category.setBorder(new RoundedBorder(13, 3));
		this.category.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.category.setPreferredSize(new Dimension(275, 38));
		this.category.setBackground(Colors.White);
		CategoryObject item = null;
		for(CategoryObject x: items) if(x.getCategory_id() == category_id) item = x;
		this.category.setSelectedItem(item);
		gbc.gridy = 1;
		panel.add(this.category, gbc);
		return panel;
	}
	
	private JPanel ProductUnitField(String unit) {
		JPanel panel = this.createPanelField();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Đơn vị tính:"), gbc);
		this.product_unit = this.createTextField(unit);
		gbc.gridy = 1;
		panel.add(this.product_unit, gbc);
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
		this.product_details = new JTextArea(5, 10);
		this.product_details.setBorder(new RoundedBorder(13));
		this.product_details.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.product_details.setText(notes);
		this.product_details.setWrapStyleWord(true);
		this.product_details.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(this.product_details);
		scroll.setBorder(new RoundedBorder(13, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
	}
	
	private JPanel BottomButton() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton cancelBtn = new Button("Hủy", ButtonType.SECONDARY);
		cancelBtn.addActionListener(e -> {
			ProductPage productPage = new ProductPage();
			productPage.setVisible(true);
			this.dispose();
		});
		panel.add(cancelBtn);
		if(this.isCreate()) {
			JButton btn = new Button("Thêm mới", ButtonType.PRIMARY);
			btn.addActionListener(e -> {
				this.handleUpdateProduct();
			});
			panel.add(btn);
		}
		if(this.isUpdate()) {
			JButton btn = new Button("Cập nhật", ButtonType.SUCCESS);
			btn.addActionListener(e -> {
				this.handleUpdateProduct();
			});
			panel.add(btn);
		}
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
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		String title = "Thông tin sản phẩm";
		if(this.isCreate()) title = "Thêm sản phẩm mới";
		JLabel label = new JLabel(title);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 20, 0));
		panel.add(label);
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
	
	private JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(0, 18, 20, 0));
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
	
	private boolean checkValid() {
		if(this.product_name.getText() == null || this.product_name.getText().trim().equals("")) {
			Dialog.error(this, "Trường tên sản phẩm không được để trống!");
			return false;
		}
		
		if(this.product_unit.getText() == null || this.product_unit.getText().trim().equals("")) {
			Dialog.error(this, "Trường đơn vị tính không được để trống!");
			return false;
		}
		
		if(this.product_size.getText() == null || this.product_size.getText().trim().equals("")) {
			Dialog.error(this, "Trường kích thước sản phẩm không được để trống!");
			return false;
		}
		
		if(this.product_quantity.getText() == null || this.product_quantity.getText().trim().equals("")) {
			Dialog.error(this, "Trường số lượng sản phẩm không được để trống!");
			return false;
		} else {
			try {
				int quantity = Integer.parseInt(this.product_quantity.getText().trim());
				if(quantity < 0) {
					throw new Exception("Trường số lượng sản phẩm phải là số nguyên ≥ 0!");
				}
			} catch(Exception e) {
				Dialog.error(this, "Trường số lượng sản phẩm phải là số nguyên ≥ 0!");
				return false;
			}
		}
		
		if(this.product_price.getText() == null || this.product_price.getText().trim().equals("")) {
			Dialog.error(this, "Trường đơn giá không được để trống!");
			return false;
		} else {
			try {
				Double price = Double.parseDouble(this.product_price.getText().trim());
				if(price < 0) {
					throw new Exception("Trường đơn giá phải là số thực ≥ 0!");
				}
			} catch(Exception e) {
				Dialog.error(this, "Trường đơn giá phải là số thực ≥ 0!");
				return false;
			}
		}
		
		if(this.product_promotion.getText() == null || this.product_promotion.getText().trim().equals("")) {
			Dialog.error(this, "Trường khuyến mại không được để trống!");
			return false;
		} else {
			try {
				double promotion = Double.parseDouble(this.product_promotion.getText().trim());
				if(promotion < 0 || promotion > 100) {
					throw new Exception("Trường khuyến mại phải là số thực ≥ 0 và ≤ 100!");
				}
			} catch(Exception e) {
				Dialog.error(this, "Trường khuyến mại phải là số thực ≥ 0 và ≤ 100!");
				return false;
			}
		}

		return true;
	}
	
	private void handleUpdateProduct() {
		if(this.checkValid()) {
			ProductObject product = new ProductObject();
			product.setProduct_id(this.productId);
			product.setAuthor_id(AuthContext.getUser().getUser_id());
			product.setProduct_name(this.product_name.getText().trim());
			product.setProduct_price(Double.parseDouble(this.product_price.getText().trim()));
			product.setProduct_quantity(Integer.parseInt(this.product_quantity.getText().trim()));
			product.setProduct_promotion(Double.parseDouble(this.product_promotion.getText().trim()));
			product.setProduct_details(this.product_details.getText().trim());
			product.setProduct_size(this.product_size.getText().trim());
			product.setProduct_unit(this.product_unit.getText().trim());
			CategoryObject category = (CategoryObject) this.category.getSelectedItem();
			product.setProduct_category_id(category.getCategory_id());
			ConnectionPool cp = ConnectionContext.getCP();
			ProductControl pc = new ProductControl(cp);
			if(cp == null) ConnectionContext.setCP(pc.getCP());
			boolean check = false;
			String successMessage = "", errMessage = "Có lỗi trong quá trình xử lý!";
			//Create
			if(this.isCreate()) {
				check = pc.addProduct(product);
				successMessage = "Thêm sản phẩm thành công!";
				errMessage = "Thêm sản phẩm không thành công!";
			}
			//Update
			if(this.isUpdate()) {
				check = pc.editProduct(product);
				successMessage = "Cập nhật sản phẩm thành công!";
				errMessage = "Cập nhật sản phẩm không thành công!";
			}
			pc.releaseConnection();
			
			if(check) {
				ProductPage productPage = new ProductPage();
				productPage.setVisible(true);
				Dialog.success(productPage, successMessage);
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
