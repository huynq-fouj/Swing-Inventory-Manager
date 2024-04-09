package Views.Auth;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utilities.ResourceUtil;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSplitPane;

public class AuthView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AuthView() {
		setTitle("Phần mềm quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 392);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon backgroundImage = new ImageIcon(
				ResourceUtil.loadPathResource("\\ImagesResource\\mainbackground.jpg"));

		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setBackground(new Color(0, 0, 0));
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangNhap.setBounds(500, 301, 110, 40);
		btnDangNhap.addActionListener(e -> {
			SignInView signInView = new SignInView();
			signInView.setVisible(true);
			dispose();
		});
		contentPane.add(btnDangNhap);

		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.setForeground(new Color(255, 255, 255));
		btnDangKy.setBackground(new Color(0, 0, 0));
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangKy.setBounds(359, 301, 110, 40);
		btnDangKy.addActionListener(e -> {
			SignUpView signUpView = new SignUpView();
			signUpView.setVisible(true);
			this.dispose();
		});
		contentPane.add(btnDangKy);

		JLabel lblNewLabel = new JLabel("Chào mừng bạn đến với phần mềm quản lý kho hàng");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(67, 43, 517, 23);
		contentPane.add(lblNewLabel);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
