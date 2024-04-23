package Views.Pages;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.TaiKhoanController.QuanlyTaiKhoanController;
import Models.KhoDb;
import Models.TaiKhoan.QuanlyTaiKhoanModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class QuanlyTaiKhoanView extends JPanel {
	private KhoDb db = new KhoDb();
	public KhoDb getDb() {
		return db;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTenDN;
	private JTextField tfMK;
	private JTextField tfHoTen;
	private JTextField tfDiaChi;
	private JTextField tfSDT;
	private JTable tbTaiKhoan;

	QuanlyTaiKhoanController ac = new QuanlyTaiKhoanController(this);

	/**
	* Create the frame.
	*/
	public QuanlyTaiKhoanView() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 756, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÍ TÀI KHOẢN\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(246, 10, 299, 48);
		contentPane.add(lblNewLabel);
		
		tfTenDN = new JTextField();
		tfTenDN.setColumns(10);
		tfTenDN.setBounds(145, 68, 133, 27);
		contentPane.add(tfTenDN);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên đăng nhập\r\n:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(30, 64, 115, 28);
		contentPane.add(lblNewLabel_1_1);
		
		tfMK = new JTextField();
		tfMK.setColumns(10);
		tfMK.setBounds(145, 116, 133, 28);
		contentPane.add(tfMK);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mật khẩu:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(30, 112, 105, 31);
		contentPane.add(lblNewLabel_1_2);
		
		tfHoTen = new JTextField();
		tfHoTen.setColumns(10);
		tfHoTen.setBounds(535, 68, 200, 25);
		contentPane.add(tfHoTen);
		
		JLabel lblNewLabel_1_3 = new JLabel("Họ tên\r\n:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(406, 68, 93, 30);
		contentPane.add(lblNewLabel_1_3);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setColumns(10);
		tfDiaChi.setBounds(535, 132, 200, 28);
		contentPane.add(tfDiaChi);
		
		JLabel lblNewLabel_1_4 = new JLabel("Địa chỉ\r\n:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(406, 131, 55, 24);
		contentPane.add(lblNewLabel_1_4);
		
		tfSDT = new JTextField();
		tfSDT.setColumns(10);
		tfSDT.setBounds(535, 188, 200, 28);
		contentPane.add(tfSDT);
		
		JLabel lblNewLabel_1_5 = new JLabel("Số điện thoại:");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(406, 187, 103, 24);
		contentPane.add(lblNewLabel_1_5);
		
		JButton btsua = new JButton("Sửa");
		btsua.addActionListener(ac);
		btsua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btsua.setBounds(406, 513, 105, 36);
		contentPane.add(btsua);
		
		JButton btxoa = new JButton("Cancel");
		btxoa.addActionListener(ac);
		btxoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btxoa.setBounds(630, 513, 105, 36);
		contentPane.add(btxoa);
		
		tbTaiKhoan = new JTable();
		tbTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		String data[][] = this.getDb().getLiTaiKhoan().convertArrayListTaiKhoantoStringArray();
		setLayout(new BorderLayout(0, 0));
		tbTaiKhoan.setModel(new DefaultTableModel(
			data,
			new String[] {
				"T\u00EAn \u0111\u0103ng nh\u1EADp", "M\u1EADt kh\u1EA9u", "H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "S\u0110T"
			}
		));
		tbTaiKhoan.getColumnModel().getColumn(0).setPreferredWidth(128);
		tbTaiKhoan.getColumnModel().getColumn(4).setPreferredWidth(80);
		tbTaiKhoan.addMouseListener(ac);
		
		JScrollPane scrollBar = new JScrollPane(tbTaiKhoan);
		scrollBar.setBounds(30, 260, 705, 226);
		contentPane.add(scrollBar);
		
		JButton btThem = new JButton("Thêm");
		btThem.addActionListener(ac);
		btThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btThem.setBounds(267, 515, 106, 36);
		contentPane.add(btThem);
		
		this.add(contentPane);
		
		JButton btXoa = new JButton("Xoá");
		btXoa.addActionListener(ac);
		btXoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btXoa.setBounds(535, 513, 85, 36);
		contentPane.add(btXoa);
		contentPane.setVisible(true);
		this.setVisible(true);
	}
	public void refreshTable() {
		String data[][] = this.getDb().getLiTaiKhoan().convertArrayListTaiKhoantoStringArray();
		this.tbTaiKhoan.setModel(new DefaultTableModel(
			data,
			new String[] {
					"T\u00EAn \u0111\u0103ng nh\u1EADp", "M\u1EADt kh\u1EA9u", "H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "S\u0110T"
			}
		));
	}
	public void setTable(QuanlyTaiKhoanModel temp) {
		String data[][] = temp.convertArrayListTaiKhoantoStringArray();
		this.tbTaiKhoan.setModel(new DefaultTableModel(
				data,
				new String[] {
						"T\u00EAn \u0111\u0103ng nh\u1EADp", "M\u1EADt kh\u1EA9u", "H\u1ECD t\u00EAn", "\u0110\u1ECBa ch\u1EC9", "S\u0110T"
				}
			));
	}
	public void clickRowTable() {
		int rowSelected = this.tbTaiKhoan.getSelectedRow();
		if (rowSelected != -1) {
			this.tfTenDN.setText(this.tbTaiKhoan.getValueAt(rowSelected, 0).toString());
			this.tfMK.setText(this.tbTaiKhoan.getValueAt(rowSelected, 1).toString());
			this.tfHoTen.setText(this.tbTaiKhoan.getValueAt(rowSelected, 2).toString());
			this.tfDiaChi.setText(this.tbTaiKhoan.getValueAt(rowSelected, 3).toString());
			this.tfSDT.setText(this.tbTaiKhoan.getValueAt(rowSelected, 4).toString());
			this.tfTenDN.setEditable(false);
		}
	}
	public JTextField getTfTenDN() {
		return tfTenDN;
	}

	public JTextField getTfMK() {
		return tfMK;
	}

	public JTextField getTfHoTen() {
		return tfHoTen;
	}

	public JTextField getTfDiaChi() {
		return tfDiaChi;
	}

	public JTextField getTfSDT() {
		return tfSDT;
	}

	public JTable getTbTaiKhoan() {
		return tbTaiKhoan;
	}
	
}
