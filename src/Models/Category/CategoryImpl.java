package Models.Category;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.CategoryObject;
import Models.Objects.UserObject;

public class CategoryImpl extends BasicImpl implements Category {

	public CategoryImpl(ConnectionPool cp) {
		super(cp, "Category");
	}

	@Override
	public ConnectionPool getCP() {
		return this.getCP();
	}

	@Override
	public void releaseConnection() {
		this.releaseConnection();
	}

	@Override
	public boolean addCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delCategory(CategoryObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getCategory(Short id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getCategories(UserObject id) {
		// TODO Auto-generated method stub
		return null;
	}

}
