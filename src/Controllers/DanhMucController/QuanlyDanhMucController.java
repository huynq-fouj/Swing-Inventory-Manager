package Controllers.DanhMucController;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

import Databases.DbEngine;
import Models.DanhMuc.DanhMuc;
import Models.DanhMuc.QuanlyDanhMucModel;
import Views.Pages.QuanlyDanhMucView;

public class QuanlyDanhMucController implements ActionListener, MouseListener {
	private QuanlyDanhMucView qldmView;

	public QuanlyDanhMucController(QuanlyDanhMucView temp) {
		this.qldmView = temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String url = e.getActionCommand();
		if (url.equals("Thêm"))
			this.Create();
		if (url.equals("Cancel"))
			this.Cancel();
		if (url.equals("Sửa"))
			this.Edit();
		if (url.equals("Tìm"))
			this.Find();
		if (url.equals("Xoá"))
			this.Delete();
	}

	private void Create() {
		String madm = this.qldmView.getTfMadanhmuc().getText();
		String tendm = this.qldmView.getTfTendanhmuc().getText();
		if (madm.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qldmView, "Yêu cầu nhập Mã danh mục", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			if (tendm.isEmpty()) {
				JOptionPane.showConfirmDialog(this.qldmView, "Yêu cầu nhập Tên danh mục", "Cảnh báo",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			} else {
				DanhMuc temp = new DanhMuc(madm, tendm);
				boolean check = this.qldmView.getDb().getLiDanhMuc().addDanhmuc(temp);
				if (check == true) {
					JOptionPane.showConfirmDialog(this.qldmView, "Thêm thành công!", "Thông báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
					this.qldmView.refreshTable();
					this.Cancel();
				} else {
					JOptionPane.showConfirmDialog(this.qldmView, "Mã danh mục đã tồn tại!", "Lỗi",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void Edit() {
		int rowSelected = this.qldmView.getTbThongtinDanhmuc().getSelectedRow();
		if (rowSelected == -1) {
			JOptionPane.showConfirmDialog(qldmView, "Yêu cầu chọn một Danh mục trong bảng để sửa!", "Lỗi",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		} else {
			int check = JOptionPane.showConfirmDialog(qldmView, "Bạn có chắc chắn muốn sửa?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (check == JOptionPane.YES_OPTION) {
				String id = this.qldmView.getTbThongtinDanhmuc().getValueAt(rowSelected, 0).toString();
				String tendm = this.qldmView.getTfTendanhmuc().getText();
				DanhMuc dmUpdate = new DanhMuc(id, tendm);
				this.qldmView.getDb().getLiDanhMuc().updateDanhmuc(id, dmUpdate);
				this.qldmView.refreshTable();
				this.Cancel();
			}
		}
	}

	public void Delete() {
		String madm = this.qldmView.getTfMadanhmuc().getText();
		if (madm.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qldmView, "Yêu cầu nhập Mã danh mục muốn xoá!", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			boolean check = this.qldmView.getDb().getLiDanhMuc().deleteDanhmuc(madm, this.qldmView);
			if (check == true) {
				JOptionPane.showConfirmDialog(this.qldmView, "Xoá thành công!", "Thông báo", JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				this.qldmView.refreshTable();
				this.Cancel();
			} else {
				JOptionPane.showConfirmDialog(this.qldmView, "Không xoá được Danh mục", "Lỗi",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void Find() {
		String madm = this.qldmView.getTfMadanhmuc().getText();
		String tendm = this.qldmView.getTfTendanhmuc().getText();
		if (madm.isEmpty() && tendm.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qldmView, "Yêu cầu nhập Mã danh mục hoặc Tên sản phẩm", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			if (!madm.isEmpty()) {
				QuanlyDanhMucModel temp = new QuanlyDanhMucModel();
				for (DanhMuc item : this.qldmView.getDb().getLiDanhMuc().getLi()) {
					if (item.getMaDM().equals(madm)) {
						temp.getLi().add(item);
						this.qldmView.setTable(temp);
						return;
					}
				}

				JOptionPane.showConfirmDialog(qldmView, "Mã Danh mục này không tồn tại!", "Thông báo",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			} else {
				QuanlyDanhMucModel temp = new QuanlyDanhMucModel();
				for (DanhMuc item : this.qldmView.getDb().getLiDanhMuc().getLi()) {
					if (item.getTenDM().contains(tendm)) {
						temp.getLi().add(item);
					}
				}
				if (temp.getLi().size() == 0) {
					JOptionPane.showConfirmDialog(qldmView, "Tên Danh mục này không tồn tại!", "Thông báo",
							JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {
					this.qldmView.setTable(temp);
				}
			}
		}
	}

	public void Cancel() {
		this.qldmView.getTfMadanhmuc().setText("");
		this.qldmView.getTfTendanhmuc().setText("");
		this.qldmView.getTfMadanhmuc().setEditable(true);
		this.qldmView.refreshTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.qldmView.clickRowTable();
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
}