package Models.DanhMuc;

import java.util.Comparator;

public class SortDanhmucOrderByName_Asc implements Comparator<DanhMuc> {

	@Override
	public int compare(DanhMuc o1, DanhMuc o2) {
		// TODO Auto-generated method stub
		return o2.getTenDM().compareTo(o1.getTenDM());
	}

}
