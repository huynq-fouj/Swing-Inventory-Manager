package Models.DanhMuc;

import java.util.Comparator;

public class SortDanhmucOrderById_Desc implements Comparator<DanhMuc> {

	@Override
	public int compare(DanhMuc o1, DanhMuc o2) {
		// TODO Auto-generated method stub
		return o1.getMaDM().compareTo(o2.getMaDM());
	}
}
