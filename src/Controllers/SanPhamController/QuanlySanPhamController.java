package Controllers.SanPhamController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Models.DanhMuc.DanhMuc;
import Models.SanPham.QuanlySanPhamModel;
import Models.SanPham.SanPham;
import Views.Pages.QuanlySanPhamView;

public class QuanlySanPhamController implements ActionListener, MouseListener {
	private QuanlySanPhamView qlspView;

	public QuanlySanPhamController(QuanlySanPhamView temp) {
		this.qlspView = temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String url = e.getActionCommand();
		if (url.equals("Thêm"))
			this.Create();
		if (url.equals("Sửa"))
			this.Edit();
		if (url.equals("Xoá"))
			this.Delete();
		if (url.equals("Tìm"))
			this.Find();
		if (url.equals("Cancel"))
			this.Cancel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.qlspView.clickRowTable();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void Create() {
		String masp = this.qlspView.getTfMasanpham().getText();
		String tensp = this.qlspView.getTfTensanpham().getText();
		int dmSelected = this.qlspView.getCbDanhmuc().getSelectedIndex();
		String madm = this.qlspView.getDb().getLiDanhMuc().getLi().get(dmSelected).getMaDM();
		String soluong_text = this.qlspView.getTfSoluongco().getText();
		String dongia_text = this.qlspView.getTfGiaban().getText();
		if (masp.isEmpty() || tensp.isEmpty() || soluong_text.isEmpty() || dongia_text.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qlspView, "Yêu cầu nhập Thông tin sản phẩm", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				int soluong = Integer.parseInt(soluong_text);
				double dongia = Double.parseDouble(dongia_text);

				SanPham temp = new SanPham(masp, tensp, dongia, soluong, madm);
				boolean check = this.qlspView.getDb().getLiSanPham().addSanPham(temp);
				if (check == true) {
					JOptionPane.showConfirmDialog(this.qlspView, "Thêm thành công!", "Thông báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					this.qlspView.refreshTable();
					this.Cancel();
				} else {
					JOptionPane.showConfirmDialog(this.qlspView, "Mã danh mục đã tồn tại!", "Lỗi",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showConfirmDialog(this.qlspView, "Số lượng hoặc đơn giá sai định dạng!", "Cảnh báo",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void Edit() {
		int rowSelected = this.qlspView.getTbSanpham().getSelectedRow();
		if (rowSelected == -1) {
			JOptionPane.showConfirmDialog(this.qlspView, "Yêu cầu chọn một Sản phẩm trong bảng để sửa!", "Lỗi",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		} else {
			int check = JOptionPane.showConfirmDialog(this.qlspView, "Bạn có chắc chắn muốn sửa?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (check == JOptionPane.YES_OPTION) {
				String id = this.qlspView.getTbSanpham().getValueAt(rowSelected, 0).toString();
				String tensp = this.qlspView.getTfTensanpham().getText();
				int dmSelected = this.qlspView.getCbDanhmuc().getSelectedIndex();
				String madm = this.qlspView.getDb().getLiDanhMuc().getLi().get(dmSelected).getMaDM();
				String soluong_text = this.qlspView.getTfSoluongco().getText();
				String dongia_text = this.qlspView.getTfGiaban().getText();
				if (tensp.isEmpty() || soluong_text.isEmpty() || dongia_text.isEmpty()) {
					JOptionPane.showConfirmDialog(this.qlspView, "Yêu cầu nhập Thông tin sản phẩm", "Cảnh báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int soluong = Integer.parseInt(soluong_text);
						double dongia = Double.parseDouble(dongia_text);

						SanPham temp = new SanPham(id, tensp, dongia, soluong, madm);
						this.qlspView.getDb().getLiSanPham().updateSanPham(id, temp);
						this.qlspView.refreshTable();
						this.Cancel();
					} catch (Exception ex) {
						JOptionPane.showConfirmDialog(this.qlspView, "Số lượng hoặc đơn giá sai định dạng!", "Cảnh báo",
								JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
	}

	public void Delete() {
		String masp = this.qlspView.getTfMasanpham().getText();
		if (masp.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qlspView, "Yêu cầu nhập Mã sản phẩm muốn xoá!", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			boolean check = this.qlspView.getDb().getLiSanPham().deleteSanPham(masp, this.qlspView);
			if (check == true) {
				JOptionPane.showConfirmDialog(this.qlspView, "Xoá thành công!", "Thông báo", JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				this.qlspView.refreshTable();
				this.Cancel();
			} else {
				JOptionPane.showConfirmDialog(this.qlspView, "Không xoá được Danh mục", "Lỗi",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void Find() {
		String masp = this.qlspView.getTfMasanpham().getText();
		if (!masp.isEmpty()) {
			int index = this.qlspView.getDb().getLiSanPham().findSanPhamOrderByID(masp);
			if (index == -1) {
				JOptionPane.showConfirmDialog(qlspView, "Không tồn tại mã sản phẩm này!", "Thông báo",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			} else {
				QuanlySanPhamModel temp = new QuanlySanPhamModel();
				temp.getLi().add(this.qlspView.getDb().getLiSanPham().getLi().get(index));
				this.qlspView.setTable(temp);
				return;
			}
		} else {
			String tensp = this.qlspView.getTfTensanpham().getText();
			if (!tensp.isEmpty()) {
				QuanlySanPhamModel temp = this.qlspView.getDb().getLiSanPham().findSanPhamOrderByName(tensp);
				if (temp.getLi().size() == 0) {
					JOptionPane.showConfirmDialog(qlspView, "Không tồn tại Tên sản phẩm này!", "Thông báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
				} else {
					this.qlspView.setTable(temp);
				}
			} else {
				int index = this.qlspView.getCbDanhmuc().getSelectedIndex();
				String maDanhmuc = this.qlspView.getDb().getLiDanhMuc().getLi().get(index).getMaDM();
				QuanlySanPhamModel temp = this.qlspView.getDb().getLiSanPham().findSanPhamOrderBymaDM(maDanhmuc);
				if (temp.getLi().size() == 0) {
					JOptionPane.showConfirmDialog(qlspView, "Danh mục trống!", "Thông báo", JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					this.qlspView.setTable(temp);
				}
			}
		}
	}

	public void Cancel() {
		this.qlspView.getTfMasanpham().setText("");
		this.qlspView.getTfTensanpham().setText("");
		this.qlspView.getTfSoluongco().setText("");
		this.qlspView.getTfGiaban().setText("");
		this.qlspView.getCbDanhmuc().setSelectedIndex(0);
		this.qlspView.getTfMasanpham().setEditable(true);
		this.qlspView.refreshTable();
	}
}
