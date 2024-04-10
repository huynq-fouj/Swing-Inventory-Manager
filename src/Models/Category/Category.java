package Models.Category;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ShareControl;
import Models.Objects.CategoryObject;
import Models.Objects.UserObject;

public interface Category extends ShareControl {

	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	public ResultSet getCategory(Short id);
	public ArrayList<ResultSet> getCategories(UserObject id);	
	
}
