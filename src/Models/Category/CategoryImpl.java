package Models.Category;

import java.sql.ResultSet;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.CategoryObject;

public class CategoryImpl extends BasicImpl implements Category {

	public CategoryImpl(ConnectionPool cp) {
		super(cp, "Category");
	}

	@Override
	public ConnectionPool getCP() {
		return super.getCP();
	}

	@Override
	public void releaseConnection() {
		super.releaseConnection();
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		return false;
	}

	@Override
	public ResultSet getCategory(int id) {
		String sql = "SELECT * FROM tblcategory WHERE category_id=?";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getCategories(CategoryObject similar) {
		String sql = "SELECT * FROM tblcategory ";
		sql += this.createConditions(similar);
		sql += " ORDER BY Category_id DESC";
		return this.gets(sql);
	}
	
	@Override
	public ResultSet countCategory(CategoryObject similar) {
		String sql = "SELECT COUNT(*) AS total FROM tblcategory ";
		sql += this.createConditions(similar);
		return this.gets(sql);
	}

	private String createConditions(CategoryObject similar) {
		StringBuilder cons = new StringBuilder();
		
		return cons.toString();
	}

}
