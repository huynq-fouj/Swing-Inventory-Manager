package Views.Auth;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.Borders.RoundedBorder;
import Components.Buttons.RoundButton;
import Themes.Colors;
import Themes.HoverEvent;
import Utilities.ResourceUtil;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AuthView() {

		this.initUI();
		
	}
	
	public void initUI() {
		this.setTitle("Phần mềm quản lý sản phẩm");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 650, 392);
		this.contentPane = this.createPanel();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.colorWhite);
		panel.setBackground(Colors.colorWhite);
		String imgPath = ResourceUtil.loadPathResource("\\ImagesResource\\mainbackground.jpg");
		ImageIcon backgroundImageIcon = new ImageIcon(imgPath);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		//Thêm nút đăng nhập
		panel.add(this.BtnDangNhapComponent());
		//Thêm nút đăng ký
		panel.add(this.BtnDangKyComponent());
		//Thêm label
		panel.add(this.labelView());
		return panel;
	}
	
	public JLabel labelView() {
		JLabel label = new JLabel("Chào mừng bạn đến với phần mềm quản lý kho hàng");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Colors.colorBlack);
		label.setBounds(67, 43, 517, 23);
		return label;
	}
	
	public JButton BtnDangNhapComponent() {
		JButton btn = this.BtnComponent("Đăng nhập", 500, 300);
		btn.addActionListener(e -> {
			SignInView signInView = new SignInView();
			signInView.setVisible(true);
			this.dispose();
		});
		
		return btn;
	}
	
	public JButton BtnDangKyComponent() {
		JButton btn = this.BtnComponent("Đăng ký", 359, 300);
		btn.addActionListener(e -> {
			SignUpView signUpView = new SignUpView();
			signUpView.setVisible(true);
			this.dispose();
		});
		
		return btn;
	}
	
	public JButton BtnComponent(String label, int x, int y) {
		JButton btn = new RoundButton(label, 15, 15);
		btn.setForeground(Colors.colorWhite);
		btn.setBackground(Colors.colorPrimary);
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBounds(x, y, 110, 40);
		btn.addMouseListener(HoverEvent.changeBackground(btn, Colors.colorPrimary, Colors.colorDarkPrimary));
		return btn;
	}
	
}
