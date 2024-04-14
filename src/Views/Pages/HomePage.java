package Views.Pages;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.SideBar;
import Components.Borders.RoundedBorder;
import Components.Borders.VerticalBorder;
import Shared.PageState;
import Themes.Colors;
import Utilities.ResourceUtil;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private JPanel contentPane;
	
	public HomePage() {
		PageState.page = "home";
		this.initUI();
		
	}
	
	private void initUI() {
		this.setTitle("Trang chủ");
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
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(this.ProductCard(), gbc);
		gbc.gridx = 1;
		panel.add(this.EmployeeCard(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(this.UserCard(), gbc);
		return panel;
	}
	
	private JPanel createTitle() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Thống kê nhanh");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setBorder(new EmptyBorder(0, 45, 20, 0));
		panel.add(label);
		return panel;
	}
	
	private JPanel ProductCard() {
		ImageIcon img = new ImageIcon(ResourceUtil.loadStaticPath("images\\cards\\product.png"));
		JPanel panel = this.createInfoCard("Số lượng sản phẩm", "73 Sản phẩm", img);
		return panel;
	}
	
	private JPanel EmployeeCard() {
		ImageIcon img = new ImageIcon(ResourceUtil.loadStaticPath("images\\cards\\employee.png"));
		JPanel panel = this.createInfoCard("Số lượng nhân viên", "24 Nhân viên", img);
		return panel;
	}
	
	private JPanel UserCard() {
		ImageIcon img = new ImageIcon(ResourceUtil.loadStaticPath("images\\cards\\user.png"));
		JPanel panel = this.createInfoCard("Số lượng người dùng", "20 Người dùng", img);
		return panel;
	}
	
	private JPanel createInfoCard(String title, String content, ImageIcon img) {
		JPanel panel = this.createPanelField();
		JPanel insidePanel = this.createPanel();
		insidePanel.setBorder(new RoundedBorder(13, 15));
		insidePanel.setPreferredSize(new Dimension(240, 100));
		insidePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
		insidePanel.add(titleLabel, gbc);
		JLabel contentLabel = new JLabel(content);
		contentLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentLabel.setIcon(img);
		gbc.gridy = 1;
		insidePanel.add(contentLabel, gbc);
		panel.add(insidePanel);
		return panel;
	}
	
	private JPanel createPanelField() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(0, 50, 20, 0));
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
		String path = ResourceUtil.loadStaticPath("images\\home.png");
		ImageIcon imageIcon = new ImageIcon(path);
		JLabel label = new JLabel(imageIcon);
		panel.add(label, gbc);
		gbc.gridy = 1;
		panel.add(sidebar, gbc);
		return panel;
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}

}
