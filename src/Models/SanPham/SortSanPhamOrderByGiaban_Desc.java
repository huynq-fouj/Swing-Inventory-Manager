package Models.SanPham;

import java.util.Comparator;

public class SortSanPhamOrderByGiaban_Desc implements Comparator<SanPham> {

	@Override
	public int compare(SanPham o1, SanPham o2) {
		// TODO Auto-generated method stub
		double sl1 = o1.getGia();
		double sl2 = o2.getGia();
		if (sl1 == sl2)
			return 0;
		if (sl1 < sl2)
			return 1;
		return -1;
	}

}
