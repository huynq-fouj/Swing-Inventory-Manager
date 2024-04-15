package Models.Objects;

public class ProductObject extends CategoryObject {
	private int product_id;
	private String product_name;
	private int product_quantity;
	private double product_price;
	private int product_category_id;
	private int author_id;
	private String product_details;
	private String product_created_date;
	private String product_modified_date;
	private String product_size;
	private double product_promotion_price;
	private double product_promotion;

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

	public String getProduct_details() {
		return product_details;
	}

	public String getProduct_created_date() {
		return product_created_date;
	}

	public String getProduct_modified_date() {
		return product_modified_date;
	}

	public String getProduct_size() {
		return product_size;
	}

	public double getProduct_promotion_price() {
		return product_promotion_price;
	}

	public double getProduct_promotion() {
		return product_promotion;
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

	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}

	public void setProduct_created_date(String product_created_date) {
		this.product_created_date = product_created_date;
	}

	public void setProduct_modified_date(String product_modified_date) {
		this.product_modified_date = product_modified_date;
	}

	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}

	public void setProduct_promotion_price(double product_promotion_price) {
		this.product_promotion_price = product_promotion_price;
	}

	public void setProduct_promotion(double product_promotion) {
		this.product_promotion = product_promotion;
	}

}
