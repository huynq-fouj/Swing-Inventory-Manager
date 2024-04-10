package Views.Auth;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;

import Components.Borders.RoundedBorder;
import Components.Buttons.Button;
import Components.Buttons.ButtonType;
import Controllers.AuthController;
import Themes.Colors;

import java.awt.FlowLayout;

public class SignUpView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirm;

	/**
	 * Create the frame.
	 */
	public SignUpView() {
		
		this.initUI();

	}
	
	public void initUI() {
		this.setTitle("Đăng ký");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 460);
		this.contentPane = this.createContentPane();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
	}
	
	public JPanel createContentPane() {
		JPanel panel = this.createPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridy = 0;
		panel.add(this.TitlePanel(), gbc);
		gbc.gridy = 1;
		panel.add(this.UsernamePanel(), gbc);
		gbc.gridy = 2;
		panel.add(this.PasswordPanel(), gbc);
		gbc.gridy = 3;
		panel.add(this.ConfirmPanel(), gbc);
		gbc.gridy = 4;
		panel.add(this.ButtonPanel(), gbc);
		gbc.gridy = 5;
		panel.add(this.SubtitlePanel(), gbc);
		
		return panel;
	}
	
	public JPanel TitlePanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel label = this.createLabel("ĐĂNG KÝ");
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		panel.add(label);
		return panel;
	}
	
	public JPanel SubtitlePanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		JLabel label = this.createLabel("Đã có tài khoản? ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(label);
		JLabel label2 = this.createLabel("Đăng nhập");
		label2.setForeground(Colors.Primary);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panel.add(label2);
		return panel;
	}
	
	public JPanel UsernamePanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Tên đăng nhập:"), gbc);
		tfUsername = new JTextField();
		tfUsername.setBorder(new RoundedBorder(12));
		tfUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridy = 1;
		panel.add(tfUsername, gbc);
		return panel;
	}
	
	public JPanel PasswordPanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Mật khẩu:"), gbc);
		pwdPassword = new JPasswordField();
		pwdPassword.setBorder(new RoundedBorder(12));
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridy = 1;
		panel.add(pwdPassword, gbc);
		return panel;
	}
	
	public JPanel ConfirmPanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 0;
		panel.add(this.createLabel("Xác nhận mật khẩu:"), gbc);
		pwdConfirm = new JPasswordField();
		pwdConfirm.setBorder(new RoundedBorder(12));
		pwdConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gbc.gridy = 1;
		panel.add(pwdConfirm, gbc);
		return panel;
	}
	
	public JPanel ButtonPanel() {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(this.BtnSignUp());
		panel.add(this.BtnCancel());
		panel.setBorder(new EmptyBorder(0,0,0,0));
		return panel;
	}
	
	public JLabel createLabel(String content) {
		JLabel label = new JLabel(content);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBorder(new EmptyBorder(0, 0, 5, 0));
		return label;
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		panel.setBorder(new EmptyBorder(0, 20, 20, 20));
		return panel;
	}
	
	public JButton BtnSignUp() {
		JButton btn = new Button("Đăng ký", ButtonType.PRIMARY);
		btn.addActionListener(e -> this.onSubmit());
		return btn;
	}
	
	public JButton BtnCancel() {
		JButton btn = new Button("Hủy", ButtonType.DANGER);
		btn.addActionListener(e -> {
			new AuthView();
			this.dispose();
		});
		return btn;
	}

	public JTextField getTfUsername() {
		return tfUsername;
	}

	public JPasswordField getPwdPassword() {
		return pwdPassword;
	}

	public JPasswordField getPwdConfirm() {
		return pwdConfirm;
	}
	
	public void onSubmit() {
		AuthController authController = new AuthController();
		authController.signUpHandler(this);
	}
}
