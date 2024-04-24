package Views.Pages.Employee;

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

import Components.Dialog;
import Components.SideBar;
import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Components.Table.TableComponent;
import Controllers.SortEmployeeController;
import Databases.ConnectionPool;
import Models.Employee.EmployeeControl;
import Models.Employee.EmployeeSortType;
import Models.Objects.EmployeeObject;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;

public class EmployeePage extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField search;
	private int employeeId;
	private String employeeName;
	private EmployeeObject similar;
	private JTable table;
	private boolean sorted;
	private EmployeeSortType type;
	
	public EmployeePage() {
		PageState.page = "employee";
		this.similar = new EmployeeObject();
		this.similar.setAuthor_id(AuthContext.getUser().getUser_id());
		this.type = EmployeeSortType.ID_DESC;
		this.sorted = false;
		this.initUI();
	}
	
	private void initUI() {
		this.setTitle("Nhân viên");
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
		SortEmployeeController sec = new SortEmployeeController(this);
		this.table.getTableHeader().addMouseListener(sec);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.table);
		scroll.setPreferredSize(new Dimension(620, 300));
		panel.add(scroll);
		return panel;
	}
	
	private void loadTable(EmployeeObject similar, EmployeeSortType type) {
		ConnectionPool cp = ConnectionContext.getCP();
		EmployeeControl ec = new EmployeeControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(ec.getCP());
		}
		DefaultTableModel dataModel = ec.getTableModel(similar, type);
		ec.releaseConnection();
		this.table.setModel(dataModel);
	}
	
	public void loadTable() {
		this.loadTable(this.similar, this.type);
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
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Danh sách nhân viên");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 15, 0));
		panel.add(label);
		return panel;
	}
	
	private JPanel GroupButton() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton addBtn = new Button("Thêm mới", ButtonType.PRIMARY);
		addBtn.addActionListener(e -> {
			this.handleAddEmployee();
		});
		panel.add(addBtn);
		JButton editBtn = new Button("Cập nhật", ButtonType.SUCCESS);
		editBtn.addActionListener(e -> {
			this.handleUpdateEmployee();
		});
		panel.add(editBtn);
		JButton delBtn = new Button("Xóa", ButtonType.DANGER);
		delBtn.addActionListener(e -> {
			this.handleDeleteEmployee();
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
		String path = ResourceUtil.loadStaticPath("images\\employee.png");
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
			this.employeeId = (int) this.table.getValueAt(row, 0);
			this.employeeName = this.table.getValueAt(row, 1).toString();
		}
	}
	
	private void handleAddEmployee() {
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setVisible(true);
		this.dispose();
	}
	
	private void handleUpdateEmployee() {
		this.handleSelectRow();
		if(this.employeeId > 0) {
			EmployeeForm employeeForm = new EmployeeForm(this.employeeId, "update");
			employeeForm.setVisible(true);
			this.dispose();
		}
		this.resetState();
	}
	
	private void handleDeleteEmployee() {
		this.handleSelectRow();
		if(this.employeeId > 0) {
			if(Dialog.confirm(this, "Bạn chắc chắn muốn xóa nhân viên: " + this.employeeName)) {
				EmployeeObject item = new EmployeeObject();
				item.setEmployee_id(this.employeeId);
				ConnectionPool cp = ConnectionContext.getCP();
				EmployeeControl ec = new EmployeeControl(cp);
				if(cp == null) {
					ConnectionContext.setCP(ec.getCP());
				}
				boolean check = ec.delEmployee(item);
				ec.releaseConnection();
				if(check) {
					Dialog.success(this, "Xóa thành công nhân viên");
					this.loadTable();
				}
				else Dialog.error(this, "Xóa nhân viên không thành công");
			}
		}
		this.resetState();
	}
	
	private void handleSearch() {
		String searchKey = this.search.getText();
		String key = searchKey.trim();
		similar.setEmployee_fullname(key);
		this.loadTable();
	}
	
	private void resetState() {
		this.employeeId = 0;
		this.employeeName = "";
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
	
	public void setEmployeeSortType(EmployeeSortType type) {
		this.type = type;
	}
	
}
