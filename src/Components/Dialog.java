package Components;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Dialog {
	
	public static int success(Component component, String label, String title) {
		return JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int success(Component component, String label) {
		return JOptionPane.showConfirmDialog(component, label, "Success",
				JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int error(Component component, String label, String title) {
		return JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int error(Component component, String label) {
		return JOptionPane.showConfirmDialog(component, label, "Error",
				JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
}
