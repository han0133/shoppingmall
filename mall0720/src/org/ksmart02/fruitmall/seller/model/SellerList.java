package org.ksmart02.fruitmall.seller.model;

import java.sql.Timestamp;

public class SellerList {
	private int sellerNo;
	private String sellerName;
	private String sellerAddr;
	private String sellerPhone;
	private Timestamp sellerDate;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSellerNo() {
		return sellerNo;
	}
	public void setSellerNo(int sellerNo) {
		this.sellerNo = sellerNo;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerAddr() {
		return sellerAddr;
	}
	public void setSellerAddr(String sellerAddr) {
		this.sellerAddr = sellerAddr;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public Timestamp getSellerDate() {
		return sellerDate;
	}
	public void setSellerDate(Timestamp sellerDate) {
		this.sellerDate = sellerDate;
	}
	@Override
	public String toString() {
		return "SellerList [sellerNo=" + sellerNo + ", sellerName=" + sellerName + ", sellerAddr=" + sellerAddr
				+ ", sellerPhone=" + sellerPhone + ", sellerDate=" + sellerDate + ", quantity=" + quantity + "]";
	}
	
	
	
}
