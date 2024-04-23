package Models.TaiKhoan;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Databases.DbEngine;

public class QuanlyTaiKhoanModel implements Serializable {
	private static final long serialVersionUID = -4357895236515096269L;
	private ArrayList<TaiKhoan> li;

	public QuanlyTaiKhoanModel() {
		this.li = new ArrayList<TaiKhoan>();
	}

	public QuanlyTaiKhoanModel(ArrayList<TaiKhoan> temp) {
		this.li = temp;
	}

	public ArrayList<TaiKhoan> getLi() {
		return li;
	}

	@Override
	public String toString() {
		return "QuanlyTaiKhoanModel [li=" + li + "]";
	}

	public boolean addTaikhoan(TaiKhoan temp) {
		for (TaiKhoan item : this.li) {
			if (item.getTenDangNhap().equals(temp.getTenDangNhap()))
				return false;
		}
		this.li.add(temp);
		DbEngine.saveChange(this);
		return true;
	}

	public void updateTaiKhoan(String tendangnhap, TaiKhoan temp) {
		int index = this.findTaiKhoanOrderByTendangnhap(tendangnhap);
		this.li.remove(index);
		this.li.add(temp);
		DbEngine.saveChange(this);
	}

	public boolean deleteTaiKhoan(String tendn, Object frame) {
		int index = this.findTaiKhoanOrderByTendangnhap(tendn);
		if (index == -1) {
			return false;
		}
		int res = JOptionPane.showConfirmDialog((Component) frame, "Bạn có chắc chắn muốn xoá?", "Xác nhận",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			DbEngine.saveChange(this);
			this.li.remove(index);
			return true;
		}
		return false;
	}

	public int findTaiKhoanOrderByTendangnhap(String tendangnhap) {
		for (int i = 0; i < this.li.size(); i++) {
			if (this.li.get(i).getTenDangNhap().equals(tendangnhap))
				return i;
		}
		return -1;
	}

	public String[][] convertArrayListTaiKhoantoStringArray() {
		String data[][] = new String[this.li.size()][6];
		for (int i = 0; i < this.li.size(); i++) {
			data[i][0] = this.li.get(i).getTenDangNhap();
			data[i][1] = this.li.get(i).getMatKhau();
			data[i][2] = this.li.get(i).getHoTen();
			data[i][3] = this.li.get(i).getDiaChi();
			data[i][4] = this.li.get(i).getSoDT();
		}
		return data;
	}

	public boolean checkSignIn(String username, String password) {
		for (TaiKhoan item : this.li) {
			if (item.getTenDangNhap().equals(username) && item.getMatKhau().equals(password))
				return true;
		}
		return false;
	}
}
