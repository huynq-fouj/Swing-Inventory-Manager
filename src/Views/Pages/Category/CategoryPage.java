package Views.Pages.Category;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Components.Table.TableComponent;
import Controllers.SortCategoryController;
import Databases.ConnectionPool;
import Models.Category.CategoryControl;
import Models.Category.CategorySortType;
import Models.Objects.CategoryObject;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;
import Components.Dialog;
import Components.SideBar;

public class CategoryPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField search;
	private int categoryId = 0;
	private String categoryName;
	private CategoryObject similar;
	private CategorySortType type;
	private boolean sorted;
	
	public CategoryPage() {
		PageState.page = "category";
		this.similar = new CategoryObject();
		this.similar.setAuthor_id(AuthContext.getUser().getUser_id());
		this.type = CategorySortType.ID_DESC;
		this.initUI();
		
	}
	
	private void initUI() {
		this.setTitle("Danh mục");
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
		panel.add(this.createTitle(), gbc);
		gbc.gridy = 1;
		panel.add(this.SearchField(), gbc);
		gbc.gridy = 2;
		panel.add(this.createTable(), gbc);
		gbc.gridy = 3;
		panel.add(this.GroupButton(), gbc);
		return panel;
	}
	
	private JPanel createTable() {
		JPanel panel = this.createPanelField();
		this.table = new TableComponent();
		this.loadTable();
		SortCategoryController scc = new SortCategoryController(this);
		this.table.getTableHeader().addMouseListener(scc);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.table);
		scroll.setPreferredSize(new Dimension(620, 300));
		panel.add(scroll);
		return panel;
	}
	
	private void loadTable(CategoryObject similar, CategorySortType type) {
		ConnectionPool cp = ConnectionContext.getCP();
		CategoryControl cc = new CategoryControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(cc.getCP());
		}
		DefaultTableModel dataModel = cc.getTableModel(similar, type);
		cc.releaseConnection();
		this.table.setModel(dataModel);
	}
	
	public void loadTable() {
		this.loadTable(this.similar, this.type);
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Danh sách danh mục");
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
		panel.add(this.search);
		JButton btn = new Button("Tìm kiếm", ButtonType.SECONDARY);
		btn.addActionListener(e -> {
			this.handleSearch();
		});
		panel.add(btn);
		return panel;
	}
	
	private JPanel GroupButton() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addBtn = new Button("Thêm mới", ButtonType.PRIMARY);
		addBtn.addActionListener(e -> {
			this.handleAddCategory();
		});
		panel.add(addBtn);
		JButton editBtn = new Button("Cập nhật", ButtonType.SUCCESS);
		editBtn.addActionListener(e -> {
			this.handleUpdateCategory();
		});
		panel.add(editBtn);
		JButton delBtn = new Button("Xóa", ButtonType.DANGER);
		delBtn.addActionListener(e -> {
			this.handleDeleteCategory();
		});
		panel.add(delBtn);
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
		String path = ResourceUtil.loadStaticPath("images\\category.png");
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
			this.categoryId = (int) this.table.getValueAt(row, 0);
			this.categoryName = this.table.getValueAt(row, 1).toString();
		}
	}
	
	private void handleAddCategory() {
		CategoryForm categoryForm = new CategoryForm();
		categoryForm.setVisible(true);
		this.dispose();
	}
	
	private void handleUpdateCategory() {
		this.handleSelectRow();
		if(this.categoryId > 0) {
			CategoryForm categoryForm = new CategoryForm(this.categoryId, "update");
			categoryForm.setVisible(true);
			this.dispose();
		}
		this.resetState();
	}
	
	private void handleDeleteCategory() {
		this.handleSelectRow();
		if(this.categoryId > 0) {
			if(Dialog.confirm(this, "Bạn chắc chắn muốn xóa thể loại: " + this.categoryName)) {
				CategoryObject item = new CategoryObject();
				item.setCategory_id(this.categoryId);
				ConnectionPool cp = ConnectionContext.getCP();
				CategoryControl cc = new CategoryControl(cp);
				if(cp == null) {
					ConnectionContext.setCP(cc.getCP());
				}
				boolean check = cc.delCategory(item);
				cc.releaseConnection();
				if(check) {
					Dialog.success(this, "Xóa thể loại thành công");
					this.loadTable();
				}
				else Dialog.error(this, "Xóa thể loại không thành công");
			}
		}
		this.resetState();
	}
	
	private void handleSearch() {
		String searchKey = this.search.getText();
		String key = searchKey.trim();
		this.similar.setCategory_name(key);
		this.loadTable();
	}
	
	private void resetState() {
		this.categoryId = 0;
		this.categoryName = "";
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
	
	public void setCategorySortType(CategorySortType type) {
		this.type = type;
	}
	
}
