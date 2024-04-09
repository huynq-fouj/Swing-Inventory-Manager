package Controllers.SanPhamController;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.DanhMuc.SortDanhmucOrderById_Asc;
import Models.DanhMuc.SortDanhmucOrderById_Desc;
import Models.DanhMuc.SortDanhmucOrderByName_Asc;
import Models.DanhMuc.SortDanhmucOrderByName_Desc;
import Models.SanPham.QuanlySanPhamModel;
import Models.SanPham.SortSanPhamOrderByGiaban_Asc;
import Models.SanPham.SortSanPhamOrderByGiaban_Desc;
import Models.SanPham.SortSanPhamOrderById_Asc;
import Models.SanPham.SortSanPhamOrderById_Desc;
import Models.SanPham.SortSanPhamOrderByName_Asc;
import Models.SanPham.SortSanPhamOrderByName_Desc;
import Models.SanPham.SortSanPhamOrderBySoluong_Asc;
import Models.SanPham.SortSanPhamOrderBySoluong_Desc;
import Views.Pages.QuanlySanPhamView;

public class ColumnHeaderClick_SanPham implements MouseListener {
	private QuanlySanPhamView qlspView;

	public ColumnHeaderClick_SanPham(QuanlySanPhamView temp) {
		this.qlspView = temp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.qlspView.getTbSanpham().columnAtPoint(point);
		QuanlySanPhamModel temp = new QuanlySanPhamModel(this.qlspView.getDb().getLiSanPham().getLi());
		if (column == 0) {
			if (this.qlspView.checkSortId) {
				temp.getLi().sort(new SortSanPhamOrderById_Asc());
				this.qlspView.checkSortId = false;
				this.qlspView.setTable(temp);
			} else {
				temp.getLi().sort(new SortSanPhamOrderById_Desc());
				this.qlspView.checkSortId = true;
				this.qlspView.setTable(temp);
			}
		}
		if (column == 1) {
			if (this.qlspView.checkSortName) {
				temp.getLi().sort(new SortSanPhamOrderByName_Asc());
				this.qlspView.checkSortName = false;
				this.qlspView.setTable(temp);
			} else {
				temp.getLi().sort(new SortSanPhamOrderByName_Desc());
				this.qlspView.checkSortName = true;
				this.qlspView.setTable(temp);
			}
		}
		if (column == 2) {
			if (this.qlspView.checkSortSoluong) {
				temp.getLi().sort(new SortSanPhamOrderBySoluong_Desc());
				this.qlspView.checkSortSoluong = false;
				this.qlspView.setTable(temp);
			} else {
				temp.getLi().sort(new SortSanPhamOrderBySoluong_Asc());
				this.qlspView.checkSortSoluong = true;
				this.qlspView.setTable(temp);
			}
		}
		if (column == 3) {
			if (this.qlspView.checkSortGiaban) {
				temp.getLi().sort(new SortSanPhamOrderByGiaban_Asc());
				this.qlspView.checkSortGiaban = false;
				this.qlspView.setTable(temp);
			} else {
				temp.getLi().sort(new SortSanPhamOrderByGiaban_Desc());
				this.qlspView.checkSortGiaban = true;
				this.qlspView.setTable(temp);
			}
		}
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
