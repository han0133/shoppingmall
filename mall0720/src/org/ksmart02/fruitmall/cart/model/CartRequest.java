package org.ksmart02.fruitmall.cart.model;

import org.ksmart02.fruitmall.item.model.Item;

public class CartRequest {
	private int cartNo;
	private Item item;
	private String memberId;
	private int cartCount;
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	@Override
	public String toString() {
		return "CartRequest [cartNo=" + cartNo + ", item=" + item + ", memberId=" + memberId + ", cartCount="
				+ cartCount + "]";
	}
	
}
