package model;

public class OrderDetail extends ProductModel {
	private int detailID;
	private int quantity;
	
	public OrderDetail(int detailID, int quantity) {
		super();
		this.detailID = detailID;
		this.quantity = quantity;
	}
	
	public OrderDetail() {}

	public int getDetailID() {
		return detailID;
	}

	public void setDetailID(int detailID) {
		this.detailID = detailID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
