package Views.Pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Components.SideBar;
import Shared.PageState;
import Themes.Colors;

public class UserPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public UserPage() {
		PageState.page = "user";
		this.initUI();
		
	}
	
	public void initUI() {
		this.setTitle("Người dùng");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 1000, 731);
		this.contentPane = this.createContentPane();
		this.setContentPane(this.contentPane);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel createContentPane() {
		JPanel panel = this.createPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(new SideBar(this));
		return panel;
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		return panel;
	}

}
