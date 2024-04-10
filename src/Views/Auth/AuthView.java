package Views.Auth;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Themes.Colors;
import Utilities.ResourceUtil;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

public class AuthView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AuthView() {
		this.initUI();
	}
	
	public void initUI() {
		this.setTitle("Phần mềm quản lý sản phẩm");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 650, 400);
		this.contentPane = this.createPanel();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Colors.White);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(this.textLabel(), gbc);
		gbc.gridy = 1;
		panel.add(this.imgLabel(), gbc);
		gbc.gridy = 2;
		panel.add(this.buttonPanel(), gbc);
		return panel;
	}
	
	public JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Colors.White);
        buttonPanel.add(this.BtnDangNhapComponent());
        buttonPanel.add(this.BtnDangKyComponent());
        return buttonPanel;
	}
	
	public JLabel imgLabel() {
		String imgPath = ResourceUtil.loadStaticPath("images\\analyze.png");
		ImageIcon imageIcon = new ImageIcon(imgPath);
		Image image = imageIcon.getImage().getScaledInstance(350, 232, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel imgLabel = new JLabel(imageIcon);
		imgLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		return imgLabel;
	}
	
	public JPanel textLabel() {
		JLabel label = new JLabel("PHẦN MỀM QUẢN LÝ SẢN PHẨM");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Colors.Black);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Colors.White);
		panel.add(label);
		return panel;
	}
	
	public JButton BtnDangNhapComponent() {
		JButton btn = this.BtnComponent("Đăng nhập");
		btn.addActionListener(e -> {
			SignInView signInView = new SignInView();
			signInView.setVisible(true);
			this.dispose();
		});
		return btn;
	}
	
	public JButton BtnDangKyComponent() {
		JButton btn = this.BtnComponent("Đăng ký");
		btn.addActionListener(e -> {
			SignUpView signUpView = new SignUpView();
			signUpView.setVisible(true);
			this.dispose();
		});
		return btn;
	}
	
	public JButton BtnComponent(String label) {
		JButton btn = new Button(label, ButtonType.PRIMARY);
		return btn;
	}
	
}
