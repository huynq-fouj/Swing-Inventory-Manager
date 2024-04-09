package Views.Pages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Controllers.DanhMucController.ColumnHeaderClick_DanhMuc;
import Controllers.DanhMucController.QuanlyDanhMucController;
import Models.KhoDb;
import Models.DanhMuc.QuanlyDanhMucModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class QuanlyDanhMucView extends JPanel {

	private KhoDb db = new KhoDb();

	public KhoDb getDb() {
		return db;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbThongtinDanhmuc;
	private JTextField tfMadanhmuc;
	private JTextField tfTendanhmuc;

	public boolean checkSortId = true;
	public boolean checkSortName = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanlyDanhMucView frame = new QuanlyDanhMucView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JTable getTbThongtinDanhmuc() {
		return tbThongtinDanhmuc;
	}

	/**
	 * Create the frame.
	 */
	public QuanlyDanhMucView() {
		QuanlyDanhMucController ac = new QuanlyDanhMucController(this);
		ColumnHeaderClick_DanhMuc ac2 = new ColumnHeaderClick_DanhMuc(this);

		this.setLayout(new BorderLayout());
		JLabel temp = new JLabel("Day la quan ly danh muc");
		this.add(temp, BorderLayout.CENTER);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 802, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblThongtinDanhmuc = new JLabel("THÔNG TIN DANH MỤC");
		lblThongtinDanhmuc.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblThongtinDanhmuc.setBounds(26, 10, 359, 62);
		contentPane.add(lblThongtinDanhmuc);

		tbThongtinDanhmuc = new JTable();
		tbThongtinDanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbThongtinDanhmuc.addMouseListener(ac);
		String data[][] = this.getDb().getLiDanhMuc().convertArrayListDanhMuctoStringArray();
		tbThongtinDanhmuc.setModel(new DefaultTableModel(data, new String[] { "Category ID", "Category Name" }));
		tbThongtinDanhmuc.setBounds(26, 74, 393, 407);

		JTableHeader header = tbThongtinDanhmuc.getTableHeader();
		header.addMouseListener(ac2);

		JScrollPane scrollBar = new JScrollPane(tbThongtinDanhmuc);
		scrollBar.setBounds(26, 77, 359, 261);
		contentPane.add(scrollBar);

		JLabel lbMadanhmuc = new JLabel("Mã danh mục:");
		lbMadanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbMadanhmuc.setBounds(421, 77, 106, 41);
		contentPane.add(lbMadanhmuc);

		tfMadanhmuc = new JTextField();
		tfMadanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfMadanhmuc.setBounds(567, 85, 194, 21);
		contentPane.add(tfMadanhmuc);
		tfMadanhmuc.setColumns(10);

		JLabel lbTendanhmuc = new JLabel("Tên danh mục:");
		lbTendanhmuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbTendanhmuc.setBounds(421, 142, 137, 41);
		contentPane.add(lbTendanhmuc);

		tfTendanhmuc = new JTextField();
		tfTendanhmuc.setBounds(567, 154, 194, 21);
		contentPane.add(tfTendanhmuc);
		tfTendanhmuc.setColumns(10);

		JButton btThem = new JButton("Thêm");
		btThem.setBounds(409, 263, 75, 21);
		btThem.addActionListener(ac);
		contentPane.add(btThem);

		JButton btSua = new JButton("Sửa");
		btSua.setBounds(409, 317, 75, 21);
		btSua.addActionListener(ac);
		contentPane.add(btSua);

		JButton btXoa = new JButton("Xoá");
		btXoa.setBounds(554, 317, 75, 21);
		btXoa.addActionListener(ac);
		contentPane.add(btXoa);

		JButton btTim = new JButton("Tìm");
		btTim.setBounds(554, 263, 75, 21);
		btTim.addActionListener(ac);
		contentPane.add(btTim);

		JButton btCancel = new JButton("Cancel");
		btCancel.setBounds(692, 318, 85, 21);
		btCancel.addActionListener(ac);
		contentPane.add(btCancel);

		JButton btSort = new JButton("Sắp xếp");
		btSort.setBounds(692, 263, 85, 21);
		contentPane.add(btSort);

		this.add(contentPane);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.setBounds(409, 77, 368, 145);
		contentPane.add(verticalBox);
		contentPane.setVisible(true);
		this.setVisible(true);
	}

	public void setTable(QuanlyDanhMucModel temp) {
		String data[][] = temp.convertArrayListDanhMuctoStringArray();
		tbThongtinDanhmuc.setModel(new DefaultTableModel(data, new String[] { "Category ID", "Category Name" }));
	}

	public void refreshTable() {
		String data[][] = this.getDb().getLiDanhMuc().convertArrayListDanhMuctoStringArray();
		tbThongtinDanhmuc.setModel(new DefaultTableModel(data, new String[] { "Category ID", "Category Name" }));
	}

	public void clickRowTable() {
		int rowSelected = this.tbThongtinDanhmuc.getSelectedRow();
		if (rowSelected != -1) {
			this.tfMadanhmuc.setText(this.tbThongtinDanhmuc.getValueAt(rowSelected, 0).toString());
			this.tfTendanhmuc.setText(this.tbThongtinDanhmuc.getValueAt(rowSelected, 1).toString());
			this.tfMadanhmuc.setEditable(false);
		}
	}

	public JTextField getTfMadanhmuc() {
		return tfMadanhmuc;
	}

	public JTextField getTfTendanhmuc() {
		return tfTendanhmuc;
	}
}
