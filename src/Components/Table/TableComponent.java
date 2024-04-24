package Components.Table;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import Themes.Colors;

public class TableComponent extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TableComponent() {
		JTableHeader header = this.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		header.setForeground(Colors.White);
		header.setBackground(Colors.Primary);
		header.setOpaque(false);
		header.setPreferredSize(new Dimension(0, 30));
		this.setTableHeader(header);
		this.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.setRowHeight(30);
		this.setSelectionBackground(Colors.Danger);
		this.setSelectionForeground(Colors.White);
		this.setShowVerticalLines(false);
		this.setIntercellSpacing(new Dimension(0, 0));
		this.setRowMargin(0);
		this.setDefaultRenderer(Object.class, new MyRenderer());
	}

}
