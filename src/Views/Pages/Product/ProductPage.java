package Views.Pages.Product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Components.Table.TableComponent;
import Controllers.SortProductController;
import Databases.ConnectionPool;
import Models.Objects.ProductObject;
import Models.Product.ProductControl;
import Models.Product.ProductSortType;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;
import Components.Dialog;
import Components.SideBar;

public class ProductPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField search;
	private int productId = 0;
	private String productName;
	private ProductObject similar;
	private ProductSortType type;
	private boolean sorted;
	
	public ProductPage() {
		PageState.page = "product";
		this.similar = new ProductObject();
		this.similar.setAuthor_id(AuthContext.getUser().getUser_id());
		this.type = ProductSortType.ID_DESC;
		this.initUI();
		
	}
	
	public void initUI() {
		this.setTitle("Sản phẩm");
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
		panel.add(this.createTitle(), gbc);
		gbc.gridy = 1;
		panel.add(this.SearchField(), gbc);
		gbc.gridy = 2;
		panel.add(this.createTable(), gbc);
		gbc.gridy = 3;
		panel.add(this.GroupButton(), gbc);
		return panel;
	}
		
	public JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Danh sách sản phẩm");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 20, 0));
		panel.add(label);
		return panel;
	}
	
	private JPanel SearchField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.search = new JTextField();
		this.search.setPreferredSize(new Dimension(200, 38));
		this.search.setBorder(new RoundedBorder(13));
		this.search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				handleSearch();
		    }
		});
		panel.add(this.search);
		JButton btn = new Button("Tìm kiếm", ButtonType.SECONDARY);
		btn.addActionListener(e -> {
			this.handleSearch();
		});
		panel.add(btn);
		JButton addBtn = new Button("Thêm mới", ButtonType.PRIMARY);
		addBtn.addActionListener(e -> {
			this.handleAddProduct();
		});
		panel.add(addBtn);
		return panel;
	}
	
	private JPanel createTable() {
		JPanel panel = this.createPanelField();
		this.table = new TableComponent();
		this.loadTable();
		SortProductController spc = new SortProductController(this);
		this.table.getTableHeader().addMouseListener(spc);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.table);
		scroll.setPreferredSize(new Dimension(620, 300));
		panel.add(scroll);
		return panel;
	}
	
	private void loadTable(ProductObject similar, ProductSortType type) {
		ConnectionPool cp = ConnectionContext.getCP();
		ProductControl pc = new ProductControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(pc.getCP());
		}
		DefaultTableModel dataModel = pc.getTableModel(similar, type);
		pc.releaseConnection();
		this.table.setModel(dataModel);
	}
	
	public void loadTable() {
		this.loadTable(this.similar, this.type);
	}
	
	private JPanel GroupButton() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton infoBtn = new Button("Xem thông tin", ButtonType.PRIMARY);
		infoBtn.addActionListener(e -> {
			this.handleUpdateProduct();
		});
		panel.add(infoBtn);
		JButton editBtn = new Button("Cập nhật", ButtonType.SUCCESS);
		editBtn.addActionListener(e -> {
			this.handleUpdateProduct();
		});
		panel.add(editBtn);
		JButton delBtn = new Button("Xóa", ButtonType.DANGER);
		delBtn.addActionListener(e -> {
			this.handleDeleteProduct();
		});
		panel.add(delBtn);
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
		String path = ResourceUtil.loadStaticPath("images\\product.png");
		ImageIcon imageIcon = new ImageIcon(path);
		JLabel label = new JLabel(imageIcon);
		panel.add(label, gbc);
		gbc.gridy = 1;
		panel.add(sidebar, gbc);
		return panel;
	}
	
	private JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setBorder(new EmptyBorder(0, 50, 10, 0));
		return panel;
	}
	
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}
	
	private void handleSelectRow() {
		int row = this.table.getSelectedRow();
		if(row != -1) {
			this.productId = (int) this.table.getValueAt(row, 0);
			this.productName = this.table.getValueAt(row, 1).toString();
		}
	}
	
	private void handleAddProduct() {
		ProductForm productForm = new ProductForm();
		productForm.setVisible(true);
		this.dispose();
	}
	
	private void handleUpdateProduct() {
		this.handleSelectRow();
		if(this.productId > 0) {
			ProductForm productForm = new ProductForm(this.productId, "update");
			productForm.setVisible(true);
			this.dispose();
		}
		this.resetState();
	}
	
	private void handleDeleteProduct() {
		this.handleSelectRow();
		if(this.productId > 0) {
			if(Dialog.confirm(this, "Bạn chắc chắn muốn xóa sản phẩm: " + this.productName)) {
				ProductObject item = new ProductObject();
				item.setProduct_id(this.productId);
				ConnectionPool cp = ConnectionContext.getCP();
				ProductControl pc = new ProductControl(cp);
				if(cp == null) {
					ConnectionContext.setCP(pc.getCP());
				}
				boolean check = pc.delProduct(item);
				pc.releaseConnection();
				if(check) {
					Dialog.success(this, "Xóa sản phẩm thành công");
					this.loadTable();
				}
				else Dialog.error(this, "Xóa sản phẩm không thành công");
			}
		}
		this.resetState();
	}
	
	private void handleSearch() {
		String searchKey = this.search.getText();
		String key = searchKey.trim();
		this.similar.setProduct_name(key);
		this.loadTable();
	}
	
	private void resetState() {
		this.productId = 0;
		this.productName = "";
	}
	
	public JTable getTable() {
		return this.table;
	}
	
	public boolean isSorted() {
		return this.sorted;
	}
	
	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
	
	public void setProductSortType(ProductSortType type) {
		this.type = type;
	}
	
}
