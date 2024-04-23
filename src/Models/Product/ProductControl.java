package Models.Product;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

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
	
	public DefaultTableModel getTableModel(ProductObject similar) {
		String columnHeaders[] = {"ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Danh mục"};
		ArrayList<ProductObject> items = this.getProducts(similar);
		Object data[][] = new Object[items.size()][columnHeaders.length];
		items.forEach(item -> {
			int i = items.indexOf(item);
			data[i][0] = item.getProduct_id();
			data[i][1] = item.getProduct_name();
			data[i][2] = item.getProduct_quantity();
			data[i][3] = item.getProduct_price();
			data[i][4] = item.getCategory_name();
		});
		return new DefaultTableModel(
				data,
				columnHeaders
		);
	}
	
}
