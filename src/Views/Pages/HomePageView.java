package Views.Pages;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import Controllers.HomePageController;
import Utilities.ResourceUtil;
import Views.Auth.AuthView;

public class HomePageView extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	private JMenuBar menuBar = new JMenuBar();

	public JRadioButtonMenuItem checkDark;
	public JMenuItem checkLight;
	public JLabel lblDanhMuc;
	public JLabel lblSanPham;
	public JLabel lblTaiKhoan;
	public JButton btnDanhMuc;
	public JButton btnSanPham;
	public JButton btnTaiKhoan;
	public JButton btnThongKe;
	private JMenu menu5;

	HomePageController ac = new HomePageController(this);
	private JPanel mainPanel;
	private QuanlyDanhMucView danhmucPanel = new QuanlyDanhMucView();
	private QuanlySanPhamView sanphamPanel = new QuanlySanPhamView();
	private QuanlyTaiKhoanView taikhoanPanel = new QuanlyTaiKhoanView();
	private JButton btTrangchu;

	/**
	 * Create the frame.
	 */
	public HomePageView() {

		setTitle("Quản lý sản phẩm");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ResourceUtil.loadPathResource("\\ImagesResource\\icon1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		mainPanel = new JPanel();

		mainPanel.setLayout(null);
		Functions();

		contentPane.add(danhmucPanel, BorderLayout.CENTER);
		contentPane.add(sanphamPanel, BorderLayout.CENTER);
		contentPane.add(taikhoanPanel, BorderLayout.CENTER);
		contentPane.add(mainPanel, BorderLayout.CENTER);

		this.danhmucPanel.setVisible(false);
		this.sanphamPanel.setVisible(false);
		this.taikhoanPanel.setVisible(false);
		mainPanel.setVisible(true);
		Menu();
		Background();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void Background() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ResourceUtil.loadPathResource("\\ImagesResource\\background.jpg")));
		lblNewLabel.setBounds(0, 0, 976, 482);
		mainPanel.add(lblNewLabel);
	}

	public void Menu() {
		// -----------Menu 1----------------
		JMenu menu1 = new JMenu("File");
		// item1_1
		JMenuItem item1_1 = new JMenuItem("New", KeyEvent.VK_N);// Gạch chân dưới menu có thể dùng phím tắt
		item1_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));// Phím tắ Ctrl+n để mở
																									// menu New
		// Thêm icon cho item1_1
		Image imgNew = Toolkit.getDefaultToolkit()
				.createImage(ResourceUtil.loadPathResource("\\ImagesResource\\icon\\new.jpg"));
		item1_1.setIcon(new ImageIcon(imgNew));

		// item1_2
		JMenuItem item1_2 = new JMenuItem("Open file", KeyEvent.VK_O);
		item1_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		// Thêm icon cho item1_2
		Image imgOpen = Toolkit.getDefaultToolkit()
				.createImage(ResourceUtil.loadPathResource("\\ImagesResource\\icon\\openFile.jpg"));
		item1_2.setIcon(new ImageIcon(imgOpen));

		// item1_3
		JMenuItem item1_3 = new JMenuItem("Recent file", KeyEvent.VK_R);
		item1_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		// Thêm icon cho item1_3
		Image imgRecent = Toolkit.getDefaultToolkit()
				.createImage(ResourceUtil.loadPathResource("\\ImagesResource\\icon\\recentFile.jpg"));
		item1_3.setIcon(new ImageIcon(imgRecent));

		menu1.add(item1_1);
		menu1.add(item1_2);
		menu1.addSeparator();
		menu1.add(item1_3);
		// -------------------------

		// -------------Menu 2---------------------
		JMenu menu2 = new JMenu("Tùy chỉnh");
		checkDark = new JRadioButtonMenuItem("Dark mode");
		checkDark.addActionListener(ac);
		menu2.add(checkDark);

		checkLight = new JRadioButtonMenuItem("Light mode");
		checkLight.addActionListener(ac);
		menu2.add(checkLight);

		btTrangchu = new JButton("Trang chủ");
		btTrangchu.addActionListener(ac);
		menuBar.add(btTrangchu);
		// ---------------------------------------

		// ----------------Menu 3-----------------

		JMenu quanliMenu = new JMenu("Quản lý");
		menuBar.add(quanliMenu);

		JMenuItem danhmucMenuItem = new JMenuItem("Quản lý danh mục");
		danhmucMenuItem.addActionListener(ac);
		quanliMenu.add(danhmucMenuItem);

		JMenuItem sanphamMenuItem = new JMenuItem("Quản lý sản phẩm");
		sanphamMenuItem.addActionListener(ac);
		quanliMenu.add(sanphamMenuItem);

		JMenuItem taikhoanMenuItem = new JMenuItem("Quản lý tài khoản");
		taikhoanMenuItem.addActionListener(ac);
		quanliMenu.add(taikhoanMenuItem);
		menuBar.add(menu1);
		menuBar.add(menu2);
		// ---------------------------------------

		// ----------------Menu 4-----------------
		JMenu menu4 = new JMenu("Thông tin");
		JMenuItem item4_1 = new JMenuItem("Thông tin", KeyEvent.VK_T);
		item4_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		item4_1.addActionListener(ac);
		menu4.add(item4_1);
		menuBar.add(menu4);

		menu5 = new JMenu("Tài khoản");
		menuBar.add(menu5);

		JMenuItem itemDangXuat = new JMenuItem("Đăng xuất");
		itemDangXuat.addActionListener(e -> {
			AuthView firstView = new AuthView();
			firstView.setVisible(true);
			this.dispose();
		});
		menu5.add(itemDangXuat);

		setJMenuBar(menuBar);
	}

	public void Functions() {
		// Chức năng Danh mục
		btnDanhMuc = new JButton("Danh mục");
		ImageIcon iconDanhMuc = new ImageIcon(ResourceUtil.loadPathResource("\\ImagesResource\\product.png"));
		Image scaledImageDanhMuc = iconDanhMuc.getImage().getScaledInstance(95, 90, Image.SCALE_SMOOTH);
		ImageIcon scaledIconDanhMuc = new ImageIcon(scaledImageDanhMuc);
		btnDanhMuc.setIcon(scaledIconDanhMuc);
		btnDanhMuc.setBounds(126, 538, 150, 108);
		btnDanhMuc.setBorderPainted(false);
		btnDanhMuc.addActionListener(ac);
		mainPanel.add(btnDanhMuc);

		// Chức năng Sản phẩm"
		btnSanPham = new JButton("Sản phẩm");
		btnSanPham.setBounds(394, 538, 150, 108);
		ImageIcon iconSanPham = new ImageIcon(ResourceUtil.loadPathResource("\\ImagesResource\\order.png"));
		Image scaledImageSanPham = iconSanPham.getImage().getScaledInstance(95, 90, Image.SCALE_SMOOTH);
		ImageIcon scaledIconSanPham = new ImageIcon(scaledImageSanPham);
		btnSanPham.setIcon(scaledIconSanPham);
		btnSanPham.setBorderPainted(false); // Disable default border painting
		btnSanPham.addActionListener(ac);
		mainPanel.add(btnSanPham);

		// Chức năng Tài khoản
		btnTaiKhoan = new JButton("Tài khoản");
		btnTaiKhoan.setBounds(678, 538, 150, 98);
		ImageIcon iconTaiKhoan = new ImageIcon(ResourceUtil.loadPathResource("\\ImagesResource\\teamwork.png"));
		Image scaledImageTaiKhoan = iconTaiKhoan.getImage().getScaledInstance(95, 90, Image.SCALE_SMOOTH);
		ImageIcon scaledIconTaiKhoan = new ImageIcon(scaledImageTaiKhoan);
		btnTaiKhoan.setIcon(scaledIconTaiKhoan);
		btnTaiKhoan.setBorderPainted(false); // Disable default border painting
		btnTaiKhoan.addActionListener(ac);
		mainPanel.add(btnTaiKhoan);

		lblDanhMuc = new JLabel("Danh mục");
		lblDanhMuc.setBounds(162, 470, 138, 62);
		lblDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lblDanhMuc);

		lblSanPham = new JLabel("Sản phẩm");
		lblSanPham.setBounds(426, 473, 100, 57);
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lblSanPham);

		lblTaiKhoan = new JLabel("Tài khoản");
		lblTaiKhoan.setBounds(703, 466, 100, 70);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lblTaiKhoan);

		mainPanel.add(new JLabel("Test"));
	}

	public void addViewDanhMuc() {
		this.mainPanel.setVisible(false);
		this.sanphamPanel.setVisible(false);
		this.taikhoanPanel.setVisible(false);

		this.danhmucPanel.setVisible(true);
		this.contentPane.setVisible(true);
	}

	public void addViewSanPham() {

		this.mainPanel.setVisible(false);
		this.danhmucPanel.setVisible(false);
		this.taikhoanPanel.setVisible(false);

		this.sanphamPanel.setVisible(true);
		this.contentPane.setVisible(true);
	}

	public void addViewTrangChu() {
		this.danhmucPanel.setVisible(false);
		this.sanphamPanel.setVisible(false);
		this.taikhoanPanel.setVisible(false);

		this.mainPanel.setVisible(true);
		this.contentPane.setVisible(true);
	}

	public void addViewTaiKhoan() {
		this.danhmucPanel.setVisible(false);
		this.sanphamPanel.setVisible(false);
		this.mainPanel.setVisible(false);

		this.taikhoanPanel.setVisible(true);
		this.contentPane.setVisible(true);
	}
}
