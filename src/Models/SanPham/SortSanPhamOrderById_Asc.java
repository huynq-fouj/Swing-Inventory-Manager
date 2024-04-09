package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderById_Asc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		return o1.getMaSP().compareTo(o2.getMaSP());
	}

}
