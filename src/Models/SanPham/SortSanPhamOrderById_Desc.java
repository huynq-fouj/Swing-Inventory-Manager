package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderById_Desc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		return o2.getMaSP().compareTo(o1.getMaSP());
	}

}
