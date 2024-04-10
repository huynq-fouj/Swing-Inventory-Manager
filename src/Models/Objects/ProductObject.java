package Models.Objects;

public class ProductObject {
	private int product_id;
	private String product_name;
	private int product_quantity;
	private double product_price;
	private int product_category_id;
	private int author_id;

	public int getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public double getProduct_price() {
		return product_price;
	}

	public int getProduct_category_id() {
		return product_category_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

}
