package Models.Product;

import java.sql.ResultSet;

import Databases.ShareControl;
import Models.Objects.ProductObject;

public interface Product extends ShareControl {

	public boolean addProduct(ProductObject product);
	public boolean delProduct(ProductObject product);
	public boolean editProduct(ProductObject product);
	
	public ResultSet getProduct(int id);
	public ResultSet getProducts(ProductObject similar);
	public ResultSet countProduct(ProductObject similar);
}
