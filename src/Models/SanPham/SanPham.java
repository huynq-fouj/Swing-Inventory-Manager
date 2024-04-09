package Models.SanPham;

import java.io.Serializable;
import java.util.Objects;

public class SanPham implements Serializable {
	private String maSP;
	private String tenSP;
	private double gia;
	private int soLuong;
	private String maDM;

	public SanPham() {

	}

	public SanPham(String id) {
		this.maSP = id;
	}

	public SanPham(String maSP, String tenSP, double gia, int soLuong, String maDM) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.gia = gia;
		this.soLuong = soLuong;
		this.maDM = maDM;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaDM() {
		return maDM;
	}

	public void setMaDM(String maDM) {
		this.maDM = maDM;
	}

	@Override
	public boolean equals(Object obj) {
		SanPham temp = (SanPham) obj;
		return this.getMaSP().equals(temp.getMaSP());
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", gia=" + gia + ", soLuong=" + soLuong + ", maDM=" + maDM
				+ "]";
	}
}
