package Models.Category;

import java.util.ArrayList;

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
	
	public ArrayList<CategoryObject> getCategories(CategoryObject similar) {
		return this.cm.getCategories(similar);
	}
	
	public int countCategory(CategoryObject similar) {
		return this.cm.countCategory(similar);
	}
	
}
