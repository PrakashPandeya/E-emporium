package model;

public class CartItem extends ProductModel{
	private int itemID;
	private int quantity;
	
	public CartItem(int itemID, int quantity) {
		super();
		this.itemID = itemID;
		this.quantity = quantity;
	}
	
	public CartItem() {}
	
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
