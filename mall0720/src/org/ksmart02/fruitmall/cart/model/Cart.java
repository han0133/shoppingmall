package org.ksmart02.fruitmall.cart.model;

public class Cart {
	private int cartNo;
	private int itemNo;
	private String memberId;
	private int cartCount;
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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
		return "Cart [cartNo=" + cartNo + ", itemNo=" + itemNo + ", memberId=" + memberId + ", cartCount=" + cartCount
				+ "]";
	}
	
}
