package Models.DanhMuc;

import java.io.Serializable;

public class DanhMuc implements Serializable {
	private String maDM;
	private String tenDM;

	public DanhMuc() {
	}

	public DanhMuc(String maDM, String tenDM) {
		this.maDM = maDM;
		this.tenDM = tenDM;
	}

	public String getMaDM() {
		return maDM;
	}

	public void setMaDM(String maDM) {
		this.maDM = maDM;
	}

	public String getTenDM() {
		return tenDM;
	}

	public void setTenDM(String tenDM) {
		this.tenDM = tenDM;
	}

	@Override
	public String toString() {
		return "DanhMuc [maDM=" + maDM + ", tenDM=" + tenDM + "]";
	}
}
