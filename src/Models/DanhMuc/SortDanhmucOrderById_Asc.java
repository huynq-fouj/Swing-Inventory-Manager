package Models.DanhMuc;

import java.util.Comparator;

public class SortDanhmucOrderById_Asc implements Comparator<DanhMuc> {

	@Override
	public int compare(DanhMuc o1, DanhMuc o2) {
		// TODO Auto-generated method stub
		return o2.getMaDM().compareTo(o1.getMaDM());
	}
}
