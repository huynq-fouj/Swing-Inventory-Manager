package Models.DanhMuc;

import java.util.Comparator;

public class SortDanhmucOrderByName_Desc implements Comparator<DanhMuc> {

	@Override
	public int compare(DanhMuc o1, DanhMuc o2) {
		// TODO Auto-generated method stub
		return o1.getTenDM().compareTo(o2.getTenDM());
	}

}
