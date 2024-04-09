package Themes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverEvent {

	public static MouseAdapter changeBackground(Component x, Color from, Color to) {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				x.setBackground(to);
            }

            public void mouseExited(MouseEvent e) {
            	x.setBackground(from);
            }
		};
	}
	
	public static MouseAdapter changeForeground(Component x, Color from, Color to) {
		return new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				x.setForeground(to);
            }

            public void mouseExited(MouseEvent e) {
            	x.setForeground(from);
            }
		};
	}
	
}
