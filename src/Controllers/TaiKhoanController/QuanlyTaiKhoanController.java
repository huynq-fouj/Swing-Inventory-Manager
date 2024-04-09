package Controllers.TaiKhoanController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import Models.DanhMuc.DanhMuc;
import Models.TaiKhoan.TaiKhoan;
import Views.Pages.QuanlyTaiKhoanView;

public class QuanlyTaiKhoanController implements ActionListener, MouseListener {
	private QuanlyTaiKhoanView qltkView;

	public QuanlyTaiKhoanController(QuanlyTaiKhoanView temp) {
		this.qltkView = temp;
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
		if (url.equals("Xoá"))
			this.Delete();
	}

	private void Create() {
		String tendn = this.qltkView.getTfTenDN().getText();
		String matkhau = this.qltkView.getTfMK().getText();
		String hoten = this.qltkView.getTfHoTen().getText();
		String diachi = this.qltkView.getTfDiaChi().getText();
		String sdt = this.qltkView.getTfSDT().getText();

		if (tendn.isEmpty() || matkhau.isEmpty() || hoten.isEmpty() || diachi.isEmpty() || sdt.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qltkView, "Yêu cầu nhập Thông tin", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			TaiKhoan temp = new TaiKhoan(tendn, matkhau, hoten, diachi, sdt);
			boolean check = this.qltkView.getDb().getLiTaiKhoan().addTaikhoan(temp);
			if (check == true) {
				JOptionPane.showConfirmDialog(this.qltkView, "Thêm thành công!", "Thông báo", JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				this.qltkView.refreshTable();
				this.Cancel();
			} else {
				JOptionPane.showConfirmDialog(this.qltkView, "Tên đăng nhập đã tồn tại!", "Lỗi",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public void Edit() {
		int rowSelected = this.qltkView.getTbTaiKhoan().getSelectedRow();
		if (rowSelected == -1) {
			JOptionPane.showConfirmDialog(this.qltkView, "Yêu cầu chọn một Tài khoản trong bảng để sửa!", "Lỗi",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		} else {
			int check = JOptionPane.showConfirmDialog(this.qltkView, "Bạn có chắc chắn muốn sửa?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (check == JOptionPane.YES_OPTION) {
				String tendn = this.qltkView.getTbTaiKhoan().getValueAt(rowSelected, 0).toString();
				String matkhau = this.qltkView.getTfMK().getText();
				String hoten = this.qltkView.getTfHoTen().getText();
				String diachi = this.qltkView.getTfDiaChi().getText();
				String sdt = this.qltkView.getTfSDT().getText();

				TaiKhoan temp = new TaiKhoan(tendn, matkhau, hoten, diachi, sdt);

				this.qltkView.getDb().getLiTaiKhoan().updateTaiKhoan(tendn, temp);
				this.qltkView.refreshTable();
				this.Cancel();
			}
		}
	}

	public void Delete() {
		String tendn = this.qltkView.getTfTenDN().getText();
		if (tendn.isEmpty()) {
			JOptionPane.showConfirmDialog(this.qltkView, "Yêu cầu nhập Tên đăng nhập muốn xoá!", "Cảnh báo",
					JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
		} else {
			boolean check = this.qltkView.getDb().getLiTaiKhoan().deleteTaiKhoan(tendn, this.qltkView);
			if (check == true) {
				JOptionPane.showConfirmDialog(this.qltkView, "Xoá thành công!", "Thông báo", JOptionPane.CLOSED_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				this.qltkView.refreshTable();
				this.Cancel();
			} else {
				JOptionPane.showConfirmDialog(this.qltkView, "Không xoá được Danh mục", "Lỗi",
						JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void Cancel() {
		this.qltkView.getTfTenDN().setText("");
		this.qltkView.getTfTenDN().setEditable(true);
		this.qltkView.getTfMK().setText("");
		this.qltkView.getTfHoTen().setText("");
		this.qltkView.getTfDiaChi().setText("");
		this.qltkView.getTfSDT().setText("");
		this.qltkView.refreshTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.qltkView.clickRowTable();
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
