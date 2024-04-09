package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderByName_Asc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		return o1.getTenSP().compareTo(o2.getTenSP());
	}

}
