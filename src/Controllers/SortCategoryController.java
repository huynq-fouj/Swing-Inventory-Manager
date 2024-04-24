package Controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.Category.CategorySortType;
import Views.Pages.Category.CategoryPage;

public class SortCategoryController implements MouseListener {

	private CategoryPage categoryPage;
	
	public SortCategoryController(CategoryPage categoryPage) {
		this.categoryPage = categoryPage;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.categoryPage.getTable().columnAtPoint(point);
		if(column == 0) {
			if(this.categoryPage.isSorted()) {
				this.categoryPage.setCategorySortType(CategorySortType.ID_DESC);
				this.categoryPage.setSorted(false);
				this.categoryPage.loadTable();
			} else {
				this.categoryPage.setCategorySortType(CategorySortType.ID_ASC);
				this.categoryPage.setSorted(true);
				this.categoryPage.loadTable();
			}
		}
		if(column == 1) {
			if(this.categoryPage.isSorted()) {
				this.categoryPage.setCategorySortType(CategorySortType.NAME_DESC);
				this.categoryPage.setSorted(false);
				this.categoryPage.loadTable();
			} else {
				this.categoryPage.setCategorySortType(CategorySortType.NAME_ASC);
				this.categoryPage.setSorted(true);
				this.categoryPage.loadTable();
			}
		}
		if(column == 2) {
			if(this.categoryPage.isSorted()) {
				this.categoryPage.setCategorySortType(CategorySortType.NOTES_DESC);
				this.categoryPage.setSorted(false);
				this.categoryPage.loadTable();
			} else {
				this.categoryPage.setCategorySortType(CategorySortType.NOTES_ASC);
				this.categoryPage.setSorted(true);
				this.categoryPage.loadTable();
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
