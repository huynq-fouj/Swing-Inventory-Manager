package Models.Product;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ShareControl;
import Models.Objects.ProductObject;
import Models.Objects.UserObject;

public interface Product extends ShareControl {

	public boolean addProduct(ProductObject product);
	public boolean delProduct(ProductObject product);
	public boolean editProduct(ProductObject product);
	
	public ResultSet getProduct(int id);
	public ArrayList<ResultSet> getProducts(UserObject user);
	
}
