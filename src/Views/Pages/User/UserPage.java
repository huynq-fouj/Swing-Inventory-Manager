package Views.Pages.User;

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
import Controllers.SortUserController;
import Databases.ConnectionPool;
import Models.Objects.UserObject;
import Models.User.UserControl;
import Models.User.UserSortType;
import Shared.AuthContext;
import Shared.ConnectionContext;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;
import Components.Dialog;
import Components.SideBar;

public class UserPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField search;
	private int idUser = 0;
	private String nameUser;
	private UserObject similar;
	private UserSortType sortType;
	private boolean sorted;
	
	public UserPage() {
		PageState.page = "user";
		this.similar = new UserObject();
		this.sortType = UserSortType.ID_DESC;
		this.sorted = false;
		this.initUI();
		
	}
	
	private void initUI() {
		this.setTitle("Người dùng");
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
		this.loadTable(null, UserSortType.ID_DESC);
		SortUserController suc = new SortUserController(this);
		this.table.getTableHeader().addMouseListener(suc);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(this.table);
		scroll.setPreferredSize(new Dimension(620, 300));
		panel.add(scroll);
		return panel;
	}
	
	private void loadTable(UserObject similar, UserSortType type) {
		ConnectionPool cp = ConnectionContext.getCP();
		UserControl uc = new UserControl(cp);
		if(cp == null) {
			ConnectionContext.setCP(uc.getCP());
		}
		DefaultTableModel dataModel = uc.getTableModel(similar, type);
		uc.releaseConnection();
		this.table.setModel(dataModel);
	}
	
	public void loadTable() {
		this.loadTable(this.similar, this.sortType);
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Danh sách người dùng");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 15, 0));
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
			this.handleAddUser();
		});
		panel.add(addBtn);
		JButton editBtn = new Button("Cập nhật", ButtonType.SUCCESS);
		editBtn.addActionListener(e -> {
			this.handleUpdateUser();
		});
		panel.add(editBtn);
		JButton delBtn = new Button("Xóa", ButtonType.DANGER);
		delBtn.addActionListener(e -> {
			this.handleDeleteUser();
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
		String path = ResourceUtil.loadStaticPath("images\\users.png");
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
			this.idUser = (int) this.table.getValueAt(row, 0);
			this.nameUser = this.table.getValueAt(row, 1).toString();
		}
	}
	
	private void handleAddUser() {
		UserForm userForm = new UserForm();
		userForm.setVisible(true);
		this.dispose();
	}
	
	private void handleUpdateUser() {
		this.handleSelectRow();
		if(this.idUser > 0) {
			UserForm userForm = new UserForm(this.idUser, "update");
			userForm.setVisible(true);
			this.dispose();
		}
		this.resetState();
	}
	
	private void handleDeleteUser() {
		this.handleSelectRow();
		if(this.idUser > 0) {
			if(Dialog.confirm(this, "Bạn chắc chắn muốn xóa người dùng: " + this.nameUser)) {
				if(this.idUser != AuthContext.getUser().getUser_id()) {	
					UserObject item = new UserObject();
					item.setUser_id(idUser);
					ConnectionPool cp = ConnectionContext.getCP();
					UserControl uc = new UserControl(cp);
					if(cp == null) {
						ConnectionContext.setCP(uc.getCP());
					}
					boolean check = uc.delUser(item);
					uc.releaseConnection();
					if(check) {
						Dialog.success(this, "Xóa thành công người dùng");
						this.loadTable();
					}
					else Dialog.error(this, "Xóa người dùng không thành công");
				} else {
					Dialog.error(this, "Không thể xóa người dùng này");
				}
			}
		}
		this.resetState();
	}
	
	private void handleSearch() {
		String searchKey = this.search.getText();
		String key = searchKey.trim();
		this.similar.setUser_name(key);
		this.loadTable();
	}
	
	private void resetState() {
		this.idUser = 0;
		this.nameUser = "";
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
	
	public void setUserSortType(UserSortType type) {
		this.sortType = type;
	}

}
