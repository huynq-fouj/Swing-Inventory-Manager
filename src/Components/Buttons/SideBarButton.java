package Components.Buttons;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import Themes.Colors;
import Themes.HoverEvent;

public class SideBarButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arcWidth;
	private int arcHeight;
	private int width;
	private int height;

	public SideBarButton(String content) {
		this(content, 10);
	}
	
	public SideBarButton(String content, int round) {
		super(content);
		this.setForeground(Colors.Black);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.arcWidth = round;
		this.arcHeight = round;
		Dimension size = super.getPreferredSize();
		this.width = size.width;
		this.height = size.height;
		this.setBackground(Colors.White);
		this.addMouseListener(HoverEvent.changeBackground(this, Colors.White, Colors.WhiteHover));
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));
		super.paintComponent(g2);
		g2.dispose();
	}

	protected void paintBorder(Graphics g) {
		// Không vẽ border mặc định của JButton
	}

	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.width = this.width;
		size.height = this.height;
		return size;
	}
	
	public int getArcWidth() {
		return arcWidth;
	}

	public int getArcHeight() {
		return arcHeight;
	}

	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}

	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}
	
	public void setRounded(int radius) {
		this.arcWidth = radius;
		this.arcHeight = radius;
	}

	
}
