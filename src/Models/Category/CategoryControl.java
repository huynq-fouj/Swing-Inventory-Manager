package Models.Category;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Databases.ConnectionPool;
import Models.Objects.CategoryObject;

public class CategoryControl {

private CategoryModel cm;
	
	public CategoryControl(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.cm.getCP();
	}
	
	public void releaseConnection() {
		this.cm.releaseConnection();
	}
	
	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}
	
	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}
	
	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}
	
	public CategoryObject getCategory(int id) {
		return this.cm.getCategory(id);
	}
	
	public ArrayList<CategoryObject> getCategories(CategoryObject similar, CategorySortType type) {
		return this.cm.getCategories(similar, type);
	}
	
	public int countCategory(CategoryObject similar) {
		return this.cm.countCategory(similar);
	}
	
	public DefaultTableModel getTableModel(CategoryObject similar, CategorySortType type) {
		String columnHeaders[] = {"ID", "Tên danh mục", "Ghi chú"};
		ArrayList<CategoryObject> items = this.getCategories(similar, type);
		Object data[][] = new Object[items.size()][columnHeaders.length];
		items.forEach(item -> {
			int i = items.indexOf(item);
			data[i][0] = item.getCategory_id();
			data[i][1] = item.getCategory_name();
			data[i][2] = item.getCategory_notes();
		});
		return new DefaultTableModel(
				data,
				columnHeaders
		);
	}
	
}
