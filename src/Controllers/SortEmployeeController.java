package Controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.Employee.EmployeeSortType;
import Views.Pages.Employee.EmployeePage;

public class SortEmployeeController implements MouseListener {

	private EmployeePage employeePage;
	
	public SortEmployeeController(EmployeePage employeePage) {
		this.employeePage = employeePage;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point point = e.getPoint();
		int column = this.employeePage.getTable().columnAtPoint(point);
		if(column == 0) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.ID_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.ID_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
			}
		}
		if(column == 1) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.FULLNAME_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.FULLNAME_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
			}
		}
		if(column == 2) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.EMAIL_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.EMAIL_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
			}
		}
		if(column == 3) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.POSITION_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.POSITION_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
			}
		}
		if(column == 4) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.PHONE_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.PHONE_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
			}
		}
		if(column == 5) {
			if(this.employeePage.isSorted()) {
				this.employeePage.setEmployeeSortType(EmployeeSortType.ADDRESS_DESC);
				this.employeePage.setSorted(false);
				this.employeePage.loadTable();
			} else {
				this.employeePage.setEmployeeSortType(EmployeeSortType.ADDRESS_ASC);
				this.employeePage.setSorted(true);
				this.employeePage.loadTable();
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
