package Models.DanhMuc;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Databases.DbEngine;

public class QuanlyDanhMucModel implements Serializable {
	private static final long serialVersionUID = -2550047113414443963L;
	private ArrayList<DanhMuc> li;

	public QuanlyDanhMucModel() {
		li = new ArrayList();
	}

	public QuanlyDanhMucModel(ArrayList<DanhMuc> li) {
		this.li = li;
	}

	public ArrayList<DanhMuc> getLi() {
		return li;
	}

	public void setLi(ArrayList<DanhMuc> li) {
		this.li = li;
	}

	@Override
	public String toString() {
		return "QuanlyDanhMucModel [li=" + li + "]";
	}

	public boolean addDanhmuc(DanhMuc dm) {
		for (DanhMuc item : this.li) {
			if (dm.getMaDM().equals(item.getMaDM()))
				return false;
		}
		this.li.add(dm);
		DbEngine.saveChange(this);
		return true;
	}

	public void updateDanhmuc(String id, DanhMuc temp) {
		int index = this.findDanhmucOrderByID(id);
		this.li.remove(index);
		this.li.add(temp);
		DbEngine.saveChange(this);
	}

	public int findDanhmucOrderByID(String id) {
		for (int i = 0; i < this.li.size(); i++) {
			if (this.li.get(i).getMaDM().equals(id))
				return i;
		}
		return -1;
	}

	public int findDanhmucOrderByName(String name) {
		for (int i = 0; i < this.li.size(); i++) {
			if (this.li.get(i).getTenDM().equals(name))
				return i;
		}
		return -1;
	}

	public boolean deleteDanhmuc(String id, Object frame) {
		int index = this.findDanhmucOrderByID(id);
		if (index == -1) {
			return false;
		}
		int res = JOptionPane.showConfirmDialog((Component) frame, "Bạn có chắc chắn muốn xoá?", "Xác nhận",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			this.li.remove(index);
			DbEngine.saveChange(this);
			return true;
		}
		return false;
	}

	public String[][] convertArrayListDanhMuctoStringArray() {
		String data[][] = new String[this.li.size()][2];
		for (int i = 0; i < this.li.size(); i++) {
			data[i][0] = this.li.get(i).getMaDM();
			data[i][1] = this.li.get(i).getTenDM();
		}
		return data;
	}
}
