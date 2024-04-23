package Views.Pages.User;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.Buttons.Button;
import Components.Buttons.ButtonType;
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
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(this.createTitle(), gbc);
		gbc.gridy = 1;
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
				this.handleCreateUser();
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
	
	private void handleCreateUser() {
		
	}
	
	private void handleUpdateUser() {
		
	}
	
	private boolean isCreate() {
		return this.type.equals("create");
	}
	
	private boolean isUpdate() {
		return this.type.equals("update");
	}

	
}
