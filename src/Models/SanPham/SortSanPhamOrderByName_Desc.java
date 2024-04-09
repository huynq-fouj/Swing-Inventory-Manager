package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderByName_Desc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		return o2.getTenSP().compareTo(o1.getTenSP());
	}

}
