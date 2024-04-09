package Views.Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Controllers.SanPhamController.ColumnHeaderClick_SanPham;
import Controllers.SanPhamController.QuanlySanPhamController;
import Models.KhoDb;
import Models.DanhMuc.DanhMuc;
import Models.DanhMuc.QuanlyDanhMucModel;
import Models.SanPham.QuanlySanPhamModel;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;

public class QuanlySanPhamView extends JPanel {
	private KhoDb db = new KhoDb();

	public KhoDb getDb() {
		return db;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMasanpham;
	private JTextField tfSoluongco;
	private JTextField tfTensanpham;
	private JTextField tfGiaban;
	private JTable table;
	private JTable tbSanpham;
	private JComboBox cbDanhmuc;

	public boolean checkSortId = true;
	public boolean checkSortName = true;
	public boolean checkSortSoluong = true;
	public boolean checkSortGiaban = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanlySanPhamView frame = new QuanlySanPhamView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuanlySanPhamView() {
		QuanlySanPhamController ac = new QuanlySanPhamController(this);
		ColumnHeaderClick_SanPham ac2 = new ColumnHeaderClick_SanPham(this);

		this.setLayout(new BorderLayout());

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbQuanlySanpham = new JLabel("Quản lý sản phẩm");
		lbQuanlySanpham.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbQuanlySanpham.setBounds(311, 10, 225, 55);
		contentPane.add(lbQuanlySanpham);

		JLabel lbMasanpham = new JLabel("Mã sản phẩm:");
		lbMasanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMasanpham.setBounds(50, 80, 106, 40);
		contentPane.add(lbMasanpham);

		tfMasanpham = new JTextField();
		tfMasanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfMasanpham.setBounds(181, 91, 250, 19);
		contentPane.add(tfMasanpham);
		tfMasanpham.setColumns(10);

		JLabel lbSoluongco = new JLabel("Số lượng có");
		lbSoluongco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbSoluongco.setBounds(501, 83, 86, 34);
		contentPane.add(lbSoluongco);

		tfSoluongco = new JTextField();
		tfSoluongco.setBounds(597, 92, 250, 19);
		contentPane.add(tfSoluongco);
		tfSoluongco.setColumns(10);

		JLabel lbTensanpham = new JLabel("Tên sản phẩm:");
		lbTensanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTensanpham.setBounds(50, 131, 106, 40);
		contentPane.add(lbTensanpham);

		tfTensanpham = new JTextField();
		tfTensanpham.setBounds(181, 140, 250, 19);
		contentPane.add(tfTensanpham);
		tfTensanpham.setColumns(10);

		JLabel lbDanhmuc = new JLabel("Danh mục:");
		lbDanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDanhmuc.setBounds(50, 182, 106, 30);
		contentPane.add(lbDanhmuc);

		this.refreshDanhMuc();
		cbDanhmuc.setBounds(181, 189, 250, 21);
		contentPane.add(cbDanhmuc);

		JLabel lbGiaban = new JLabel("Giá bán");
		lbGiaban.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbGiaban.setBounds(501, 136, 86, 30);
		contentPane.add(lbGiaban);

		tfGiaban = new JTextField();
		tfGiaban.setBounds(597, 143, 250, 19);
		contentPane.add(tfGiaban);
		tfGiaban.setColumns(10);

		JButton btTimkiem = new JButton("Tìm");
		btTimkiem.setBounds(34, 247, 75, 30);
		btTimkiem.addActionListener(ac);
		contentPane.add(btTimkiem);

		table = new JTable();
		table.setBounds(53, 276, 1, 1);
		contentPane.add(table);

		tbSanpham = new JTable();
		String data[][] = this.getDb().getLiSanPham().convertArrayListSanPhamtoStringArray();
		tbSanpham.addMouseListener(ac);
		tbSanpham.setModel(new DefaultTableModel(data,
				new String[] { "Product ID", "Product Name", "Number", "Price", "Category ID" }));
		tbSanpham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tbSanpham.setBounds(53, 275, 803, 201);

		JTableHeader header = tbSanpham.getTableHeader();
		header.addMouseListener(ac2);

		JScrollPane scTable = new JScrollPane(tbSanpham);
		scTable.setBounds(34, 291, 832, 227);
		contentPane.add(scTable);

		JButton btThem = new JButton("Thêm");
		btThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btThem.setBounds(34, 529, 86, 30);
		btThem.addActionListener(ac);
		contentPane.add(btThem);

		JButton btSua = new JButton("Sửa");
		btSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btSua.setBounds(280, 529, 86, 30);
		btSua.addActionListener(ac);
		contentPane.add(btSua);

		JButton btXoa = new JButton("Xoá");
		btXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btXoa.addActionListener(ac);
		btXoa.setBounds(534, 529, 86, 30);
		contentPane.add(btXoa);

		JButton btCancel = new JButton("Cancel");
		btCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCancel.setBounds(775, 529, 91, 26);
		btCancel.addActionListener(ac);
		contentPane.add(btCancel);

		this.add(contentPane, BorderLayout.CENTER);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		verticalBox.setBounds(34, 64, 832, 164);
		contentPane.add(verticalBox);
		contentPane.setVisible(true);
		this.setVisible(true);
	}

	public void setTable(QuanlySanPhamModel temp) {
		String data[][] = temp.convertArrayListSanPhamtoStringArray();
		this.tbSanpham.setModel(new DefaultTableModel(data,
				new String[] { "Product ID", "Product Name", "Number", "Price", "Category ID" }));
	}

	public void refreshDanhMuc() {
		KhoDb temp = new KhoDb();
		int count = temp.getLiDanhMuc().getLi().size();
		String[] danhmuc = new String[count];
		for (int i = 0; i < count; i++) {
			danhmuc[i] = temp.getLiDanhMuc().getLi().get(i).getTenDM();
		}
		this.cbDanhmuc = new JComboBox(danhmuc);
		this.cbDanhmuc.setSelectedIndex(0);
	}

	public JTextField getTfMasanpham() {
		return tfMasanpham;
	}

	public JTextField getTfSoluongco() {
		return tfSoluongco;
	}

	public JTextField getTfTensanpham() {
		return tfTensanpham;
	}

	public JTextField getTfGiaban() {
		return tfGiaban;
	}

	public JComboBox getCbDanhmuc() {
		return cbDanhmuc;
	}

	public JTable getTbSanpham() {
		return tbSanpham;
	}

	public void refreshTable() {
		// TODO Auto-generated method stub
		String data[][] = this.getDb().getLiSanPham().convertArrayListSanPhamtoStringArray();
		this.tbSanpham.setModel(new DefaultTableModel(data,
				new String[] { "Product ID", "Product Name", "Number", "Price", "Category ID" }));
	}

	public void clickRowTable() {
		int rowSelected = this.tbSanpham.getSelectedRow();
		if (rowSelected != -1) {
			this.tfMasanpham.setText(this.tbSanpham.getValueAt(rowSelected, 0).toString());
			this.tfTensanpham.setText(this.tbSanpham.getValueAt(rowSelected, 1).toString());
			this.tfSoluongco.setText(this.tbSanpham.getValueAt(rowSelected, 2).toString());
			this.tfGiaban.setText(this.tbSanpham.getValueAt(rowSelected, 3).toString());
			System.out.println(this.tbSanpham.getValueAt(rowSelected, 4).toString());
			int indexDm = this.getDb().getLiDanhMuc()
					.findDanhmucOrderByID(this.tbSanpham.getValueAt(rowSelected, 4).toString());
			this.cbDanhmuc.setSelectedIndex(indexDm);
			this.tfMasanpham.setEditable(false);
		}
	}
}
