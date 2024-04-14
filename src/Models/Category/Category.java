package Models.Category;

import java.sql.ResultSet;

import Databases.ShareControl;
import Models.Objects.CategoryObject;

public interface Category extends ShareControl {

	public boolean addCategory(CategoryObject item);
	public boolean editCategory(CategoryObject item);
	public boolean delCategory(CategoryObject item);
	
	public ResultSet getCategory(int id);
	public ResultSet getCategories(CategoryObject similar);	
	public ResultSet countCategory(CategoryObject similar);
}
