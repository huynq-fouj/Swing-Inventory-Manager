package Models.Product;

import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Objects.ProductObject;

public class ProductControl {

	private ProductModel pm;
	
	public ProductControl(ConnectionPool cp) {
		this.pm = new ProductModel(cp);
	}
	
	public ConnectionPool getCP() {
		return this.pm.getCP();
	}
	
	public void releaseConnection() {
		this.pm.releaseConnection();
	}
	
	public boolean addProduct(ProductObject item) {
		return this.pm.addProduct(item);
	}
	
	public boolean editProduct(ProductObject item) {
		return this.pm.editProduct(item);
	}
	
	public boolean delProduct(ProductObject item) {
		return this.pm.delProduct(item);
	}
	
	public ProductObject getProduct(int id) {
		return this.pm.getProduct(id);
	}
	
	public ArrayList<ProductObject> getProducts(ProductObject similar) {
		return this.pm.getProducts(similar);
	}
	
	public int countProduct(ProductObject similar) {
		return this.pm.countProduct(similar);
	}
	
}
