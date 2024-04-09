package Models.SanPham;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Databases.DbEngine;
import Models.DanhMuc.DanhMuc;
import Models.DanhMuc.SortDanhmucOrderById_Desc;
import Models.DanhMuc.SortDanhmucOrderByName_Desc;

public class QuanlySanPhamModel implements Serializable {
	private static final long serialVersionUID = 1335197377056588109L;
	private ArrayList<SanPham> li;

	public QuanlySanPhamModel() {
		li = new ArrayList();
	}

	public QuanlySanPhamModel(ArrayList<SanPham> li) {
		this.li = li;
	}

	public ArrayList<SanPham> getLi() {
		return li;
	}

	public void setLi(ArrayList<SanPham> li) {
		this.li = li;
	}

	@Override
	public String toString() {
		return "QuanlySanPhamModel [li=" + li + "]";
	}

	public boolean addSanPham(SanPham sp) {
		for (SanPham item : this.li) {
			if (sp.getMaSP().equals(item.getMaSP()))
				return false;
		}
		this.li.add(sp);
		DbEngine.saveChange(this);
		return true;
	}

	public void updateSanPham(String id, SanPham temp) {
		int index = this.findSanPhamOrderByID(id);
		this.li.remove(index);
		this.li.add(temp);
		DbEngine.saveChange(this);
	}

	public int findSanPhamOrderByID(String id) {
		for (int i = 0; i < this.li.size(); i++) {
			if (this.li.get(i).getMaSP().equals(id))
				return i;
		}
		return -1;
	}

	public QuanlySanPhamModel findSanPhamOrderByName(String name) {
		QuanlySanPhamModel temp = new QuanlySanPhamModel();
		for (SanPham item : this.li) {
			if (item.getTenSP().contains(name))
				temp.getLi().add(item);
		}
		return temp;
	}

	public QuanlySanPhamModel findSanPhamOrderBymaDM(String maDanhmuc) {
		QuanlySanPhamModel temp = new QuanlySanPhamModel();
		for (SanPham item : this.li) {
			if (item.getMaDM().equals(maDanhmuc))
				temp.getLi().add(item);
		}
		return temp;
	}

	public boolean deleteSanPham(String id, Object frame) {
		int index = this.findSanPhamOrderByID(id);
		if (index == -1) {
			return false;
		}
		int res = JOptionPane.showConfirmDialog((Component) frame,
				"Bạn có chắc chắn muốn xoá sản phẩm " + this.li.get(index).getMaSP() + ": "
						+ this.li.get(index).getTenSP(),
				"Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			this.li.remove(index);
			DbEngine.saveChange(this);
			return true;
		}
		return false;
	}

	public void sortSanPhamOrderById() {
		this.li.sort(new SortSanPhamOrderById_Asc());
	}

	public void sortSanPhamOrderByName() {
		this.li.sort(new SortSanPhamOrderByName_Asc());
	}

	public String[][] convertArrayListSanPhamtoStringArray() {
		String data[][] = new String[this.li.size()][5];
		for (int i = 0; i < this.li.size(); i++) {
			data[i][0] = this.li.get(i).getMaSP();
			data[i][1] = this.li.get(i).getTenSP();
			data[i][2] = this.li.get(i).getSoLuong() + "";
			data[i][3] = this.li.get(i).getGia() + "";
			data[i][4] = this.li.get(i).getMaDM();
		}
		return data;
	}
}
