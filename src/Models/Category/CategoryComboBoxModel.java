package Models.Category;

import javax.swing.DefaultComboBoxModel;

import Models.Objects.CategoryObject;

public class CategoryComboBoxModel extends DefaultComboBoxModel<CategoryObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoryComboBoxModel(CategoryObject[] items) {
		super(items);
	}
	
}
