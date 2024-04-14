package Models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Objects.CategoryObject;

public class CategoryModel {

private Category c;
	
	public CategoryModel(ConnectionPool cp) {
		this.c = new CategoryImpl(cp);
	}
	
	public ConnectionPool getCP() {
		return this.c.getCP();
	}
	
	public void releaseConnection() {
		this.c.releaseConnection();
	}
	
	public boolean addCategory(CategoryObject item) {
		return this.c.addCategory(item);
	}
	
	public boolean editCategory(CategoryObject item) {
		return this.c.editCategory(item);
	}
	
	public boolean delCategory(CategoryObject item) {
		return this.c.delCategory(item);
	}
	
	public CategoryObject getCategory(int id) {
		ResultSet rs = this.c.getCategory(id);
		CategoryObject item = null;
		if(rs != null) {
			try {
				if(rs.next()) {
					item = new CategoryObject();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public ArrayList<CategoryObject> getCategories(CategoryObject similar) {
		ArrayList<CategoryObject> items = new ArrayList<>();
		CategoryObject item = null;
		ResultSet rs = this.c.getCategories(similar);
		if(rs != null) {
			try {
				while(rs.next()) {
					item = new CategoryObject();
					
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}
	
	public int countCategory(CategoryObject similar) {
		ResultSet rs = this.c.countCategory(similar);
		if(rs != null) {
			try {
				if(rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
