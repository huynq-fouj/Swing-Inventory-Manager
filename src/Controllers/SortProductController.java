package Controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.Product.ProductSortType;
import Views.Pages.Product.ProductPage;

public class SortProductController implements MouseListener {

	private ProductPage productPage;
	
	public SortProductController(ProductPage productPage) {
		this.productPage = productPage;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.productPage.getTable().columnAtPoint(point);
		if(column == 0) {
			if(this.productPage.isSorted()) {
				this.productPage.setProductSortType(ProductSortType.ID_DESC);
				this.productPage.setSorted(false);
				this.productPage.loadTable();
			} else {
				this.productPage.setProductSortType(ProductSortType.ID_ASC);
				this.productPage.setSorted(true);
				this.productPage.loadTable();
			}
		}
		if(column == 1) {
			if(this.productPage.isSorted()) {
				this.productPage.setProductSortType(ProductSortType.NAME_DESC);
				this.productPage.setSorted(false);
				this.productPage.loadTable();
			} else {
				this.productPage.setProductSortType(ProductSortType.NAME_ASC);
				this.productPage.setSorted(true);
				this.productPage.loadTable();
			}
		}
		if(column == 2) {
			if(this.productPage.isSorted()) {
				this.productPage.setProductSortType(ProductSortType.QUANTITY_DESC);
				this.productPage.setSorted(false);
				this.productPage.loadTable();
			} else {
				this.productPage.setProductSortType(ProductSortType.QUANTITY_ASC);
				this.productPage.setSorted(true);
				this.productPage.loadTable();
			}
		}
		if(column == 3) {
			if(this.productPage.isSorted()) {
				this.productPage.setProductSortType(ProductSortType.PRICE_DESC);
				this.productPage.setSorted(false);
				this.productPage.loadTable();
			} else {
				this.productPage.setProductSortType(ProductSortType.PRICE_ASC);
				this.productPage.setSorted(true);
				this.productPage.loadTable();
			}
		}
		if(column == 4) {
			if(this.productPage.isSorted()) {
				this.productPage.setProductSortType(ProductSortType.CATEGORY_NAME_DESC);
				this.productPage.setSorted(false);
				this.productPage.loadTable();
			} else {
				this.productPage.setProductSortType(ProductSortType.CATEGORY_NAME_ASC);
				this.productPage.setSorted(true);
				this.productPage.loadTable();
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
