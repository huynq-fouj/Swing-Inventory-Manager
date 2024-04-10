package Models.Product;

import java.sql.ResultSet;
import java.util.ArrayList;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.ProductObject;
import Models.Objects.UserObject;

public class ProductImpl extends BasicImpl implements Product {

	public ProductImpl(ConnectionPool cp) {
		super(cp, "Product");
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
	public boolean addProduct(ProductObject product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delProduct(ProductObject product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editProduct(ProductObject product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getProducts(UserObject user) {
		// TODO Auto-generated method stub
		return null;
	}

}
