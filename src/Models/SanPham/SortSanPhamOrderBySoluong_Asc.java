package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderBySoluong_Asc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		int sl1 = o1.getSoLuong(); int sl2 = o2.getSoLuong();
		if (sl1 == sl2) return 0;
		if (sl1 > sl2) return 1;
		return -1;
	}

}
