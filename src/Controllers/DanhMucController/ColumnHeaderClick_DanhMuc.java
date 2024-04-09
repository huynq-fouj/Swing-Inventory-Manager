package Controllers.DanhMucController;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.DanhMuc.SortDanhmucOrderById_Asc;
import Models.DanhMuc.SortDanhmucOrderById_Desc;
import Models.DanhMuc.SortDanhmucOrderByName_Asc;
import Models.DanhMuc.SortDanhmucOrderByName_Desc;
import Views.Pages.QuanlyDanhMucView;

public class ColumnHeaderClick_DanhMuc implements MouseListener {
	private QuanlyDanhMucView qldmView;

	public ColumnHeaderClick_DanhMuc(QuanlyDanhMucView temp) {
		this.qldmView = temp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.qldmView.getTbThongtinDanhmuc().columnAtPoint(point);
		if (column == 0) {
			if (this.qldmView.checkSortId) {
				this.qldmView.getDb().getLiDanhMuc().getLi().sort(new SortDanhmucOrderById_Asc());
				this.qldmView.checkSortId = false;
				this.qldmView.refreshTable();
			} else {
				this.qldmView.getDb().getLiDanhMuc().getLi().sort(new SortDanhmucOrderById_Desc());
				this.qldmView.refreshTable();
				this.qldmView.checkSortId = true;
			}
		} else {
			if (this.qldmView.checkSortName) {
				this.qldmView.getDb().getLiDanhMuc().getLi().sort(new SortDanhmucOrderByName_Asc());
				this.qldmView.checkSortName = false;
				this.qldmView.refreshTable();
			} else {
				this.qldmView.getDb().getLiDanhMuc().getLi().sort(new SortDanhmucOrderByName_Desc());
				this.qldmView.refreshTable();
				this.qldmView.checkSortName = true;
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
