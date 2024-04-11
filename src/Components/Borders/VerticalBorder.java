package Components.Borders;

import java.awt.Dimension;

import javax.swing.JPanel;

import Themes.Colors;

public class VerticalBorder extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;

	public VerticalBorder() {
		this(2, 1000);
	}
	
	public VerticalBorder(int width, int height) {
		this.setBackground(Colors.Dark);
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(this.width, this.height));
	}
	
}
