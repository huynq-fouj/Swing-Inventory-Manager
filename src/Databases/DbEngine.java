package Databases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Models.DanhMuc.DanhMuc;
import Models.DanhMuc.QuanlyDanhMucModel;
import Models.SanPham.QuanlySanPhamModel;
import Models.TaiKhoan.QuanlyTaiKhoanModel;
import Utilities.ResourceUtil;

public class DbEngine {

	public static void saveChange(Object quanly) {
		try {
			String obj = quanly.getClass().getSimpleName();
			String fileName = "";

			if (obj.equals("QuanlyDanhMucModel")) {
				fileName = ResourceUtil.loadDataPath("DanhMucDb.txt");
			} else {
				if (obj.equals("QuanlySanPhamModel")) {
					fileName = ResourceUtil.loadDataPath("SanPhamDb.txt");
				} else {
					if (obj.equals("QuanlyTaiKhoanModel")) {
						fileName = ResourceUtil.loadDataPath("TaiKhoanDb.txt");
					}
				}
			}

			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(quanly);
			oos.flush();
			oos.close();
			fout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Chú ý sửa lại openData cho gọn hơn, chú ý đóng tất cả các file
	public static Object openData(String key) {
		try {
			String fileName = "";
			if (key.equals("QuanlyDanhMucModel")) {
				fileName = ResourceUtil.loadDataPath("DanhMucDb.txt");
				FileInputStream fin = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Object temp = ois.readObject();
				ois.close();
				fin.close();
				return temp;
			}
			if (key.equals("QuanlySanPhamModel")) {
				fileName = ResourceUtil.loadDataPath("SanPhamDb.txt");
				FileInputStream fin = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Object temp = ois.readObject();
				ois.close();
				fin.close();
				return temp;
			}
			if (key.equals("QuanlyTaiKhoanModel")) {
				fileName = ResourceUtil.loadDataPath("TaiKhoanDb.txt");
				FileInputStream fin = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fin);
				Object temp = ois.readObject();
				ois.close();
				fin.close();
				return temp;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
