package Controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.User.UserSortType;
import Views.Pages.User.UserPage;

public class SortUserController implements MouseListener {
	
	private UserPage userPage;
	
	public SortUserController(UserPage userPage) {
		this.userPage = userPage;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.userPage.getTable().columnAtPoint(point);
		if(column == 0) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.ID_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.ID_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
		if(column == 1) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.NAME_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.NAME_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
		if(column == 2) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.FULLNAME_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.FULLNAME_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
		if(column == 3) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.EMAIL_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.EMAIL_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
		if(column == 4) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.PHONE_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.PHONE_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
		if(column == 5) {
			if(this.userPage.isSorted()) {
				this.userPage.setUserSortType(UserSortType.LOGINED_DESC);
				this.userPage.setSorted(false);
				this.userPage.loadTable();
			} else {
				this.userPage.setUserSortType(UserSortType.LOGINED_ASC);
				this.userPage.setSorted(true);
				this.userPage.loadTable();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
