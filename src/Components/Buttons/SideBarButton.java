package Components.Buttons;

import java.awt.Font;

import javax.swing.JButton;

import Themes.Colors;

public class SideBarButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SideBarButton(String content) {
		super(content);
		this.setForeground(Colors.Black);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
	
}
