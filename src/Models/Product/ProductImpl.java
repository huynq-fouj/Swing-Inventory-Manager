package Models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Databases.ConnectionPool;
import Models.Basic.BasicImpl;
import Models.Objects.ProductObject;
import Utilities.Utilities;
import Utilities.Utilities_date;

public class ProductImpl extends BasicImpl implements Product {

	public ProductImpl(ConnectionPool cp) {
		super(cp, "Product");
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
	public boolean addProduct(ProductObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblproduct(")
		.append("product_name, product_quantity, product_price,")
		.append("product_category_id, product_author_id, product_details,")
		.append("product_created_date, product_modified_date, product_size,")
		.append("product_unit, product_promotion")
		.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getProduct_name()));
			pre.setInt(2, item.getProduct_quantity());
			pre.setDouble(3, item.getProduct_price());
			pre.setInt(4, item.getProduct_category_id());
			pre.setInt(5, item.getAuthor_id());
			pre.setString(6, Utilities.encode(item.getProduct_details()));
			pre.setString(7, Utilities_date.getDate());
			pre.setString(8, Utilities_date.getDate());
			pre.setString(9, Utilities.encode(item.getProduct_size()));
			pre.setString(10, Utilities.encode(item.getProduct_unit()));
			pre.setDouble(11, item.getProduct_promotion());
			return this.add(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delProduct(ProductObject item) {
		String sql = "DELETE FROM tblproduct WHERE product_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getProduct_id());
			return this.del(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editProduct(ProductObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblproduct SET ")
		.append("product_name=?, product_quantity=?, product_price=?,")
		.append("product_category_id=?, product_details=?,")
		.append("product_modified_date=?, product_size=?,")
		.append("product_unit=?, product_promotion=? ")
		.append("WHERE product_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getProduct_name()));
			pre.setInt(2, item.getProduct_quantity());
			pre.setDouble(3, item.getProduct_price());
			pre.setInt(4, item.getProduct_category_id());
			pre.setString(5, Utilities.encode(item.getProduct_details()));
			pre.setString(6, Utilities_date.getDate());
			pre.setString(7, Utilities.encode(item.getProduct_size()));
			pre.setString(8, Utilities.encode(item.getProduct_unit()));
			pre.setDouble(9, item.getProduct_promotion());
			pre.setInt(10, item.getProduct_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getProduct(int id) {
		String sql = "SELECT * FROM tblproduct WHERE product_id=?;";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getProducts(ProductObject similar, ProductSortType type) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblproduct ")
		.append("LEFT JOIN tblcategory ON tblproduct.product_category_id=tblcategory.category_id ")
		.append(this.createConditions(similar));
		switch(type) {
		case ID_ASC:
			sql.append("ORDER BY product_id ASC");
			break;
		case NAME_ASC:
			sql.append("ORDER BY product_name ASC");
			break;
		case QUANTITY_ASC:
			sql.append("ORDER BY product_quantity ASC");
			break;
		case PRICE_ASC:
			sql.append("ORDER BY product_price ASC");
			break;
		case CATEGORY_NAME_ASC:
			sql.append("ORDER BY category_name ASC");
			break;
		case NAME_DESC:
			sql.append("ORDER BY product_name DESC");
			break;
		case QUANTITY_DESC:
			sql.append("ORDER BY product_quantity DESC");
			break;
		case PRICE_DESC:
			sql.append("ORDER BY product_price DESC");
			break;
		case CATEGORY_NAME_DESC:
			sql.append("ORDER BY category_name DESC");
			break;
		default:
			sql.append("ORDER BY product_id DESC");
		}
		sql.append(";");
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet countProduct(ProductObject similar) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS total FROM tblproduct ")
		.append(this.createConditions(similar));
		return this.gets(sql.toString());
	}
	
	private String createConditions(ProductObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar != null) {
			if(similar.getAuthor_id() > 0) {
				int id = similar.getAuthor_id();
				conds.append(" product_author_id=").append(id);
			}
			
			String name = similar.getProduct_name();
			if(name != null && !name.equalsIgnoreCase("")) {
				if(!conds.toString().equalsIgnoreCase("")) {
					conds.append(" AND ");
				}
				String key = Utilities.encode(name);
				conds.append(" ((product_name LIKE '%"+key+"%') OR ");
				conds.append(" (product_details LIKE '%"+key+"%')) ");
			}
		}

		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		conds.append(" ");
		
		return conds.toString();
	}

}
