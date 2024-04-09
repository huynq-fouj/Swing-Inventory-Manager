package Models;

import Databases.DbEngine;
import Models.DanhMuc.QuanlyDanhMucModel;
import Models.SanPham.QuanlySanPhamModel;
import Models.TaiKhoan.QuanlyTaiKhoanModel;

// Model để đọc các danh sách từ File Database
public class KhoDb {
	private QuanlyDanhMucModel liDanhMuc;
	private QuanlySanPhamModel liSanPham;
	private QuanlyTaiKhoanModel liTaiKhoan;

	public KhoDb() {
		this.liDanhMuc = (QuanlyDanhMucModel) DbEngine.openData("QuanlyDanhMucModel");
		this.liSanPham = (QuanlySanPhamModel) DbEngine.openData("QuanlySanPhamModel");
		this.liTaiKhoan = (QuanlyTaiKhoanModel) DbEngine.openData("QuanlyTaiKhoanModel");
	}

	public QuanlyDanhMucModel getLiDanhMuc() {
		return this.liDanhMuc;
	}

	public QuanlySanPhamModel getLiSanPham() {
		return this.liSanPham;
	}

	public QuanlyTaiKhoanModel getLiTaiKhoan() {
		return this.liTaiKhoan;
	}
}
