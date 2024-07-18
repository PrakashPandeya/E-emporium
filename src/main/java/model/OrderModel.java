package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderModel extends UserModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderID;
	private LocalDate orderDate;
	private int total;
	private String status;
	private ArrayList<OrderDetail> details;
	
	public OrderModel(int orderID, LocalDate orderDate, int total, String status) {
		super();
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.total = total;
		this.status = status;
	}
	
	public OrderModel() {}
	
	public ArrayList<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<OrderDetail> details) {
		this.details = details;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
