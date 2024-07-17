package model;

import java.util.ArrayList;

public class CartModel extends UserModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cartID;
	private ArrayList<CartItem> item;

	public CartModel(int cartID) {
		this.cartID = cartID;
	}
	
	public CartModel() {
		
	}

	public ArrayList<CartItem> getItem() {
		return item;
	}

	public void setItem(ArrayList<CartItem> item) {
		this.item = item;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	
}
