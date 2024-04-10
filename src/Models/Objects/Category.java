package Models.Objects;

public class Category {
	private int category_id;
	private String category_name;
	private String category_notes;
	private int auhtor_id;

	public int getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public String getCategory_notes() {
		return category_notes;
	}

	public int getAuhtor_id() {
		return auhtor_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setCategory_notes(String category_notes) {
		this.category_notes = category_notes;
	}

	public void setAuhtor_id(int auhtor_id) {
		this.auhtor_id = auhtor_id;
	}

}
