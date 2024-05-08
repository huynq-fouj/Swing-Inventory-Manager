package Models.User;

import javax.swing.DefaultComboBoxModel;

import Models.Objects.UserRole;

public class UserRoleComboBoxModel extends DefaultComboBoxModel<UserRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserRoleComboBoxModel(UserRole[] items) {
		super(items);
	}
	
}
